package com.tracktacular.service.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.account.credentials.Credentials;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * CredentialsServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CredentialsServiceImplTest extends TracktacularServiceTestCase {
	
	public static class PersistableOperations extends TracktacularServiceTestCase {
		Credentials credentials;
		
		@Override
		public void before() throws Exception {
			super.before();
	
			User user = new User();
			
			credentials = new Credentials(user);
			credentials.setPassword("password");
			credentials.setVerificationId("verificationId");
			credentials.setFacebookId(System.currentTimeMillis());
			
			userService.save(user);
		}
		
		@Test
		public void commonPersistableOperations() {
			ServiceTestUtil.assertPersistable(credentials, credentialsService);
		}	
	
	}
	
	public static class UserDetailsOperations extends TracktacularServiceTestCase {		
		Credentials credentials;
		
		@Override
		public void before() throws Exception {
			super.before();
	
			User user = new User();
			
			credentials = new Credentials(user);
			credentials.setPassword("password");
			credentials.setVerificationId("verificationId");
			credentials.setFacebookId(System.currentTimeMillis());
			
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
	
		@Test
		public void findByFacebookId() {
			Credentials loadedCredentials = credentialsService.findBy(credentials.getFacebookId());
	
			assertNotNull(loadedCredentials);
			assertEquals(loadedCredentials.getFacebookId(), credentials.getFacebookId());
		}

	}
	
}