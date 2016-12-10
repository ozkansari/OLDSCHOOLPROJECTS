package com.provus.util;

import java.util.Collection;

import org.apache.log4j.Logger;

/**
 * @author korayg
 * 
 */
public class CollectionUtil {

	private static final Logger logger = Logger.getLogger(CollectionUtil.class);

	/**
	 * Checks if the given array is null or its length is empty
	 * 
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection collection) {
		if (collection == null)
			return true;
		if (collection.size() == 0)
			return true;
		return false;
	}

	public static boolean isNotEmpty(Collection collection) {
		return !isEmpty(collection);
	}

	public static Collection<Long> convertCollection(
			Collection<String> collection) {
		Collection listLong = null;
		try {
			listLong = (Collection) Class.forName(
					collection.getClass().getName()).newInstance();
		} catch (InstantiationException e) {
			logger.error(e);
			return null; 
		} catch (IllegalAccessException e) {
			logger.error(e);
			return null;
		} catch (ClassNotFoundException e) {
			logger.error(e);
			return null;
		}
		for (String str : collection) {
			listLong.add(Long.parseLong(str));
		}
		return listLong;
	}
}
