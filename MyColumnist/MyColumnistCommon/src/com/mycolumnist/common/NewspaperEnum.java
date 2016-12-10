package com.mycolumnist.common;


public enum NewspaperEnum {
	
	SABAH		(1L, "Sabah",			"sabah_logo.png",		"sabah_mini_logo.gif"),
	HABERTURK	(2L, "Habert\u00FCrk",	"haberturk_logo.png",	"haberturk_mini_logo.gif"),
	ZAMAN		(3L, "Zaman",			"zaman_logo.png",		"zaman_mini_logo.gif"),
	TAKVIM		(4L, "Takvim",			"takvim_logo.png",		"takvim_mini_logo.gif"),
	FANATIK		(5L, "Fanatik",			"fanatik_logo.png",		"fanatik_mini_logo.gif"),
	POSTA		(6L, "Posta",			"posta_logo.png",		"posta_mini_logo.gif"),
	VATAN		(7L, "Vatan",			"vatan_logo.png",		"vatan_mini_logo.gif"),
	RADIKAL		(8L, "Radikal",			"radikal_logo.png",		"radikal_mini_logo.gif"),
	MILLIYET	(9L, "Milliyet",		"milliyet_logo.png",	"milliyet_mini_logo.gif"),
	HURRIYET	(10L, "H\u00FCrriyet",	"hurriyet_logo.png",	"hurriyet_mini_logo.gif");
	
	private Long id;
	private String name;
	private String mainLogo;
	private String stackLogo;
	
	private static String MAIN_LOGO_DIR  ="/images/newspapers/";
	private static String STACK_LOGO_DIR  ="/images/newspapers/mini/";
	
	
	private NewspaperEnum(Long id, String name, String mainLogo, String stackLogo) {
		this.id=id;
		this.name = name;
		this.mainLogo = mainLogo;
		this.stackLogo = stackLogo;
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

	public String getMainLogo() {
		return MAIN_LOGO_DIR + mainLogo;
	}

	public String getStackLogo() {
		return STACK_LOGO_DIR + stackLogo;
	}

}
