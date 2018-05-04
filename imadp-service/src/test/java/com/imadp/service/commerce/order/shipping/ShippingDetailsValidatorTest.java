package com.imadp.service.commerce.order.shipping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.imadp.core.validation.ValidationResult;
import com.imadp.service.test.IMADPServiceTestCase;

/**
 * ShippingDetailsValidatorTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ShippingDetailsValidatorTest extends IMADPServiceTestCase {
	
	@Test 
	public void testNotNull() throws Exception {
		ShippingDetailsValidator validator = new ShippingDetailsValidator("field", null);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(7, result.getFailureCount());
	}
	
}