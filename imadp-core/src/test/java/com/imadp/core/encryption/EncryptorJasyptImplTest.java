package com.imadp.core.encryption;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.test.annotation.ExpectedException;

import com.imadp.core.encryption.DecryptionException;
import com.imadp.core.test.IMADPCoreTestCase;


/**
 * EncryptorJasyptImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class EncryptorJasyptImplTest extends IMADPCoreTestCase {
	String value;
	
	@Override
	public void before() throws Exception {
		super.before();
		
		value = "UnencryptedValue";
	}
	
	@Test 
	public void encryptAndDecrypt() throws Exception {		
		String encryptedValue = encryptorJasypt.encrypt(value);

		assertFalse("The original value should not match the encrypted value",
				value.equals(encryptedValue));

		String decryptedValue = encryptorJasypt.decrypt(encryptedValue);

		assertTrue("The original value should match the decrypted value",
				value.equals(decryptedValue));
	}	

	@Test 	
	@ExpectedException(DecryptionException.class)
	public void modifyEncryptedValueAndFailDecryption() throws Exception {		
		String encryptedValue = encryptorJasypt.encrypt(value);

		encryptedValue = encryptedValue.substring(0, encryptedValue.length()/2);

		assertFalse("The encrypted value should not match the encrypted value",
				value.equals(encryptedValue));

		encryptorJasypt.decrypt(encryptedValue);		
	}	

}
