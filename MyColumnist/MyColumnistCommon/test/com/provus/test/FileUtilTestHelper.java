package com.provus.test;

import java.io.File;
import java.io.IOException;

public class FileUtilTestHelper {
	
	public static void createRecursiveDirectories(String mainDir) throws IOException {
		File dir = new File(mainDir);
		File subDir1 = new File(dir.getAbsolutePath() + "\\subdir1");
		File subDir2 = new File(dir.getAbsolutePath() + "\\subdir2");
		dir.mkdir();
		subDir1.mkdir();
		subDir2.mkdir();
		File tempFile1 = new File(dir.getAbsolutePath() + "\\file1.txt");
		File tempFile2 = new File(subDir1.getAbsolutePath() + "\\file2.txt");
		tempFile1.createNewFile();
		tempFile2.createNewFile();
	}
	
	public static void deleteRecursiveDirectories(String mainDir) {
		File dir = new File(mainDir);
		File subDir1 = new File(dir.getAbsolutePath() + "\\subdir1");
		File subDir2 = new File(dir.getAbsolutePath() + "\\subdir2");
		File tempFile1 = new File(dir.getAbsolutePath() + "\\file1.txt");
		File tempFile2 = new File(subDir1.getAbsolutePath() + "\\file2.txt");
		tempFile1.delete();
		tempFile2.delete();
		subDir1.delete();
		subDir2.delete();
		dir.delete();
	}
}
