package com.imadp.service.commerce.cart;

import org.apache.commons.lang.Validate;

import com.imadp.service.commerce.cart.item.CartItem;
import com.imadp.service.commerce.cart.item.CartItemService;
import com.imadp.service.user.PersistableUserServiceImpl;

/**
 * CartServiceImpl
 * 
 * The standard implementation of the CartService.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class CartServiceImpl extends PersistableUserServiceImpl<Cart> implements CartService {
	private CartItemService cartItemService;
	
	@Override
	protected void onInit() {
		super.onInit();
		
		Validate.notNull(cartItemService);
	}
	
	@Override
	public void saveCartItem(CartItem cartItem) {
		cartItemService.save(cartItem);
		
		clearUserCaches(cartItem.getUser());
	}
	
	@Override
	public void deleteCartItem(CartItem cartItem) {
		cartItemService.delete(cartItem);
		
		clearUserCaches(cartItem.getUser());
	}

	// getters and setters
	public CartItemService getCartItemService() {
		return cartItemService;
	}

	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}
	
}