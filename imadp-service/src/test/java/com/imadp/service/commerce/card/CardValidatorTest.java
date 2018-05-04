package com.imadp.service.commerce.card;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.imadp.core.validation.ValidationResult;
import com.imadp.service.test.IMADPServiceTestCase;

/**
 * CardValidatorTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CardValidatorTest extends IMADPServiceTestCase {
	
	@Test 
	public void testValidCard() throws Exception {
		Card card = new Card();
		card.setNumber("4111111111111111");
		card.setExpirationMonth(10);
		card.setExpirationYear(2020);
		card.setCvv("cvv");
		
		CardValidator validator = new CardValidator("card", card);
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertTrue(result.isValid());
		assertEquals(0, result.getFailureCount());
	}
	
	@Test 
	public void testNullCard() throws Exception {
		CardValidator validator = new CardValidator("card", null);
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(4, result.getFailureCount());
	}
		
	@Test 
	public void testInvalidCard() throws Exception {
		Card card = new Card();
		
		CardValidator validator = new CardValidator("card", card);
		ValidationResult result = validator.getValidationResult();
		
		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(4, result.getFailureCount());
	}
		
}
