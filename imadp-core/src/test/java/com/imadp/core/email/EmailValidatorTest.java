package com.imadp.core.email;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.imadp.core.email.EmailValidator;
import com.imadp.core.test.IMADPCoreTestCase;
import com.imadp.core.validation.ValidationResult;

/**
 * EmailValidatorTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class EmailValidatorTest extends IMADPCoreTestCase {
	
	@Test 
	public void testValidEmail() throws Exception {
		EmailValidator validator = new EmailValidator("field", "a@b.com");
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());
	}
	
	@Test 
	public void testInvalidEmail() throws Exception {
		EmailValidator validator = new EmailValidator("field", "a@b");
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
	}
		
	@Test 
	public void testNullEmail() throws Exception {
		EmailValidator validator = new EmailValidator("field", null);
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
	}
		
}