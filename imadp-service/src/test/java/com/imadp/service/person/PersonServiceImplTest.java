package com.imadp.service.person;

import org.joda.time.DateTime;
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
public class PersonServiceImplTest extends IMADPServiceTestCase {
	Person person;
	
	@Override
	public void before() throws Exception {
		super.before();
		
		User user = new User();
		
		person = new Person();
		person.setFirstName("firstName");
		person.setLastName("lastName");
		person.setMiddleName("middleName");
		person.setPrefix("prefix");
		person.setGender(Person.Gender.MALE);
		person.setBirthdate(new DateTime());
		person.setUser(user);
		
		userService.save(user);
	}
	
	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(person, personService);
	}	

}