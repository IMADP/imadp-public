package com.imadp.service.account.authority;

import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.test.IMADPServiceTestCase;
import com.imadp.service.user.User;


/**
 * AuthorityServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class AuthorityServiceImplTest extends IMADPServiceTestCase {
	Authority authority;
	
	@Override
	public void before() throws Exception {
		super.before();
		
		User user = new User();
		
		authority = new Authority();
		authority.setName("name");
		
		userService.save(user);
	}
	
	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(authority, authorityService);
	}	
	
}