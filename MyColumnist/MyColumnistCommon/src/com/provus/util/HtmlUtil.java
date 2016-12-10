package com.provus.util;

import java.util.StringTokenizer;

public class HtmlUtil {

	
	/**
	 * Encodes given string with utf-8 encoding for html.
	 * 
	 * @param s
	 * @return
	 */
	public static String htmlEntityEncode(String s) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0'
					&& c <= '9') {
				buf.append(c);
			} else {
				buf.append("&#" + (int) c + ";");
				/*if (c == '/' || c == '<' || c == '>' || c == ' ' || c == '\r'
						|| c == '\n' || c == '"') {
					buf.append(c);
				} else {
					buf.append("&#" + (int) c + ";");
				}*/
			}
		}
		return buf.toString();
	}

	public static String htmlEntityEncode2(String pText) {
		StringTokenizer tokenizer = new StringTokenizer(pText, "&<>\"", true);
		int tokenCount = tokenizer.countTokens();

		/* no encoding's needed */
		if (tokenCount == 1)
			return pText;

		/*
		 * text.length + (tokenCount * 6) gives buffer large enough so no
		 * addition memory would be needed and no costly copy operations would
		 * occur
		 */
		StringBuffer buffer = new StringBuffer(pText.length() + tokenCount * 6);
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if (token.length() == 1) {
				switch (token.charAt(0)) {
				case '&':
					buffer.append("&amp;");
					break;
				case '<':
					buffer.append("&lt;");
					break;
				case '>':
					buffer.append("&gt;");
					break;
				case '"':
					buffer.append("&quot;");
					break;
				default:
					buffer.append(token);
				}
			} else {
				buffer.append(token);
			}
		}
		return buffer.toString();
	}
}
