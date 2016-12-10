package com.provus.util;

import junit.framework.TestCase;
import com.provus.util.StringEncrypter;

/**
 * Test class for com.provus.util.StringEncrypter
 * 
 * @author
 * 
 */
public class StringEncrypterTest extends TestCase {

	/**
	 * Tests StringEncrypter.DEFAULT_ENCRYPTION_KEY
	 */
	public void testEncryptionKeyIsDefined() {
		assertEquals(StringEncrypter.DEFAULT_ENCRYPTION_KEY,
				"This is a fairly long phrase used to encrypt");
	}

	/**
	 * Tests if given scheme is supported or not
	 * 
	 * @throws Exception
	 */
	public void testThrowsErrorOnInvalidKeySpec() throws Exception {
		String encryptionScheme = "asdf";
		String encryptionKey = "123456789012345678901234567890";

		try {
			StringEncrypter encrypter = new StringEncrypter(encryptionScheme,
					encryptionKey);
		} catch (IllegalArgumentException e) {
			assertEquals("Encryption scheme not supported: asdf", e
					.getMessage());
		}
	}

	/**
	 * Tests enrcyption with StringEncrypter.DESEDE_ENCRYPTION_SCHEME scheme
	 * 
	 * @throws Exception
	 */
	public void testEncryptsUsingDesEde() throws Exception {
		String stringToEncrypt = "test";
		String encryptionKey = "123456789012345678901234567890";
		String encryptionScheme = StringEncrypter.DESEDE_ENCRYPTION_SCHEME;

		StringEncrypter encrypter = new StringEncrypter(encryptionScheme,
				encryptionKey);
		String encryptedString = encrypter.encrypt(stringToEncrypt);

		assertEquals("Ni2Bih3nCUU=", encryptedString);
	}

	/**
	 * Tests enrcyption with StringEncrypter.DES_ENCRYPTION_SCHEME scheme
	 * 
	 * @throws Exception
	 */
	public void testEncryptsUsingDes() throws Exception {
		String stringToEncrypt = "test";
		String encryptionKey = "123456789012345678901234567890";
		String encryptionScheme = StringEncrypter.DES_ENCRYPTION_SCHEME;

		StringEncrypter encrypter = new StringEncrypter(encryptionScheme,
				encryptionKey);
		String encryptedString = encrypter.encrypt(stringToEncrypt);

		assertEquals("oEtoaxGK9ns=", encryptedString);
	}

	/**
	 * If this test does not fail, this means encryptionKey could be composed of
	 * letters and spaces
	 * 
	 * @throws Exception
	 */
	public void testEncryptionKeyCanContainLetters() throws Exception {
		String string = "test";
		String encryptionKey = "ASDF asdf 1234 8983 jklasdf J2Jaf8";
		String encryptionScheme = StringEncrypter.DESEDE_ENCRYPTION_SCHEME;

		StringEncrypter encrypter = new StringEncrypter(encryptionScheme,
				encryptionKey);
		String encryptedString = encrypter.encrypt(string);

		assertEquals("Q+UyPrxdge0=", encryptedString);
	}

	/**
	 * Tests decryption with StringEncrypter.DESEDE_ENCRYPTION_SCHEME scheme
	 * 
	 * @throws Exception
	 */
	public void testDecryptsUsingDesEde() throws Exception {
		String string = "Ni2Bih3nCUU=";
		String encryptionKey = "123456789012345678901234567890";
		String encryptionScheme = StringEncrypter.DESEDE_ENCRYPTION_SCHEME;

		StringEncrypter encrypter = new StringEncrypter(encryptionScheme,
				encryptionKey);
		String decryptedString = encrypter.decrypt(string);

		assertEquals("test", decryptedString);
	}

	/**
	 * Tests decryption with StringEncrypter.DES_ENCRYPTION_SCHEME scheme
	 * 
	 * @throws Exception
	 */
	public void testDecryptsUsingDes() throws Exception {
		String string = "oEtoaxGK9ns=";
		String encryptionKey = "123456789012345678901234567890";
		String encryptionScheme = StringEncrypter.DES_ENCRYPTION_SCHEME;

		StringEncrypter encrypter = new StringEncrypter(encryptionScheme,
				encryptionKey);
		String decryptedString = encrypter.decrypt(string);

		assertEquals("test", decryptedString);
	}

	/**
	 * If this test does not fail, this means encryptionKey could be null
	 * 
	 * @throws Exception
	 */
	public void testCantInstantiateWithNullEncryptionKey() throws Exception {
		try {
			String encryptionScheme = StringEncrypter.DESEDE_ENCRYPTION_SCHEME;
			String encryptionKey = null;

			StringEncrypter encrypter = new StringEncrypter(encryptionScheme,
					encryptionKey);
		} catch (IllegalArgumentException e) {
			assertEquals("encryption key was null", e.getMessage());
		}

	}

	/**
	 * If this test does not fail, this means encryptionKey could be
	 * empty(ie:"")
	 * 
	 * @throws Exception
	 */
	public void testCantInstantiateWithEmptyEncryptionKey() throws Exception {
		try {
			String encryptionScheme = StringEncrypter.DESEDE_ENCRYPTION_SCHEME;
			String encryptionKey = "";

			StringEncrypter encrypter = new StringEncrypter(encryptionScheme,
					encryptionKey);
		} catch (IllegalArgumentException e) {
			assertEquals("encryption key was less than 24 characters", e
					.getMessage());
		}

	}

	/**
	 * If this test does not fail, this means encrypter will not throw exception
	 * while decrypting null string
	 * 
	 * @throws Exception
	 */
	public void testCantDecryptWithNullString() throws Exception {
		try {
			String encryptionScheme = StringEncrypter.DESEDE_ENCRYPTION_SCHEME;
			String encryptionKey = "123456789012345678901234";

			StringEncrypter encrypter = new StringEncrypter(encryptionScheme,
					encryptionKey);
			encrypter.decrypt(null);
		} catch (IllegalArgumentException e) {
			assertEquals("encrypted string was null or empty", e.getMessage());
		}

	}

	/**
	 * If this test does not fail, this means encrypter will not throw exception
	 * while decrypting empty(ie:"") string
	 * 
	 * @throws Exception
	 */
	public void testCantDecryptWithEmptyString() throws Exception {
		try {
			String encryptionScheme = StringEncrypter.DESEDE_ENCRYPTION_SCHEME;
			String encryptionKey = "123456789012345678901234";

			StringEncrypter encrypter = new StringEncrypter(encryptionScheme,
					encryptionKey);
			encrypter.decrypt("");
		} catch (IllegalArgumentException e) {
			assertEquals("encrypted string was null or empty", e.getMessage());
		}

	}

	/**
	 * If this test does not fail, this means encrypter will not throw exception
	 * while decrypting empty(ie:"") string
	 * 
	 * @throws Exception
	 */
	public void testCantEncryptWithNullString() throws Exception {
		try {
			String encryptionScheme = StringEncrypter.DESEDE_ENCRYPTION_SCHEME;
			String encryptionKey = "123456789012345678901234";

			StringEncrypter encrypter = new StringEncrypter(encryptionScheme,
					encryptionKey);
			encrypter.encrypt(null);
		} catch (IllegalArgumentException e) {
			assertEquals("unencrypted string was null or empty", e.getMessage());
		}

	}

	/**
	 * If this test does not fail, this means encrypter will not throw exception
	 * while encrypting empty(ie:"") string
	 * 
	 * @throws Exception
	 */
	public void testCantEncryptWithEmptyString() throws Exception {
		try {
			String encryptionScheme = StringEncrypter.DESEDE_ENCRYPTION_SCHEME;
			String encryptionKey = "123456789012345678901234";

			StringEncrypter encrypter = new StringEncrypter(encryptionScheme,
					encryptionKey);
			encrypter.encrypt("");
		} catch (IllegalArgumentException e) {
			assertEquals("unencrypted string was null or empty", e.getMessage());
		}
	}
}