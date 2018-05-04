package com.imadp.service.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;
import org.springframework.test.annotation.ExpectedException;

import com.imadp.dao.DaoException;
import com.imadp.service.account.authority.Authority;
import com.imadp.service.account.credentials.Credentials;
import com.imadp.service.account.credentials.CredentialsEmail;
import com.imadp.service.account.credentials.CredentialsPassword;
import com.imadp.service.account.credentials.CredentialsResetPassword;
import com.imadp.service.account.credentials.CredentialsUsername;
import com.imadp.service.test.IMADPServiceTestCase;
import com.imadp.service.user.User;

/**
 * AccountFacadeImplTest

 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class AccountFacadeImplTest extends IMADPServiceTestCase {

	public static class CreateAccount extends IMADPServiceTestCase {
		Account account;
		Authority authority;

		@Override
		public void before() throws Exception {
			super.before();

			authority = new Authority();
			authority.setName("name");

			User user = new User();

			CredentialsEmail credentialsEmail = new CredentialsEmail();
			credentialsEmail.setEmail("email@email.com");

			CredentialsUsername credentialsUsername = new CredentialsUsername();
			credentialsUsername.setUsername("username");

			CredentialsPassword credentialsPassword = new CredentialsPassword();
			credentialsPassword.setPassword("password");
			credentialsPassword.setConfirmPassword("password");

			account = new Account();
			account.setUser(user);
			account.setCredentialsEmail(credentialsEmail);
			account.setCredentialsUsername(credentialsUsername);
			account.setCredentialsPassword(credentialsPassword);

			authorityService.save(authority);
		}

		@Test
		public void createAccount() {
			assertEquals(0, userService.findCount());
			assertEquals(0, credentialsService.findCount());

			accountFacade.createAccount(account, authority);

			assertEquals(1, userService.findCount());
			assertEquals(1, credentialsService.findCount());

			Credentials credentials = credentialsService.findFirstByUser(account.getUser());
			User userSaved = userService.get(credentials.getUser().getId());
			Credentials credentialsSaved = credentialsService.get(credentials.getId());

			assertNotNull(userSaved);
			assertNotNull(credentialsSaved);
			assertEquals(credentials.getUser().getId(), userSaved.getId());
			assertEquals(credentials.getUser().getId(), credentialsSaved.getUser().getId());
			assertEquals(1, credentials.getAuthorities().size());
		}

		@Test
		public void resetPassword() {
			accountFacade.createAccount(account, authority);

			User user = account.getUser();
			String oldPassword = accountFacade.getCredentials(user).getPassword();

			CredentialsResetPassword credentialsResetPassword = new CredentialsResetPassword();
			credentialsResetPassword.setEmail(account.getCredentialsEmail().getEmail());

			accountFacade.resetPassword(credentialsResetPassword, Locale.ENGLISH);

			String newPassword = accountFacade.getCredentials(user).getPassword();

			assertFalse(accountFacade.getDigestor().isEqualDigest(oldPassword, newPassword));
		}

	}

	public static class RemoveAccount extends IMADPServiceTestCase {
		Account account;

		@Override
		public void before() throws Exception {
			super.before();

			Authority authority = new Authority();
			authority.setName("name");

			User user = new User();

			CredentialsEmail credentialsEmail = new CredentialsEmail();
			credentialsEmail.setEmail("email@email.com");

			CredentialsUsername credentialsUsername = new CredentialsUsername();
			credentialsUsername.setUsername("username");

			CredentialsPassword credentialsPassword = new CredentialsPassword();
			credentialsPassword.setPassword("password");
			credentialsPassword.setConfirmPassword("password");

			account = new Account();
			account.setUser(user);
			account.setCredentialsEmail(credentialsEmail);
			account.setCredentialsUsername(credentialsUsername);
			account.setCredentialsPassword(credentialsPassword);

			authorityService.save(authority);
			accountFacade.createAccount(account, authority);
		}

		@Test
		public void deleteAccount() {
			assertEquals(1, userService.findCount());
			assertEquals(1, credentialsService.findCount());

			Credentials credentials = credentialsService.findFirstByUser(account.getUser());
			User userSaved = userService.get(credentials.getUser().getId());
			Credentials credentialsSaved = credentialsService.get(credentials.getId());

			assertNotNull(userSaved);
			assertNotNull(credentialsSaved);
			assertEquals(credentials.getUser().getId(), userSaved.getId());
			assertEquals(credentials.getUser().getId(), credentialsSaved.getUser().getId());

			accountFacade.deleteAccount(account.getUser());

			assertEquals(0, userService.findCount());
			assertEquals(0, credentialsService.findCount());
		}

	}

	public static class AccountException extends IMADPServiceTestCase {
		Account account;
		Authority authority;
		Credentials existingCredentials;

		@Override
		public void before() throws Exception {
			super.before();

			existingCredentials = new Credentials();
			existingCredentials.setUsername("username");

			authority = new Authority();
			authority.setName("name");

			User user = new User();

			CredentialsEmail credentialsEmail = new CredentialsEmail();
			credentialsEmail.setEmail("email@email.com");

			CredentialsUsername credentialsUsername = new CredentialsUsername();
			credentialsUsername.setUsername("username");

			CredentialsPassword credentialsPassword = new CredentialsPassword();
			credentialsPassword.setPassword("password");
			credentialsPassword.setConfirmPassword("password");

			account = new Account();
			account.setUser(user);
			account.setCredentialsEmail(credentialsEmail);
			account.setCredentialsUsername(credentialsUsername);
			account.setCredentialsPassword(credentialsPassword);

			credentialsService.save(existingCredentials);
	        authorityService.save(authority);
		}

		@Test
		public void createAccountUnsuccessfulTransaction() {
			assertEquals(0, userService.findCount());
			assertEquals(1, credentialsService.findCount());

			try
			{
				accountFacade.createAccount(account, authority);
		    }
			catch(DaoException exception)
			{

			}

			assertEquals(0, userService.findCount());
	        assertEquals(1, credentialsService.findCount());
	  	}

		@Test
		@ExpectedException(DaoException.class)
		public void createAccountException() {
	      	assertEquals(0, userService.findCount());
			assertEquals(1, credentialsService.findCount());

			accountFacade.createAccount(account, authority);
		}

	}

}