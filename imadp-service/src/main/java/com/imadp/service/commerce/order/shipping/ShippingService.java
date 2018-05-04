package com.imadp.service.commerce.order.shipping;

import com.imadp.service.commerce.order.Order;



/**
 * IShippingService
 * 
 * Provides shipping services for orders. 
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public interface ShippingService {

	/**
	 * Request the initiation of the shipping process for the given order. 
	 * 
	 * @param order
	 * @return ShippingTransaction
	 */
	public ShippingTransaction requestShipping(Order order);

}