package com.provus.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;
import com.provus.test.FileUtilTestHelper;
import com.provus.util.FileUtil;
import com.provus.util.ZipUtil;

public class ZipUtilTest extends TestCase {

	public void testZipAndUnZip() throws IOException {
		String baseDir = "c:\\junit.ZipUtilTest";
		String unzipDir = "c:\\junit.ZipUtilTest.unzip";
		File zipFile = null;
		try {
			FileUtilTestHelper.createRecursiveDirectories(baseDir);
			List<File> fileList = FileUtil.list(baseDir, ".*", true, false,
					true, false);
			zipFile = new File("c:\\junit.ZipUtil.zip");
			ZipUtil.zip(zipFile.getAbsolutePath(), fileList, baseDir);
			assertTrue(zipFile.exists());
			assertTrue(zipFile.length() > 0);

			// Unzip test ediliyor
			new File(unzipDir).mkdir();
			ZipUtil.unZip(zipFile.getAbsolutePath(), unzipDir);
			List<File> fileList2 = FileUtil.list(baseDir, ".*", true, false,
					true, false);
			assertEquals(fileList.size(), fileList2.size());
		} finally {
			FileUtilTestHelper.deleteRecursiveDirectories(baseDir);
			FileUtilTestHelper.deleteRecursiveDirectories(unzipDir);
			if (zipFile != null)
				zipFile.delete();
		}

	}

}
