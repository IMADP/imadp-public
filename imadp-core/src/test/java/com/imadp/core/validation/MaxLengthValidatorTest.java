package com.imadp.core.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.imadp.core.test.IMADPCoreTestCase;

/**
 * MaxLengthValidatorTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class MaxLengthValidatorTest extends IMADPCoreTestCase {

	@Test
	public void testNullString() throws Exception {
		MaxLengthValidator validator = new MaxLengthValidator("field", null, 10);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());
	}

	@Test
	public void testValidString() throws Exception {
		MaxLengthValidator validator = new MaxLengthValidator("field", "string", 10);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());
	}

	@Test
	public void testInvalidString() throws Exception {
		MaxLengthValidator validator = new MaxLengthValidator("field", "string", 3);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
	}

	@Test
	public void testValidInt() throws Exception {
		MaxLengthValidator validator = new MaxLengthValidator("field", 5, 10);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());
	}

	@Test
	public void testInvalidInt() throws Exception {
		MaxLengthValidator validator = new MaxLengthValidator("field", 5, 3);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
	}

	@Test
	public void testValidLong() throws Exception {
		MaxLengthValidator validator = new MaxLengthValidator("field", 5L, 10L);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());
	}

	@Test
	public void testInvalidLong() throws Exception {
		MaxLengthValidator validator = new MaxLengthValidator("field", 5L, 3L);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
	}

	@Test
	public void testValidDouble() throws Exception {
		MaxLengthValidator validator = new MaxLengthValidator("field", 5.0, 10.0);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());
	}

	@Test
	public void testInvalidDouble() throws Exception {
		MaxLengthValidator validator = new MaxLengthValidator("field", 5.0, 3.0);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
	}

}