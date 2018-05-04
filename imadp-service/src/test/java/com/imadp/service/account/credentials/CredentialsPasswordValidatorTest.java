package com.imadp.service.account.credentials;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.imadp.core.validation.ValidationResult;
import com.imadp.service.test.IMADPServiceTestCase;
import com.imadp.service.user.User;

/**
 * CredentialsPasswordValidatorTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CredentialsPasswordValidatorTest extends IMADPServiceTestCase {
	
	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();
		
		Credentials credentials = new Credentials();
		credentials.setUser(user);
		credentials.setUsername("username");
		credentials.setEmail("email@email.com");
		credentials.setPassword("password");
		credentials.setFacebookId(System.currentTimeMillis());
		
		userService.save(user);		
		credentialsService.save(credentials);
	}
	
	@Test 
	public void testValid() throws Exception {
		CredentialsPassword credentialsPassword = new CredentialsPassword();
		credentialsPassword.setPassword("password");
		credentialsPassword.setConfirmPassword("password");
		
		CredentialsPasswordValidator validator = new CredentialsPasswordValidator(
				"credentialsPassword", credentialsPassword);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertTrue(result.isValid());
	}
	
	@Test 
	public void testNull() throws Exception {
		CredentialsPasswordValidator validator = new CredentialsPasswordValidator(
				"credentialsPassword", null);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(2, result.getFailureCount());
		assertTrue(result.hasFailure("credentialsPassword.password.invalidNull"));
		assertTrue(result.hasFailure("credentialsPassword.confirmPassword.invalidNull"));
	}
		
	@Test 
	public void testInvalidMin() throws Exception {
		CredentialsPassword credentialsPassword = new CredentialsPassword();
		credentialsPassword.setPassword("p");
		credentialsPassword.setConfirmPassword("p");
		
		CredentialsPasswordValidator validator = new CredentialsPasswordValidator(
				"credentialsPassword", credentialsPassword);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
		assertTrue(result.hasFailure("credentialsPassword.password.invalidMinLength"));
	}
	
	@Test 
	public void testInvalidConfirm() throws Exception {
		CredentialsPassword credentialsPassword = new CredentialsPassword();
		credentialsPassword.setPassword("password");
		credentialsPassword.setConfirmPassword("confirmPassword");
		
		CredentialsPasswordValidator validator = new CredentialsPasswordValidator(
				"credentialsPassword", credentialsPassword);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
		assertTrue(result.hasFailure("credentialsPassword.confirmPassword.invalidConfirmPassword"));
	}
	
}