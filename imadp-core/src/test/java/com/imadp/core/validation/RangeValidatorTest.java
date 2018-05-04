package com.imadp.core.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.imadp.core.test.IMADPCoreTestCase;

/**
 * RangeValidatorTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class RangeValidatorTest extends IMADPCoreTestCase {

	@Test
	public void testValidString() throws Exception {
		RangeValidator validator = new RangeValidator("field", "stringstringstring", 10, 20);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());
	}

	@Test
	public void testInvalidString() throws Exception {
		RangeValidator validator = new RangeValidator("field", "s", 3, 20);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
	}

	@Test
	public void testValidInt() throws Exception {
		RangeValidator validator = new RangeValidator("field", 15, 10, 20);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());
	}

	@Test
	public void testInvalidInt() throws Exception {
		RangeValidator validator = new RangeValidator("field", 1, 3, 20);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
	}

	@Test
	public void testValidLong() throws Exception {
		RangeValidator validator = new RangeValidator("field", 15L, 10L, 20L);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());
	}

	@Test
	public void testInvalidLong() throws Exception {
		RangeValidator validator = new RangeValidator("field", 1L, 3L, 20L);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
	}

	@Test
	public void testValidDouble() throws Exception {
		RangeValidator validator = new RangeValidator("field", 15.0, 10.0, 20.0);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());
	}

	@Test
	public void testInvalidDouble() throws Exception {
		RangeValidator validator = new RangeValidator("field", 1.0, 3.0, 20.0);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
	}

}