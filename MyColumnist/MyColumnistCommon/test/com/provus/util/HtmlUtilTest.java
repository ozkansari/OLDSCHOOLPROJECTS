package com.provus.util;

import junit.framework.TestCase;
import com.provus.util.HtmlUtil;
import com.provus.util.TurkishLocaleHelper;

public class HtmlUtilTest extends TestCase {
	public void testHtmlEntityEncode() {
		String trStr = TurkishLocaleHelper.TURKISH_APLHABET_UPPER;
		String encodedStr = HtmlUtil.htmlEntityEncode(trStr);
		assertEquals(encodedStr, "ABC&#199;DEFG&#286;HI&#304;JKLMNO&#214;PRS&#350;TU&#220;VYZ");
		
		trStr = TurkishLocaleHelper.TURKISH_APLHABET_LOWER;
		encodedStr = HtmlUtil.htmlEntityEncode(trStr);
		assertEquals(encodedStr, "abc&#231;defg&#287;h&#305;ijklmno&#246;prs&#351;tu&#252;vyz");
	}
}
