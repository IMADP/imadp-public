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
 * CredentialsUsernameValidatorTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CredentialsUsernameValidatorTest extends IMADPServiceTestCase {
	User user;
	
	@Override
	public void before() throws Exception {
		super.before();

		user = new User();
		
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
		CredentialsUsername credentialsUsername = new CredentialsUsername();
		credentialsUsername.setUser(null);
		credentialsUsername.setUsername("user");
		
		CredentialsUsernameValidator validator = new CredentialsUsernameValidator(
				"credentialsUsername", credentialsUsername, credentialsService);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertTrue(result.isValid());
	}
	
	@Test 
	public void testNull() throws Exception {
		CredentialsUsernameValidator validator = new CredentialsUsernameValidator(
				"credentialsUsername", null, credentialsService);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
		assertTrue(result.hasFailure("credentialsUsername.username.invalidNull"));
	}
	
	@Test 
	public void testInvalidMin() throws Exception {
		CredentialsUsername credentialsUsername = new CredentialsUsername();
		credentialsUsername.setUser(null);
		credentialsUsername.setUsername("us");
		
		CredentialsUsernameValidator validator = new CredentialsUsernameValidator(
				"credentialsUsername", credentialsUsername, credentialsService);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
		assertTrue(result.hasFailure("credentialsUsername.username.invalidMinLength"));
	}
	
	@Test 
	public void testInvalidMax() throws Exception {
		CredentialsUsername credentialsUsername = new CredentialsUsername();
		credentialsUsername.setUser(null);
		credentialsUsername.setUsername("usernameusernameusername");
		
		CredentialsUsernameValidator validator = new CredentialsUsernameValidator(
				"credentialsUsername", credentialsUsername, credentialsService);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
		assertTrue(result.hasFailure("credentialsUsername.username.invalidMaxLength"));
	}
	
	@Test 
	public void testInvalidUsernameInUse() throws Exception {
		CredentialsUsername credentialsUsername = new CredentialsUsername();
		credentialsUsername.setUser(null);
		credentialsUsername.setUsername("username");
		
		CredentialsUsernameValidator validator = new CredentialsUsernameValidator(
				"credentialsUsername", credentialsUsername, credentialsService);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
		assertTrue(result.hasFailure("credentialsUsername.username.invalidUsernameInUse"));
	}
	
	@Test 
	public void testValidUsernameInUse() throws Exception {
		CredentialsUsername credentialsUsername = new CredentialsUsername();
		credentialsUsername.setUser(user);
		credentialsUsername.setUsername("username");
		
		CredentialsUsernameValidator validator = new CredentialsUsernameValidator(
				"credentialsUsername", credentialsUsername, credentialsService);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertTrue(result.isValid());
	}
	
}