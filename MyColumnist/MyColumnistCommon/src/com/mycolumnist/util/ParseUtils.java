package com.mycolumnist.util;

import java.util.Date;

public class ParseUtils {

	/**
	 * 
	 * @param text
	 * @return
	 */
	public static String cleanStringFromWhitespaces(String text){
		return text.replaceAll("&nbsp;", " ").trim();
	}
}
