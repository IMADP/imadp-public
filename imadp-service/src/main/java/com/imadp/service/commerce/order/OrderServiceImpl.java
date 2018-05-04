package com.imadp.service.commerce.order;

import java.util.List;

import org.joda.time.DateTime;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.commerce.order.billing.BillingTransaction.BillingTransactionStatus;
import com.imadp.service.commerce.order.shipping.ShippingTransaction.ShippingTransactionStatus;
import com.imadp.service.user.PersistableUserServiceImpl;


/**
 * OrderServiceImpl
 *
 * The standard implementation of the OrderService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class OrderServiceImpl extends PersistableUserServiceImpl<Order> implements OrderService {

	@Override
	public List<Order> findPaymentCapturedWithin(DateTime start, DateTime end, CriteriaParams<Order> criteriaParams) {
		FindCriteria<Order> findCriteria = findCriteriaBuilder(criteriaParams)
			.whereEqualTo(Order.BILLING_RECEIPT_BILLING_STATUS, BillingTransactionStatus.PAYMENT_CAPTURED)
			.whereAfterOrOnDate(Order.DATE, start)
			.whereBeforeOrOnDate(Order.DATE, end).build();

		return findBy(findCriteria);
	}

	@Override
	public List<Order> findByBillingStatus(BillingTransactionStatus status, CriteriaParams<Order> criteriaParams) {
		return findBy(Order.BILLING_RECEIPT_BILLING_STATUS, status, criteriaParams);
	}

	@Override
	public List<Order> findByShippingStatus(ShippingTransactionStatus status, CriteriaParams<Order> criteriaParams) {
		return findBy(Order.SHIPPING_RECEIPT_SHIPPING_STATUS, status, criteriaParams);
	}

}