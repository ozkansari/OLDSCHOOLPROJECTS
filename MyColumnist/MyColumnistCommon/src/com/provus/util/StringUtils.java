/*
 * StringUtils.java
 *
 * Created on 11 Ocak 2005 Salý, 17:16
 */

package com.provus.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A helper class for string operations
 * 
 * @author korayg
 */
public class StringUtils {
	/**
	 * Creates a delimeted string out of given collection
	 * 
	 * @param c
	 * @param delim
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String collectionToDelimitedString(java.util.Collection c,
			String delim) {
		StringBuffer buff = new StringBuffer();
		java.util.Iterator iterator = c.iterator();
		while (iterator.hasNext()) {
			Object obj = iterator.next();
			if (obj == null)
				buff.append("null"); // sonuncunun arkasýna delimiter
			// koyulmaz
			else
				buff.append(obj.toString());
			if (iterator.hasNext())
				buff.append(delim);
		}
		return buff.toString();
	}

	/**
	 * Creates a delimeted string out of given string array
	 * 
	 * @param strArray
	 * @param delim
	 * @return
	 */
	public static String stringArrayToDelimitedString(String strArray[],
			String delim) {
		StringBuffer buff = new StringBuffer();
		if (strArray == null)
			return "";
		int len = strArray.length;
		for (int i = 0; i < len; i++) {
			buff.append(strArray[i]);
			if (i != len - 1) // sonuncunun arkasýna delimiter koyulmaz
				buff.append(delim);
		}
		return buff.toString();
	}

	/**
	 * Creates a string array, out of given string splitting with given
	 * delimeter
	 * 
	 * @param str
	 * @param delim
	 * @return
	 */
	public static String[] stringToStringArray(String str, String delim) {
		return str.split(delim);
	}

	/**
	 * String sinifinin guzel split i delimeter olarak regex aldigi icin
	 * yazildi. delimeter regex karakterleri oldugunda sorun yasaniyor
	 * 
	 * @param inputLine
	 * @param delim
	 * @return
	 */
	public static String[] split(String inputLine, String delim) {
		List<String> list = splitAsList(inputLine, delim);
		String[] array = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	/**
	 * String sinifinin guzel split i delimeter olarak regex aldigi icin
	 * yazildi. delimeter regex karakterleri oldugunda sorun yasaniyor
	 * 
	 * @param inputLine
	 * @param delim
	 * @return
	 */
	public static List<String> splitAsList(String inputLine, String delim) {
		List<String> retVal = new ArrayList<String>();

		while (true) {
			int index = inputLine.indexOf(delim);

			if (index == -1) {
				retVal.add(inputLine);
				break;
			}

			retVal.add(inputLine.substring(0, index));
			inputLine = inputLine.substring(index + delim.length());
		}
		return retVal;
	}

	/**
	 * Returns "Y" for true and "N" for false
	 * 
	 * @param b
	 * @return
	 */
	public static String booleanToYesNo(Boolean b) {
		return b.booleanValue() ? "Y" : "N";
	}

	/**
	 * Returns true for "Y" and false for "N"
	 * 
	 * @param s
	 * @return
	 */
	public static Boolean yesNoToBoolean(String s) {
		return new Boolean(s.equals("Y") ? true : false);
	}

	/**
	 * Returns stack trace as string
	 * 
	 * @param t
	 * @return
	 */
	public static String stack2string(Throwable t) {
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			return sw.toString();
		} catch (Throwable t2) {
			return "bad stack2string";
		}
	}

	/**
	 * Fills the string with the given char, as long as given length
	 * 
	 * @param ch
	 * @param len
	 * @return
	 */
	public static String iterateChar(char ch, int len) {
		if (len < 1)
			return "";
		char buf[] = new char[len];
		for (int i = 0; i < len; i++)
			buf[i] = ch;
		return new String(buf);
	}

	/**
	 * Pad the src to make it [len] bytes with trailing spaces.
	 * 
	 * @param src
	 * @param len
	 * @return
	 */
	public static String rightPadded(String src, int len) {
		return rightPadded(src, len, ' ');
	}

	/**
	 * Pad the src to make it [len] bytes with trailing last parameter.
	 * 
	 * @param src
	 * @param len
	 * @param padStr
	 * @return
	 */
	public static String rightPadded(String src, int len, char padChar) {
		if (len < 1)
			return "";
		if (src == null)
			src = "";
		src = src.trim();
		int srcLen = src.length();
		if (srcLen > len)
			return src.substring(0, len);
		return src + iterateChar(padChar, len - srcLen);
	}

	/**
	 * Pad the src to make it [len] bytes with trailing spaces.
	 * 
	 * @param src
	 * @param len
	 * @return
	 */
	public static String leftPadded(String src, int len) {
		return leftPadded(src, len, ' ');
	}

	/**
	 * Pad the src to make it [len] bytes with trailing last parameter.
	 * 
	 * @param src
	 * @param len
	 * @param padChar
	 * @return
	 */
	public static String leftPadded(String src, int len, char padChar) {
		if (len < 1)
			return "";
		if (src == null)
			src = "";
		src = src.trim();
		int srcLen = src.length();
		if (srcLen > len)
			return src.substring(0, len);
		return iterateChar(padChar, len - srcLen) + src;
	}

	/**
	 * 
	 * 
	 * @param src
	 * @param len
	 * @return
	 */
	public static String centerAlign(String src, int len) {
		if (len < 1)
			return "";
		if (src == null)
			src = "";
		src = src.trim();
		int srcLen = src.length();
		if (srcLen > len)
			return src.substring(0, len);
		int dif = len - srcLen;
		int leftLen = dif / 2;
		return iterateChar(' ', leftLen) + src
				+ iterateChar(' ', dif - leftLen);
	}

	/**
	 * Replace all occurance of from -> to in src return new string instance
	 * Without using reqular expression
	 * 
	 * @param src
	 * @param from
	 * @param to
	 * @return
	 */
	public static String replaceAll(String src, String from, String to) {
		final StringBuffer result = new StringBuffer();
		if (from == null || from.equals("")) {
			throw new IllegalArgumentException("from must have content.");
		}
		int startIdx = 0;
		int idxOld = 0;
		while ((idxOld = src.indexOf(from, startIdx)) >= 0) {
			result.append(src.substring(startIdx, idxOld));
			result.append(to);
			startIdx = idxOld + from.length();
		}
		result.append(src.substring(startIdx));
		return result.toString();
	}

	/**
	 * Repcales every character with the corresponding values in the rep[n][2]
	 * 
	 * @param str
	 *            Source string
	 * @param rep
	 *            [n][2] array. all [n][0] 's are replaced with [n][1]
	 * @return result string
	 */
	public static String replaceMultipleChars(String str, char[][] rep) {
		if (str == null)
			throw new InvalidParameterException("source string can not be null");
		if (rep == null || rep.length == 0)
			throw new InvalidParameterException(
					"replacement char array cant be empty");
		char s[] = str.toCharArray();
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < rep.length; j++) {
				if (rep[j].length != 2)
					throw new InvalidParameterException(
							"replacement char array dimention must be [n][2]");
				if (s[i] == rep[j][0])
					s[i] = rep[j][1];
			}
		}
		return new String(s);
	}

	/**
	 * Returns second parameter wrapped in braces concatted after parameter one
	 * Eg: dilara (þahin);
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String concatSummary(String str1, String str2) {
		if (str2 == null || str2.trim().length() == 0) {
			return str1;
		}
		return str1 + "(" + str2 + ")";
	}

	/**
	 * Trims the character both from left and right
	 * 
	 * @param str
	 * @param filler
	 * @return
	 */
	public static String trim(String str, final char filler) {
		// TODO: Performasi arttirilmali!!
		return rtrim(ltrim(str, filler), filler);
	}

	/**
	 * Performs left trim
	 * 
	 * @param str
	 * @param filler
	 * @return
	 */
	public static String ltrim(final String str, final char filler) {
		// TODO: Performasi arttirilmali!!
		if (str == null)
			return null;
		for (int i = 0; i < str.length(); ++i) {
			if (str.charAt(i) != filler) {
				return str.substring(i);
			}
		}
		return "";
	}

	/**
	 * PErforms right trim
	 * 
	 * @param str
	 * @param filler
	 * @return
	 */
	public static String rtrim(final String str, final char filler) {
		// TODO: performansi arttirilmali
		if (str == null)
			return null;
		for (int i = str.length() - 1; i >= 0; --i) {
			if (str.charAt(i) != filler)
				return str.substring(0, i + 1);
		}
		return "";
	}

	/**
	 * Checks if the given object is null or having a 0 length
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isEmpty(String field) {
		if (field == null || field.trim().length() == 0)
			return true;
		return false;
	}

	/**
	 * Checks if the given object is null or having a 0 length
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isNotEmpty(String field) {
		return !isEmpty(field);
	}

	/**
	 * Splits the given string to lines and returns the line list
	 * 
	 * @param stringToSplit
	 * @param delimiters
	 * @param maxChar
	 * @param maxLine
	 * @return
	 */
	public static List<String> splitToLines(String stringToSplit,
			List<String> delimiters, int maxChar, int maxLine) {

		// donulecek sonuclar
		List<String> result = new ArrayList<String>();

		// eger metnin uzunlugu bolunme sayisindan kisa ise, metin aynen
		// donuluyor
		if (maxChar >= stringToSplit.length()) {
			result.add(stringToSplit);
			return result;
		}

		StringBuilder lineBuilder = new StringBuilder(stringToSplit);
		int lineCount = 0;

		// Bir dongu icinde olmasi gereken line lari tespit ediyoruz.En fazla
		// maxLine sayisi kadar line donulebilir
		while (lineCount < maxLine) {

			// result a eklenip eklemeyecigini belirtir
			boolean addToResult = true;

			// 0 dan kacinci karaktere kadar olan metin parcasinin
			// kullanildigini beirtir
			int deleteIndex = maxChar;

			// bolunecek metnin ilk kismi aliniyor, en fazla maxChar kadar
			// karakter icerecek sekilde
			String line = "";
			if (lineBuilder.length() < maxChar) {
				// eger bolunecek metinde maxChar kadar karakter yoksa, metnin
				// hepsi line a aliniyor
				line = lineBuilder.toString();
			} else {
				line = lineBuilder.substring(0, maxChar);
			}

			// baslangicta hicbir ayirac metinde yokmus gibi kabul ediyoruz
			int maxIndex = -1;
			// metinde bulunan ayiracin uzunlugu
			int delimiterLength = 0;

			// tum ayiraclar tek tek line icinde araniyor.Indeks degeri en buyuk
			// olan ayrac kullanilacak.
			for (String delimiter : delimiters) {

				int index = line.lastIndexOf(delimiter);
				if (index > maxIndex) {
					maxIndex = index;
					delimiterLength = delimiter.length();
				}
			}

			if (maxIndex == -1) {
				/*
				 * yapacak bir sey yok, default degerler ile zaten metnin tumu
				 * alinip result a konuluyor ve lineBuilder dan cikariliyor.
				 */
			} else if (maxIndex == 0) {

				// eger ayrac ilk karakterden itibaren bulunuyorsa line i
				// delimiterLength den itibaren ekliyoruz
				if (delimiterLength == maxChar) {
					// ayiractan baska bir sey yoksa result a bir sey
					// eklemiyoruz
					addToResult = false;
				} else {
					// ayiractan baska karakterler varsa
					addToResult = false;
					deleteIndex = delimiterLength;
				}
			} else if (maxIndex + delimiterLength == maxChar) {
				// eger ayirac line in son kisminda bulunuyorsa
				if (delimiterLength == maxChar) {
					// ayiractan baska bir sey yoksa result a bir sey
					// eklemiyoruz
					addToResult = false;
				} else {
					// ayiractan baska karakterler varsa ayiraca kadar olan
					// kismi result a ekleyecegiz
					line = line.substring(0, maxIndex);
				}
			} else {
				// diger durumlarda 0. indeksten ayracin oldugu yere kadar
				// ekliyoruz
				line = line.substring(0, maxIndex);
				// ayracin sonuna kadar olan yeri silecegiz
				deleteIndex = maxIndex + delimiterLength;
			}

			// result a ekleniyor
			// butun ayiraclar icin line a bakiliyor. Eger bir line da ayiractan
			// baska bir sey bulunamaz ise sonuca eklenmiyor.
			for (String delimiter : delimiters) {
				if (org.apache.commons.lang.StringUtils.containsOnly(line,
						delimiter))
					addToResult = false;
			}

			if (addToResult) {
				result.add(line);
				lineCount++;
			}

			// stringToSplit ten kullandigimiz kisim cikariliyor
			lineBuilder.delete(0, deleteIndex);

			// eger stringToSplit bitti ise sonuclari donuyoruz
			if (lineBuilder.length() < 1) {
				return result;
			}

		} // end of for

		return result;
	}

	/**
	 * Checks given string contains only given array of characters
	 * 
	 * @param str
	 * @param charArray
	 * @return
	 */
	public static boolean containsOnly(String str, char charArray[]) {
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			boolean found = false;
			for (int j = 0; j < charArray.length; j++) {
				if (ch == charArray[j]) {
					found = true;
					break;
				}
			}
			if (found == false)
				return false;
		}
		return true;
	}

	/**
	 * replace key with value for given string. key syntax: {#key} For example
	 * when str is "Mr #{name};", key=name, value=Koray result is "Mr Koray";
	 * 
	 * @param str
	 * @param key
	 * @param value
	 * @return
	 */
	public static String replaceParameter(String str, String key, String value) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put(key, value);
		return replaceParameters(str, paramMap);
	}

	/**
	 * replace paramMap key's with values for given string. key syntax: {#key}
	 * 
	 * @param str
	 * @param paramMap
	 * @return
	 */
	public static String replaceParameters(String str,
			Map<String, String> paramMap) {
		Pattern pattern = Pattern.compile("\\#\\{\\w*\\}");
		int start = -1;
		while (true) {
			Matcher matcher = pattern.matcher(str);
			if (!matcher.find(start + 1)) {
				break;
			}
			start = matcher.start();
			int end = matcher.end();
			if (start < 0 || end < 0)
				break;
			String parameter = str.substring(start + 2, end - 1);
			String parameterValue = paramMap.get(parameter);
			if (parameterValue != null) {
				str = str.substring(0, start) + parameterValue
						+ str.substring(end);
			}
		}
		return str;
	}

	/**
	 * Returns true if all character is digit in the string.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isAllDigits(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i)))
				return false;
		}
		return true;
	}

	/**
	 * Return string whihe is not include unsupported file name charakters in
	 * window OS
	 * 
	 * @param source
	 * @return
	 */
	public static String clearUnSupportedFileNameChars(String source) {
		if (source == null)
			throw new InvalidParameterException("source string can not be null");
		// window sun dosya isminde kabul etmedgi karakterler
		char[] unsuppurtedChars = FileUtil.getUnSupportedFileNameCharSet();

		char s[] = source.toCharArray();
		StringBuffer buffer = new StringBuffer(source.length());
		for (int i = 0; i < s.length; i++) {
			boolean isUnsupportedChar = false;
			for (int j = 0; j < unsuppurtedChars.length; j++) {
				if (s[i] == unsuppurtedChars[j]) {
					// desteklenmeyen karakter
					isUnsupportedChar = true;
				}
			}

			if (!isUnsupportedChar) {
				buffer.append(s[i]);
			}
		}
		return buffer.toString();
	}
}
