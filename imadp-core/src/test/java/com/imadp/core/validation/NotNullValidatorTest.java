package com.imadp.core.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.imadp.core.test.IMADPCoreTestCase;

/**
 * NotNullValidatorTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class NotNullValidatorTest extends IMADPCoreTestCase {
	
	@Test 
	public void testNotNull() throws Exception {
		NotNullValidator validator = new NotNullValidator("field", new Object());
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());
	}
	
	@Test 
	public void testNull() throws Exception {
		NotNullValidator validator = new NotNullValidator("field", null);
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
	}
		
}
