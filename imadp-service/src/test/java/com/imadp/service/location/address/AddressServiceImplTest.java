package com.imadp.service.location.address;

import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.location.country.Country;
import com.imadp.service.test.IMADPServiceTestCase;
import com.imadp.service.user.User;


/**
 * AddressServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class AddressServiceImplTest extends IMADPServiceTestCase {
	Address address;
	
	@Override
	public void before() throws Exception {
		super.before();
		
		User user = new User();
		Country country = new Country();
		
		address = new Address();
		address.setAddress1("address1");
		address.setAddress2("address2");
		address.setCity("city");
		address.setHomePhone("homePhone");
		address.setState("state");
		address.setPostalCode("postalCode");
		address.setUser(user);
		address.setCountry(country);
		
		userService.save(user);
		countryService.save(country);
	}
				
	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(address, addressService);
	}
		
}