package com.imadp.web.commerce.order.billing.paypal;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.imadp.core.money.Money;
import com.imadp.service.commerce.order.Order;
import com.imadp.service.commerce.order.OrderItem;
import com.imadp.service.commerce.order.billing.BillingTransaction;
import com.imadp.service.commerce.order.billing.BillingTransaction.BillingTransactionStatus;
import com.imadp.service.commerce.product.Product;
import com.imadp.service.location.country.Country;
import com.imadp.web.test.IMADPWebTestCase;


/**
 * BillingTransactionDispatcherPaypalImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BillingTransactionDispatcherPaypalImplTest extends IMADPWebTestCase {
	Order order;

	@Override
	public void before() throws Exception {
		super.before();

		Country country = new Country();
		country.setCode("US");
		country.setName("United States");

		Product product = new Product();
		product.setCode("productCode");
		product.setName("productName");
		product.setBasePrice(Money.ONE);

		order = new Order();
		order.setUid("1");
		order.setLocale(Locale.ENGLISH);

		order.getOrderInvoice().setShippingPrice(Money.ONE);

		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(order);
		orderItem.setProduct(product);
		orderItem.setQuantity(1);
		orderItem.setPrice(Money.ONE);

		order.getOrderInvoice().add(orderItem);

		countryService.save(country);
		productService.save(product);
		orderService.save(order);
	}

	@Test
	public void toTransaction() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("item_number", "productCode");
		request.addParameter("residence_country", "US");
		request.addParameter("invoice", "1");
		request.addParameter("verify_sign", "AKXczEqbFygM3NjOXofqYpuf20KWAGQkSJKYUMlx2gAMLA0rkv7rCIPb");
		request.addParameter("address_country", "United States");
		request.addParameter("address_city", "addressCity");
		request.addParameter("payment_status", "Completed");
		request.addParameter("business", "seller@paypalsandbox.com");
		request.addParameter("address_status", "confirmed");
		request.addParameter("shipping", "1");
		request.addParameter("first_name", "firstName");
		request.addParameter("payer_id", "TESTBUYERID01");
		request.addParameter("payer_email", "buyer@paypalsandbox.com");
		request.addParameter("mc_fee", "0.44");
		request.addParameter("txn_id", "96241316");
		request.addParameter("receiver_email", "seller@paypalsandbox.com");
		request.addParameter("quantity", "1");
		request.addParameter("notify_version", "2.4");
		request.addParameter("txn_type", "cart");
		request.addParameter("mc_gross", "2");
		request.addParameter("payer_status", "verified");
		request.addParameter("mc_currency", "USD");
		request.addParameter("mc_shipping", "1");
		request.addParameter("test_ipn", "1");
		request.addParameter("custom", "custom");
		request.addParameter("mc_gross_1", "0");
		request.addParameter("payment_date", "06:16:09 Jun 24, 2011 PDT");
		request.addParameter("mc_shipping1", "0");
		request.addParameter("charset", "windows-1252");
		request.addParameter("address_country_code", "US");
		request.addParameter("address_zip", "addressZip");
		request.addParameter("address_state", "addressState");
		request.addParameter("mc_handling", "0");
		request.addParameter("mc_handling1", "0");
		request.addParameter("tax", "0");
		request.addParameter("item_name", "productName");
		request.addParameter("address_name", "addressName");
		request.addParameter("last_name", "lastName");
		request.addParameter("payment_type", "instant");
		request.addParameter("receiver_id", "TESTSELLERID1");
		request.addParameter("address_street", "addressStreet");

		MockHttpServletResponse response = new MockHttpServletResponse();

		BillingTransaction billingTransaction = billingTransactionDispatcherPaypal.receiveTransaction(request, response);
		Order receivedOrder = billingTransaction.getOrder();

		assertEquals(order, receivedOrder);

		assertEquals("firstName", receivedOrder.getShippingDetails().getFirstName());
		assertEquals("lastName", receivedOrder.getShippingDetails().getLastName());
		assertEquals("addressStreet", receivedOrder.getShippingDetails().getAddress1());
		assertEquals("addressCity", receivedOrder.getShippingDetails().getCity());
		assertEquals("addressState", receivedOrder.getShippingDetails().getState());
		assertEquals("addressZip", receivedOrder.getShippingDetails().getPostalCode());
		assertEquals("US", receivedOrder.getShippingDetails().getCountry().getCode());
		assertEquals("buyer@paypalsandbox.com", receivedOrder.getShippingDetails().getEmail());

		assertEquals(BillingTransactionStatus.PAYMENT_CAPTURED, billingTransaction.getStatus());
	}

}