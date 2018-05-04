package com.imadp.core.encryption;

import org.apache.commons.lang.Validate;
import org.jasypt.encryption.StringEncryptor;

import com.imadp.core.CoreComponent;

/**
 * EncryptorJasyptImpl
 * 
 * The Jasypt implementation of the Encryptor.
 * 
 * @note This service is declared final as it was not designed with inheritance in mind.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public final class EncryptorJasyptImpl extends CoreComponent implements Encryptor {
	private StringEncryptor stringEncryptor;
	
	@Override
	protected void onInit() {
		super.onInit();
		
		Validate.notNull(stringEncryptor);
	}
	
	@Override
	public String encrypt(String unencryptedString) {
		if(unencryptedString == null)
			return null;
		
		try
		{
			return stringEncryptor.encrypt(unencryptedString);
		}
		catch(Exception exception)
		{
			throw new EncryptionException("Unable to encrypt the string", exception, unencryptedString);
		}
	}
	
	@Override
	public String decrypt(String encryptedString) {
		if(encryptedString == null)
			return null;
		
		try 
		{
			return stringEncryptor.decrypt(encryptedString);
		}
		catch(Exception exception)
		{
			throw new DecryptionException("Unable to decrypt the string", exception, encryptedString);
		}
	}

	// getters and setters
	public StringEncryptor getStringEncryptor() {
		return stringEncryptor;
	}

	public void setStringEncryptor(StringEncryptor stringEncryptor) {
		this.stringEncryptor = stringEncryptor;
	}

}
