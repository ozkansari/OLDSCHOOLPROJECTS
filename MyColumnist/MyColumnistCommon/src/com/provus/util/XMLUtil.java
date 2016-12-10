package com.provus.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@SuppressWarnings("unchecked")
public class XMLUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(XMLUtil.class);

	/**
	 * Reads from configuration file and creates the object. If
	 * 
	 * @param configFilePath
	 * @param aliasMap
	 * @return
	 */
	public static Object parseConfiguration(String configFilePath,
			Map<String, Class> aliasMap) {
		return parseConfiguration(configFilePath, aliasMap, false);
	}

	/**
	 * Reads from configuration file path and creates the object. If frommUrl is
	 * true, it use createObjectFromUrl which we can use for files encoded with
	 * UTF-8. Other it use createObjectFromFile which we can use for files
	 * encoded with ASCII.
	 * 
	 * @param configFilePath
	 * @param aliasMap
	 * @param fromUrl
	 * @return
	 */
	public static Object parseConfiguration(String configFilePath,
			Map<String, Class> aliasMap, boolean fromUrl) {
		// Config file path is null, return null
		if (configFilePath == null) {
			logger.error("ConfigFilePath argument is null");
			return null;
		}

		// Create stream object
		XStream stream = new XStream(new DomDriver());
		// Create aliases for the ones in aliasMap, if map is not null
		if (aliasMap != null && aliasMap.size() > 0) {
			Set<String> keySet = aliasMap.keySet();
			for (String key : keySet) {
				stream.alias(key, aliasMap.get(key));
			}
		}
		Object tmpObj = null;
		if (fromUrl) {
			tmpObj = createObjectFromUrl(configFilePath, stream);
		} else {
			tmpObj = createObjectFromFile(configFilePath, stream);
		}
		return tmpObj;
	}

	private static Object createObjectFromFile(String configFilePath,
			XStream stream) {
		Object tempObject = null;
		// Try to create a file object from path
		File configFile = new File(configFilePath);
		// If configFile is null, return null
		if (configFile == null) {
			logger.error("Could not create a file from " + configFilePath);
			return null;
		}
		// If no such file exists, return null
		if (!configFile.exists()) {
			logger.error("No such file : " + configFilePath);
			return null;
		}

		try {
			tempObject = stream.fromXML(new FileReader(configFile));
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		}
		return tempObject;
	}

	private static Object createObjectFromUrl(String configFilePath,
			XStream stream) {
		Object tempObject = null;
		// Try to create a file object from path

		try {
			tempObject = stream.fromXML(new URL("file:" + configFilePath)
					.openStream());
		} catch (MalformedURLException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		}
		return tempObject;
	}

	public static String toXML(Object object, Map<String, Class> aliasMap) {
		if (object == null)
			return null;
		XStream stream = new XStream(new DomDriver());
		if (aliasMap != null && aliasMap.size() > 0) {
			Set<String> keySet = aliasMap.keySet();
			for (String key : keySet) {
				stream.alias(key, aliasMap.get(key));
			}
		}
		return stream.toXML(object);
	}
}
