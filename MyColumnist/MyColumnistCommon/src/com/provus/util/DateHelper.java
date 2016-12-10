package com.provus.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * A helper class for date functions
 * 
 * @author korayg
 * 
 */
public class DateHelper {

	public DateHelper() {
		// Constructor
	}

	/**
	 * Sets the time field of given date to 00:00:00:00. Date field stays the
	 * same.
	 * 
	 * @param date
	 * @return
	 */
	public static Date beginingOfDay(Date date) {
		GregorianCalendar beginingOfDay = new GregorianCalendar();
		beginingOfDay.setTime(date);
		// 24 saat seklindeki saat set ediliyor
		beginingOfDay.set(Calendar.HOUR_OF_DAY, 0);
		beginingOfDay.set(Calendar.SECOND, 0);
		beginingOfDay.set(Calendar.MINUTE, 0);
		beginingOfDay.set(Calendar.MILLISECOND, 0);
		// System.out.println(beginingOfDay.getTime());
		return beginingOfDay.getTime();
	}

	/**
	 * 
	 * Sets the time field of given date to 24:00:00:00. Date field rolls 1 day
	 * up.
	 * 
	 * @param date
	 * @return
	 */
	public static Date endOfDay(Date date) {
		GregorianCalendar endOfDay = new GregorianCalendar();
		endOfDay.setTime(date);
		// 24 saat seklindeki saat set ediliyor
		endOfDay.set(Calendar.HOUR_OF_DAY, 24);
		endOfDay.set(Calendar.SECOND, 0);
		endOfDay.set(Calendar.MINUTE, 0);
		endOfDay.set(Calendar.MILLISECOND, 0);
		// System.out.println(endOfDay.getTime());
		return endOfDay.getTime();
	}

	/**
	 * 
	 * Sets the time field of given date to 11:59:59.
	 * 
	 * @param date
	 * @return
	 */
	public static Date midOfDay(Date date) {
		GregorianCalendar midOfDay = new GregorianCalendar();
		midOfDay.setTime(date);
		// 24 saat seklindeki saat set ediliyor
		midOfDay.set(Calendar.HOUR_OF_DAY, 11);
		midOfDay.set(Calendar.SECOND, 59);
		midOfDay.set(Calendar.MINUTE, 59);
		midOfDay.set(Calendar.MILLISECOND, 0);
		return midOfDay.getTime();
	}

	/**
	 * Sets the time part of given date with the given int type parameters
	 * 
	 * @param date
	 * @param hour
	 *            24 hour format
	 * @param minute
	 * @param second
	 * @param millisecond
	 * @return
	 */
	public static Date setTimePart(Date date, int hour, int minute, int second,
			int millisecond) {
		GregorianCalendar endOfDay = new GregorianCalendar();
		endOfDay.setTime(date);
		// 24 saat seklindeki saat set ediliyor
		endOfDay.set(Calendar.HOUR_OF_DAY, hour);
		endOfDay.set(Calendar.MINUTE, minute);
		endOfDay.set(Calendar.SECOND, second);
		endOfDay.set(Calendar.MILLISECOND, millisecond);
		return endOfDay.getTime();
	}

	/**
	 * Sets the time part of given date with the given int type parameters. Sets
	 * the millisecond to 0 automatically
	 * 
	 * @param date
	 *            24 hour format
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date setTimePart(Date date, int hour, int minute, int second) {
		return setTimePart(date, hour, minute, second, 0);
	}

	/**
	 * Sets the time part of given date with the given int type parameters. Sets
	 * the millisecond to 0 automatically
	 * 
	 * @param date
	 *            24 hour format
	 * @param hourMinuteSecond
	 *            ex: 91234 means: 09:12:34 (AM) ex: 145633 means: 14:56:33 (PM)
	 * @return
	 */
	public static Date setTimePart(Date date, int hourMinuteSecond) {
		int hour = hourMinuteSecond / 10000;
		int minute = (hourMinuteSecond - (10000 * hour)) / 100;
		int second = (hourMinuteSecond - (10000 * hour) - (100 * minute));
		return setTimePart(date, hour, minute, second, 0);
	}

	/**
	 * Returns true if the given date is either Saturday or Sunday
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isWeekend(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK);
		return ((dayOfWeek == GregorianCalendar.SATURDAY) || (dayOfWeek == GregorianCalendar.SUNDAY));
	}

	/**
	 * Returns the date formatted in the pattern given
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getFormatted(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String time = formatter.format(date);
		return time;
	}

	/**
	 * Adds given minute(s) to the given date
	 * 
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addMinute(Date date, int minute) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	/**
	 * Adds given second(s) to the given date
	 * 
	 * @param date
	 * @param second
	 * @return
	 */
	public static Date addSecond(Date date, int second) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, second);
		return calendar.getTime();
	}

	/**
	 * Adds given day(s) to the given date
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		// yilbasinda -15 gun yapinca yili azaltmiyor, yanlis sonucu veriyordu
		// cal.roll(Calendar.DAY_OF_YEAR, days);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	/**
	 * Adds given month(s) to the given date
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date addMonths(Date date, int months) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}

	/**
	 * Adds given year(s) to the given date
	 * 
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date addYears(Date date, int years) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);
		return cal.getTime();
	}

	/**
	 * Deprecated!
	 * 
	 * @param date
	 * @param time
	 * @return
	 */
	@Deprecated
	public static Date addTime(Date date, String time) {
		// TODO: Bu method ortak sinif'a gore duzenlenecek
		int hour = Integer.parseInt(time.substring(0, 2));
		int minute = Integer.parseInt(time.substring(2, 4));
		int second = Integer.parseInt(time.substring(4, 6));
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		return calendar.getTime();
	}

	/**
	 * Sets the time part of the given date by using pattern 'HHmmss', and sets
	 * the milliseconds.
	 * 
	 * @param date
	 * @param time
	 * @param milliseconds
	 * @return
	 */
	public static Date setTimePart(Date date, String time, Integer milliseconds) {
		if (time == null)
			return null;
		if (time.length() != 6)
			return null;

		int hour = Integer.parseInt(time.substring(0, 2));
		int minute = Integer.parseInt(time.substring(2, 4));
		int second = Integer.parseInt(time.substring(4, 6));

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		if (milliseconds != null)
			calendar.set(Calendar.MILLISECOND, milliseconds.intValue());

		return calendar.getTime();
	}

	/**
	 * Creates date object out of given date and time strings. Returns null,if
	 * there is a logical error like 35 in the DAY_OF_MONTH field or 15 in the
	 * MONTH_OF_YEAR field
	 * 
	 * @param dateString
	 *            <b>Pattern:</b>"dd.mm.yyyy" (could be any kind of separator
	 *            instead of (.))
	 * @param timeString
	 *            <b>Pattern:</b> "hh:mm:ss" (could be any kind of separator
	 *            instead of (.))
	 * @return
	 */
	public static Date dateFromString(String dateString, String timeString) {
		Calendar cal = calendarFromString(dateString, timeString);
		if (cal == null)
			return null;
		/*
		 * setLenient methodu ise, hatali bir deger verilirse (ay icin 15 gibi)
		 * asagida yakaliyoruz
		 */
		cal.setLenient(false);
		Date retDate = null;
		try {
			retDate = cal.getTime();
		} catch (IllegalArgumentException e) {
			/*
			 * ay, gun vs hatali bir deger verilmis ise, getTime methodu icinden
			 * bu exception firlatilir
			 */
			return null;
		}
		return retDate;
	}

	/**
	 * Returns calendar instance created from given date string. WARNING : sets
	 * millisecond field to 0 automatically !
	 * 
	 * @param dateString
	 * @param timeString
	 * @return
	 */
	public static Calendar calendarFromString(String dateString,
			String timeString) {
		if (dateString.length() < 10)
			return null;
		if (timeString.length() < 8)
			return null;
		GregorianCalendar cal = new GregorianCalendar();
		try {
			int day = Integer.parseInt(dateString.substring(0, 2));
			int month = Integer.parseInt(dateString.substring(3, 5));
			int year = Integer.parseInt(dateString.substring(6, 10));
			int hour = Integer.parseInt(timeString.substring(0, 2));
			int minute = Integer.parseInt(timeString.substring(3, 5));
			int second = Integer.parseInt(timeString.substring(6, 8));

			cal.set(Calendar.DAY_OF_MONTH, day);
			// 0=ocak, 1=subat tir
			cal.set(Calendar.MONTH, month - 1);
			cal.set(Calendar.YEAR, year);
			// 24 saat seklindeki saat set ediliyor
			cal.set(Calendar.HOUR_OF_DAY, hour);
			cal.set(Calendar.MINUTE, minute);
			cal.set(Calendar.SECOND, second);
			cal.set(Calendar.MILLISECOND, 0);

		} catch (Exception e) {
			return null;
		}
		return cal;
	}
	
	/**
	 * Returns the first day of the given month of the given year. Takes month
	 * and year parameters as integers.
	 * 
	 * @param month
	 * @param year
	 * @return
	 */
	public static Date beginingOfMonth(int month, int year) {
		Calendar cal = new GregorianCalendar();
		cal.set(year, month - 1, 1);
		//int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//cal.set(Calendar.DAY_OF_MONTH, maxDay);
		return cal.getTime();
	}
	
	/**
	 * Returns the first day of the given month of the given year. Takes date parameter
	 * 
	 * @param date
	 * @return
	 */
	public static Date beginingOfMonth(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * Returns the final day of the given month of the given year. Takes month
	 * and year parameters as integers.
	 * 
	 * @param month
	 * @param year
	 * @return
	 */
	public static Date endOfMonth(int month, int year) {
		Calendar cal = new GregorianCalendar();
		cal.set(year, month - 1, 1);
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, maxDay);
		return cal.getTime();
	}

	/**
	 * Returns the final day of the given month of the given year. Takes date
	 * object as parameter.
	 * 
	 * @param date
	 * @return
	 */
	public static Date endOfMonth(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, maxDay);
		return cal.getTime();
	}

	/**
	 * Returns the seconds passed as long between given two dates.
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */
	public static long secondsPassed(Date dateFrom, Date dateTo) {
		return (dateFrom.getTime() - dateTo.getTime()) / 1000;
	}

	/**
	 * Checks if the date is between the given hours inclusively
	 * 
	 * @param date
	 * @param hourFrom
	 *            (Eg:16:05:10 --> 160510)
	 * @param hourTo
	 *            (Eg:16:05:10 --> 160510)
	 * @return
	 */
	public static boolean isTimeBetween(Date date, int hourFrom, int hourTo) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR_OF_DAY) * 10000
				+ cal.get(Calendar.MINUTE) * 100 + cal.get(Calendar.SECOND);
		return hour >= hourFrom && hour <= hourTo;
	}

	/**
	 * sorts given date values. If date is null, return list does not contain
	 * this date value
	 * 
	 * @param dates
	 * @return
	 */
	public static List<Date> getNotNullDatesOrdered(Date... dates) {
		List<Date> list = new ArrayList<Date>();
		for (Date date : dates) {
			if (date != null)
				list.add(date);
		}
		Collections.sort(list);
		return list;
	}

	/**
	 * returns max date (null safe)
	 * 
	 * @param dates
	 * @return
	 */
	public static long findMax(Date... dates) {
		List<Date> list = getNotNullDatesOrdered(dates);
		if (list.size() == 0)
			return 0;
		return list.get(list.size() - 1).getTime();
	}

	/**
	 * returns max date (null safe)
	 * 
	 * @param dates
	 * @return
	 */
	public static Date findMaxDate(Date... dates) {
		List<Date> list = getNotNullDatesOrdered(dates);
		if (list.size() == 0)
			return null;
		return list.get(list.size() - 1);
	}

	/**
	 * returns min date (null safe)
	 * 
	 * @param dates
	 * @return
	 */
	public static long findMin(Date... dates) {
		List<Date> list = getNotNullDatesOrdered(dates);
		if (list.size() == 0)
			return 0;
		return list.get(0).getTime();
	}

	/**
	 * returns min date (null safe)
	 * 
	 * @param dates
	 * @return
	 */
	public static Date findMinDate(Date... dates) {
		List<Date> list = getNotNullDatesOrdered(dates);
		if (list.size() == 0)
			return null;
		return list.get(0);
	}

	public static boolean isDateBetween(Date beginDate, Date isBetween,
			Date endDate) {
		if (beginDate == null || endDate == null || isBetween == null)
			return false;
		long beginDateInMillis = beginDate.getTime();
		long endDateInMillis = endDate.getTime();
		long isBetweenInMillis = isBetween.getTime();
		if ((beginDateInMillis <= isBetweenInMillis)
				&& (isBetweenInMillis <= endDateInMillis))
			return true;
		return false;
	}

	@Deprecated
	public static String generateTurkishFormat(Date date, boolean useDay,
			boolean shortYear) {
		return generateDateStrInTurkishFormat(date, useDay, shortYear);
	}

	public static String generateDateStrInTurkishFormat(Date date, boolean useDay,
			boolean shortYear) {
		return generateDateStrInTurkishFormat(date, "-", useDay, shortYear);
	}

	@SuppressWarnings("deprecation")
	public static String generateDateStrInTurkishFormat(Date date, String delimeter,
			boolean useDay, boolean shortYear) {
		StringBuilder dateBuilder = new StringBuilder();

		// gün ekleniyor
		if (useDay) {
			dateBuilder.append(StringUtils.leftPadded(String.valueOf(date
					.getDate()), 2));
			dateBuilder.append(delimeter);
		}

		// ay ekleniyor
		switch (date.getMonth()) {
		case 0:
			dateBuilder.append("Ocak");
			break;
		case 1:
			dateBuilder.append("\u015Eubat");
			break;
		case 2:
			dateBuilder.append("Mart");
			break;
		case 3:
			dateBuilder.append("Nisan");
			break;
		case 4:
			dateBuilder.append("May\u0131s");
			break;
		case 5:
			dateBuilder.append("Haziran");
			break;
		case 6:
			dateBuilder.append("Temmuz");
			break;
		case 7:
			dateBuilder.append("A\u011Fustos");
			break;
		case 8:
			dateBuilder.append("Eyl\u00FCl");
			break;
		case 9:
			dateBuilder.append("Ekim");
			break;
		case 10:
			dateBuilder.append("Kas\u0131m");
			break;
		case 11:
			dateBuilder.append("Aral\u0131k");
			break;
		}
		
		dateBuilder.append(delimeter);

		// yil ekleniyor
		int year = date.getYear() + 1900;
		if (shortYear) {
			dateBuilder.append(String.valueOf(year).substring(2));
		} else {
			dateBuilder.append(String.valueOf(year));
		}

		return dateBuilder.toString();
	}	
}
