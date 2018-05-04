package com.imadp.service.location.country;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.test.IMADPServiceTestCase;


/**
 * CountryServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CountryServiceImplTest extends IMADPServiceTestCase {
	Country country;
	
	@Override
	public void before() throws Exception {
		super.before();
		
		country = new Country();
		country.setCode("co");
		country.setName("name");
	}
	
	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(country, countryService);
	}	
	
	@Test
	public void findByCode() {
		countryService.save(country);
		
		Country result = countryService.findByCode(country.getCode());
		
		assertNotNull(result);
		assertEquals(country, result);
	}	
	
}