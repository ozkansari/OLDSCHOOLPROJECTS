package com.provus.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import com.provus.util.StringUtils;

/**
 * Test class for com.provus.util.StringUtils
 * 
 * @author
 * 
 */
public class StringUtilsTest extends TestCase {
	protected Collection<String> list;

	protected Collection<String> emptyElementList;

	@Override
	protected void setUp() {
		list = new ArrayList<String>();
		list.add("koray");
		list.add("gecici");
		list.add("test");

		emptyElementList = new ArrayList<String>();
		emptyElementList.add("koray");
		emptyElementList.add(null);
		emptyElementList.add("test");
	}

	/**
	 * Tests
	 * tr.com.provus.common.test.StringUtilsTest.testCollectionToDelimitedString()
	 */
	public void testCollectionToDelimitedString() {
		String s = StringUtils.collectionToDelimitedString(list, ",");
		assertEquals(s, "koray,gecici,test");

		s = StringUtils.collectionToDelimitedString(emptyElementList, ",");
		assertEquals(s, "koray,null,test");

	}

	/**
	 * Tests
	 * tr.com.provus.common.test.StringUtilsTest.testStringArrayToDelimitedString()
	 */
	public void testStringArrayToDelimitedString() {
		String ar[] = new String[] { "koray", "gecici", "test" };
		String s = StringUtils.stringArrayToDelimitedString(ar, ",");
		assertEquals(s, "koray,gecici,test");

		ar = new String[] { "koray", null, "test" };
		s = StringUtils.stringArrayToDelimitedString(ar, ",");
		assertEquals(s, "koray,null,test");
	}

	/**
	 * Tests tr.com.provus.common.test.StringUtilsTest.testConcatSummary()
	 */
	public void testConcatSummary() {
		String s = StringUtils.concatSummary("35", "IZMIR");
		assertEquals(s, "35(IZMIR)");
	}

	public void testContainsOnly() {
		assertTrue(StringUtils.containsOnly("+905325838379", "+1234567890"
				.toCharArray()));
		assertFalse(StringUtils.containsOnly(" 905325838379", "+1234567890"
				.toCharArray()));
	}

	public void testReplaceParameter() {
		String str = StringUtils.replaceParameter("Mr #{name};", "name",
				"Koray");
		assertEquals("Mr Koray;", str);
	}
	
	public void testReplaceParametersNoVariable() {
		Map <String,String>paramMap = new HashMap<String,String>();
		paramMap.put("abc", "123");
		paramMap.put("name1", "Koray");
		paramMap.put("name2", "Yusuf");
		String str = StringUtils.replaceParameters("Mr #{name1} #{name3};", paramMap);
		assertEquals("Mr Koray #{name3};", str);
	}
	
	public void testLTrim() {
		String a = StringUtils.ltrim("000ABC", '0');
		assertEquals("ABC", a);
	}
	
	public void testSplitAsList(){
		List<String> list = StringUtils.splitAsList("|#part1|#(|)char in delim |#(#)char in delim |#part4|# ", "|#");
		assertEquals(6, list.size());
		assertEquals("", list.get(0));
		assertEquals("part1", list.get(1));
		assertEquals("(|)char in delim ", list.get(2));
		assertEquals("(#)char in delim ", list.get(3));
		assertEquals("part4", list.get(4));
		assertEquals(" ", list.get(5));
	}
	
	public void testSplit(){
		String[] array = StringUtils.split("|#part1|#(|)char in delim |#(#)char in delim |#part4|# ", "|#");
		assertEquals(6, array.length);
		assertEquals("", array[0]);
		assertEquals("part1", array[1]);
		assertEquals("(|)char in delim ", array[2]);
		assertEquals("(#)char in delim ", array[3]);
		assertEquals("part4", array[4]);
		assertEquals(" ", array[5]);
	}
	
	public void testClearUnSupportedFileNameChars(){
		String str = StringUtils.clearUnSupportedFileNameChars("*f\\i/l|e<n>a?m:e");
		assertEquals(str, "filename");
	}
}
