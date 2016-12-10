package com.provus.util;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Buffered Random Access File is created to get a higher performance out of
 * Java IO. By using RandomAccessFile, reads texts line by line. 
 * For further reference: http://www.javaworld.com/javaworld/javatips/jw-javatip26.html
 * 
 * @author korayg
 * 
 */
public class Braf extends RandomAccessFile {
	int BUF_SIZE;

	byte buffer[];

	int bufEnd = 0;

	int bufPos = 0;

	long realPos = 0;

	public Braf(String filename, String mode, int bufsize) throws IOException {
		super(filename, mode);
		invalidate();
		BUF_SIZE = bufsize;
		buffer = new byte[BUF_SIZE];
	}

	public final byte readOneByte() throws IOException {
		if (bufPos >= bufEnd) {
			if (fillBuffer() < 0)
				return -1;
		}
		if (bufEnd == 0) {
			return -1;
		} else {
			return buffer[bufPos++];
		}
	}

	private int fillBuffer() throws IOException {
		int n = super.read(buffer, 0, BUF_SIZE);
		if (n >= 0) {
			realPos += n;
			bufEnd = n;
			bufPos = 0;
		}
		return n;
	}

	private void invalidate() throws IOException {
		bufEnd = 0;
		bufPos = 0;
		realPos = super.getFilePointer();
	}

	public int read(byte b[], int off, int len) throws IOException {
		int leftover = bufEnd - bufPos;
		if (len <= leftover) {
			System.arraycopy(buffer, bufPos, b, off, len);
			bufPos += len;
			return len;
		}

		for (int i = 0; i < len; i++) {
			byte c = this.readOneByte();
			if (c != -1)
				b[off + i] = c;
			else {
				return i;
			}
		}
		return len;
	}

	public long getFilePointer() {
		long l = realPos;
		return (l - bufEnd + bufPos);
	}

	public void seek(long pos) throws IOException {
		int n = (int) (realPos - pos);
		if (n >= 0 && n <= bufEnd) {
			bufPos = bufEnd - n;
		} else {
			super.seek(pos);
			invalidate();
		}
	}

	/**
	 * return a next line in String
	 */
	public String getNextLine() throws IOException {
		return getNextLineInternal();
	}

	/**
	 * return a next line in String, rba etc variable remains same
	 */
	public String getNextLineWithoutMove() throws IOException {
		byte prevBuffer[] = buffer;
		int prevBufEnd = bufEnd;
		int prevBufPos = bufPos;
		long prevRealPos = realPos;
		String line = getNextLineInternal();
		buffer = prevBuffer;
		bufEnd = prevBufEnd;
		bufPos = prevBufPos;
		realPos = prevRealPos;
		return line;
	}

	public final String getNextLineInternal() throws IOException {
		String str = null;
		if (bufEnd - bufPos <= 0) {
			if (fillBuffer() < 0)
				return null;
		}
		int lineend = -1;
		for (int i = bufPos; i < bufEnd; i++) {
			if (buffer[i] == '\n') {
				lineend = i;
				break;
			}
		}
		if (lineend < 0) {
			if (bufEnd > 0 && buffer[bufEnd - 1] == '\r'
					&& (bufEnd - bufPos - 1) >= 0)
				str = new String(buffer, bufPos, bufEnd - bufPos - 1,
						"ISO-8859-9");
			else
				str = new String(buffer, bufPos, bufEnd - bufPos, "ISO-8859-9");
			bufPos = buffer.length;
			String str2 = getNextLineInternal();
			if (str2 == null)
				return str;
			return str + str2;
		}
		if (lineend > 0 && buffer[lineend - 1] == '\r')
			str = new String(buffer, bufPos, lineend - bufPos - 1, "ISO-8859-9");
		else
			str = new String(buffer, bufPos, lineend - bufPos, "ISO-8859-9");
		bufPos = lineend + 1;
		return str;
	}
}