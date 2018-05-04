package com.imadp.core.encryption;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.imadp.core.test.IMADPCoreTestCase;


/**
 * DigestorJasyptImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class DigestorJasyptImplTest extends IMADPCoreTestCase {
	String value;
	
	@Override
	public void before() throws Exception {
		super.before();
		
		value = "UnencryptedValue";
	}
	
	@Test 
	public void digest() throws Exception {		
		String digestedValue = digestorJasypt.digest(value);

		assertFalse("The original value should not match the digested value",
				value.equals(digestedValue));
	}	

	@Test 	
	public void isEqualDigest() throws Exception {		
		String digestedValue = digestorJasypt.digest(value);

		assertFalse("The original value should not match the digested value",
				value.equals(digestedValue));
	
		assertTrue("The original value should digest to the same value", 
				digestorJasypt.isEqualDigest(value, digestedValue));
	}	

}
