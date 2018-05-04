package com.imadp.core.encryption;


/**
 * Encryptor
 *
 * Provides bi-directional String encryption services.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface Encryptor {

	/**
	 * Encrypts a String and returns the encrypted value.
	 *
	 * @param unencryptedString
	 * @return String
	 * @throws EncryptionException if unable to encrypt
	 */
	public String encrypt(String unencryptedString) throws EncryptionException;

	/**
	 * Decrypts a String and returns the decrypted value.
	 *
	 * @param encryptedString
	 * @return encryptedValue
	 * @throws DecryptionException if unable to decrypt
	 */
	public String decrypt(String encryptedString) throws DecryptionException;

}
