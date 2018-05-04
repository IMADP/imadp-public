package com.imadp.service.commerce.order.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;

import com.imadp.core.money.Money;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.service.commerce.order.Order;
import com.imadp.service.commerce.order.OrderService;
import com.imadp.service.commerce.order.shipping.ShippingTransaction.ShippingTransactionStatus;
import com.imadp.service.report.ReportServiceImpl;

/**
 * OrderReportServiceImpl
 *
 * The standard implementation of the OrderReportService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class OrderReportServiceImpl extends ReportServiceImpl<OrderReport> implements OrderReportService {
	private OrderService orderService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(orderService);
	}

	@Override
	public List<Order> getRequestedOrders() {
		return orderService.findByShippingStatus(ShippingTransactionStatus.REQUESTED, CriteriaParams.<Order>of(Results.ALL));
	}

	@Override
	public OrderReport generateDailyOrderReport() {
		DateTime dateTime = new DateTime();
		return generateOrderReport(dateTime.minusDays(1), dateTime);
	}

	@Override
	public OrderReport generateWeeklyOrderReport() {
		DateTime dateTime = new DateTime();
		return generateOrderReport(dateTime.minusWeeks(1), dateTime);
	}

	@Override
	public OrderReport generateMonthlyOrderReport() {
		DateTime dateTime = new DateTime();
		return generateOrderReport(dateTime.minusMonths(1), dateTime);
	}

	@Override
	public OrderReport generateAnnualOrderReport() {
		DateTime dateTime = new DateTime();
		return generateOrderReport(dateTime.minusYears(1), dateTime);
	}

	@Override
	public OrderReport generateOrderReport(DateTime start, DateTime end) {
		List<Order> orders = orderService.findPaymentCapturedWithin(start, end, CriteriaParams.<Order>of(Results.ALL));

		Money totalDiscountsPrice = Money.ZERO;
		Money totalItemsPrice = Money.ZERO;
		Money totalShippingPrice = Money.ZERO;
		Money totalTaxPrice = Money.ZERO;
		Money totalPrice = Money.ZERO;

		Map<String, AtomicLong> billingProcessors = new HashMap<>();

		for(Order order : orders)
		{
			totalDiscountsPrice = totalDiscountsPrice.add(order.getOrderInvoice().getDiscountsPrice());
			totalItemsPrice = totalItemsPrice.add(order.getOrderInvoice().getItemsPrice());
			totalShippingPrice = totalShippingPrice.add(order.getOrderInvoice().getShippingPrice());
			totalTaxPrice = totalTaxPrice.add(order.getOrderInvoice().getTaxPrice());
			totalPrice = totalPrice.add(order.getOrderInvoice().getTotalPrice());

			// add the billing processor to the processor map and increment the occurrence
			String processor = order.getBillingReceipt().getLastTransaction().getProcessor();
			AtomicLong count = billingProcessors.get(processor);

			if(count == null)
			{
				count = new AtomicLong(0);
				billingProcessors.put(processor, count);
			}

			count.incrementAndGet();
		}

		OrderReport orderReport = new OrderReport();
		orderReport.setDate(new DateTime());
		orderReport.setStartDate(start);
		orderReport.setEndDate(end);
		orderReport.setOrderCount(orders.size());
		orderReport.setTotalDiscountsPrice(totalDiscountsPrice);
		orderReport.setTotalItemsPrice(totalItemsPrice);
		orderReport.setTotalShippingPrice(totalShippingPrice);
		orderReport.setTotalTaxPrice(totalTaxPrice);
		orderReport.setTotalPrice(totalPrice);
		orderReport.setBillingProcessors(billingProcessors);
		return orderReport;
	}

	// getters and setters
	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

}