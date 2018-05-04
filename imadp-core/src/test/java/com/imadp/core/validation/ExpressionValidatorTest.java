package com.imadp.core.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.imadp.core.test.IMADPCoreTestCase;

/**
 * ExpressionValidatorTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ExpressionValidatorTest extends IMADPCoreTestCase {
	
	@Test 
	public void testValidExpression() throws Exception {
		ExpressionValidator validator = new ExpressionValidator("field", "field", "^[a-z0-9_-]+$");
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());
	}
	
	@Test 
	public void testInvalidExpression() throws Exception {
		ExpressionValidator validator = new ExpressionValidator("field", "fi%^eld", "^[a-z0-9_-]+$");
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
	}
		
}