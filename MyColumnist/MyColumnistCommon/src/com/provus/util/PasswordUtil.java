package com.provus.util;

import java.security.SecureRandom;

public class PasswordUtil {

	public static int generatePasswordMax18() {
		SecureRandom random = new SecureRandom();
		int mod = 9999999;
		int number = Math.abs((random.nextInt() % mod));
		if (String.valueOf(number).length() > 18) {
			return generatePasswordMax18();
		} else
			return number;
	}

}
