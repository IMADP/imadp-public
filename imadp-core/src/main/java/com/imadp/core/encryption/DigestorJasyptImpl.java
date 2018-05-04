package com.imadp.core.encryption;

import org.apache.commons.lang.Validate;
import org.jasypt.util.password.PasswordEncryptor;

import com.imadp.core.CoreComponent;

/**
 * DigestorJasyptImpl
 * 
 * The Jasypt implementation of the Digestor.
 * 
 * @note This service is declared final as it was not designed with inheritance in mind.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public final class DigestorJasyptImpl extends CoreComponent implements Digestor {
	private PasswordEncryptor passwordEncryptor;
	
	@Override
	protected void onInit() {
		super.onInit();
		
		Validate.notNull(passwordEncryptor);
	}	

	@Override
	public String digest(String string) {
		try
		{
			return passwordEncryptor.encryptPassword(string);
		}
		catch(Exception exception)
		{
			throw new DigestionException("Unable to digest the string", exception, string);
		}
	}

	@Override
	public boolean isEqualDigest(String undigested, String digested) {
		try
		{
			return passwordEncryptor.checkPassword(undigested, digested);
		}
		catch(Exception exception)
		{
			logger.warn("Unable to digest the string=[{}]", undigested);
			return false;
		}
	}	
	
	// getters and setters
	public PasswordEncryptor getPasswordEncryptor() {
		return passwordEncryptor;
	}

	public void setPasswordEncryptor(PasswordEncryptor passwordEncryptor) {
		this.passwordEncryptor = passwordEncryptor;
	}

}