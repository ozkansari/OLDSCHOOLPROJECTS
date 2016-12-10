package com.provus.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

public class ResourceUtils {

	/** Pseudo URL prefix for loading from the class path: "classpath:" */
	public static final String CLASSPATH_URL_PREFIX = "classpath:";

	/** URL protocol for a file in the file system: "file" */
	public static final String URL_PROTOCOL_FILE = "file";

	/**
	 * Resolve the given resource location to a <code>java.io.File</code>,
	 * i.e. to a file in the file system.
	 * <p>
	 * Does not check whether the file actually exists; simply returns the File
	 * that the given location would correspond to.
	 * 
	 * @param resourceLocation
	 *            the resource location to resolve: either a "classpath:" pseudo
	 *            URL, a "file:" URL, or a plain file path
	 * @return a corresponding File object
	 * @throws FileNotFoundException
	 *             if the resource cannot be resolved to a file in the file
	 *             system
	 */
	public static URL getURL(String resourceLocation)
			throws FileNotFoundException {
		if (resourceLocation.startsWith(CLASSPATH_URL_PREFIX)) {
			String path = resourceLocation.substring(CLASSPATH_URL_PREFIX
					.length());
			String description = "class path resource [" + path + "]";
			URL url = Thread.currentThread().getContextClassLoader()
					.getResource(path);
			if (url == null) {
				throw new FileNotFoundException(
						description
								+ " cannot be resolved to URL because it does not exist");
			}
			return url;
		}
		try {
			// try URL
			return new URL(resourceLocation);
		} catch (MalformedURLException ex) {
			// no URL -> treat as file path
			try {
				return new URL("file:" + resourceLocation);
			} catch (MalformedURLException ex2) {
				throw new FileNotFoundException("Resource location ["
						+ resourceLocation
						+ "] is neither a URL not a well-formed file path");
			}
		}
	}

	/**
	 * Resolve the given resource location to a <code>java.io.File</code>,
	 * i.e. to a file in the file system.
	 * <p>
	 * Does not check whether the fil actually exists; simply returns the File
	 * that the given location would correspond to.
	 * 
	 * @param resourceLocation
	 *            the resource location to resolve: either a "classpath:" pseudo
	 *            URL, a "file:" URL, or a plain file path
	 * @return a corresponding File object
	 * @throws FileNotFoundException
	 *             if the resource cannot be resolved to a file in the file
	 *             system
	 */
	public static File getFile(String resourceLocation)
			throws FileNotFoundException {
		if (resourceLocation.startsWith(CLASSPATH_URL_PREFIX)) {
			String path = resourceLocation.substring(CLASSPATH_URL_PREFIX
					.length());
			String description = "class path resource [" + path + "]";
			URL url = Thread.currentThread().getContextClassLoader()
					.getResource(path);
			if (url == null) {
				throw new FileNotFoundException(description
						+ " cannot be resolved to absolute file path "
						+ "because it does not reside in the file system");
			}
			return getFile(url, description);
		}
		try {
			// try URL
			return getFile(new URL(resourceLocation));
		} catch (MalformedURLException ex) {
			// no URL -> treat as file path
			return new File(resourceLocation);
		}
	}

	/**
	 * Resolve the given resource URL to a <code>java.io.File</code>, i.e. to
	 * a file in the file system.
	 * 
	 * @param resourceUrl
	 *            the resource URL to resolve
	 * @return a corresponding File object
	 * @throws FileNotFoundException
	 *             if the URL cannot be resolved to a file in the file system
	 */
	public static File getFile(URL resourceUrl) throws FileNotFoundException {
		return getFile(resourceUrl, "URL");
	}

	/**
	 * Resolve the given resource URL to a <code>java.io.File</code>, i.e. to
	 * a file in the file system.
	 * 
	 * @param resourceUrl
	 *            the resource URL to resolve
	 * @param description
	 *            a description of the original resource that the URL was
	 *            created for (for example, a class path location)
	 * @return a corresponding File object
	 * @throws FileNotFoundException
	 *             if the URL cannot be resolved to a file in the file system
	 */
	public static File getFile(URL resourceUrl, String description)
			throws FileNotFoundException {
		if (!URL_PROTOCOL_FILE.equals(resourceUrl.getProtocol())) {
			throw new FileNotFoundException(description
					+ " cannot be resolved to absolute file path "
					+ "because it does not reside in the file system: "
					+ resourceUrl);
		}
		return new File(URLDecoder.decode(resourceUrl.getFile()));
	}

	/**
	 * Return whether the given resource location is a URL: either a special
	 * "classpath" pseudo URL or a standard URL.
	 * 
	 * @see #CLASSPATH_URL_PREFIX
	 * @see java.net.URL
	 */
	public static boolean isUrl(String resourceLocation) {
		if (resourceLocation.startsWith(CLASSPATH_URL_PREFIX)) {
			return true;
		}
		try {
			new URL(resourceLocation);
			return true;
		} catch (MalformedURLException ex) {
			return false;
		}
	}
}
