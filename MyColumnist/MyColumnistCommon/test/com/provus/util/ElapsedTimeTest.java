package com.provus.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.Assert;
import junit.framework.TestCase;
import com.provus.util.ElapsedTime;

/**
 * Test class for com.provus.util.ElapsedTime
 * 
 * @author dilaras
 * 
 */
public class ElapsedTimeTest extends TestCase {

	/**
	 * Tests com.provus.util.ElapsedTime.toString()
	 */
	public void testToString() {
		Date today = new Date();

		Calendar startDate = new GregorianCalendar();
		startDate.setTime(today);
		startDate.set(Calendar.HOUR_OF_DAY, 7);
		startDate.set(Calendar.MINUTE, 15);
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);

		Calendar endDate = new GregorianCalendar();
		endDate.setTime(today);
		endDate.roll(Calendar.DAY_OF_MONTH, 1);
		endDate.set(Calendar.HOUR_OF_DAY, 9);
		endDate.set(Calendar.MINUTE, 45);
		endDate.set(Calendar.SECOND, 0);
		endDate.set(Calendar.MILLISECOND, 0);

		ElapsedTime elapsedTime = new ElapsedTime(startDate.getTimeInMillis(),
				endDate.getTimeInMillis());
		String funcResult = elapsedTime.toString();

		String result = "1 day, 2 hours, 30 mins";

		Assert.assertEquals(funcResult, result);

	}
}
