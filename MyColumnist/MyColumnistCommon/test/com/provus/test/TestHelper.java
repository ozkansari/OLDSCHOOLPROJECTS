package com.provus.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestHelper {

	public static Object callMethod(Object obj, String methodName,
			Object... args) {
		try {
			Method[] methods = Class.forName(obj.getClass().getCanonicalName())
					.getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().equals(methodName)) {
					try {
						methods[i].setAccessible(true);
						return methods[i].invoke(obj, args);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
