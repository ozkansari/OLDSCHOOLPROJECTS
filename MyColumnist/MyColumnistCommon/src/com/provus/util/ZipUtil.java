package com.provus.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

public class ZipUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZipUtil.class);

	static final int BUFFER = 2048;

	/**
	 * Compresses the specified files
	 * 
	 * @param zipFile
	 *            Output zip file name
	 * @param fileList
	 *            Files to zip
	 * @param baseDirectory
	 *            Zip files base directory
	 * @throws IOException
	 */
	public static void zip(String zipFile, List<File> fileList,
			String baseDirectory) throws IOException{
		zip(zipFile, fileList, baseDirectory, null);
	}
	
	public static void zip(String zipFile, List<File> fileList,
			String baseDirectory, Integer compressionType) throws IOException {
		if (CollectionUtil.isEmpty(fileList))
			throw new IOException("Zip file list cannot be empty");
		FileOutputStream dest = null;
		CheckedOutputStream checksum = null;
		ZipOutputStream out = null;

		try {
			dest = new FileOutputStream(zipFile);
			checksum = new CheckedOutputStream(dest, new Adler32());
			out = new ZipOutputStream(new BufferedOutputStream(checksum));
			if (compressionType != null)
				out.setLevel(compressionType);
			byte data[] = new byte[BUFFER];
			for (int i = 0; i < fileList.size(); i++) {
				File file = fileList.get(i);
				BufferedInputStream origin = null;
				try {
					String zipEntryPath = file.getAbsolutePath();
					if (zipEntryPath.startsWith(baseDirectory))
						zipEntryPath = zipEntryPath.substring(baseDirectory
								.length());
					if (file.isDirectory()) {
						/*
						 * dokumantasyona gore isletim sisteminden bagimsiz
						 * olarak '/' ile biterse dizin olarak yorumlanirmis.
						 */
						zipEntryPath = FileUtil
								.removeFileSeperator(zipEntryPath);
						if (zipEntryPath.endsWith("/") == false)
							zipEntryPath += "/";
						ZipEntry entry = new ZipEntry(zipEntryPath);
						out.putNextEntry(entry);
					} else {
						FileInputStream fi = new FileInputStream(file);
						origin = new BufferedInputStream(fi, BUFFER);
						ZipEntry entry = new ZipEntry(zipEntryPath);
						out.putNextEntry(entry);
						int count;
						while ((count = origin.read(data, 0, BUFFER)) != -1) {
							out.write(data, 0, count);
						}
					}
				} finally {
					if (origin != null)
						origin.close();
				}
			}
		} finally {
			if (out != null)
				out.close();
			if (checksum != null)
				checksum.close();
			if (dest != null)
				dest.close();
		}

	}

	public static List<File> unZip(String zipFile, String destFolder)
			throws IOException {
		List<File> extractedFiles = new ArrayList<File>();

		destFolder = FileUtil.appendFileSeperator(destFolder);
		FileInputStream fis = new FileInputStream(zipFile);
		CheckedInputStream checksum = new CheckedInputStream(fis, new Adler32());

		ZipInputStream zis = null;
		try {
			zis = new ZipInputStream(new BufferedInputStream(checksum));
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				int count;
				byte data[] = new byte[BUFFER];
				// write the files to the disk
				// destFolder + entry.getName()
				File f = new File(entry.getName());
				if (!f.isAbsolute())
					f = new File(destFolder + entry.getName());
				extractedFiles.add(f);

				File parentFile = f.getParentFile();
				if (parentFile.exists() == false) {
					parentFile.mkdirs();
				}
				if (entry.isDirectory()) {
					f.mkdirs();
					continue;
				}
				FileOutputStream fos = new FileOutputStream(f);
				BufferedOutputStream dest = null;
				try {
					dest = new BufferedOutputStream(fos, BUFFER);
					while ((count = zis.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, count);
					}
					dest.flush();
				} finally {
					if (dest != null)
						dest.close();
				}
			}
		} finally {
			if (zis != null)
				zis.close();
		}

		return extractedFiles;
	}

	/**
	 * Zips the file and places it in the same directory as the source
	 * 
	 * @param absouluteFileName
	 * @return
	 * @throws IOException
	 */
	public static boolean zipFile(String absoluteFileName,
			boolean keepFileExtension) throws IOException {
		if (StringUtils.isEmpty(absoluteFileName))
			return false;

		// Source file
		File sourceAsFile = new File(absoluteFileName);
		if (!sourceAsFile.exists()) {
			throw new FileNotFoundException("Source file:" + absoluteFileName
					+ " does not exist");
		}

		// Create and place source file in list
		List<File> fileToZip = new ArrayList<File>();
		fileToZip.add(sourceAsFile);

		// To get basePath get file name and remove it from absolute file name
		String sourceFileName = absoluteFileName;
		sourceFileName = FileUtil.trimFilePath(absoluteFileName);
		String sourcePath = absoluteFileName;
		sourcePath = sourcePath.replace(sourceFileName, "");

		// Create zip file name by assignin .zip as file extension
		String zipFileName = absoluteFileName;
		if (keepFileExtension) {
			zipFileName += ".zip";
		} else {
			zipFileName = FileUtil.trimFileExtension(absoluteFileName) + ".zip";
		}

		simpleZip(zipFileName, absoluteFileName);
		// Check if file exists
		File zipFile = new File(zipFileName);
		if (zipFile.exists())
			return true;
		return false;
	}

	private static void simpleZip(String outputFileName, String... files) {
		// Create a buffer for reading the files
		byte[] buf = new byte[BUFFER];

		try {
			// Create the ZIP file
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					outputFileName));

			// Compress the files
			for (int i = 0; i < files.length; i++) {
				FileInputStream in = new FileInputStream(files[i]);

				// Add ZIP entry to output stream.
				out.putNextEntry(new ZipEntry(files[i]));

				// Transfer bytes from the file to the ZIP file
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}

				// Complete the entry
				out.closeEntry();
				in.close();
			}

			// Complete the ZIP file
			out.close();
		} catch (IOException e) {
			logger.error("Exception occured zipping file:", e);
		}
	}

	public static void main(String[] args) throws IOException {
		boolean suc = zipFile(
				"C:\\PAYS\\version\\v.${pays.deploy.version}\\PAYSCommandServer_1.0.08\\PAYSCommandServer.log",
				true);
		System.err.println(suc);
	}
}
