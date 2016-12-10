package com.provus.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * A class created to manipulate exceptions
 * 
 * @author
 * 
 */
public class ExceptionUtil {

	/**
	 * <p>
	 * returns a list of all throwables (including the one you passed in)
	 * wrapped by the given throwable. In contrast to a simple call to
	 * <code>getClause()</code> on each throwable it will also check if the
	 * throwable class contain a method <code>getRootCause()</code> (e.g.
	 * ServletException or JspException) and call it instead.
	 * </p>
	 * <p>
	 * The first list element will your passed in exception, the last list
	 * element is the cause.
	 * </p>
	 */
	public static List<Throwable> getExceptions(Throwable cause) {
		List<Throwable> exceptions = new ArrayList<Throwable>();
		exceptions.add(cause);
		do {
			Throwable nextCause;
			try {
				Method rootCause = cause.getClass().getMethod("getRootCause",
						new Class[] {});
				nextCause = (Throwable) rootCause
						.invoke(cause, new Object[] {});
			} catch (Exception e) {
				nextCause = cause.getCause();
			}
			if (cause == nextCause) {
				break;
			}

			if (nextCause != null) {
				exceptions.add(nextCause);
			}

			cause = nextCause;
		} while (cause != null);

		return exceptions;
	}

	/**
	 * Find a throwable message starting with the last element.<br />
	 * Returns the first throwable message where
	 * <code>throwable.getMessage() != null</code>
	 */
	public static String getExceptionMessage(List<Throwable> throwables) {
		if (throwables == null) {
			return null;
		}

		for (int i = throwables.size() - 1; i > 0; i--) {
			Throwable t = (Throwable) throwables.get(i);
			if (t.getMessage() != null) {
				return t.getMessage();
			}
		}
		return null;
	}

	/**
	 * Find a throwable message starting with the last element.<br />
	 * Returns the first throwable message where
	 * <code>throwable.getMessage() != null</code>
	 */
	public static String getExceptionMessage(Throwable cause) {
		List list = getExceptions(cause);
		return getExceptionMessage(list);
	}

	/**
	 * Exception icinde ORA hatasi varsa hata mesajini bulur aksi taktirde null
	 * return eder.
	 * 
	 * @param lastCause
	 * @return
	 */
	public static String findOraException(Throwable lastCause) {
		while (lastCause.getCause() != null)
			lastCause = lastCause.getCause();

		if (lastCause.getMessage().startsWith("ORA-"))
			return "ORA hatasi yakalandi: " + lastCause.getMessage();
		else
			return null;
	}
}
