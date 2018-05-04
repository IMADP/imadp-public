package com.imadp.service.commerce.card;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.test.IMADPServiceTestCase;


/**
 * CardServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CardServiceImplTest extends IMADPServiceTestCase {
	Card card;
	
	@Override
	public void before() throws Exception {
		super.before();
		
		card = new Card();
		card.setType("type");
		card.setNumber("number");
		card.setCvv("cvv");
		card.setExpirationMonth(1);
		card.setExpirationYear(1);
	}
	
	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(card, cardService);
	}	
	
	@Test
	public void getExpirationMonths() {
		int size = cardService.getExpirationMonths().size();
		assertEquals(12, size);
		assertEquals(Integer.valueOf(1), cardService.getExpirationMonths().get(0));
		assertEquals(Integer.valueOf(12), cardService.getExpirationMonths().get(size - 1));
	}	
	
	@Test
	public void getExpirationYears() {
		int size = cardService.getExpirationYears().size();
		assertEquals(10, size);
		assertEquals(Integer.valueOf(new DateTime().getYear()), cardService.getExpirationYears().get(0));
		assertEquals(Integer.valueOf(new DateTime().getYear() + 9), cardService.getExpirationYears().get(size - 1));
	}	
	
}