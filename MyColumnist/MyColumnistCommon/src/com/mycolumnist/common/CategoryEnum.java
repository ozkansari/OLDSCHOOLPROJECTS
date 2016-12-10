package com.mycolumnist.common;

public enum CategoryEnum {

	SPOR 			(1L,"Spor",""),
	POLITIKA 		(2L,"Politika",""),
	EKONOMI 		(3L,"Ekonomi",""),
	DIS_HABERLER 	(4L,"Dý\u015F Haberler",""),
	KULTUR_SANAT 	(5L,"K\u00FClt\u00FCr Sanat",""),
	MAGAZIN 		(6L,"Magazin",""),
	GUNCEL 			(7L,"G\u00FCncel",""),
	YORUM 			(8L,"Yorum",""),
	TURKIYE 		(9L,"T\u00FCrkiye","");
	
	private Long id;
	private String name;
	private String mainLogo;
	
	private CategoryEnum(Long id, String name, String mainLogo) {
		this.id = id;
		this.name = name;
		this.mainLogo = mainLogo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMainLogo() {
		return mainLogo;
	}

	public void setMainLogo(String mainLogo) {
		this.mainLogo = mainLogo;
	}
	
}
