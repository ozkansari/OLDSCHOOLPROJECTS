package com.provus.util;

import java.util.Locale;

public class TurkishLocaleHelper {
	private static final Locale enLocale = new Locale("ISO-8859-1");

	private static final Locale trLocale = new Locale("ISO-8859-9");

	public static final char TR_g = '\u011f';

	public static final char TR_i = '\u0131';

	public static final char TR_s = '\u015f';

	public static final char TR_G = '\u011e';

	public static final char TR_I = '\u0130';

	public static final char TR_S = '\u015e';

	public static final String TURKISH_APLHABET_LOWER = "abcçdefg\u011fh\u0131ijklmnoöprs\u015ftuüvyz";

	public static final String TURKISH_APLHABET_UPPER = "ABCÇDEFG\u011eHI\u0130JKLMNOÖPRS\u015eTUÜVYZ";

	public static final String TURKISH_APLHABET = TURKISH_APLHABET_LOWER
			+ TURKISH_APLHABET_UPPER;

	public static final byte ISTANBUL = 34;
	
	public static final byte ANKARA = 6;
	
	public static final byte IZMIR = 35;
	
	public static final byte DIGER = 99;
	
	/**
	 * Replaces turkish characters with english corespondands then changes the
	 * case to upper
	 * 
	 * @param value
	 * @return
	 */
	public static String makeUpperNonTR(String value) {
		value = convertToNonTR(value);
		value = toUpperCaseEN(value);
		return value;
	}

	/**
	 * Replaces turkish characters with english corespondands
	 * 
	 * @param value
	 * @return
	 */
	public static String convertToNonTR(String value) {
		String r = "";
		for (int j = 0; j < value.length(); j++) {
			char ch = value.charAt(j);
			if (ch == '\u011f')
				r = r + "g";
			else if (ch == 'ü')
				r = r + "u";
			else if (ch == '\u015f')
				r = r + "s";
			else if (ch == '\u0131')
				r = r + "i";
			else if (ch == 'ö')
				r = r + "o";
			else if (ch == 'ç')
				r = r + "c";
			else if (ch == '\u011e')
				r = r + "G";
			else if (ch == 'Ü')
				r = r + "U";
			else if (ch == '\u015e')
				r = r + "S";
			else if (ch == '\u0130')
				r = r + "I"; // buyuk i ise
			else if (ch == 'Ö')
				r = r + "O";
			else if (ch == 'Ç')
				r = r + "C";
			else
				r = r + value.charAt(j);
		}
		return r;
	}

	/**
	 * Replaces turkish characters with dos corespondands
	 * 
	 * @param value
	 * @return
	 */
	public static String convertToDOSTR(String value) {
		String r = "";
		for (int j = 0; j < value.length(); j++) {
			char ch = value.charAt(j);
			if (ch == '\u011f') // giresun
				r = r + (char) 167;
			else if (ch == 'ü') // ulke
				r = r + (char) 129;
			else if (ch == '\u015f') // semsiye
				r = r + (char) 159;
			else if (ch == '\u0131') // inegol
				r = r + (char) 141;
			else if (ch == 'ö') // odemis
				r = r + (char) 148;
			else if (ch == 'ç') // canakkale
				r = r + (char) 135;
			else if (ch == '\u011e') // yumusak G
				r = r + (char) 166;
			else if (ch == 'Ü') // ULKE
				r = r + (char) 154;
			else if (ch == '\u015e') // SEMSIYE
				r = r + (char) 158;
			else if (ch == '\u0130') // INEGOL
				r = r + (char) 152;
			else if (ch == 'Ö') // ODEMIS
				r = r + (char) 153;
			else if ((ch == 'Ç')) // CANAKKALE
				r = r + (char) 128;
			else
				r = r + ch;
		}
		return r;
	}

	/**
	 * Converts given string to Windows Turkish encoding
	 * 
	 * @param value
	 * @return
	 */
	public static String convertToWindowsTR(String value) {
		String r = "";
		for (int j = 0; j < value.length(); j++) {
			char ch = value.charAt(j);
			if (ch == (char) 167) // giresun
				r = r + '\u011f';
			else if (ch == (char) 129) // ulke
				r = r + 'ü';
			else if (ch == (char) 159) // semsiye
				r = r + '\u015f';
			else if (ch == (char) 141) // inegol
				r = r + '\u0131';
			else if (ch == (char) 148) // odemis
				r = r + 'ö';
			else if (ch == (char) 135) // canakkale
				r = r + 'ç';
			else if (ch == (char) 166) // yumusak G
				r = r + '\u011e';
			else if (ch == (char) 154) // ULKE
				r = r + 'Ü';
			else if (ch == (char) 158) // SEMSIYE
				r = r + '\u015e';
			else if (ch == (char) 152) // INEGOL
				r = r + '\u0130';
			else if (ch == (char) 153) // ODEMIS
				r = r + 'Ö';
			else if ((ch == (char) 128)) // CANAKKALE
				r = r + 'Ç';
			else
				r = r + ch;
		}
		return r;
	}

	/**
	 * Replaces Turkish characters with the English ones 
	 * (EMBOSS --> I is not converted to (i) bc the machine read I's)
	 * 
	 * @param value
	 * @return
	 */
	public static String convertUpperTRToLowerEN(String value) {
		String r = "";
		for (int j = 0; j < value.length(); j++) {
			char ch = value.charAt(j);
			if (ch == '\u011e')
				r = r + "g";
			else if (ch == 'Ü')
				r = r + "u";
			else if (ch == '\u015e')
				r = r + "s";
			else if (ch == '\u0130')
				r = r + "i"; // buyuk i ise
			else if (ch == 'Ö')
				r = r + "o";
			else if ((ch == 'Ç'))
				r = r + "c";
			else
				r = r + ch;
		}
		return r;
	}

	/**
	 * Converts only turkish characters to utf 8 
	 * @param s
	 * @return
	 */
	public static String convertTrToUTF8(String s) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == TurkishLocaleHelper.TR_g) {
				buf.append("&#287;");
			} else if (c == 'ü') {
				buf.append("&#252;");
			} else if (c == TurkishLocaleHelper.TR_s) {
				buf.append("&#351;");
			} else if (c == 'ö') {
				buf.append("&#246;");
			} else if (c == 'ç') {
				buf.append("&#231;");
			} else if (c == TurkishLocaleHelper.TR_i) {
				buf.append("&#305;");
			} else if (c == TurkishLocaleHelper.TR_G) {
				buf.append("&#286;");
			} else if (c == 'Ü') {
				buf.append("&#220;");
			} else if (c == TurkishLocaleHelper.TR_S) {
				buf.append("&#350;");
			} else if (c == TurkishLocaleHelper.TR_I) {
				buf.append("&#304;");
			} else if (c == 'Ö') {
			 	buf.append("&#214;");
			}else if (c == 'Ç') {
			 	buf.append("&#199;");
			} else {
				buf.append(c);
			}
				
		}
		return buf.toString();
	}
	
	public static byte findKnownCityCode(String city){
		
		if (city == null || city.length() == 0)
			return DIGER;
		
		city = convertToNonTR(city);
		city = city.toLowerCase(Locale.ENGLISH);
		if (city.equals("istanbul"))
			return ISTANBUL;
		if (city.equals("ankara"))
			return ANKARA;
		if (city.equals("izmir"))
			return IZMIR;
		return DIGER;
	}	
	
	@Deprecated
	public static String toUpperCaseEN(String str) {
		return str.toUpperCase(enLocale);
	}

	@Deprecated
	public static String toLowerCaseEN(String str) {
		return str.toLowerCase(enLocale);
	}

	@Deprecated
	public static String toUpperCaseTR(String str) {
		return str.toUpperCase(trLocale);
	}

	@Deprecated
	public static String toLowerCaseTR(String str) {
		return str.toLowerCase(trLocale);
	}
}
