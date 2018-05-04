package com.imadp.service.commerce.order;

import java.math.BigDecimal;

import com.imadp.core.Property;
import com.imadp.core.money.Money;
import com.imadp.service.user.AbstractPersistableUser;

/**
 * OrderDiscount
 * 
 * A discount applied to a given order.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class OrderDiscount extends AbstractPersistableUser {
	
	// static Properties
	public static final Property<OrderDiscount, Order> ORDER = Property.of("order");
	public static final Property<OrderDiscount, BigDecimal> PRICE_AMOUNT = Property.of("price.amount");
	public static final Property<OrderDiscount, String> PRICE_CURRENCY = Property.of("price.currency");

	// properties
	private Order order;
	private Money price;
	
	// constructor
	public OrderDiscount() {
		
	}
	
	// getters and setters
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