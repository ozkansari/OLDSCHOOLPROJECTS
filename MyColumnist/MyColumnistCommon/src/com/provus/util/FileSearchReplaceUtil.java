package com.provus.util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * File search replace utility functions. Uses nio packages.
 * 
 * @author korayg
 * 
 */
public class FileSearchReplaceUtil {

	public static final int DEFAULT_NIO_BUFFER_SIZE = 1024 * 1024 * 1024; // 1GB

	/**
	 * Runtime sirasinda degistirilerek kullanilabilir. (multithread korumasi
	 * yoktur!)
	 */
	public static int NIO_BUFFER_SIZE = 1024 * 1024 * 1024; // 1GB

	private static boolean matches(MappedByteBuffer bb, int pos, byte[] sought) {
		for (int j = 0; j < sought.length; ++j) {
			if (sought[j] != bb.get(pos + j)) {
				return false;
			}
		}
		return true;
	}

	private static void replace(MappedByteBuffer bb, int pos, byte[] sought,
			byte[] replacement) {
		for (int j = 0; j < sought.length; ++j) {
			byte b = (j < replacement.length) ? replacement[j] : (byte) ' ';
			bb.put(pos + j, b);
		}
	}

	/**
	 * Finds a string or byte array in file. It may replace found peace of file
	 * with replacement parameter. Find direction may be specified (reverse
	 * orderer or normal ordered). This feature meaningful when matchFirstOnly
	 * parameter set to true.
	 * 
	 * @param absoluteFileName
	 *            File name to search byte array
	 * @param sought
	 *            Byte array to seek
	 * @param replacement
	 *            If not null, found piece of file will be repleced with given
	 *            value. If null only find operation will be performed.
	 * @param matchFirtstOnly
	 *            when true, after first match of sought parameter, search
	 *            operation will not continue. <br>
	 *            when false, all occurances of sought variable will find / (and
	 *            may be replace)
	 * @param reverseSearch
	 *            when true, search operation starts beginning of the file.<br>
	 *            when false, search operation starts end of the file.
	 * @return
	 * 
	 * @throws IOException
	 */
	public static List<Long> searchReplace(String absoluteFileName,
			byte[] sought, byte[] replacement, boolean matchFirtstOnly,
			boolean reverseSearch) throws IOException {
		List<Long> positionList = new ArrayList<Long>();
		RandomAccessFile raf = null;
		FileChannel fc = null;
		if (sought.length > NIO_BUFFER_SIZE) {
			throw new IOException("Sought buffer lenght[" + sought.length
					+ "] is greater then NIO_BUFFER_SIZE[" + NIO_BUFFER_SIZE
					+ "]");
		}
		try {
			String fileOpenMode = replacement == null ? "r" : "rw";
			FileChannel.MapMode mapMode = replacement == null ? FileChannel.MapMode.READ_ONLY
					: FileChannel.MapMode.READ_WRITE;
			raf = new RandomAccessFile(absoluteFileName, fileOpenMode); // "rws",
			// "rwd"
			fc = raf.getChannel();
			long fileSize = fc.size();
			int increment = (NIO_BUFFER_SIZE - sought.length + 1);
			if (!reverseSearch) {
				for (long offset = 0; offset < fileSize; offset += increment) {
					int memMapSize = (int) Math.min(NIO_BUFFER_SIZE, fileSize
							- offset);
					MappedByteBuffer bb = null;
					try {
						bb = fc.map(mapMode, offset, memMapSize);
						for (int pos = 0; pos <= (memMapSize - sought.length); pos++) {
							if (matches(bb, pos, sought)) {
								if (replacement != null) {
									replace(bb, pos, sought, replacement);
								}
								positionList.add(new Long(offset + pos));
								if (matchFirtstOnly) {
									return positionList;
								}
								pos += sought.length - 1;
							}
						}
					} finally {
						// bb kapatilmadan iceride return positionList
						// yapildiginda acik kaliyordu??
						if (bb != null) {
							bb.force(); // Write back to file, like "flush()"
							bb.clear();
							bb = null;
						}
					}
				}
			} else {
				for (long offset = fileSize; offset > 0; offset -= increment) {
					int memMapSize = (int) Math.min(NIO_BUFFER_SIZE, offset);
					MappedByteBuffer bb = null;
					try {
						bb = fc.map(mapMode, offset - memMapSize, memMapSize);
						for (int pos = (memMapSize - sought.length); pos >= 0; pos--) {
							if (matches(bb, pos, sought)) {
								if (replacement != null) {
									replace(bb, pos, sought, replacement);
								}
								positionList.add(new Long((offset - memMapSize)
										+ pos));
								if (matchFirtstOnly) {
									return positionList;
								}
								pos -= (sought.length - 1);
							}
						}
					} finally {
						// bb kapatilmadan iceride return positionList
						// yapildiginda acik kaliyordu??
						if (bb != null) {
							bb.force(); // Write back to file, like "flush()"
							bb.clear();
							bb = null;
						}
					}
				}
			}
		} finally {
			if (fc != null) {
				fc.close();
				fc = null;
			}
			if (raf != null) {
				raf.close();
				raf = null;
			}
			/*
			 * close islemleri aninda yapilamadigi icin file bir sure acik
			 * kaliyordu.
			 * (http://forum.java.sun.com/thread.jspa?threadID=323467&messageID=1311868)
			 * JSF 1.5.07 ile bu durum yasandi
			 */
			System.gc();
		}
		return positionList;
	}

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			args = new String[] { "123456123456ýI", "ðüþiöç123456ýI",
					"c:\\tmp\\1.txt" };
		}
		if (args.length < 3) {
			System.err.println("Usage: java Patch sought replacement file...");
			return;
		}
		byte[] sought = args[0].getBytes();
		byte[] replacement = args[1].getBytes();
		if (sought.length != replacement.length) {
			// Better build-in some support for padding with blanks.
			System.err.println("Usage: sought (" + args[0]
					+ ") and replacement (" + args[1]
					+ ") must have same length");
			return;
		}
		long startTime = System.currentTimeMillis();
		for (int i = 2; i < args.length; i++) {
			// patch(f, sought, replacement);
			List<Long> positionList = searchReplace(args[i], sought,
					replacement, false, true);
			for (Long pos : positionList) {
				System.out.println("Match position:" + pos);
			}

		}
		System.out.println("took:" + (System.currentTimeMillis() - startTime)
				+ " ms");
	}
}
