package com.imadp.core.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.imadp.core.test.IMADPCoreTestCase;

/**
 * MinLengthValidatorTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class MinLengthValidatorTest extends IMADPCoreTestCase {
	
	@Test 
	public void testValidString() throws Exception {
		MinLengthValidator validator = new MinLengthValidator("field", "stringstringstring", 10);
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());
	}
	
	@Test 
	public void testInvalidString() throws Exception {
		MinLengthValidator validator = new MinLengthValidator("field", "s", 3);
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
	}
	
	@Test 
	public void testValidInt() throws Exception {
		MinLengthValidator validator = new MinLengthValidator("field", 15, 10);
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());
	}
	
	@Test 
	public void testInvalidInt() throws Exception {
		MinLengthValidator validator = new MinLengthValidator("field", 1, 3);
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
	}
	
	@Test 
	public void testValidLong() throws Exception {
		MinLengthValidator validator = new MinLengthValidator("field", 15L, 10L);
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());
	}
	
	@Test 
	public void testInvalidLong() throws Exception {
		MinLengthValidator validator = new MinLengthValidator("field", 1L, 3L);
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
	}
	
	@Test 
	public void testValidDouble() throws Exception {
		MinLengthValidator validator = new MinLengthValidator("field", 15.0, 10.0);
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());
	}
	
	@Test 
	public void testInvalidDouble() throws Exception {
		MinLengthValidator validator = new MinLengthValidator("field", 1.0, 3.0);
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
	}
	
}