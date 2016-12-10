package com.provus.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import com.provus.test.FileUtilTestHelper;
import com.provus.util.FileUtil;
import com.provus.util.MD5Wrapper;

/**
 * Test class for com.provus.util.FileUtil
 * 
 * @author
 * 
 */
public class FileUtilTest extends TestCase {
	String tempFileName;

	String recursiveTempDir;

	@Override
	protected void setUp() throws IOException {
		File tempFile = File.createTempFile("FileUtilTest", ".txt");
		tempFileName = tempFile.getAbsolutePath();
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName));
		for (int i = 0; i < 100; i++) {
			writer.write(String.valueOf(i));
			writer.newLine();
		}
		writer.flush();
		writer.close();
	}

	/**
	 * Tests com.provus.util.FileUtil.copy(String, String)
	 * 
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 */
	public void testCopy() throws IOException, NoSuchAlgorithmException,
			NoSuchProviderException {
		File tempFileCopy = File.createTempFile("FileUtilTest", ".txt.copy");
		FileUtil.copy(tempFileName, tempFileCopy.getAbsolutePath());
		MD5Wrapper md5 = new MD5Wrapper();
		String tempFileCopyMD5 = md5.getValue(tempFileCopy);
		tempFileCopy.delete();
		String tempFileMD5 = md5.getValue(new File(tempFileName));
		assertTrue(tempFileMD5.equals(tempFileCopyMD5));
	}

	/**
	 * Tests com.provus.util.FileUtil.readFile(String)
	 * 
	 * @throws IOException
	 */
	public void testReadFile() throws IOException {
		String str = FileUtil.readFile(tempFileName);
		assertEquals(str.length(), 390);
	}

	/**
	 * Tests com.provus.util.FileUtil.readFileAsLines(String)
	 * 
	 * @throws IOException
	 */
	public void testReadFileAsLines() throws IOException {
		List lineList = FileUtil.readFileAsLines(tempFileName);
		assertEquals(lineList.size(), 100);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		File tempFile = new File(tempFileName);
		tempFile.delete();
		if (recursiveTempDir != null)
			FileUtilTestHelper.deleteRecursiveDirectories(recursiveTempDir);
	}

	/**
	 * Tests com.provus.util.FileUtil.trimFileExtension(String)
	 */
	public void testTrimFileExtension() {
		String s = FileUtil.trimFileExtension("abcd.txt");
		assertEquals(s, "abcd");
		s = FileUtil.trimFileExtension("abcd.");
		assertEquals(s, "abcd");
		s = FileUtil.trimFileExtension("abcd");
		assertEquals(s, "abcd");
	}

	/**
	 * Tests com.provus.util.FileUtil.trimFilePath(String)
	 */
	public void testTrimFilePath() {
		String s = FileUtil.trimFilePath("c:\\abcd\\efgh.txt");
		assertEquals(s, "efgh.txt");
		s = FileUtil.trimFilePath("\\efgh.txt");
		assertEquals(s, "efgh.txt");
		s = FileUtil.trimFilePath("efgh.txt");
		assertEquals(s, "efgh.txt");
		s = FileUtil.trimFilePath("c:\\abcd\\");
		assertEquals(s, "");
	}

	/**
	 * Tests com.provus.util.FileUtil.appendFileSeperator(String)
	 */
	public void testAppendFileSeperator() {
		String s = FileUtil.appendFileSeperator("c:\\abcd");
		assertEquals(s, "c:\\abcd\\");
		s = FileUtil.appendFileSeperator("c:\\abcd\\");
		assertEquals(s, "c:\\abcd\\");
	}

	public void testDeleteDirectory() throws IOException {
		String mainDir = "c:\\ProvusUtil.junit.test";
		FileUtilTestHelper.createRecursiveDirectories(mainDir);
		FileUtil.deleteDirectory(new File(mainDir));
		assertFalse(new File(mainDir).exists());
	}

	public void testCleanDirectory() throws IOException {
		String mainDir = "c:\\ProvusUtil.junit.test";
		FileUtilTestHelper.createRecursiveDirectories(mainDir);
		FileUtil.cleanDirectory(new File(mainDir));
		assertTrue(new File(mainDir).exists());
		assertFalse(new File(mainDir + "\\subdir1").exists());
		assertFalse(new File(mainDir + "\\file1.txt").exists());
	}

	public void testForceDelete() throws IOException {
		String mainDir = "c:\\ProvusUtil.junit.test";
		FileUtilTestHelper.createRecursiveDirectories(mainDir);
		FileUtil.forceDelete(new File(mainDir));
		assertFalse(new File(mainDir).exists());
	}

	public void testList1() throws IOException {
		this.recursiveTempDir = "c:\\ProvusUtil.junit.test";
		FileUtilTestHelper.createRecursiveDirectories(recursiveTempDir);
		List<File> list = FileUtil.list(recursiveTempDir, "\\.*$", true, true,
				true, true);
		assertEquals(4, list.size());

	}

	public void testList2() throws IOException {
		// Sadece file listelemeyi test eder
		this.recursiveTempDir = "c:\\ProvusUtil.junit.test";
		FileUtilTestHelper.createRecursiveDirectories(recursiveTempDir);
		List<File> list = FileUtil.list(recursiveTempDir, "\\.*$", true, false,
				true, true);
		assertEquals(2, list.size());

	}

	public void testList3() throws IOException {
		// Sadece dizin listelemeyi test eder
		this.recursiveTempDir = "c:\\ProvusUtil.junit.test";
		FileUtilTestHelper.createRecursiveDirectories(recursiveTempDir);
		List<File> list = FileUtil.list(recursiveTempDir, "\\.*$", false, true,
				true, true);
		assertEquals(2, list.size());
	}

	public void testList4() throws IOException {
		// Sadece recursive olmayan listelemeyi test eder
		this.recursiveTempDir = "c:\\ProvusUtil.junit.test";
		FileUtilTestHelper.createRecursiveDirectories(recursiveTempDir);
		List<File> list = FileUtil.list(recursiveTempDir, "\\.*$", true, true,
				false, true);
		assertEquals(3, list.size());

	}

	public void writeFileTest() throws IOException {
		String str = "1234567890";
		tempFileName = "junit.writeFileTest.txt";
		File tempFile = new File(tempFileName);

		assertFalse(tempFile.exists());
		FileUtil.writeFile(tempFileName, str, false);
		assertEquals(str.length(), tempFile.length());
	}

	public void writeFileTestAppend() throws IOException {
		String str = "1234567890";
		tempFileName = "junit.writeFileTest.txt";
		File tempFile = new File(tempFileName);
		assertFalse(tempFile.exists());
		FileUtil.writeFile(tempFileName, str, false);
		FileUtil.writeFile(tempFileName, str, true);
		assertEquals(str.length() * 2, tempFile.length());
	}

	public void writeFileLines() throws IOException {
		List<String> list = Arrays.asList(new String[] { "123", "456" });
		tempFileName = "junit.writeFileTest.txt";
		File tempFile = new File(tempFileName);
		assertFalse(tempFile.exists());
		FileUtil.writeFileLines(tempFileName, list, false);
		assertEquals(list.get(0).length() + list.get(1).length()
				+ FileUtil.osLineSep.length(), tempFile.length());
	}
}
