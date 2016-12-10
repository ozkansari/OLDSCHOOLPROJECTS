package com.provus.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;
import com.provus.util.FileSearchReplaceUtil;
import com.provus.util.FileUtil;

/**
 * Test class form FileSearchReplaceUtil
 * 
 * @author korayg
 * 
 */
public class FileSearchReplaceUtilTest extends TestCase {

	private String testFileName;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		FileSearchReplaceUtil.NIO_BUFFER_SIZE = 1024 * 1024 * 1024; // 1GB;
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		if (testFileName != null) {
			new File(testFileName).delete();
		}
	}

	public void testSearch() throws IOException {
		String content = "koray gecici 123 koray gecici";
		this.testFileName = "c:\\junit.FileSearchReplaceUtilTest";
		File file = new File(testFileName);
		FileUtil.writeFile("c:\\junit.FileSearchReplaceUtilTest", content,
				false);
		List<Long> positionList = FileSearchReplaceUtil.searchReplace(file
				.getAbsolutePath(), "koray".getBytes(), null, false, false);
		assertEquals(2, positionList.size());
		assertEquals(new Long(0), positionList.get(0));
		assertEquals(new Long(17), positionList.get(1));
	}


	public void testSearchMatchFirst() throws IOException {
		String content = "koray gecici 123 koray gecici";
		this.testFileName = "c:\\junit.FileSearchReplaceUtilTest";
		File file = new File(testFileName);
		FileUtil.writeFile("c:\\junit.FileSearchReplaceUtilTest", content,
				false);
		List<Long> positionList = FileSearchReplaceUtil.searchReplace(file
				.getAbsolutePath(), "koray".getBytes(), null, true, false);
		assertEquals(1, positionList.size());
		assertEquals(new Long(0), positionList.get(0));
	}
	
	public void testSearchMatchFirstReverse() throws IOException {
		String content = "koray gecici 123 koray gecici";
		this.testFileName = "c:\\junit.FileSearchReplaceUtilTest";
		File file = new File(testFileName);
		FileUtil.writeFile("c:\\junit.FileSearchReplaceUtilTest", content,
				false);
		List<Long> positionList = FileSearchReplaceUtil.searchReplace(file
				.getAbsolutePath(), "koray".getBytes(), null, true, true);
		assertEquals(1, positionList.size());
		assertEquals(new Long(17), positionList.get(0));
	}
	
	
	public void testSearchPartialBuffer() throws IOException {
		String content = "koraykoray 123 koray";
		this.testFileName = "c:\\junit.FileSearchReplaceUtilTest";
		File file = new File(testFileName);
		FileUtil.writeFile("c:\\junit.FileSearchReplaceUtilTest", content,
				false);
		List<Long> positionList = FileSearchReplaceUtil.searchReplace(file
				.getAbsolutePath(), "koray".getBytes(), null, false, false);
		assertEquals(3, positionList.size());
		assertEquals(new Long(0), positionList.get(0));
		assertEquals(new Long(5), positionList.get(1));
		assertEquals(new Long(15), positionList.get(2));

		// Parcali buffer okuma slemi aranan ifadenin tam ortasina gelsin
		// istiyoruz
		FileSearchReplaceUtil.NIO_BUFFER_SIZE = 5;
		positionList = FileSearchReplaceUtil.searchReplace(file
				.getAbsolutePath(), "koray".getBytes(), null, false, false);
		assertEquals(3, positionList.size());
		assertEquals(new Long(0), positionList.get(0));
		assertEquals(new Long(5), positionList.get(1));
		assertEquals(new Long(15), positionList.get(2));

		// Parcali buffer okuma slemi aranan ifadenin tam bir karakter sonrasina
		// gelsin istiyoruz
		FileSearchReplaceUtil.NIO_BUFFER_SIZE = 6;
		positionList = FileSearchReplaceUtil.searchReplace(file
				.getAbsolutePath(), "koray".getBytes(), null, false, false);
		assertEquals(3, positionList.size());
		assertEquals(new Long(0), positionList.get(0));
		assertEquals(new Long(5), positionList.get(1));
		assertEquals(new Long(15), positionList.get(2));

		// Parcali buffer okuma slemi aranan ifadenin tam bir karakter sonrasina
		// gelsin istiyoruz
		FileSearchReplaceUtil.NIO_BUFFER_SIZE = 13;
		positionList = FileSearchReplaceUtil.searchReplace(file
				.getAbsolutePath(), "koray".getBytes(), null, false, false);
		assertEquals(3, positionList.size());
		assertEquals(new Long(0), positionList.get(0));
		assertEquals(new Long(5), positionList.get(1));
		assertEquals(new Long(15), positionList.get(2));
	}

	public void testSearchReverse() throws IOException {
		String content = "koray gecici 123 koray gecici";
		this.testFileName = "c:\\junit.FileSearchReplaceUtilTest";
		File file = new File(testFileName);
		FileUtil.writeFile("c:\\junit.FileSearchReplaceUtilTest", content,
				false);
		List<Long> positionList = FileSearchReplaceUtil.searchReplace(file
				.getAbsolutePath(), "koray".getBytes(), null, false, true);
		assertEquals(2, positionList.size());
		assertEquals(new Long(17), positionList.get(0));
		assertEquals(new Long(0), positionList.get(1));
	}

	public void testSearchPartialBufferReverse() throws IOException {
		String content = "koraykoray 123 koray";
		this.testFileName = "c:\\junit.FileSearchReplaceUtilTest";
		File file = new File(testFileName);
		FileUtil.writeFile("c:\\junit.FileSearchReplaceUtilTest", content,
				false);
		List<Long> positionList = FileSearchReplaceUtil.searchReplace(file
				.getAbsolutePath(), "koray".getBytes(), null, false, true);
		assertEquals(3, positionList.size());
		assertEquals(new Long(15), positionList.get(0));
		assertEquals(new Long(5), positionList.get(1));
		assertEquals(new Long(0), positionList.get(2));

		// Parcali buffer okuma slemi aranan ifadenin tam ortasina gelsin
		// istiyoruz
		FileSearchReplaceUtil.NIO_BUFFER_SIZE = 5;
		positionList = FileSearchReplaceUtil.searchReplace(file
				.getAbsolutePath(), "koray".getBytes(), null, false, true);
		assertEquals(3, positionList.size());
		assertEquals(new Long(15), positionList.get(0));
		assertEquals(new Long(5), positionList.get(1));
		assertEquals(new Long(0), positionList.get(2));

		// Parcali buffer okuma slemi aranan ifadenin tam bir karakter sonrasina
		// gelsin istiyoruz
		FileSearchReplaceUtil.NIO_BUFFER_SIZE = 6;
		positionList = FileSearchReplaceUtil.searchReplace(file
				.getAbsolutePath(), "koray".getBytes(), null, false, true);
		assertEquals(3, positionList.size());
		assertEquals(new Long(15), positionList.get(0));
		assertEquals(new Long(5), positionList.get(1));
		assertEquals(new Long(0), positionList.get(2));

		// Parcali buffer okuma slemi aranan ifadenin tam bir karakter sonrasina
		// gelsin istiyoruz
		FileSearchReplaceUtil.NIO_BUFFER_SIZE = 13;
		positionList = FileSearchReplaceUtil.searchReplace(file
				.getAbsolutePath(), "koray".getBytes(), null, false, true);
		assertEquals(3, positionList.size());
		assertEquals(new Long(15), positionList.get(0));
		assertEquals(new Long(5), positionList.get(1));
		assertEquals(new Long(0), positionList.get(2));
	}

	public void testReplace() throws IOException {
		String content = "koray gecici 123 koray gecici";
		this.testFileName = "c:\\junit.FileSearchReplaceUtilTest";
		File file = new File(testFileName);
		FileUtil.writeFile("c:\\junit.FileSearchReplaceUtilTest", content,
				false);
		List<Long> positionList = FileSearchReplaceUtil.searchReplace(file
				.getAbsolutePath(), "koray".getBytes(), "12345".getBytes(),
				false, false);
		assertEquals(2, positionList.size());
		assertEquals(new Long(0), positionList.get(0));
		assertEquals(new Long(17), positionList.get(1));
		String newContent = FileUtil.readFile(file.getAbsolutePath());
		assertEquals(content.replaceAll("koray", "12345"), newContent);
	}
}
