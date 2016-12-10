package net.javafun.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name="userSettings")
public class UserSettings {

	// private final static Logger logger = LoggerFactory.getLogger(UserSettings.class);
	
	private String currentTemplateFile = "templates/oceanfresh/template.xhtml";

	public String getCurrentTemplateFile() {
		return currentTemplateFile;
	}

	public void setCurrentTemplateFile(String currentTemplateFile) {
		this.currentTemplateFile = currentTemplateFile;
	}
}
