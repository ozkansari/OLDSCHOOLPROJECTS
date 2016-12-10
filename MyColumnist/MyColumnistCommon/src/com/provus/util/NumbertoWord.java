/*******************************************************************************
 * $Author: Korayg $
 * $Date: 8/04/06 1:17p $
 * $Modtime: 8/04/06 1:04p $
 * $Revision: 2 $
 * $History: NumbertoWord.java $
 *
 * *****************  Version 2  *****************
 * User: Korayg       Date: 8/04/06    Time: 1:17p
 * Updated in $/JMS/src/tr/com/provus/common/util
 *
 * *****************  Version 1  *****************
 * User: Korayg       Date: 26.07.06   Time: 16:49
 * Created in $/JMS/src/tr/com/provus/common/util
 *
 * *****************  Version 1  *****************
 * User: Korayg       Date: 5/22/06    Time: 2:43p
 * Created in $/JMS/src/tr/com/provus/util
 *
 * *****************  Version 1  *****************
 * User: Korayg       Date: 8/29/05    Time: 1:31p
 * Created in $/Provus/ViewController/src/tr/com/provus/util
 *
 * *****************  Version 1  *****************
 * User: Korayg       Date: 8/29/05    Time: 9:43a
 * Created in $/Provus/ViewController/src/it/tr/com/provus/util
 *
 * *****************  Version 7  *****************
 * User: Korayg       Date: 3/16/05    Time: 3:14p
 * Updated in $/tr/com/provus/util
 *
 * *****************  Version 6  *****************
 * User: Korayg       Date: 2/18/05    Time: 5:53p
 * Updated in $/tr/com/provus/util
 *
 * *****************  Version 5  *****************
 * User: Korayg       Date: 2/17/05    Time: 4:52p
 * Updated in $/tr/com/provus/util
 *
 * *****************  Version 4  *****************
 * User: Korayg       Date: 5.02.05    Time: 17:11
 * Updated in $/tr/com/provus/util
 * yuvarlama hatasý düzeltildi
 *
 * *****************  Version 3  *****************
 * User: Korayg       Date: 7.12.04    Time: 18:40
 * Updated in $/tr/com/provus/util
 *
 * *****************  Version 2  *****************
 * User: Korayg       Date: 3.12.04    Time: 14:50
 * Updated in $/tr/com/provus/util
 *
 * *****************  Version 1  *****************
 * User: Erkanc       Date: 22.10.04   Time: 14:24
 * Created in $/tr/com/provus/util
 * Initial release of utility classes
 *
 ******************************************************************************/
package com.provus.util;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.HashMap;

/**
 * This class returns the wording for a given long number. i.e. 1456787456 to
 * BirMilyarDörtYüzElliAltýMilyonYediYüzSeksenYediBinDörtYüzElliAltý
 */
public class NumbertoWord {
	private static HashMap dct_oths = new HashMap();

	private static String dct_nums[] = { "S\u0131f\u0131r", "Bir", "\u0130ki",
			"Üç", "Dört", "Be\u015f", "Alt\u0131", "Yedi", "Sekiz", "Dokuz" };

	private static String dct_tens[] = { "", "On", "Yirmi", "Otuz",
			"K\u0131rk", "Elli", "Altm\u0131\u015f", "Yetmi\u015f", "Seksen",
			"Doksan" };

	private static String digitize(String value) {
		String result = new String();
		while (value.length() < 3)
			value = "0" + value;
		int first = new Integer(value.substring(0, 1)).intValue();
		int second = new Integer(value.substring(1, 2)).intValue();
		int third = new Integer(value.substring(2)).intValue();
		if (first > 1)
			result = result.concat(dct_nums[first]);
		if (first > 0)
			result = result.concat("Yüz");
		if (second > 0)
			result = result.concat(dct_tens[second]);
		if (third > 0)
			result = result.concat(dct_nums[third]);
		return result;
	}

	public static String parse(double value, double scale, String pattern) {
		BigDecimal dec = new BigDecimal(value);
		long integerPart = dec.longValue();
		BigDecimal decFra = new BigDecimal(value - integerPart);
		decFra = decFra.setScale(2, BigDecimal.ROUND_HALF_UP);
		double fraction = decFra.doubleValue();
		for (int ndx = 0; ndx < scale; ndx++)
			fraction *= 10;
		BigDecimal decFra2 = new BigDecimal((double) fraction);
		// 231.47 sayýsýnýn fractionPart'ý .499999999999 þeklinde gelip 49'a
		// çevriliyordu.
		// bunu engellemek için 0 fraction'a göre yukarý yuvarlayýp, decFra2 ye
		// alýp
		long fractionPart = (long) decFra2
				.setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
		String parts[] = new String[2];
		parts[0] = parse(integerPart);
		parts[1] = parse(fractionPart);
		String result = MessageFormat.format(pattern, parts);
		return result;
	}

	public static String parse(long value) {
		dct_oths.put(new Integer(1), "");
		dct_oths.put(new Integer(100), "Yüz");
		dct_oths.put(new Integer(1000), "Bin");
		dct_oths.put(new Integer(1000000), "Milyon");
		dct_oths.put(new Integer(1000000000), "Milyar");
		String strValue = Long.toString(value);
		String result = new String();
		String digits = new String();
		int i = 3;
		int strValueLen = strValue.length();
		int j = strValueLen;
		while (i < strValueLen) {
			digits = digits.concat(strValue.substring(strValueLen - i, j));
			j = strValueLen - i;
			i += 3;
		}
		digits = digits.concat(strValue.substring(0, j));
		i = 1;
		String digitizeResult;
		for (int ndx = 0; ndx < digits.length(); ndx += 3) {
			/*
			 * result = (digitizeResult = digitize(digits.substring(ndx, ((ndx +
			 * 3) < digits.length()?(ndx + 3):digits.length()))) ) +
			 * ((digitizeResult.equals(""))?"":(String)dct_oths.get(new
			 * Integer(i))) + result;
			 */
			String part;
			if ((ndx + 3) < digits.length())
				part = digits.substring(ndx, (ndx + 3));
			else
				part = digits.substring(ndx, digits.length());
			digitizeResult = digitize(part);
			if (!digitizeResult.equals("")) {
				if (ndx == 3 && digitizeResult.equals("Bir")) // 1675 gibi bir
					// say?n?n
					// "BirBin"
					// ?eklinde
					// yaz?lmas?n?
					// engelliyoruz
					result = (String) dct_oths.get(new Integer(i)) + result;
				else
					result = digitizeResult
							+ (String) dct_oths.get(new Integer(i)) + result;
			}
			i *= 1000;
		}

		return result;
	}

	public static void main(String[] args) {
		System.out.println(NumbertoWord.parse(0.10, 2, "{0};{1} Ykr"));
		System.out.println(NumbertoWord.parse(1675));
		System.out.println(NumbertoWord.parse(1901));
		System.out.println(NumbertoWord.parse(1001234));
		System.out.println(NumbertoWord.parse(9440559.0, 2, "{0};{1}"));
		System.out.println(NumbertoWord.parse(2));
		System.out.println(NumbertoWord.parse(3));
		System.out.println(NumbertoWord.parse(4));
		System.out.println(NumbertoWord.parse(5));
		System.out.println(NumbertoWord.parse(6));
		System.out.println(NumbertoWord.parse(7));
		System.out.println(NumbertoWord.parse(8));
		System.out.println(NumbertoWord.parse(9));
		System.out.println(NumbertoWord.parse(10));
		System.out.println(NumbertoWord.parse(20));
		System.out.println(NumbertoWord.parse(30));
		System.out.println(NumbertoWord.parse(38));
		System.out.println(NumbertoWord.parse(99));
		System.out.println(NumbertoWord.parse(100));
		System.out.println(NumbertoWord.parse(1000));
		System.out.println(NumbertoWord.parse(10000));
		System.out.println(NumbertoWord.parse(100000));
		System.out.println(NumbertoWord.parse(1000000));
		System.out.println(NumbertoWord.parse(1100));
		System.out.println(NumbertoWord.parse(1100));
		System.out.println(NumbertoWord.parse(2100));
		System.out.println(NumbertoWord.parse(1456787456));
		System.out.println(NumbertoWord.parse(1000));

	}
}
