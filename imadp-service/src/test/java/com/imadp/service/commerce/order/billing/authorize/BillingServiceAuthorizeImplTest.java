package com.imadp.service.commerce.order.billing.authorize;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.core.money.Money;
import com.imadp.service.commerce.card.Card;
import com.imadp.service.commerce.order.Order;
import com.imadp.service.commerce.order.OrderInvoice;
import com.imadp.service.commerce.order.OrderItem;
import com.imadp.service.commerce.order.billing.BillingDetails;
import com.imadp.service.commerce.order.billing.BillingTransaction;
import com.imadp.service.commerce.order.billing.BillingTransaction.BillingTransactionStatus;
import com.imadp.service.commerce.order.shipping.ShippingDetails;
import com.imadp.service.commerce.product.Product;
import com.imadp.service.location.country.Country;
import com.imadp.service.test.IMADPServiceTestCase;


/**
 * BillingServiceAuthorizeImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BillingServiceAuthorizeImplTest extends IMADPServiceTestCase {
	Order order;
	Card card;

	@Override
	public void before() throws Exception {
		super.before();

		Country country = new Country();
		country.setCode("US");

		Product product = new Product();

		order = new Order();
		order.setDate(new DateTime());

		OrderInvoice orderInvoice = new OrderInvoice();
		orderInvoice.setShippingPrice(Money.ONE);
		orderInvoice.setTaxPrice(Money.ONE);

		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(order);
		orderItem.setProduct(product);
		orderItem.setQuantity(1);
		orderItem.setPrice(Money.ONE);

		orderInvoice.add(orderItem);

		order.setOrderInvoice(orderInvoice);

		BillingDetails billingDetails = new BillingDetails();
		billingDetails.setFirstName("billing_firstName");
		billingDetails.setLastName("billing_lastName");
		billingDetails.setAddress1("billing_address1");
		billingDetails.setAddress2("billing_address2");
		billingDetails.setCity("billing_city");
		billingDetails.setEmail("billing_email@email.com");
		billingDetails.setHomePhone("billing_homePhone");
		billingDetails.setState("billing_state");
		billingDetails.setPostalCode("billing_postalCode");
		billingDetails.setCountry(country);

		order.setBillingDetails(billingDetails);

		ShippingDetails shippingDetails = new ShippingDetails();
		shippingDetails.setFirstName("shipping_firstName");
		shippingDetails.setLastName("shipping_lastName");
		shippingDetails.setAddress1("shipping_address1");
		shippingDetails.setAddress2("shipping_address2");
		shippingDetails.setCity("shipping_city");
		shippingDetails.setEmail("shipping_email@email.com");
		shippingDetails.setHomePhone("shipping_homePhone");
		shippingDetails.setState("shipping_state");
		shippingDetails.setPostalCode("shipping_postalCode");
		shippingDetails.setCountry(country);

		order.setShippingDetails(shippingDetails);

		countryService.save(country);
		productService.save(product);

		orderService.save(order);

		card = new Card();
		card.setCvv("123");
		card.setExpirationMonth(10);
		card.setExpirationYear(2020);
		card.setNumber("4111111111111111");
	}

	@Test
	public void process() {
		BillingTransaction billingTransaction = billingServiceAuthorize.cardCapture(order, card);

		assertEquals(BillingTransactionStatus.PAYMENT_CAPTURED, billingTransaction.getStatus());
	}

}