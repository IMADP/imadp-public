package com.imadp.service.commerce.order.report;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.core.money.Money;
import com.imadp.service.ServiceTestUtil;
import com.imadp.service.commerce.order.Order;
import com.imadp.service.commerce.order.OrderInvoice;
import com.imadp.service.commerce.order.OrderItem;
import com.imadp.service.commerce.order.billing.BillingReceipt;
import com.imadp.service.commerce.order.billing.BillingTransaction;
import com.imadp.service.commerce.order.billing.BillingTransaction.BillingTransactionStatus;
import com.imadp.service.test.IMADPServiceTestCase;


/**
 * OrderReportServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class OrderReportServiceImplTest extends IMADPServiceTestCase {
	OrderReport orderReport;

	@Override
	public void before() throws Exception {
		super.before();

		orderReport = new OrderReport();
		orderReport.setDate(new DateTime());
		orderReport.setStartDate(new DateTime());
		orderReport.setEndDate(new DateTime());
		orderReport.setTotalDiscountsPrice(Money.ONE);
		orderReport.setTotalItemsPrice(Money.ONE);
		orderReport.setTotalShippingPrice(Money.ONE);
		orderReport.setTotalTaxPrice(Money.ONE);
		orderReport.setTotalPrice(Money.ONE);
		orderReport.setOrderCount(1);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(orderReport, orderReportService);
	}

	@Test
	public void billingProcessors() {
		Order order = getOrder();
		order.getBillingReceipt().getLastTransaction().setProcessor("p1");
		orderService.save(order);

		order = getOrder();
		order.getBillingReceipt().getLastTransaction().setProcessor("p2");
		orderService.save(order);

		order = getOrder();
		order.getBillingReceipt().getLastTransaction().setProcessor("p2");
		orderService.save(order);

		OrderReport orderReport = orderReportService.generateOrderReport(new DateTime().minusDays(1), new DateTime());
		assertEquals(1L, orderReport.getBillingProcessors().get("p1").longValue());
		assertEquals(2L, orderReport.getBillingProcessors().get("p2").longValue());
	}

	/**
	 * Returns a sample order.
	 *
	 * @return Order
	 */
	private Order getOrder() {
		Order order = new Order();
		order.setDate(new DateTime());

		OrderInvoice orderInvoice = new OrderInvoice();
		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(order);
		orderItem.setQuantity(1);
		orderItem.setPrice(Money.ONE);
		orderInvoice.add(orderItem);
		order.setOrderInvoice(orderInvoice);

		BillingReceipt billingReceipt = new BillingReceipt();
		BillingTransaction billingTransaction = new BillingTransaction();
		billingTransaction.setDate(new DateTime());
		billingTransaction.setData("data");
		billingTransaction.setTransactionId("transactionId");
		billingTransaction.setCustomData("customData");
		billingTransaction.setStatus(BillingTransactionStatus.PAYMENT_CAPTURED);
		billingReceipt.addTransaction(billingTransaction);
		order.setBillingReceipt(billingReceipt);

		return order;
	}

}