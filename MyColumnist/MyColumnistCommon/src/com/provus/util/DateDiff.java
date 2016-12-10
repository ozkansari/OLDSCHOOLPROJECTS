package com.provus.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Example use: 
 * Calendar d1 = GregorianCalendar.getInstance(), 
 * Calendar d2 = GregorianCalendar.getInstance(); 
 * d1.set( 2005, 0, 10, 1, 0, 30 ); // 01:00:30 10-Jan-2005 
 * d2.set( 2006, 0, 9, 2, 0, 0 ); // 02:00:00 09-Jan-2006 
 * DateDiff d = DateDiff.diff( d1, d2 ); System.out.println( d.getY() ); // 0
 * System.out.println( d.getM() ); // 11 System.out.println( d.getD() ); // 30
 */
public class DateDiff {
	private final static long ONE_SECOND = 1;

	private final static long ONE_MINUTE = 60 * ONE_SECOND;

	private final static long ONE_HOUR = 60 * ONE_MINUTE;

	private final static long ONE_DAY = 24 * ONE_HOUR;

	private long days;

	private long hours;

	private long minutes;

	private long seconds;

	/**
	 * Takes two dates and calculates the difference between them
	 * 
	 * @param earlier
	 * @param later
	 */
	public void diff(Calendar earlier, Calendar later) {
		diff(later.getTimeInMillis(), earlier.getTimeInMillis());
		
	}
	
	public void diff(Date earlier, Date later) {
		diff(later.getTime(), earlier.getTime());
	}
	
	public void diff(long earlier, long later) {
		long diff = (later - earlier) / 1000;
		days = diff / ONE_DAY;
		diff -= (days * ONE_DAY);

		hours = diff / ONE_HOUR;
		diff -= hours * ONE_HOUR;

		minutes = diff / ONE_MINUTE;
		diff -= minutes * ONE_MINUTE;

		seconds = diff / ONE_SECOND;
	}
	
	/**
	 * Returns the day value of the date difference calculated
	 * 
	 * @return
	 */
	public long getDays() {
		return days;
	}

	public void setDays(long days) {
		this.days = days;
	}

	/**
	 * Returns the hour value of the date difference calculated
	 * 
	 * @return
	 */
	public long getHours() {
		return hours;
	}

	public void setHours(long hours) {
		this.hours = hours;
	}

	/**
	 * Returns the minute value of the date difference calculated
	 * 
	 * @return
	 */
	public long getMinutes() {
		return minutes;
	}

	public void setMinutes(long minutes) {
		this.minutes = minutes;
	}

	/**
	 * Returns the second value of the date difference calculated
	 * 
	 * @return
	 */
	public long getSeconds() {
		return seconds;
	}

	public void setSeconds(long seconds) {
		this.seconds = seconds;
	}

}