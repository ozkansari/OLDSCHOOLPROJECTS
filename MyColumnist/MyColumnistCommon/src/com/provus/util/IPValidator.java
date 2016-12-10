package com.provus.util;

import java.util.regex.Pattern;

public class IPValidator {
	
	private static String patternStr = "\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";
	
	private static Pattern pattern = Pattern.compile(patternStr);
	
	public static boolean validate(String ip){
		return pattern.matcher(ip).matches();
	}
}
