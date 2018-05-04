package com.imadp.core.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.imadp.core.test.IMADPCoreTestCase;

/**
 * ChainValidatorTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ChainValidatorTest extends IMADPCoreTestCase {

	@Test 
	public void testSuccessfulChain() throws Exception {
		ChainValidator validator = new ChainValidator.Builder()
			.add(new NotNullValidator("field", new Object()))
			.add(new NotNullValidator("field", new Object()))
			.add(new NotNullValidator("field", new Object())).build();

		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());	
	}

	@Test 
	public void testUnsuccessfulChain() throws Exception {
		ChainValidator validator = new ChainValidator.Builder()
			.add(new NotNullValidator("field", new Object()))
			.add(new NotNullValidator("field", null))
			.add(new NotNullValidator("field", null)).build();

		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());	
	}

}
