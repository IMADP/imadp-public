package com.imadp.service.commerce.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.imadp.core.money.Money;
import com.imadp.service.ServiceTestUtil;
import com.imadp.service.test.IMADPServiceTestCase;


/**
 * ProductServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ProductServiceImplTest extends IMADPServiceTestCase {
	Product product;
	
	@Override
	public void before() throws Exception {
		super.before();
		
		product = new Product();
		product.setName("firstName");
		product.setCode("code");
		product.setDescription("description");
		product.setBasePrice(Money.ONE);
		product.setActive(true);
	}
	
	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(product, productService);
	}	
	
	@Test
	public void findByCode() {
		Product foundProduct = productService.findByCode(product.getCode());
		
		assertNull(foundProduct);
		
		productService.save(product);
		
		foundProduct = productService.findByCode(product.getCode());
		
		assertNotNull(foundProduct);
		assertEquals(product, foundProduct);
	}	
	
}