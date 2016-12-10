package com.provus.util;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

public class ReflectionHelper {
	/**
	 * Returns true if class and member both is public
	 * 
	 * @param clazz
	 * @param member
	 * @return
	 */
	public static boolean isPublic(Class clazz, Member member) {
		return Modifier.isPublic(member.getModifiers())
				&& Modifier.isPublic(clazz.getModifiers());
	}

}
