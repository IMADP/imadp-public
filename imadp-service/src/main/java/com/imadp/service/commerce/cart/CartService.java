package com.imadp.service.commerce.cart;

import com.imadp.service.PersistableService;
import com.imadp.service.commerce.cart.item.CartItem;


/**
 * ICartService
 * 
 * Provides common retrieval operations for Cart objects.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public interface CartService extends PersistableService<Cart> {
	
	/**
	 * Saves a cartItem.
	 * 
	 * @param cartItem
	 */
	public void saveCartItem(CartItem cartItem);

	/**
	 * Deletes a cartItem.
	 * 
	 * @param cartItem
	 */
	public void deleteCartItem(CartItem cartItem);
	
}