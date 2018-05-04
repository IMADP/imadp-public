package com.imadp.core.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.imadp.core.test.IMADPCoreTestCase;

/**
 * SimpleValidatorTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class SimpleValidatorTest extends IMADPCoreTestCase {
	
	@Test 
	public void testTrue() throws Exception {
		SimpleValidator validator = new SimpleValidator(true, "key", "field");
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());
	}
	
	@Test 
	public void testFalse() throws Exception {
		SimpleValidator validator = new SimpleValidator(false, "key", "field");
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
		assertEquals("key", result.getFailures().get(0).getKey());
	}
		
}
