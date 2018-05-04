package com.imadp.service.account.credentials;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.test.IMADPServiceTestCase;
import com.imadp.service.user.User;


/**
 * CredentialsServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CredentialsServiceImplTest extends IMADPServiceTestCase {
	
	public static class PersistableOperations extends IMADPServiceTestCase {
		Credentials credentials1;
		Credentials credentials2;
		
		@Override
		public void before() throws Exception {
			super.before();
	
			User user1 = new User();
			User user2 = new User();
			
			credentials1 = new Credentials(user1);
			credentials1.setUser(user1);
			credentials1.setUsername("username");
			credentials1.setEmail("email@email.com");
			credentials1.setFacebookId(System.currentTimeMillis());
			
			credentials2 = new Credentials(user2);
			credentials2.setUser(user2);
			credentials2.setUsername("username2");
			credentials2.setEmail("email2@email.com");
			credentials2.setFacebookId(System.currentTimeMillis());
			
			userService.save(user1);
			userService.save(user2);
		}
		
		@Test
		public void commonPersistableOperations() {
			ServiceTestUtil.assertPersistable(credentials1, credentialsService);
		}	
			
		@Test
		public void findByFacebookId() {
			credentialsService.save(credentials1);
			credentialsService.save(credentials2);
			
			Credentials credentialsByFacebookId = credentialsService.findBy(credentials1.getFacebookId());
	
			assertNotNull(credentialsByFacebookId);
			assertEquals(credentialsByFacebookId.getFacebookId(), credentials1.getFacebookId());
		}
		
		@Test
		public void usernameNotInUse() {			
			credentialsService.save(credentials1);
			credentialsService.save(credentials2);
			
			assertFalse(credentialsService.isUsernameInUse("unusedUsername", null));
			assertFalse(credentialsService.isUsernameInUse("unusedUsername", credentials1.getUser()));
			assertFalse(credentialsService.isUsernameInUse("unusedUsername", credentials2.getUser()));
			assertFalse(credentialsService.isUsernameInUse(credentials1.getUsername(), credentials1.getUser()));
			assertFalse(credentialsService.isUsernameInUse(credentials1.getUsernameLowerCase(), credentials1.getUser()));
			assertFalse(credentialsService.isUsernameInUse(credentials2.getUsername(), credentials2.getUser()));
			assertFalse(credentialsService.isUsernameInUse(credentials2.getUsernameLowerCase(), credentials2.getUser()));
		}
		
		@Test
		public void usernameInUse() {			
			credentialsService.save(credentials1);
			credentialsService.save(credentials2);
			
			assertTrue(credentialsService.isUsernameInUse(credentials1.getUsername(), null));
			assertTrue(credentialsService.isUsernameInUse(credentials1.getUsernameLowerCase(), null));
			assertTrue(credentialsService.isUsernameInUse(credentials1.getUsername(), credentials2.getUser()));
			assertTrue(credentialsService.isUsernameInUse(credentials1.getUsernameLowerCase(), credentials2.getUser()));
			assertTrue(credentialsService.isUsernameInUse(credentials2.getUsername(), null));
			assertTrue(credentialsService.isUsernameInUse(credentials2.getUsernameLowerCase(), null));
			assertTrue(credentialsService.isUsernameInUse(credentials2.getUsername(), credentials1.getUser()));
			assertTrue(credentialsService.isUsernameInUse(credentials2.getUsernameLowerCase(), credentials1.getUser()));
		}
		
		@Test
		public void emailNotInUse() {			
			credentialsService.save(credentials1);
			credentialsService.save(credentials2);
			
			assertFalse(credentialsService.isEmailInUse("unusedEmail", null));
			assertFalse(credentialsService.isEmailInUse("unusedEmail", credentials1.getUser()));
			assertFalse(credentialsService.isEmailInUse("unusedEmail", credentials2.getUser()));
			assertFalse(credentialsService.isEmailInUse(credentials1.getEmail(), credentials1.getUser()));
			assertFalse(credentialsService.isEmailInUse(credentials2.getEmail(), credentials2.getUser()));
		}
		
		@Test
		public void emailInUse() {			
			credentialsService.save(credentials1);
			credentialsService.save(credentials2);
			
			assertTrue(credentialsService.isEmailInUse(credentials1.getEmail(), null));
			assertTrue(credentialsService.isEmailInUse(credentials1.getEmail(), credentials2.getUser()));
			assertTrue(credentialsService.isEmailInUse(credentials2.getEmail(), null));
			assertTrue(credentialsService.isEmailInUse(credentials2.getEmail(), credentials1.getUser()));
		}
		
	}
	
	public static class UserDetailsOperations extends IMADPServiceTestCase {		
		Credentials credentials;
		
		@Override
		public void before() throws Exception {
			super.before();
	
			User user = new User();
			
			credentials = new Credentials(user);
			credentials.setUser(user);
			credentials.setFacebookId(System.currentTimeMillis());
			credentials.setUsername("username");
			credentials.setEmail("email@email.com");
			
			userService.save(user);
			credentialsService.save(credentials);
		} 
	
		@Test
		public void loadUserByUsername() {
			UserDetails userDetails = credentialsService.loadUserByUsername(credentials.getUsername());
	
			assertNotNull(userDetails);
			assertEquals(userDetails.getUsername(), credentials.getUsername());
		}
	
		@Test
		public void loadUserByEmail() {
			UserDetails userDetails = credentialsService.loadUserByUsername(credentials.getEmail());
	
			assertNotNull(userDetails);
			assertEquals(userDetails.getUsername(), credentials.getUsername());
		}
		
	}
	
}