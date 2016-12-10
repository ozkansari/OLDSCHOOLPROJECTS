package com.mycolumnist.model;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {

	public void checkCookies() {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		Cookie[] cookies = httpServletRequest.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equalsIgnoreCase("cookieKey")) {
					String cookieValue = cookies[i].getValue();
				}
			}
		}
	}
	
	public void createCookies(){
		
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		Cookie cookie = new Cookie("cookieKey", "cookieValue");
		cookie.setMaxAge(365);
		cookie.setComment("A Comment");
		httpServletResponse.addCookie(cookie);
	}
}
