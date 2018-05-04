package com.imadp.web.commerce.order.billing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imadp.service.commerce.order.billing.BillingTransaction;

/**
 * IBillingTransactionDispatcher
 * 
 * Receives and processes billing transactions via http notifications.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public interface BillingTransactionDispatcher {

	/**
	 * Retrieves a billing transaction that has been processed remotely.
	 * 
	 * @param request
	 * @param response
	 * @return BillingTransaction
	 */
	public BillingTransaction receiveTransaction(HttpServletRequest request, HttpServletResponse response);
	
}