package com.imadp.service.commerce.order.billing;

import com.imadp.service.commerce.card.Card;
import com.imadp.service.commerce.order.Order;

/**
 * IBillingService
 * 
 * Provides billing services for card processing. 
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public interface BillingService {

	/**
	 * Processes a card capture and stores the result in a billingTransaction from the order's billing receipt.
	 * 
	 * @note Implementing classes should NOT add the billing transaction to the billing receipt.
	 * 
	 * @param order
	 * @param card
	 * @return BillingTransaction
	 */
	public BillingTransaction cardCapture(Order order, Card card);

}