package com.mycolumnist.common;

import com.provus.util.TurkishLocaleHelper;

public enum TurkishDateEnum {

	JANUARY( 1, "Ocak", "OCAK" ),
	FEBRUARY( 2, "\u015Eubat", "SUBAT" ),
	MARCH( 3, "Mart", "MART" ),
	APRIL( 4, "Nisan", "NISAN" ),
	MAY( 5, "May\u0131s", "MAYIS" ),
	JUNE( 6, "Haziran", "HAZIRAN" ),
	JULY( 7, "Temmuz", "TEMMUZ" ),
	AUGUST( 8, "A\u011Fustos", "AGUSTOS" ),
	SEPTEMBER( 9, "Eyl�l", "EYLUL" ),
	OCTOBER( 10, "Ekim", "EKIM" ),
	NOVEMBER( 11, "Kas\u0131m", "KASIM" ),
	DECEMBER( 12, "Aral\u0131k", "ARALIK" );
	
	
	private int no;
	private String monthTr;
	private String monthNormalized;
	
	private TurkishDateEnum(int no, String monthTr, String monthNormalized) {
		this.no = no;
		this.monthTr = monthTr;
		this.monthNormalized = monthNormalized;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getMonthTr() {
		return monthTr;
	}

	public void setMonthTr(String monthTr) {
		this.monthTr = monthTr;
	}

	public String getMonthNormalized() {
		return monthNormalized;
	}

	public void setMonthNormalized(String monthNormalized) {
		this.monthNormalized = monthNormalized;
	}
	
	public static int getMonthNo(String dateStr){
		
		String dateNormalized = TurkishLocaleHelper.makeUpperNonTR(dateStr);
		
		for( TurkishDateEnum e : values() ){
			if( dateNormalized.equals( e.getMonthNormalized() ) ) {
				return e.getNo();
			}
		}
		
		return -1;
	}
}
