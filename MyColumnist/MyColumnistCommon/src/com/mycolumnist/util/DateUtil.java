package com.mycolumnist.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.mycolumnist.common.TurkishDateEnum;

public class DateUtil {

	private static Logger logger = Logger.getLogger(DateUtil.class);
	
	@SuppressWarnings("deprecation")
	public static Date parseTurkishDateHavingMonthAsString( String dateStr ){

		char [][] charToReplace = 
		{ 
			{ '.', ' ' }, 
			{ ',', ' ' }, 
			{ '?', ' ' }, 
			{ '_', ' ' } 
		};
		String dateStrClean = com.provus.util.StringUtils.replaceMultipleChars(dateStr, charToReplace).trim();
	
		try {
			String [] dateParts = dateStrClean.split(" ");
			
			int day = Integer.valueOf( dateParts[0] );
			int month = TurkishDateEnum.getMonthNo(dateParts[1]);
			int year = Integer.valueOf( dateParts[2] );
			
			
			Date dateValue = new Date(year-1900,month-1,day);
			return dateValue;
		}
		catch(Exception e){
			logger.error("Exception parsing turkish date: " + dateStr, e);
			return null;
		}
	}
	
	/**
	 * Parses date in dd.MM.yyyy format (10.04.2010)
	 * 
	 * @param dateStr date string to b e parsed
	 * @param delimiter delimeter reg ex
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Date parseRegularTurkishStyleDate(String dateStr,String delimiter) {
		
		Date date = null;
		try {
			String[] dateParts = dateStr.split(delimiter);
			int year = Integer.parseInt(dateParts[2].trim())-1900;
			int month = Integer.parseInt(dateParts[1].trim())-1;
			int day = Integer.parseInt(dateParts[0].trim());
			date = new Date(year, month, day);
		}
		catch(Exception e) {
			date = new Date();
		}
		return date==null? new Date(): date;
	}
}
