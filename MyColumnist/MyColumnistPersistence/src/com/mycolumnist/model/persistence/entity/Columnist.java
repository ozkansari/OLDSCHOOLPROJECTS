package com.mycolumnist.model.persistence.entity;

import com.mycolumnist.common.ProjectConstant;
import com.mycolumnist.model.persistence.entity.base.EntityBase;
import com.mycolumnist.util.EntityUtil;

public class Columnist extends EntityBase<Long> {

	private static final long serialVersionUID = -6042177692009787622L;

	private String name;
	
	private String newspaperName;
	
	private Newspaper newspaper;

	private String imageSrc;

	private Column latestColumn;

	private String category;
	
	private String nameTag;

	public Columnist(){
		
	}
	
	public Columnist(String newspaperName, String name, String nameTag, String imageSrc, String category, Newspaper newspaper, Column latestColumn ) {
		super();
		this.newspaperName = newspaperName;
		this.newspaper = newspaper;
		this.name = name;
		this.nameTag = nameTag;
		this.imageSrc = imageSrc;
		this.latestColumn = latestColumn;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNewspaperName() {
		return newspaperName;
	}

	public void setNewspaperName(String newspaperName) {
		this.newspaperName = newspaperName;
	}

	public Newspaper getNewspaper() {
		return newspaper;
	}

	public void setNewspaper(Newspaper newspaper) {
		this.newspaper = newspaper;
	}

	public String getImageSrc() {
		return imageSrc==null || imageSrc.trim().length()==0 ? ProjectConstant.defaultImageSrc : imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public Column getLatestColumn() {
		return latestColumn;
	}

	public void setLatestColumn(Column latestColumn) {
		this.latestColumn = latestColumn;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNameTag() {
		return nameTag;
	}

	public void setNameTag(String nameTag) {
		this.nameTag = nameTag;
	}

	@Override
	public String toString() {
		return 
			"Columnist [columnist_id = " + id + ", " +
			"category=" + category + ", " +
			"latestColumnId=" + ( latestColumn==null || latestColumn.getId()==null ?null:latestColumn.getId() ) + ", " +
			"name=" + name + ", " +
			"newspaper="+ newspaperName + "]";
	}
	
	public String getNameFullyCapitalized() {
		return EntityUtil.getFullyCapitalizedText(name);
	}

}
