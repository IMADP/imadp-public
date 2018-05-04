package com.imadp.service.commerce.order;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.IntRange;
import org.junit.Test;

import com.imadp.core.money.Money;
import com.imadp.service.test.IMADPServiceTestCase;


/**
 * OrderItemPriceTierTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class OrderItemPriceTierTest extends IMADPServiceTestCase {
	OrderItemPriceTier orderItemPriceTier;

	@Override
	public void before() throws Exception {
		super.before();

		Map<Money, IntRange> priceTiers = new HashMap<>();
		priceTiers.put(new Money("11.99"), new IntRange(1, 24));
		priceTiers.put(new Money("8.99"), new IntRange(25, 49));
		priceTiers.put(new Money("7.99"), new IntRange(50, 99));
		priceTiers.put(new Money("6.99"), new IntRange(100, 200));

		orderItemPriceTier = new OrderItemPriceTier(priceTiers);
	}

	@Test
	public void testPriceTiers() {
		assertEquals(new Money("11.99"), orderItemPriceTier.getPrice(1));
		assertEquals(new Money("11.99"), orderItemPriceTier.getPrice(10));
		assertEquals(new Money("11.99"), orderItemPriceTier.getPrice(24));
		assertEquals(new Money("8.99"), orderItemPriceTier.getPrice(25));
		assertEquals(new Money("8.99"), orderItemPriceTier.getPrice(35));
		assertEquals(new Money("8.99"), orderItemPriceTier.getPrice(49));
		assertEquals(new Money("7.99"), orderItemPriceTier.getPrice(50));
		assertEquals(new Money("7.99"), orderItemPriceTier.getPrice(75));
		assertEquals(new Money("7.99"), orderItemPriceTier.getPrice(99));
		assertEquals(new Money("6.99"), orderItemPriceTier.getPrice(100));
		assertEquals(new Money("6.99"), orderItemPriceTier.getPrice(150));
		assertEquals(new Money("6.99"), orderItemPriceTier.getPrice(200));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPriceTiersInvalid() {
		orderItemPriceTier.getPrice(0);
	}

}