package com.provus.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.Assert;
import junit.framework.TestCase;
import com.provus.util.DateHelper;

/**
 * Test class for com.provus.util.DateHelper
 * 
 * @author dilaras
 * 
 */
public class DateHelperTest extends TestCase {

	/**
	 * Tests com.provus.util.DateHelper.addDays(Date, int)
	 */
	public void testAddDays() {
		Date now = new Date();
		Date tomorrow = DateHelper.addDays(now, 1);

		long getTimeNow = now.getTime();
		long getTimeTomorrow = tomorrow.getTime();
		long diff = getTimeTomorrow - getTimeNow;
		long millisInADay = 86400000;

		// Difference should be a day
		Assert.assertEquals(diff, millisInADay);
	}

	/**
	 * Yil basinda gunu geri cekince hata olmustu. 1 ocak da 2 gun geri alarak
	 * test ediyoruz
	 */
	public void testAddDaysBeginningOfYear() {
		Date now = new GregorianCalendar(2008, 0, 1, 9, 0, 0).getTime();
		Date date = DateHelper.addDays(now, -2);

		long getTimeNow = now.getTime();
		long getTime = date.getTime();
		long diff = getTimeNow - getTime;
		long millisInADay = 86400000;

		// Difference should be a day
		Assert.assertEquals(diff, millisInADay * 2);
	}

	/**
	 * Yil sonunda ertesi sabah i bulurken hata olmustu. 31 aralik da ertesi
	 * sabah i bulmaya calisiyoruz
	 */
	public void testAddDaysEndOfYear() {
		Date now = new GregorianCalendar(2007, 11, 31, 10, 0, 0).getTime();
		Date tomorrow = DateHelper.setTimePart(now, 9, 0, 1); // o gün sabah
		if (tomorrow.getTime() < now.getTime()) {
			tomorrow = DateHelper.setTimePart(DateHelper.addDays(now, 1), 9, 0,
					1); // ertesi gün sabah
		}
		assertEquals(new GregorianCalendar(2008, 0, 1, 9, 0, 1).getTime(),
				tomorrow);
	}

	/**
	 * Tests com.provus.util.DateHelper.addMinute(Date, int)
	 */
	public void testAddMinute() {
		Date now = new Date();
		Date aMinuteFromNow = DateHelper.addMinute(now, 1);

		long getTimeNow = now.getTime();
		long getTimeAMinuteFromNow = aMinuteFromNow.getTime();
		long diff = getTimeAMinuteFromNow - getTimeNow;
		long millisInAMinute = 60000;

		// Difference should be a minute
		Assert.assertEquals(diff, millisInAMinute);
	}

	/**
	 * Tests com.provus.util.DateHelper.addSecond(Date, int)
	 */
	public void testAddSecond() {
		Date now = new Date();
		Date aSecondFromNow = DateHelper.addSecond(now, 1);

		long getTimeNow = now.getTime();
		long getTimeASecondFromNow = aSecondFromNow.getTime();
		long diff = getTimeASecondFromNow - getTimeNow;
		long millisInASecond = 1000;

		// Difference should be a second
		Assert.assertEquals(diff, millisInASecond);
	}

	/*
	 * public void testAddSecond() { Date funcResult =
	 * DateHelper.dateFromString("10/10/2007", "10:10:10"); Date now = new
	 * Date(); Date aSecondFromNow = DateHelper.addSecond(now, 1);
	 * 
	 * long getTimeNow = now.getTime(); long getTimeASecondFromNow =
	 * aSecondFromNow.getTime(); long diff = getTimeASecondFromNow - getTimeNow;
	 * long millisInASecond = 1000; // Difference should be a second
	 * Assert.assertEquals(diff, millisInASecond); }
	 */

	/**
	 * Tests com.provus.util.DateHelper.beginingOfDay(Date)
	 */
	public void testBeginningOfDay() {
		Date today = new Date();
		Date beginingOfToday = DateHelper.beginingOfDay(today);

		Calendar beginingOfThisDay = new GregorianCalendar();
		beginingOfThisDay.setTime(today);
		beginingOfThisDay.set(Calendar.HOUR_OF_DAY, 0);
		beginingOfThisDay.set(Calendar.MINUTE, 0);
		beginingOfThisDay.set(Calendar.SECOND, 0);
		beginingOfThisDay.set(Calendar.MILLISECOND, 0);

		long getTimeBeginingOfToday = beginingOfToday.getTime();
		long getTimeBeginingOfThisDay = beginingOfThisDay.getTime().getTime();

		Assert.assertEquals(getTimeBeginingOfToday, getTimeBeginingOfThisDay);
	}

	/**
	 * Tests com.provus.util.DateHelper.calendarFromString(String,
	 * String)
	 */
	public void testCalendarFromString() {
		Calendar test = new GregorianCalendar();
		test.set(2007, 9, 10, 10, 10, 10);
		test.set(Calendar.MILLISECOND, 0);
		Calendar funcResult = DateHelper.calendarFromString("10/10/2007",
				"10:10:10");

		Assert.assertEquals(funcResult, test);
	}

	/**
	 * Tests com.provus.util.DateHelper.dateFromString(String, String)
	 */
	public void testDateFromString() {
		Date funcResult = DateHelper.dateFromString("10/10/2007", "10:10:10");

		Calendar test = new GregorianCalendar();
		test.set(2007, 9, 10, 10, 10, 10);
		test.set(Calendar.MILLISECOND, 0);
		Date testDate = test.getTime();

		Assert.assertEquals(funcResult, testDate);

	}

	/**
	 * Tests com.provus.util.DateHelper.endOfDay(Date)
	 */
	public void testEndOfDay() {
		Date today = new Date();
		Date endOfToday = DateHelper.endOfDay(today);

		Calendar endOfThisDay = new GregorianCalendar();
		endOfThisDay.setTime(today);
		endOfThisDay.roll(Calendar.DAY_OF_MONTH, 1);
		endOfThisDay.set(Calendar.HOUR_OF_DAY, 0);
		endOfThisDay.set(Calendar.MINUTE, 0);
		endOfThisDay.set(Calendar.SECOND, 0);
		endOfThisDay.set(Calendar.MILLISECOND, 0);

		long getTimeBeginingOfToday = endOfToday.getTime();
		long getTimeBeginingOfThisDay = endOfThisDay.getTime().getTime();

		Assert.assertEquals(getTimeBeginingOfToday, getTimeBeginingOfThisDay);
	}

	/**
	 * Tests com.provus.util.DateHelper.getFormatted(Date, String)
	 */
	public void testGetFormatted() {
		Calendar calendar = new GregorianCalendar();

		// "08/17/2007"
		calendar.set(Calendar.YEAR, 2007);
		calendar.set(Calendar.MONTH, 7);
		calendar.set(Calendar.DAY_OF_MONTH, 17);
		calendar.set(Calendar.HOUR_OF_DAY, 11);
		calendar.set(Calendar.MINUTE, 05);
		calendar.set(Calendar.SECOND, 15);
		calendar.set(Calendar.MILLISECOND, 0);

		String format = "MM/dd/yyyy";
		String funcResult = DateHelper.getFormatted(calendar.getTime(), format);

		String formattedDate = "08/17/2007";

		Assert.assertEquals(funcResult, formattedDate);

	}

	/**
	 * Tests isTimeBetween(Date, int, int)
	 */
	public void testIsTimeBetween() {
		Calendar now = new GregorianCalendar();
		now.setTime(new Date());

		boolean funcResult = DateHelper.isTimeBetween(now.getTime(), 070000,
				180000);

		Assert.assertEquals(funcResult, true);
	}

	/**
	 * Test com.provus.util.DateHelper.isWeekend(Date)
	 */
	public void testIsWeekend() {
		Calendar thisDayIsCertainlyAWeekend = new GregorianCalendar();
		thisDayIsCertainlyAWeekend.set(Calendar.YEAR, 2007);
		thisDayIsCertainlyAWeekend.set(Calendar.MONTH, 7);
		thisDayIsCertainlyAWeekend.set(Calendar.DAY_OF_MONTH, 18);

		boolean funcResult = DateHelper.isWeekend(thisDayIsCertainlyAWeekend
				.getTime());

		Assert.assertEquals(funcResult, true);
	}

	/**
	 * Tests com.provus.util.DateHelper.secondsPassed(Date, Date)
	 */
	public void testSecondsPassed() {
		Date now = new Date();

		Date tenSecondsFromNow = new Date();
		tenSecondsFromNow.setTime(now.getTime() + 10000);

		// TODO : tekrar bak ters mi olmasý gerekir???!?
		long secondsPassed = DateHelper.secondsPassed(tenSecondsFromNow, now);

		Assert.assertEquals(secondsPassed, 10);
	}

	/**
	 * Tests com.provus.util.DateHelper.setTimePart(Date, int, int,
	 * int)
	 */
	public void testSetTimePart() {
		Date nowDate = new Date();
		Calendar now = new GregorianCalendar();
		now.setTime(nowDate);
		now.set(Calendar.HOUR_OF_DAY, 10);
		now.set(Calendar.MINUTE, 10);
		now.set(Calendar.SECOND, 10);
		now.set(Calendar.MILLISECOND, 0);

		Date funcResult = DateHelper.setTimePart(nowDate, 10, 10, 10);

		Assert.assertEquals(funcResult, now.getTime());

	}

	/**
	 * Tests com.provus.util.DateHelper.setTimePart(Date, int, int,
	 * int, int)
	 */
	public void testSetTimePart_() {
		Date nowDate = new Date();
		Calendar now = new GregorianCalendar();
		now.setTime(nowDate);
		now.set(Calendar.HOUR_OF_DAY, 10);
		now.set(Calendar.MINUTE, 10);
		now.set(Calendar.SECOND, 10);
		now.set(Calendar.MILLISECOND, 10);

		Date funcResult = DateHelper.setTimePart(nowDate, 10, 10, 10, 10);

		Assert.assertEquals(funcResult, now.getTime());
	}

	/**
	 * Tests com.provus.util.DateHelper.setTimePart(Date, String,
	 * Integer)
	 */
	public void testSetTimePart__() {
		Date now = new Date();
		String timeString = "120202";
		Date funcResult = DateHelper.setTimePart(now, timeString,
				new Integer(2));

		Calendar postNowCalculated = new GregorianCalendar();
		postNowCalculated.set(Calendar.HOUR_OF_DAY, 12);
		postNowCalculated.set(Calendar.MINUTE, 2);
		postNowCalculated.set(Calendar.SECOND, 2);
		postNowCalculated.set(Calendar.MILLISECOND, 2);

		Assert.assertEquals(funcResult, postNowCalculated.getTime());
	}

	/**
	 * Tests com.provus.util.DateHelper.setTimePart(Date, int)
	 */
	public void testSetTimePartHourMinuteSecond() {
		Date nowDate = new Date();
		Calendar now = new GregorianCalendar();
		now.setTime(nowDate);
		now.set(Calendar.HOUR_OF_DAY, 9);
		now.set(Calendar.MINUTE, 12);
		now.set(Calendar.SECOND, 34);
		now.set(Calendar.MILLISECOND, 0);

		Date funcResult = DateHelper.setTimePart(nowDate, 91234);

		Assert.assertEquals(funcResult, now.getTime());

	}

	/**
	 * Tests com.provus.util.DateHelper.setTimePart(Date, int)
	 */
	public void testSetTimePartHourMinuteSecond2() {
		Date nowDate = new Date();
		Calendar now = new GregorianCalendar();
		now.setTime(nowDate);
		now.set(Calendar.HOUR_OF_DAY, 14);
		now.set(Calendar.MINUTE, 56);
		now.set(Calendar.SECOND, 33);
		now.set(Calendar.MILLISECOND, 0);

		Date funcResult = DateHelper.setTimePart(nowDate, 145633);

		Assert.assertEquals(funcResult, now.getTime());

	}

	/**
	 * Tests com.provus.util.DateHelper.endOfMonth(Date)
	 */
	public void testEndOfMonth() {
		Calendar thisMonth = new GregorianCalendar();
		thisMonth.set(Calendar.YEAR, 2007);
		thisMonth.set(Calendar.MONTH, 5);
		thisMonth.set(Calendar.DAY_OF_MONTH, 30);
		thisMonth.set(Calendar.HOUR_OF_DAY, 12);
		thisMonth.set(Calendar.MINUTE, 2);
		thisMonth.set(Calendar.SECOND, 2);
		thisMonth.set(Calendar.MILLISECOND, 2);

		Date funcResult = DateHelper.endOfMonth(thisMonth.getTime());

		Assert.assertEquals(thisMonth.getTime(), funcResult);
	}

	/**
	 * Tests com.provus.util.DateHelper.endOfMonth(int, int)
	 */
	public void testEndOfMonth_() {
		Calendar thisMonth = new GregorianCalendar();
		thisMonth.set(Calendar.YEAR, 2007);
		thisMonth.set(Calendar.MONTH, 5);
		thisMonth.set(Calendar.DAY_OF_MONTH, 30);

		Date funcResult = DateHelper.endOfMonth(6, 2007);

		assertEquals(funcResult, thisMonth.getTime());
	}
	
	public void testBeginingOfMonth_() {
		Calendar thisMonth = new GregorianCalendar();
		thisMonth.set(Calendar.YEAR, 2009);
		thisMonth.set(Calendar.MONTH, 10);
		thisMonth.set(Calendar.DAY_OF_MONTH, 1);

		Date funcResult = DateHelper.beginingOfMonth(10, 2009);

		assertEquals(funcResult, thisMonth.getTime());
	}
	
	public void testBeginingOfMonth() {
		Calendar thisMonth = new GregorianCalendar();
		thisMonth.set(Calendar.YEAR, 2009);
		thisMonth.set(Calendar.MONTH, 10);
		thisMonth.set(Calendar.DAY_OF_MONTH, 1);

		Date funcResult = DateHelper.beginingOfMonth(new Date());

		assertEquals(funcResult, thisMonth.getTime());
	}

	public void testFindMin() {
		Calendar cal1 = new GregorianCalendar(2007, 01, 02, 12, 0, 0);
		Calendar cal2 = new GregorianCalendar(2006, 01, 02, 12, 0, 0);
		Long max = DateHelper.findMin(cal1.getTime(), cal2.getTime(), null,
				null);
		assertEquals(new Long(cal2.getTime().getTime()), max);
	}

	public void testFindMinDate() {
		Calendar cal1 = new GregorianCalendar(2007, 01, 02, 12, 0, 0);
		Calendar cal2 = new GregorianCalendar(2006, 01, 02, 12, 0, 0);
		Date maxDate = DateHelper.findMinDate(cal1.getTime(), cal2.getTime(),
				null, null);
		assertEquals(cal2.getTime(), maxDate);
	}

	public void testFindMax() {
		Calendar cal1 = new GregorianCalendar(2007, 01, 02, 12, 0, 0);
		Calendar cal2 = new GregorianCalendar(2006, 01, 02, 12, 0, 0);
		Long max = DateHelper.findMax(cal1.getTime(), cal2.getTime(), null,
				null);
		assertEquals(new Long(cal1.getTime().getTime()), max);
	}

	public void testFindMaxDate() {
		Calendar cal1 = new GregorianCalendar(2007, 01, 02, 12, 0, 0);
		Calendar cal2 = new GregorianCalendar(2006, 01, 02, 12, 0, 0);
		Date maxDate = DateHelper.findMaxDate(cal1.getTime(), cal2.getTime(),
				null, null);
		assertEquals(cal1.getTime(), maxDate);
	}

	/**
	 * Tests com.provus.util.DateHelper.addMonths(Date, int)
	 */
	public void testAddMonths() {
		Date now = new GregorianCalendar(2008, 0, 1, 9, 0, 0).getTime();
		Date date = DateHelper.addMonths(now, 1);

		long getTimeNow = now.getTime();
		long getTime = date.getTime();
		long diff = getTime - getTimeNow;
		long millisInADay = 86400000;

		// Difference should be one month
		Assert.assertEquals(diff, millisInADay * 31);
	}

	/**
	 * Yil basinda gunu geri cekince hata olmustu. ayni durumu ay icin deniyoruz
	 */
	public void testAddMonthsBeginningOfYear() {
		Date now = new GregorianCalendar(2008, 0, 1, 9, 0, 0).getTime();
		Date date = DateHelper.addMonths(now, -1);

		long getTimeNow = now.getTime();
		long getTime = date.getTime();
		long diff = getTimeNow - getTime;
		long millisInADay = 86400000;

		// Difference should be one month
		Assert.assertEquals(diff, millisInADay * 31);
	}

	/**
	 * Tests com.provus.util.DateHelper.addYears(Date, int)
	 */
	public void testAddYears() {
		Date now = new GregorianCalendar(2008, 0, 1, 9, 0, 0).getTime();
		Date date = DateHelper.addYears(now, -1);

		long getTimeNow = now.getTime();
		long getTime = date.getTime();
		long diff = getTimeNow - getTime;
		long millisInADay = 86400000;

		// Difference should be one year
		Assert.assertEquals(diff, millisInADay * 365);
	}
}
