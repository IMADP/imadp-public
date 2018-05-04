package com.imadp.service.commerce.cart.item;

import com.imadp.core.Property;
import com.imadp.service.commerce.cart.Cart;
import com.imadp.service.commerce.product.Product;
import com.imadp.service.user.AbstractPersistableUser;

/**
 * CartItem
 * 
 * A representation of a shopping cart item.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class CartItem extends AbstractPersistableUser {
	
	// static Properties
	public static final Property<CartItem, Cart> CART = Property.of("cart");
	public static final Property<CartItem, Product> PRODUCT = Property.of("product");
	public static final Property<CartItem, Integer> QUANTITY = Property.of("quantity");
	
	// properties
	private Cart cart;
	private Product product;
	private int quantity;
			
	// constructor
	public CartItem() {
		
	}
	
	// getters and setters
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
}