package com.provus.util;

import junit.framework.TestCase;

import org.junit.Assert;

import com.provus.util.TurkishLocaleHelper;

/**
 * Test class for com.provus.util.TurkishLocaleHelper
 * 
 * @author dilaras
 * 
 */
public class TurkishLocaleHelperTest extends TestCase {

	// "çÇþÞðÐiÝýI"
	private final static String testString = "çÇ" + TurkishLocaleHelper.TR_s
			+ TurkishLocaleHelper.TR_S + TurkishLocaleHelper.TR_g
			+ TurkishLocaleHelper.TR_G + "i" + TurkishLocaleHelper.TR_I
			+ TurkishLocaleHelper.TR_i + "I";

	/**
	 * Tests
	 * com.provus.util.TurkishLocaleHelper.convertToNonTR(String)
	 */
	public void testConvertToNonTR() {
		String funcResult = TurkishLocaleHelper
				.convertToNonTR(TurkishLocaleHelperTest.testString);

		// çÇþÞðÐiÝýI
		String result = "cCsSgGiIiI";

		Assert.assertEquals(funcResult, result);
	}

	/**
	 * Tests
	 * com.provus.util.TurkishLocaleHelper.convertToWindowsTR(String)
	 */
	public void testConvertToWindowsTR() {
		String funcResult = TurkishLocaleHelper
				.convertToWindowsTR(TurkishLocaleHelperTest.testString);

		Assert.assertEquals(funcResult, TurkishLocaleHelperTest.testString);
	}

	/**
	 * Tests
	 * com.provus.util.TurkishLocaleHelper.convertUpperTRToLowerEN(String)
	 */
	public void testConvertToUpperTRToLowerEN() {
		String funcResult = TurkishLocaleHelper
				.convertUpperTRToLowerEN(TurkishLocaleHelperTest.testString);

		// "çcþsðgiiýI"
		String result = "çc" + TurkishLocaleHelper.TR_s + "s"
				+ TurkishLocaleHelper.TR_g + "g" + "ii"
				+ TurkishLocaleHelper.TR_i + "I";

		Assert.assertEquals(funcResult, result);
	}

	/**
	 * Tests
	 * com.provus.util.TurkishLocaleHelper.makeUpperNonTR(String)
	 */
	public void testMakeUpperNonTR() {
		String funcResult = TurkishLocaleHelper
				.makeUpperNonTR(TurkishLocaleHelperTest.testString);

		// çÇþÞðÐiÝýI
		String result = "CCSSGGIIII";

		Assert.assertEquals(funcResult, result);
	}
	
	public void testConvertTrToUTF8() {
		String trStr = TurkishLocaleHelper.TURKISH_APLHABET_UPPER;
		String encodedStr = TurkishLocaleHelper.convertTrToUTF8(trStr);
		assertEquals(encodedStr, "ABC&#199;DEFG&#286;HI&#304;JKLMNO&#214;PRS&#350;TU&#220;VYZ");
		
		trStr = TurkishLocaleHelper.TURKISH_APLHABET_LOWER;
		encodedStr = TurkishLocaleHelper.convertTrToUTF8(trStr);
		assertEquals(encodedStr, "abc&#231;defg&#287;h&#305;ijklmno&#246;prs&#351;tu&#252;vyz");
	}
}
