package com.imadp.service.commerce.order;

import com.imadp.service.commerce.card.Card;



/**
 * ShippingServiceImpl
 *
 * Tests implementation of the AbstractOrderFacade.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class OrderFacadeImpl extends AbstractOrderFacade {

	/**
	 * Places an order.
	 * 
	 * @param order
	 * @param card
	 * @return Order
	 */
	public Order placeOrder(Order order, Card card) {
		return billAndShipOrder(order, card);
	}
	
}