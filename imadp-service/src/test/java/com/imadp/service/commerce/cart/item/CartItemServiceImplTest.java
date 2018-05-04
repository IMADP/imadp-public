package com.imadp.service.commerce.cart.item;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.commerce.cart.Cart;
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
public class CartItemServiceImplTest extends IMADPServiceTestCase {
	CartItem cartItem;

	@Override
	public void before() throws Exception {
		super.before();

		Product product = new Product();
		product.setName("name");

		Cart cart = new Cart();
		cart.setDate(new DateTime());

		cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setQuantity(1);
		cartItem.setCart(cart);

		productService.save(product);

		cartService.save(cart);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(cartItem, cartItemService);
	}

}