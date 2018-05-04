package com.imadp.service.commerce.order;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * OrderException
 *
 * The exception thrown when unable to process an order through the order workflow.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class OrderException extends RuntimeException {
	private Order order;
	
	// constructor
	public OrderException(String message, Exception exception, Order order) {
		super(message, exception);
		this.order = order;
	}

	// getters and setters
	public Order getOrder() {
		return order;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).
		appendSuper(super.toString()).
		append("orderId", order == null ? "null" : order.getId()).
		toString();
	}
	
}