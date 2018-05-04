package com.imadp.service.commerce.order;

import java.util.List;

import org.joda.time.DateTime;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.commerce.order.billing.BillingTransaction.BillingTransactionStatus;
import com.imadp.service.commerce.order.shipping.ShippingTransaction.ShippingTransactionStatus;
import com.imadp.service.user.PersistableUserService;

/**
 * IOrderService
 *
 * Provides common retrieval operations for Order objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface OrderService extends PersistableUserService<Order> {

	/**
	 * Returns a List of orders where the billing receipt status is a payment capture.
	 *
	 * @param start
	 * @param end
	 * @param criteriaParams
	 * @return List<Order>
	 */
	public List<Order> findPaymentCapturedWithin(DateTime start, DateTime end, CriteriaParams<Order> criteriaParams);

	/**
	 * Returns a List of orders by the given billing status and criteria params.
	 *
	 * @param status
	 * @param criteriaParams
	 * @return List<Order>
	 */
	public List<Order> findByBillingStatus(BillingTransactionStatus status, CriteriaParams<Order> criteriaParams);

	/**
	 * Returns a List of orders by the given shipping status and criteria params.
	 *
	 * @param status
	 * @param criteriaParams
	 * @return List<Order>
	 */
	public List<Order> findByShippingStatus(ShippingTransactionStatus status, CriteriaParams<Order> criteriaParams);

}