package com.provus.util;

import java.util.Collection;

/**
 * @author dilaras
 * 
 */
public class ArrayUtil {

	public static final String[] EMPTY_STRING_ARRAY = {};

	/**
	 * Checks if the given array is null or its length is empty
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(Object[] array) {
		if (array == null)
			return true;
		if (array.length == 0)
			return true;
		return false;
	}

	/**
	 * Converts given object array to string array. uses each object's toString
	 * method
	 * 
	 * @param objects
	 * @return
	 */
	public static String[] toStringArray(Object[] objects) {
		int length = objects.length;
		String[] result = new String[length];
		for (int i = 0; i < length; i++) {
			result[i] = objects[i].toString();
		}
		return result;
	}

	/**
	 * Converts given collection to string array.
	 * 
	 * @param coll
	 * @return
	 */
	public static String[] toStringArray(Collection coll) {
		return (String[]) coll.toArray(EMPTY_STRING_ARRAY);
	}

}
