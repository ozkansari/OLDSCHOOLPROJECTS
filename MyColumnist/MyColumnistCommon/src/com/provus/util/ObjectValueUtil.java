package com.provus.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectValueUtil {

	public static boolean isLongValue(String value) {
		if (StringUtils.isEmpty(value))
			return false;
		try {
			Long.valueOf(value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isIntegerValue(String value) {
		if (StringUtils.isEmpty(value))
			return false;
		try {
			Integer.valueOf(value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static boolean isPropertiesSame(Object obj1, Object obj2) {
		if (!obj1.getClass().equals(obj2.getClass())) {
			throw new RuntimeException("Object type in not equals. Obj1: "
					+ obj1.getClass() + " Obj1: " + obj2.getClass());
		}
		Class clazz = obj1.getClass();
		Method[] methots = clazz.getMethods();
		for (Method method : methots) {
			if (method.getName().startsWith("is")
					|| method.getName().startsWith("get")) {
				try {
					Object value1 = method.invoke(obj1);
					Object value2 = method.invoke(obj2);
					if (value1 == null && value2 == null) {
						continue;
					}
					if (value1 == null && value2 != null) {
						if (value2 instanceof String) {
							if (((String) value2).length() == 0) {
								continue;
							} else {
								return false;
							}
						} else {
							return false;
						}
					}
					if (value2 == null && value1 != null) {
						if (value1 instanceof String) {
							if (((String) value1).length() == 0) {
								continue;
							} else {
								return false;
							}
						} else {
							return false;
						}
					}
					if (value1 instanceof java.util.Date) {
						if (((java.util.Date) value1).getTime() != ((java.util.Date) value2)
								.getTime()) {
							return false;
						}
					} else {

						if (!value1.equals(value2)) {
							return false;
						}
					}
				} catch (IllegalArgumentException e) {
					return false;
				} catch (IllegalAccessException e) {
					return false;
				} catch (InvocationTargetException e) {
					return false;
				}
			}
		}
		return true;
	}
}
