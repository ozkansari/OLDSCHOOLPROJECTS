/*******************************************************************************
 * $Author: Korayg $
 * $Date: 8/04/06 1:17p $
 * $Modtime: 8/04/06 11:28a $
 * $Revision: 2 $
 * $History: ElapsedTime.java $
 * 
 * *****************  Version 2  *****************
 * User: Korayg       Date: 8/04/06    Time: 1:17p
 * Updated in $/JMS/src/tr/com/provus/common/util
 * 
 * *****************  Version 1  *****************
 * User: Korayg       Date: 26.07.06   Time: 16:48
 * Created in $/JMS/src/tr/com/provus/common/util
 * 
 * *****************  Version 1  *****************
 * User: Korayg       Date: 5/22/06    Time: 2:43p
 * Created in $/JMS/src/tr/com/provus/util
 * 
 * *****************  Version 1  *****************
 * User: Korayg       Date: 8/29/05    Time: 1:30p
 * Created in $/Provus/ViewController/src/tr/com/provus/util
 * 
 * *****************  Version 1  *****************
 * User: Korayg       Date: 8/29/05    Time: 9:42a
 * Created in $/Provus/ViewController/src/it/tr/com/provus/util
 * 
 * *****************  Version 1  *****************
 * User: Erkanc       Date: 18.02.05   Time: 14:24
 * Created in $/tr/com/provus/util
 * Initial release
 ******************************************************************************/

package com.provus.util;

/**
 * A class for calculating elapsed time between two dates
 * 
 * @author erkanc
 */
public class ElapsedTime {
	private long start;

	private long end;

	private static long multiplier[] = { 86400000, 3600000, 60000, 1000, 1 };

	private static String legend[] = { "day", "hour", "min", "sec", "ms" };

	/** Creates a new instance of ElapsedTime */
	public ElapsedTime() {
		start = end = 0;
	}

	public ElapsedTime(long s, long e) {
		start = s;
		end = e;
	}

	public String toString() {
		long value[] = new long[5];
		long elapsed = end - start;
		for (int ndx = 0; ndx < multiplier.length; ndx++) {
			value[ndx] = elapsed / multiplier[ndx];
			elapsed -= (value[ndx] * multiplier[ndx]);
		}
		boolean putComma = false;
		String asString = new String();
		for (int ndx = 0; ndx < multiplier.length; ndx++) {
			asString += ((putComma && value[ndx] > 0) ? ", " : "")
					+ ((value[ndx] > 0) ? (value[ndx] + " " + legend[ndx]) : "");
			if (value[ndx] > 1
					&& !legend[ndx].equals(legend[legend.length - 1]))
				asString += "s";
			if (value[ndx] > 0)
				putComma = true;
		}
		return asString;

	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		ElapsedTime time = new ElapsedTime(0, (3 * 24 * 60 * 60 * 1000)
				+ (4 * 60 * 60 * 1000) + (1 * 60 * 1000) + (6 * 1000) + 789);
		System.out.println(time.toString());
		time = new ElapsedTime(0, 6525000);
		System.out.println(time.toString());
	}

}
