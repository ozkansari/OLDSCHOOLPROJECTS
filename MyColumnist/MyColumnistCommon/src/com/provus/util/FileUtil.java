package com.provus.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

/**
 * A class created to handle file operations
 * 
 * @author
 * 
 */
public class FileUtil {
	public static final String osLineSep = System.getProperty("line.separator");

	public static final String ALL_FILES_PATTERN = "\\.*$";

	private static class FileNameComparator implements Comparator<File> {
		public int compare(File file1, File file2) {
			if (file1 == null)
				return 1;
			if (file2 == null)
				return -1;
			return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
		}
	}

	/*
	 * Bu method hem file hem de directory leri donuyordu Bunun yerine
	 * findDirectories yada findFiles methodlari kullanilmali
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public static Vector listFiles(String directory, String patternString,
			boolean sort) {
		RegExpFilenameFilter filenameFilter = new RegExpFilenameFilter();
		filenameFilter.setPattern(patternString);
		directory = appendFileSeperator(directory);
		File sourceDirectoryPath = new File(directory);
		File sourceFiles[] = sourceDirectoryPath.listFiles(filenameFilter);
		if (sourceFiles == null || sourceFiles.length == 0)
			return new Vector();
		Vector sourceFileNames = new Vector();
		if (sort) {
			Set sortedFileNames = new TreeSet();
			for (int i = 0; i < sourceFiles.length; i++) {
				sortedFileNames.add(sourceFiles[i].getAbsolutePath());
			}
			sourceFileNames.addAll(sortedFileNames);
		} else {
			for (int i = 0; i < sourceFiles.length; i++) {
				sourceFileNames.add(sourceFiles[i].getAbsolutePath());
			}
		}
		return sourceFileNames;
	}

	/**
	 * Returns the list of directories under the given directory matching the
	 * pattern. If sort is true sorts the names in the list.
	 * 
	 * @param directory
	 * @param patternString
	 * @param sort
	 * @return
	 */
	public static List<String> findDirectories(String directory,
			String patternString, boolean sort) {
		RegExpFilenameFilter filenameFilter = new RegExpFilenameFilter();
		filenameFilter.setPattern(patternString);
		directory = appendFileSeperator(directory);
		File directoryFile = new File(directory);
		String filesAndDirs[] = directoryFile.list(filenameFilter);
		if (filesAndDirs == null || filesAndDirs.length == 0)
			return new ArrayList<String>();
		List<String> dirList = new ArrayList<String>();
		for (String fileOrDir : filesAndDirs) {
			File f = new File(directory + fileOrDir);
			if (f.isDirectory())
				dirList.add(fileOrDir);
		}
		if (sort) {
			Collections.sort(dirList);
		}
		return dirList;
	}

	/**
	 * Returns the list of files under the given directory matching the pattern.
	 * If sort is true sorts the names in the list.
	 * 
	 * @param directory
	 * @param patternString
	 * @param sort
	 * @return
	 */
	public static List<String> findFiles(String directory,
			String patternString, boolean sort) {
		return findFiles(directory, patternString, sort, true);
	}

	/**
	 * Returns the list of files under the given directory matching the pattern.
	 * If sort is true sorts the names in the list. If abslutePath is true,
	 * includes the abslude file name in the list
	 * 
	 * @param directory
	 * @param patternString
	 * @param sort
	 * @param absolutePath
	 * @return
	 */
	public static List<String> findFiles(String directory,
			String patternString, boolean sort, boolean absolutePath) {
		RegExpFilenameFilter filenameFilter = new RegExpFilenameFilter();
		filenameFilter.setPattern(patternString);
		File sourceDirectoryPath = new File(directory);
		File sourceFiles[] = sourceDirectoryPath.listFiles(filenameFilter);
		if (sourceFiles == null || sourceFiles.length == 0)
			return new ArrayList<String>();
		List<String> fileList = new ArrayList<String>();
		for (File file : sourceFiles) {
			if (file.isFile()) {
				String path = file.getAbsolutePath();
				if (!absolutePath)
					path = removeFileSeperator(path);
				fileList.add(path);
			}
		}
		if (sort) {
			Collections.sort(fileList);
		}
		return fileList;
	}

	private static void listFilesInternal(Collection<File> files,
			File directory, FilenameFilter filter, boolean findFiles,
			boolean findDirectories, boolean recursive) {

		File[] matchingFiles = directory.listFiles(filter);
		Set<File> founds = new HashSet<File>();
		if (matchingFiles != null) {
			for (File file : matchingFiles) {
				founds.add(file);
			}
		}

		FileFilter dirFileFilter = new FileFilter() {

			public boolean accept(File file) {
				if (file.isDirectory())
					return true;
				return false;
			}
		};

		File[] directories = directory.listFiles(dirFileFilter);
		if (directories != null) {
			for (File file : directories) {
				founds.add(file);
			}
		}

		for (File file : founds) {
			if (file.isDirectory()) {
				if (findDirectories)
					files.add(file);
				if (recursive) {
					listFilesInternal(files, file, filter, findFiles,
							findDirectories, recursive);
				}
			} else {
				if (findFiles)
					files.add(file);
			}
		}

	}

	public static List<File> list(String directory, String patternString,
			boolean findFiles, boolean findDirectories, boolean recursive,
			boolean sort) {
		RegExpFilenameFilter filenameFilter = new RegExpFilenameFilter();
		filenameFilter.setPattern(patternString);
		File sourceDirectoryPath = new File(directory);
		List<File> fileList = new ArrayList<File>();
		listFilesInternal(fileList, sourceDirectoryPath, filenameFilter,
				findFiles, findDirectories, recursive);
		if (sort) {
			Collections.sort(fileList, new FileNameComparator());
		}
		return fileList;
	}

	/**
	 * Copy source File to destination
	 * 
	 * @param sourceFileName
	 * @param destFileName
	 * @throws IOException
	 */
	public static void copy(String sourceFileName, String destFileName)
			throws IOException {
		File sourceFile = new File(sourceFileName);
		File destFile = new File(destFileName);
		FileInputStream src = null;
		FileOutputStream dst = null;
		try {
			src = new FileInputStream(sourceFile);
			dst = new FileOutputStream(destFile);
			byte[] buffer = new byte[2048];
			while (true) {
				int count = src.read(buffer);
				if (count == -1)
					break;
				dst.write(buffer, 0, count);
			}
		} finally {
			if (src != null)
				src.close();
			if (dst != null)
				dst.close();
		}
	}

	/**
	 * Fills the contents of the file given to a String
	 * 
	 * @param absoluteFileName
	 * @return
	 * @throws IOException
	 */
	public static String readFile(String absoluteFileName) throws IOException {
		File file = new File(absoluteFileName);
		byte buffer[] = null;
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(absoluteFileName, "r");
			int fileSize = (int) file.length();
			buffer = new byte[fileSize];
			raf.read(buffer, 0, fileSize);
		} finally {
			if (raf != null)
				raf.close();
		}
		return new String(buffer, "ISO-8859-9");
	}

	/**
	 * Fills the contents of the file from resource given to a String
	 * 
	 * @param absoluteFileName
	 * @return
	 * @throws IOException
	 */
	public static String readFileFromResource(String resource)
			throws IOException {
		String stripped = resource.startsWith("/") ? resource.substring(1)
				: resource;
		InputStream stream = null;
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		if (classLoader != null) {
			stream = classLoader.getResourceAsStream(stripped);
		}
		if (stream == null) {
			stream = FileUtil.class.getResourceAsStream(resource);
		}
		if (stream == null) {
			stream = FileUtil.class.getClassLoader().getResourceAsStream(
					stripped);
		}
		if (stream == null)
			return null;
		ByteArrayOutputStream contentStream = new ByteArrayOutputStream();
		byte[] b = new byte[4096];
		int len = 0;
		while ((len = stream.read(b)) > 0) {
			contentStream.write(b, 0, len);
		}
		String outputString = contentStream.toString("ISO-8859-9");
		return outputString;
	}

	/**
	 * Reads and returns as a string list the lines in the given file.
	 * 
	 * @param absoluteFileName
	 * @return
	 * @throws IOException
	 */
	public static List<String> readFileAsLines(String absoluteFileName)
			throws IOException {
		return readFileAsLines(absoluteFileName, null, null);
	}

	/**
	 * Reads and returns as a string list the lines in the given file.
	 * 
	 * @param absoluteFileName
	 * @return
	 * @throws IOException
	 */
	public static List<String> readFileAsLines(String absoluteFileName,
			Long rba, Integer returnLineSize) throws IOException {
		List<String> lineList = new ArrayList<String>();
		Braf braf = null;
		try {
			braf = new Braf(absoluteFileName, "r", 4096);
			if (rba != null)
				braf.seek(rba);
			String line = null;
			while ((line = braf.getNextLine()) != null) {
				lineList.add(line);
				if (returnLineSize != null && lineList.size() == returnLineSize)
					break;
			}
		} finally {
			if (braf != null)
				braf.close();
		}
		return lineList;
	}

	/**
	 * Writes a string to file
	 * 
	 * @param absoluteFileName
	 * @param content
	 * @param append
	 * @throws IOException
	 */
	public static void writeFile(String absoluteFileName, String content,
			boolean append) throws IOException {
		FileWriter fw = null;
		Writer writer = null;
		try {
			fw = new FileWriter(absoluteFileName, append);
			writer = new BufferedWriter(fw);
			writer.write(content);
		} finally {
			if (writer != null)
				writer.close();
			if (fw != null)
				fw.close();
		}
	}

	/**
	 * Writes collection of string to file
	 * 
	 * @param absoluteFileName
	 * @param coll
	 * @param append
	 * @throws IOException
	 */
	public static void writeFileLines(String absoluteFileName,
			Collection<String> coll, boolean append) throws IOException {
		FileWriter fw = null;
		Writer writer = null;
		try {
			fw = new FileWriter(absoluteFileName, append);
			writer = new BufferedWriter(fw);
			for (String s : coll) {
				writer.write(s);
				writer.append(FileUtil.osLineSep);
			}
		} finally {
			if (writer != null)
				writer.close();
			if (fw != null)
				fw.close();
		}
	}

	/**
	 * Writes collection of string to file
	 * 
	 * @param absoluteFileName
	 * @param coll
	 * @param append
	 * @throws IOException
	 */
	public static void writeFileLinesAsTurkishLocal(String absoluteFileName,
			Collection<String> coll, boolean append) throws IOException {
		FileWriter fw = null;
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(absoluteFileName, append),
					"ISO-8859-9"));
			for (String s : coll) {
				writer.write(s);
				writer.append(FileUtil.osLineSep);
			}
		} finally {
			if (writer != null)
				writer.close();
			if (fw != null)
				fw.close();
		}
	}

	/**
	 * Removes the file extension
	 * 
	 * @param fileName
	 * @return
	 */
	public static String trimFileExtension(String fileName) {
		int sep = fileName.lastIndexOf(".");
		if (sep < 0)
			return fileName;
		String result = fileName.substring(0, sep);
		return result;
	}

	/**
	 * Removes filePath from file name
	 * 
	 * @param absoluteFileName
	 * @return
	 */
	public static String trimFilePath(String absoluteFileName) {
		String result = null;
		int sep1 = absoluteFileName.lastIndexOf('/');
		int sep2 = absoluteFileName.lastIndexOf('\\');
		int maxSep = sep1 > sep2 ? sep1 : sep2;
		if (maxSep < 0) {
			return absoluteFileName;
		}
		result = absoluteFileName.substring(maxSep + 1);
		return result;
	}

	/**
	 * Returns file path
	 * 
	 * @param absFileName
	 * @return
	 */
	public static String findFilePath(String absFileName) {
		String fileName = FileUtil.trimFilePath(absFileName);
		if (fileName.equals(absFileName)) {
			return "";
		}

		return absFileName.substring(0, absFileName.indexOf(fileName));
	}

	/**
	 * Appends file separator to a path. If file ends with a file separtor thens
	 * returns the given path
	 * 
	 * @param path
	 * @return
	 */
	public static String appendFileSeperator(String path) {
		if (path.endsWith(File.separator))
			return path;
		return path + File.separator;
	}

	/**
	 * Removes file separator from the end of path given if there is any
	 * 
	 * @param path
	 * @return
	 */
	public static String removeFileSeperator(String path) {
		if (path.endsWith(File.separator))
			return path.substring(0, path.length() - File.separator.length());
		return path;
	}

	/**
	 * Moves files to target directory and returns the last paths of moved files
	 * 
	 * @param fileNames
	 *            filenames to remove
	 * @param targetDir
	 *            target directory
	 * @return
	 */
	public static List<String> moveFiles(List<String> fileNames,
			String targetDir) throws IOException {

		targetDir = FileUtil.appendFileSeperator(targetDir);

		List<String> movingFiles = new ArrayList<String>();
		for (String inputFileName : fileNames) {

			String fileNameWithoutPath = FileUtil.trimFilePath(inputFileName);
			String targetFileName = targetDir + fileNameWithoutPath;
			File targetFile = new File(targetFileName);
			File inputFile = new File(inputFileName);
			inputFile.renameTo(targetFile);

			movingFiles.add(targetFileName);
		}
		return movingFiles;
	}

	/**
	 * Recursively delete directory.
	 * 
	 * @param directory
	 *            directory to delete
	 * @throws IOException
	 *             in case deletion is unsuccessful
	 */
	public static void deleteDirectory(File directory) throws IOException {
		if (!directory.exists()) {
			return;
		}
		cleanDirectory(directory);
		if (!directory.delete()) {
			throw new IOException("Unable to delete directory " + directory
					+ ".");
		}
	}

	/**
	 * Clean a directory without deleting it.
	 * 
	 * @param directory
	 *            directory to clean
	 * @throws IOException
	 *             in case cleaning is unsuccessful
	 */
	public static void cleanDirectory(File directory) throws IOException {
		if (!directory.exists()) {
			throw new IllegalArgumentException(directory + " does not exist");
		}

		if (!directory.isDirectory()) {
			String message = directory + " is not a directory";
			throw new IllegalArgumentException(message);
		}

		File[] files = directory.listFiles();
		if (files == null) { // null if security restricted
			throw new IOException("Failed to list contents of " + directory);
		}

		IOException exception = null;
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			try {
				forceDelete(file);
			} catch (IOException ioe) {
				exception = ioe;
			}
		}

		if (null != exception) {
			throw exception;
		}
	}

	/**
	 * <p>
	 * Delete a file. If file is a directory, delete it and all sub-directories.
	 * </p>
	 * <p>
	 * The difference between File.delete() and this method are:
	 * </p>
	 * <ul>
	 * <li>A directory to be deleted does not have to be empty.</li>
	 * <li>You get exceptions when a file or directory cannot be deleted.
	 * (java.io.File methods returns a boolean)</li>
	 * </ul>
	 * 
	 * @param file
	 *            file or directory to delete.
	 * @throws IOException
	 *             in case deletion is unsuccessful
	 */
	public static void forceDelete(File file) throws IOException {
		if (file.isDirectory()) {
			deleteDirectory(file);
		} else {
			if (!file.exists()) {
				throw new FileNotFoundException("File does not exist: " + file);
			}
			if (!file.delete()) {
				String message = "Unable to delete file: " + file;
				throw new IOException(message);
			}
		}
	}

	/**
	 * Converts absolute filename to given File collection
	 * 
	 * @param coll
	 * @return
	 */
	public static List<String> convertFileNameList(Collection<File> coll) {
		if (coll == null)
			return null;
		List<String> fileNameList = new ArrayList<String>();
		for (File file : coll) {
			fileNameList.add(file.getAbsolutePath());
		}
		return fileNameList;
	}

	/**
	 * Converts given absolute filename collection to File list
	 * 
	 * @param coll
	 * @return
	 */
	public static List<File> convertFileList(Collection<String> coll) {
		if (coll == null)
			return null;
		List<File> fileList = new ArrayList<File>();
		for (String fileName : coll) {
			fileList.add(new File(fileName));
		}
		return fileList;
	}

	/**
	 * Converts a {@link File} to an {@link URL}.
	 */
	public static URL toURL(File file) throws IOException {
		return file.toURL();
	}

	/**
	 * Convert from a <code>URL</code> to a <code>File</code>.
	 * <p>
	 * From version 1.1 this method will decode the URL. Syntax such as
	 * <code>file:///my%20docs/file.txt</code> will be correctly decoded to
	 * <code>/my docs/file.txt</code>.
	 * 
	 * @param url
	 *            the file URL to convert, <code>null</code> returns
	 *            <code>null</code>
	 * @return the equivalent <code>File</code> object, or <code>null</code> if
	 *         the URL's protocol is not <code>file</code>
	 * @throws IllegalArgumentException
	 *             if the file is incorrectly encoded
	 */
	public static File toFile(URL url) {
		if (url == null || !url.getProtocol().equals("file")) {
			return null;
		} else {
			String filename = url.getFile().replace('/', File.separatorChar);
			int pos = 0;
			while ((pos = filename.indexOf('%', pos)) >= 0) {
				if (pos + 2 < filename.length()) {
					String hexStr = filename.substring(pos + 1, pos + 3);
					char ch = (char) Integer.parseInt(hexStr, 16);
					filename = filename.substring(0, pos) + ch
							+ filename.substring(pos + 3);
				}
			}
			return new File(filename);
		}
	}

	/**
	 * Moves single file from source to destination and checks if action is
	 * successful
	 * 
	 * @param sourceFileName
	 * @param destination
	 * @return
	 * @throws FileNotFoundException
	 */
	public static boolean moveFile(String sourceFileName, String destination)
			throws FileNotFoundException, IOException {
		// Convert from path to file
		File sourceAsFile = new File(sourceFileName);
		File destinationAsFile = new File(destination);
		boolean success = false;
		// Check if source file exists
		if (!sourceAsFile.exists()) {
			throw new FileNotFoundException("File : " + sourceFileName
					+ " does not exist");
		}
		// Check if destination directory exists
		if (!destinationAsFile.getParentFile().exists()) {
			throw new FileNotFoundException("File : " + destination
					+ " does not exists");
		}
		// Move file to new directory
		success = sourceAsFile.renameTo(destinationAsFile);
		return success;
	}

	/**
	 * Return unSupported file name character set
	 * 
	 * @return
	 * @throws Exception
	 */
	public static char[] getUnSupportedFileNameCharSet() {

		String osName = System.getProperty("os.name");
		if (osName.indexOf("Windows") == -1) {
			throw new RuntimeException("Unsupported Operating System: "
					+ osName);
		}

		// window sun dosya isminde kabul etmedgi karakterler
		char[] unsuppurtedChars = { (char) 34, // "
				(char) 42, // *
				(char) 47, // /
				(char) 58, // :
				(char) 60, // <
				(char) 62, // >
				(char) 63, // ?
				(char) 92, // \
				(char) 124 // |
		};

		return unsuppurtedChars;
	}

	public static boolean isStartWithFileSeparator(String path) {
		if (path.startsWith("/") || path.startsWith("\\")) {
			return true;
		}
		return false;
	}

	public static boolean isEndWithFileSeparator(String path) {
		if (path.endsWith("/") || path.endsWith("\\")) {
			return true;
		}
		return false;
	}
}
