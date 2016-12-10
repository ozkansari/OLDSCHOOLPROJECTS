package com.provus.util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import com.provus.util.ArrayUtil;

/**
 * Test class for com.provus.util.ArrayUtil
 * 
 * @author dilaras
 * 
 */
public class ArrayUtilsTest extends TestCase {

	/**
	 * Tests com.provus.util.ArrayUtil isEmpty(Object[] array) method
	 */
	public void testIsEmpty() {
		Object[] test = null;

		// Test null
		boolean funcResult = ArrayUtil.isEmpty(test);
		Assert.assertEquals(funcResult, true);

		// Test array with 0 size
		List<Object> list = new ArrayList<Object>();
		test = list.toArray();
		funcResult = ArrayUtil.isEmpty(test);
		Assert.assertEquals(funcResult, true);

		// Test array with elements
		list.add(new Object());
		test = list.toArray();
		funcResult = ArrayUtil.isEmpty(test);
		Assert.assertEquals(funcResult, false);
	}
	
	public void testToStringArray() {
		String ar [] = ArrayUtil.toStringArray(new Object[] {new Long(1), new Long(2)});
		assertEquals(ar[0], "1");
		assertEquals(ar[1], "2");
	}

	public void testToStringArray2() {
		List list = new ArrayList();
		list.add("1");
		list.add("2");
		String ar [] = ArrayUtil.toStringArray(list);
		assertEquals(ar[0], "1");
		assertEquals(ar[1], "2");
	}
}
