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
 * CredentialsEmailValidatorTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CredentialsEmailValidatorTest extends IMADPServiceTestCase {
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
		CredentialsEmail credentalsUsername = new CredentialsEmail();
		credentalsUsername.setUser(null);
		credentalsUsername.setEmail("email2@email.com");
		
		CredentialsEmailValidator validator = new CredentialsEmailValidator(
				"credentialsEmail", credentalsUsername, credentialsService);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertTrue(result.isValid());
	}
	
	@Test 
	public void testNull() throws Exception {
		CredentialsEmailValidator validator = new CredentialsEmailValidator(
				"credentialsEmail", null, credentialsService);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
		assertTrue(result.hasFailure("credentialsEmail.email.invalidNull"));
	}
		
	@Test 
	public void testInvalid() throws Exception {
		CredentialsEmail credentalsUsername = new CredentialsEmail();
		credentalsUsername.setUser(null);
		credentalsUsername.setEmail("email");
		
		CredentialsEmailValidator validator = new CredentialsEmailValidator(
				"credentialsEmail", credentalsUsername, credentialsService);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
		assertTrue(result.hasFailure("credentialsEmail.email.invalidEmail"));
	}
	
	@Test 
	public void testInvalidEmailInUse() throws Exception {
		CredentialsEmail credentalsUsername = new CredentialsEmail();
		credentalsUsername.setUser(null);
		credentalsUsername.setEmail("email@email.com");
		
		CredentialsEmailValidator validator = new CredentialsEmailValidator(
				"credentialsEmail", credentalsUsername, credentialsService);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
		assertTrue(result.hasFailure("credentialsEmail.email.invalidEmailInUse"));
	}
	
	@Test 
	public void testValidEmailInUse() throws Exception {
		CredentialsEmail credentalsUsername = new CredentialsEmail();
		credentalsUsername.setUser(user);
		credentalsUsername.setEmail("email@email.com");
		
		CredentialsEmailValidator validator = new CredentialsEmailValidator(
				"credentialsEmail", credentalsUsername, credentialsService);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertTrue(result.isValid());
	}
	
}