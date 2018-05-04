package com.imadp.service.commerce.cart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.commerce.cart.item.CartItem;
import com.imadp.service.commerce.product.Product;
import com.imadp.service.test.IMADPServiceTestCase;


/**
 * CartServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CartServiceImplTest extends IMADPServiceTestCase {
	Cart cart;
	
	@Override
	public void before() throws Exception {
		super.before();
		
		cart = new Cart();
		cart.setDate(new DateTime());
	}
	
	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(cart, cartService);
	}	
	
	@Test
	public void cartItems() {
		cartService.save(cart);
		
		Product product1 = new Product();
		product1.setName("1");
		
		Product product2 = new Product();
		product2.setName("2");
		
		productService.save(product1);
		productService.save(product2);
		
		CartItem cartItem1 = new CartItem();
		cartItem1.setProduct(product1);
		cartItem1.setQuantity(1);
		cartItem1.setCart(cart);
		
		cartService.saveCartItem(cartItem1);
		
		CartItem cartItem2 = new CartItem();
		cartItem2.setProduct(product2);
		cartItem2.setQuantity(1);
		cartItem2.setCart(cart);
		
		cartService.saveCartItem(cartItem2);
				
		Cart cartResult = cartService.get(cart.getId());
		
		assertNotNull(cartResult);
		assertEquals(2, cartResult.getCartItems().size());
		
		cartService.deleteCartItem(cartItem1);
		
		cartService.save(cartResult);
		
		cartResult = cartService.get(cart.getId());
		
		assertNotNull(cartResult);
		assertEquals(1, cartResult.getCartItems().size());
	}	
	
}