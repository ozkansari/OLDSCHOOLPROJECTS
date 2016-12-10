package com.mycolumnist.model.persistence.entity;

import java.util.List;

import com.mycolumnist.model.persistence.entity.base.EntityBase;

/**
 * 
 * @author ozkansari
 *
 */
public class Newspaper extends EntityBase<Long> {

	private static final long serialVersionUID = 7970457389194501454L;

	private String name;
	
	private Integer readCount;
	
	private Integer favCount;
	
	private List<Columnist> columnists;

	public Newspaper(){
		
	}
	
	public Newspaper(String name, Integer readCount, Integer favCount) {
		super();
		this.name = name;
		this.readCount = readCount;
		this.favCount = favCount;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Integer getFavCount() {
		return favCount;
	}

	public void setFavCount(Integer favCount) {
		this.favCount = favCount;
	}

	public List<Columnist> getColumnists() {
		return columnists;
	}

	public void setColumnists(List<Columnist> columnists) {
		this.columnists = columnists;
	}

}
