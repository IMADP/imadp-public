package com.imadp.service.commerce.order;

import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.core.money.Money;
import com.imadp.service.commerce.card.Card;
import com.imadp.service.commerce.order.billing.BillingDetails;
import com.imadp.service.commerce.order.shipping.ShippingDetails;
import com.imadp.service.commerce.product.Product;
import com.imadp.service.location.country.Country;
import com.imadp.service.test.IMADPServiceTestCase;


/**
 * OrderFacadeImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class OrderFacadeImplTest extends IMADPServiceTestCase {
	Order order;

	@Override
	public void before() throws Exception {
		super.before();

		Country country = new Country();
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

		OrderDiscount orderDiscount = new OrderDiscount();
		orderDiscount.setPrice(Money.ONE);
		orderDiscount.setOrder(order);

		orderInvoice.add(orderDiscount);

		order.setOrderInvoice(orderInvoice);

		BillingDetails billingDetails = new BillingDetails();
		billingDetails.setFirstName("firstName");
		billingDetails.setLastName("lastName");
		billingDetails.setAddress1("address1");
		billingDetails.setAddress2("address2");
		billingDetails.setCity("city");
		billingDetails.setEmail("email@email.com");
		billingDetails.setHomePhone("homePhone");
		billingDetails.setState("state");
		billingDetails.setPostalCode("postalCode");
		billingDetails.setCountry(country);

		order.setBillingDetails(billingDetails);

		ShippingDetails shippingDetails = new ShippingDetails();
		shippingDetails.setFirstName("firstName");
		shippingDetails.setLastName("lastName");
		shippingDetails.setAddress1("address1");
		shippingDetails.setAddress2("address2");
		shippingDetails.setCity("city");
		shippingDetails.setEmail("email@email.com");
		shippingDetails.setHomePhone("homePhone");
		shippingDetails.setState("state");
		shippingDetails.setPostalCode("postalCode");
		shippingDetails.setCountry(country);

		order.setShippingDetails(shippingDetails);

		countryService.save(country);
		productService.save(product);
		orderService.save(order);
	}

	@Test
	public void placeOrder() {
		Card card = new Card();
		card.setCvv("123");
		card.setExpirationMonth(10);
		card.setExpirationYear(2020);
		card.setNumber("4111111111111111");

		Order placedOrder = orderFacade.placeOrder(order, card);

		assertTrue(placedOrder.getBillingReceipt().isPaymentCaptured());
		assertTrue(placedOrder.getShippingReceipt().isShippingRequested());
	}

}