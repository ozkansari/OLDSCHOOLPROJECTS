package com.mycolumnist.model.persistence.entity;

import java.util.Date;

import com.mycolumnist.model.persistence.entity.base.EntityBase;
import com.mycolumnist.util.EntityUtil;

public class Column extends EntityBase<Long> {

	private static final long serialVersionUID = 2922793717655221872L;
	
	private Columnist columnist;
	
	private Date date;
	
	private int sequence;
	
	private String title;
	
	private String summary;
	
	private String hyperlink;

	public Column(){
		
	}
	
	public Column(Columnist columnist, Date date, int sequence,
			String title, String summary, String hyperlink) {
		this.columnist = columnist;
		this.date = date;
		this.sequence = sequence;
		this.title = title;
		this.summary = summary;
		this.hyperlink = hyperlink;
	}

	public Columnist getColumnist() {
		return columnist;
	}

	public void setColumnist(Columnist columnist) {
		this.columnist = columnist;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int dequence) {
		this.sequence = dequence;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getHyperlink() {
		return hyperlink;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}

	@Override
	public String toString() {
		return "Column [columnId=" + id + ", columnistId=" + columnist.getId()
				+ ", date=" + date + ", hyperlink=" + hyperlink + ", sequence="
				+ sequence + ", summary=" + summary + ", title=" + title + "]";
	}
	
	public String getDateTurkishStyleShortDateFormatted() {
		return EntityUtil.getTurkishStyleShortDateFormatter().format(date);
	}
	
	public String getTitleFullyCapitalized() {
		return EntityUtil.getFullyCapitalizedText(title);
	}

}
