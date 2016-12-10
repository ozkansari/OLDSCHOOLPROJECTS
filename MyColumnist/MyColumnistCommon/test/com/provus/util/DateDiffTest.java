package com.provus.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.Assert;
import junit.framework.TestCase;
import com.provus.util.DateDiff;

/**
 * Test class for com.provus.util.DateDiff
 * 
 * @author dilaras
 * 
 */
public class DateDiffTest extends TestCase {

	/**
	 * Tests com.provus.util.DateDiff.diff(Calendar, Calendar)
	 */
	public void testDiff() {
		// First calendar object
		Calendar firstCal = new GregorianCalendar();
		firstCal.set(2008, 06, 15, 10, 15, 30);

		// Second calendar object
		Calendar secondCal = new GregorianCalendar();
		secondCal.set(2009, 07, 16, 11, 16, 31);

		DateDiff date = new DateDiff();
		date.diff(firstCal, secondCal);

		// 397 days
		Assert.assertEquals(date.getDays(), 397);
		// 1 hour
		Assert.assertEquals(date.getHours(), 1);
		// 1 minute
		Assert.assertEquals(date.getMinutes(), 1);
		// 1 second
		Assert.assertEquals(date.getSeconds(), 1);

	}
}
