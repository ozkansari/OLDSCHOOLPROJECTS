package com.provus.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import junit.framework.TestCase;
import com.provus.util.Braf;
import com.provus.util.FileUtil;

/**
 * Test class for com.provus.util.Braf
 * 
 * @author
 * 
 */
public class BrafTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		File toRead = new File("c:/dilara.txt");
		if (!toRead.exists())
			toRead.createNewFile();
		FileWriter writePlease = new FileWriter(toRead);
		for (int i = 0; i <= 15; i++) {
			writePlease.write("dilara" + FileUtil.osLineSep);
		}
		writePlease.close();
	}

	/**
	 * Tests com.provus.util.Braf.getNextLine()
	 * 
	 * @throws IOException
	 */
	public void test0d0d0a() throws IOException {
		Braf b = new Braf("c:/dilara.txt", "r", 2048);
		String str = b.getNextLine();
		int i = 0;
		while (str != null) {
			str = b.getNextLine();
			System.out.println(++i + " " + str);
		}
		b.close();
		System.out.println("bitti");
	}

	@Override
	protected void tearDown() throws Exception {
		File toRead = new File("c:/dilara.txt");
		if (toRead.exists()) {
			toRead.delete();
		}
	}
}
