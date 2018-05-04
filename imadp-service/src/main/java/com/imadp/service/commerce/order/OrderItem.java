package com.imadp.service.commerce.order;

import java.math.BigDecimal;

import com.imadp.core.Property;
import com.imadp.core.money.Money;
import com.imadp.service.commerce.product.Product;
import com.imadp.service.user.AbstractPersistableUser;

/**
 * OrderItem
 * 
 * A representation of a purchase order item.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class OrderItem extends AbstractPersistableUser {
	
	// static Properties
	public static final Property<OrderItem, Order> ORDER = Property.of("order");
	public static final Property<OrderItem, Product> PRODUCT = Property.of("product");
	public static final Property<OrderItem, Integer> QUANTITY = Property.of("quantity");
	public static final Property<OrderItem, BigDecimal> PRICE_AMOUNT = Property.of("price.amount");
	public static final Property<OrderItem, String> PRICE_CURRENCY = Property.of("price.currency");

	// properties
	private Order order;
	private Product product;
	private Money price;
	private int quantity;
			
	// constructor
	public OrderItem() {
		
	}
	
	/**
	 * Gets the total price.
	 * 
	 * @return Money
	 */
	public Money getTotalPrice() {
		return price.multiply(quantity);
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Money getPrice() {
		return price;
	}

	public void setPrice(Money price) {
		this.price = price;
	}

}