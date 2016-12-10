package com.mycolumnist.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

public class EntityUtil {
	
	private static SimpleDateFormat turkishStyleShortDateFormatter;
	
	public static SimpleDateFormat getTurkishStyleShortDateFormatter() {
		return turkishStyleShortDateFormatter == null 
				? turkishStyleShortDateFormatter = new SimpleDateFormat("dd/MM/yyyy")
				: turkishStyleShortDateFormatter;
						
	}
	
	public static String getFullyCapitalizedText(String text){
		if(!Locale.getDefault().getCountry().equalsIgnoreCase("TR")){
			Locale locale = new Locale("tr", "TR");
			Locale.setDefault(locale);
		}
		
		String result = WordUtils.capitalizeFully(text);
		
		// TODO: Turkish Char Problem
		result = text;
		
		return result;
	}

	public static String getCleanText(String text) {
		if(!Locale.getDefault().getCountry().equalsIgnoreCase("TR")){
			Locale locale = new Locale("tr", "TR");
			Locale.setDefault(locale);
		}
		
		String cleanText = text;
		cleanText = cleanText.trim();
		cleanText = StringUtils.lowerCase(cleanText);
		cleanText = StringUtils.deleteWhitespace(cleanText);
		cleanText = StringUtils.replaceChars(cleanText, ".", "");
		
		return cleanText;
	}
	
	public static String getStandardizedText(String text){
		
		if(!Locale.getDefault().getCountry().equalsIgnoreCase("TR")){
			Locale locale = new Locale("tr", "TR");
			Locale.setDefault(locale);
		}
		
		String result = text;
		result = result.trim();
		result = getFullyCapitalizedText(result);
		return result;
	}
}
