package com.provus.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class BundleUtil {

	protected static ClassLoader getCurrentClassLoader(Object defaultObject) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = defaultObject.getClass().getClassLoader();
		}
		return loader;
	}

	/**
	 * Returns the string value of the key from given properties file
	 * 
	 * @param bundleName
	 * @param locale
	 * @param id
	 * @param params
	 * @return
	 */
	public static String getString(String bundleName, Locale locale, String id,
			Object... params) {
		String text = null;
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale,
				getCurrentClassLoader(params));
		try {
			text = bundle.getString(id);
		} catch (MissingResourceException e) {
			text = "!! key " + id + " not found !!";
		}
		if (params.length > 0) {
			MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params).toString();
		}
		return text;
	}

}
