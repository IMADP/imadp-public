package com.imadp.service.user;

import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.test.IMADPServiceTestCase;


/**
 * UserServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class UserServiceImplTest extends IMADPServiceTestCase {
	User user;
	
	@Override
	public void before() throws Exception {
		super.before();
		
		user = new User();		
	}
	
	@Test
	public void commonPersistableOperations() throws Exception {		
		ServiceTestUtil.assertPersistable(user, userService);
	}
			
}