package com.imadp.web.commerce.order.billing;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.Validate;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.imadp.core.CoreComponent;
import com.imadp.service.commerce.order.Order;
import com.imadp.service.commerce.order.OrderService;
import com.imadp.service.commerce.order.billing.BillingDetails;
import com.imadp.service.commerce.order.billing.BillingTransaction;
import com.imadp.service.commerce.order.billing.BillingTransaction.BillingTransactionStatus;
import com.imadp.service.commerce.order.shipping.ShippingDetails;
import com.imadp.service.location.country.CountryService;
import com.imadp.web.WebUtil;

/**
 * BillingTransactionDispatcherPaypalImpl
 *
 * Handles and processes paypal notifications.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BillingTransactionDispatcherPaypalImpl extends CoreComponent implements BillingTransactionDispatcher {

	// processor
	private static final String PROCESSOR = "Paypal";

	// endpoints
	private static final String LIVE_ENDPOINT = "https://www.paypal.com/cgi-bin/webscr";
	private static final String TEST_ENDPOINT = "https://www.sandbox.paypal.com/cgi-bin/webscr";

	// parameter names
	private static final String CUSTOM = "custom";
	private static final String TXN_ID = "txn_id";
	private static final String PAYMENT_STATUS = "payment_status";
	private static final String INVOICE = "invoice";
	private static final String FIRST_NAME = "first_name";
	private static final String LAST_NAME = "last_name";
	private static final String ADDRESS_STREET = "address_street";
	private static final String ADDRESS_CITY = "address_city";
	private static final String ADDRESS_COUNTRY_CODE = "address_country_code";
	private static final String PAYER_EMAIL = "payer_email";
	private static final String PAYMENT_DATE = "payment_date";
	private static final String ADDRESS_ZIP = "address_zip";
	private static final String ADDRESS_STATE = "address_state";
	private static final String MC_GROSS = "mc_gross";

	// datetime formatter
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("HH:mm:ss MMM d, yyyy")
		.withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone("PST")));

	// property determining whether to hit the live or test site
	private boolean live;

	// services
	private OrderService orderService;
	private CountryService countryService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(orderService);
		Validate.notNull(countryService);
	}

	@Override
	public BillingTransaction receiveTransaction(HttpServletRequest request, HttpServletResponse response) {
		String transactionId = request.getParameter(TXN_ID);
		String data = getParameterString(request);

		logger.info("Paypal remote order received with transactionId [{}]", transactionId);

		// retrieve the order uid associated with this notification
		String uid = request.getParameter(INVOICE);

		logger.info("Paypal orderUid retrieved from the invoice value [{}]", uid);

		Order order = orderService.get(uid);

		if(order == null)
			throw new IllegalArgumentException("No order was found with the paypal orderUid ["+uid+"]");

		BigDecimal mcGross = new BigDecimal(request.getParameter(MC_GROSS));

		if(!order.getOrderInvoice().getTotalPrice().getAmount().equals(mcGross))
			throw new IllegalArgumentException("Order total price ["+order.getOrderInvoice().getTotalPrice()+"] " +
					"does not match paypal mcGross ["+mcGross+"]");

		// populate billing details
		BillingDetails billingDetails = new BillingDetails();
		billingDetails.setFirstName(request.getParameter(FIRST_NAME));
		billingDetails.setLastName(request.getParameter(LAST_NAME));
		billingDetails.setAddress1(request.getParameter(ADDRESS_STREET));
		billingDetails.setCity(request.getParameter(ADDRESS_CITY));
		billingDetails.setCountry(countryService.findByCode(request.getParameter(ADDRESS_COUNTRY_CODE)));
		billingDetails.setEmail(request.getParameter(PAYER_EMAIL));
		billingDetails.setPostalCode(request.getParameter(ADDRESS_ZIP));
		billingDetails.setState(request.getParameter(ADDRESS_STATE));

		order.setBillingDetails(billingDetails);

		// populate shipping details
		order.setShippingDetails(new ShippingDetails(billingDetails));

		// prepare the billing transaction
		BillingTransaction billingTransaction = new BillingTransaction();
		billingTransaction.setOrder(order);
		billingTransaction.setTransactionId(transactionId);
		billingTransaction.setProcessor(PROCESSOR);
		billingTransaction.setData(data);
		billingTransaction.setCustomData(request.getParameter(CUSTOM));

		// set the payment date, this requires substringing out the time zone
		String paymentDate = request.getParameter(PAYMENT_DATE);
		billingTransaction.setDate(paymentDate == null ? null :
			DATE_FORMATTER.parseDateTime(paymentDate.substring(0, paymentDate.length()-4)));

		// set the billing status
		String paymentStatus = request.getParameter(PAYMENT_STATUS);

		if("Completed".equalsIgnoreCase(paymentStatus))
			billingTransaction.setStatus(BillingTransactionStatus.PAYMENT_CAPTURED);
		else
			billingTransaction.setStatus(BillingTransactionStatus.UNKNOWN);

		return billingTransaction;
	}

	/**
	 * Creates a parameter string out of a given request.
	 *
	 * @param request
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	private String getParameterString(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		Enumeration<String> parameterNames = request.getParameterNames();

		// create the parameter string out of the parameter names enumeration
		while(parameterNames.hasMoreElements())
		{
			String paramName = parameterNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);

			// loop through and expand duplicate parameter names
			for(int i = 0; i < paramValues.length; i++)
			{
				sb.append(WebUtil.encodeUrl(paramName));
				sb.append("=");
				sb.append(WebUtil.encodeUrl(paramValues[i]));

				if(i < paramValues.length - 1)
					sb.append("&");
			}

			if(parameterNames.hasMoreElements())
				sb.append("&");
		}

		return sb.toString();
	}

	/**
	 * Verifies the transaction data by confirming with Paypal.
	 *
	 * @param transactionData
	 * @return boolean
	 */
	@SuppressWarnings("unused")
	private boolean verifyTransaction(String transactionData) {

		// construct the url to send
		String url = (live ? LIVE_ENDPOINT : TEST_ENDPOINT) + "?cmd=_notify-validate&" + transactionData;

		HttpClient httpclient = new DefaultHttpClient();

		try
		{
			boolean verified = "VERIFIED".equalsIgnoreCase(
					httpclient.execute(new HttpPost(url), new BasicResponseHandler()));

			if(!verified)
				logger.warn("Unable to verify paypal transaction with url [{}]", url);

			return verified;
		}
		catch (Exception exception)
		{
			logger.error("Could not verify paypal transaction ["+transactionData+"]", exception);
			return false;
		}
		finally
		{
			 httpclient.getConnectionManager().shutdown();
		}

	}

	// getters and setters
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public CountryService getCountryService() {
		return countryService;
	}

	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}

}