package com.imadp.service.commerce.cart;

import java.util.Set;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.service.commerce.cart.item.CartItem;
import com.imadp.service.user.AbstractPersistableUser;


/**
 * Cart
 * 
 * A representation of a shopping cart.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class Cart extends AbstractPersistableUser {
	
	// static Properties
	public static final Property<Cart, DateTime> DATE = Property.of("date");
	
	// properties
	private DateTime date;
	private Set<CartItem> cartItems;
		
	// constructor
	public Cart() {
		
	}
	
	// getters and setters	
	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

}