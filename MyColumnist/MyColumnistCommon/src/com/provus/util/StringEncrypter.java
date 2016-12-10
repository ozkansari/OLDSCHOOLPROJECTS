/*******************************************************************************
 * $Author: Korayg $
 * $Date: 26.07.06 16:49 $
 * $Modtime: 26.07.06 14:46 $
 * $Revision: 1 $
 * $History: StringEncrypter.java $
 * 
 * *****************  Version 1  *****************
 * User: Korayg       Date: 26.07.06   Time: 16:49
 * Created in $/JMS/src/tr/com/provus/common/util
 * 
 * *****************  Version 1  *****************
 * User: Korayg       Date: 5/22/06    Time: 2:43p
 * Created in $/JMS/src/tr/com/provus/util
 * 
 * *****************  Version 1  *****************
 * User: Korayg       Date: 8/29/05    Time: 1:31p
 * Created in $/Provus/ViewController/src/tr/com/provus/util
 * 
 * *****************  Version 1  *****************
 * User: Korayg       Date: 8/29/05    Time: 9:43a
 * Created in $/Provus/ViewController/src/it/tr/com/provus/util
 * 
 * *****************  Version 1  *****************
 * User: Erkanc       Date: 10.11.04   Time: 9:57
 * Created in $/tr/com/provus/util
 * Initial release 
 ******************************************************************************/
package com.provus.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Uses 3 different schemes to encrypt/decrypt based on choice
 * 
 * @author
 * 
 */
public class StringEncrypter {

	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

	public static final String DES_ENCRYPTION_SCHEME = "DES";

	public static final String DEFAULT_ENCRYPTION_KEY = "This is a fairly long phrase used to encrypt";

	private KeySpec keySpec;

	private SecretKeyFactory keyFactory;

	private Cipher cipher;

	private static final String UNICODE_FORMAT = "UTF8";

	public StringEncrypter(String encryptionScheme) throws EncryptionException {
		this(encryptionScheme, DEFAULT_ENCRYPTION_KEY);
	}

	/**
	 * constructor initializing encryption scheme and encryption key
	 * 
	 * @param encryptionScheme
	 * @param encryptionKey
	 * @throws EncryptionException
	 */
	public StringEncrypter(String encryptionScheme, String encryptionKey)
			throws EncryptionException {
		if (encryptionKey == null)
			throw new IllegalArgumentException("encryption key was null");
		if (encryptionKey.trim().length() < 24)
			throw new IllegalArgumentException(
					"encryption key was less than 24 characters");
		try {
			byte[] keyAsBytes = encryptionKey.getBytes(UNICODE_FORMAT);
			if (encryptionScheme.equals(DESEDE_ENCRYPTION_SCHEME)) {
				keySpec = new DESedeKeySpec(keyAsBytes);
			} else if (encryptionScheme.equals(DES_ENCRYPTION_SCHEME)) {
				keySpec = new DESKeySpec(keyAsBytes);
			} else {
				throw new IllegalArgumentException(
						"Encryption scheme not supported: " + encryptionScheme);
			}
			keyFactory = SecretKeyFactory.getInstance(encryptionScheme);
			cipher = Cipher.getInstance(encryptionScheme);
		} catch (InvalidKeyException e) {
			throw new EncryptionException(e);
		} catch (UnsupportedEncodingException e) {
			throw new EncryptionException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new EncryptionException(e);
		} catch (NoSuchPaddingException e) {
			throw new EncryptionException(e);
		}
	}

	/**
	 * Encrypts given unencrypted string
	 * 
	 * @param unencryptedString
	 * @return
	 * @throws EncryptionException
	 */
	public String encrypt(String unencryptedString) throws EncryptionException {
		if (unencryptedString == null || unencryptedString.trim().length() == 0)
			throw new IllegalArgumentException(
					"unencrypted string was null or empty");
		try {
			SecretKey key = keyFactory.generateSecret(keySpec);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] cleartext = unencryptedString.getBytes(UNICODE_FORMAT);
			byte[] ciphertext = cipher.doFinal(cleartext);
			BASE64Encoder base64encoder = new BASE64Encoder();
			return base64encoder.encode(ciphertext);
		} catch (Exception e) {
			throw new EncryptionException(e);
		}
	}

	/**
	 * Decrypts given encrypted string
	 * 
	 * @param encryptedString
	 * @return
	 * @throws EncryptionException
	 */
	public String decrypt(String encryptedString) throws EncryptionException {
		if (encryptedString == null || encryptedString.trim().length() <= 0)
			throw new IllegalArgumentException(
					"encrypted string was null or empty");

		try {
			SecretKey key = keyFactory.generateSecret(keySpec);
			cipher.init(Cipher.DECRYPT_MODE, key);
			BASE64Decoder base64decoder = new BASE64Decoder();
			byte[] cleartext = base64decoder.decodeBuffer(encryptedString);
			byte[] ciphertext = cipher.doFinal(cleartext);

			return bytes2String(ciphertext);
		} catch (Exception e) {
			throw new EncryptionException(e);
		}
	}

	/**
	 * Returns given byte[] as string
	 * 
	 * @param bytes
	 * @return
	 */
	private static String bytes2String(byte[] bytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			stringBuffer.append((char) bytes[i]);
		}
		return stringBuffer.toString();
	}

	public static class EncryptionException extends Exception {
		public EncryptionException(Throwable t) {
			super(t);
		}
	}
}