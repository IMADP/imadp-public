package com.imadp.service.commerce.order.billing.authorize;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.joda.time.DateTime;

import com.imadp.service.ServiceComponent;
import com.imadp.service.commerce.card.Card;
import com.imadp.service.commerce.order.Order;
import com.imadp.service.commerce.order.billing.BillingDetails;
import com.imadp.service.commerce.order.billing.BillingService;
import com.imadp.service.commerce.order.billing.BillingTransaction;
import com.imadp.service.commerce.order.billing.BillingTransaction.BillingTransactionStatus;
import com.imadp.service.commerce.order.shipping.ShippingDetails;


/**
 * BillingServiceAuthorizeImpl
 *
 * The Authorize.net implementation of the BillingService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class BillingServiceAuthorizeImpl extends ServiceComponent implements BillingService {

	// processor
	private static final String PROCESSOR = "Authorize.net";

	// endpoints
	private static final String LIVE_ENDPOINT = "https://secure.authorize.net/gateway/transact.dll";

	// delimeter
	private static final String DELIMITER = "|";

	// transaction response order indexes
	private static final int RESPONSE_CODE = 1;
	private static final int TRANSACTION_ID = 7;

	// merchant parameters
	private String apiLoginId;
	private String transactionKey;

	// property determining whether to hit the live or test site
	private boolean live;

	 @Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(apiLoginId);
		Validate.notNull(transactionKey);
	}

	@Override
	public BillingTransaction cardCapture(Order order, Card card) {
		BillingTransaction billingTransaction = new BillingTransaction();
		billingTransaction.setOrder(order);
		billingTransaction.setDate(new DateTime());
		billingTransaction.setProcessor(PROCESSOR);

		HttpClient httpclient = new DefaultHttpClient();

		try
		{
			HttpPost httpost = new HttpPost(LIVE_ENDPOINT);

            List <NameValuePair> nvps = new ArrayList<>();

            // transaction details
            nvps.add(new BasicNameValuePair("x_test_request", live ? "false" : "true"));
            nvps.add(new BasicNameValuePair("x_login", apiLoginId));
            nvps.add(new BasicNameValuePair("x_tran_key", transactionKey));
            nvps.add(new BasicNameValuePair("x_version", "3.1"));
            nvps.add(new BasicNameValuePair("x_relay_response", "FALSE"));
            nvps.add(new BasicNameValuePair("x_delim_data", "true"));
            nvps.add(new BasicNameValuePair("x_delim_char", DELIMITER));

            // order details
            nvps.add(new BasicNameValuePair("x_type", "AUTH_CAPTURE"));
            nvps.add(new BasicNameValuePair("x_card_num", card.getNumber()));
            nvps.add(new BasicNameValuePair("x_card_code", card.getCvv()));
            nvps.add(new BasicNameValuePair("x_exp_date", card.getFullExpirationDate()));
            nvps.add(new BasicNameValuePair("x_amount", String.valueOf(order.getOrderInvoice().getTotalPrice().getAmount())));
            nvps.add(new BasicNameValuePair("x_invoice_num", String.valueOf(order.getId())));

            // billing details
            BillingDetails billingDetails = order.getBillingDetails();
            nvps.add(new BasicNameValuePair("x_first_name", billingDetails.getFirstName()));
            nvps.add(new BasicNameValuePair("x_last_name", billingDetails.getLastName()));
            nvps.add(new BasicNameValuePair("x_address", billingDetails.getFullAddress()));
            nvps.add(new BasicNameValuePair("x_city", billingDetails.getCity()));
            nvps.add(new BasicNameValuePair("x_state", billingDetails.getState()));
            nvps.add(new BasicNameValuePair("x_zip", billingDetails.getPostalCode()));
            nvps.add(new BasicNameValuePair("x_country", billingDetails.getCountry().getCode()));
            nvps.add(new BasicNameValuePair("x_phone", billingDetails.getHomePhone()));
            nvps.add(new BasicNameValuePair("x_email", billingDetails.getEmail()));

        	// shipping details
            ShippingDetails shippingDetails = order.getShippingDetails();
            nvps.add(new BasicNameValuePair("x_ship_to_first_name", shippingDetails.getFirstName()));
            nvps.add(new BasicNameValuePair("x_ship_to_last_name", shippingDetails.getLastName()));
            nvps.add(new BasicNameValuePair("x_ship_to_address", shippingDetails.getFullAddress()));
            nvps.add(new BasicNameValuePair("x_ship_to_city", shippingDetails.getCity()));
            nvps.add(new BasicNameValuePair("x_ship_to_state", shippingDetails.getState()));
            nvps.add(new BasicNameValuePair("x_ship_to_zip", shippingDetails.getPostalCode()));
            nvps.add(new BasicNameValuePair("x_ship_to_country", shippingDetails.getCountry().getCode()));

            httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

            logger.debug("Sending card capture request to Authorize.net for order=[{}]", order.getId());

            String transactionData = httpclient.execute(httpost, new BasicResponseHandler());

            logger.debug("Received card capture response from Authorize.net for order=[{}], response=[{}]",
            		order.getId(), transactionData);

            billingTransaction.setData(transactionData);
            billingTransaction.setTransactionId(getTransactionResponseValue(transactionData, TRANSACTION_ID));

            // substring out the response code, which should be the first value
            switch(Integer.valueOf(getTransactionResponseValue(transactionData, RESPONSE_CODE)))
    		{
	    		case 1 :  billingTransaction.setStatus(BillingTransactionStatus.PAYMENT_CAPTURED); break;
	    		case 2 :  billingTransaction.setStatus(BillingTransactionStatus.PAYMENT_DECLINED); break;
	    		case 3 :  billingTransaction.setStatus(BillingTransactionStatus.PAYMENT_DECLINED); break;
	    		case 4 :  billingTransaction.setStatus(BillingTransactionStatus.UNKNOWN); break;
	    		default : throw new RuntimeException("Error processing billing transaction ["+transactionData+"]");
	    	}

            return billingTransaction;
		}
		catch (Exception exception)
		{
			throw new RuntimeException(exception);
		}
		finally
		{
			 httpclient.getConnectionManager().shutdown();
		}
	}

	/**
	 * Returns the parameter corresponding to the order number given, ie 1 would return the
	 * first value found in the delimited response.
	 *
	 * @param transactionData
	 * @param order
	 * @return String
	 */
	private String getTransactionResponseValue(String transactionData, int order) {
		String[] values = transactionData.split("\\"+DELIMITER);
		return values.length >= order ? values[order - 1] : null;
	}

	// getters and setters
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public String getApiLoginId() {
		return apiLoginId;
	}

	public void setApiLoginId(String apiLoginId) {
		this.apiLoginId = apiLoginId;
	}

	public String getTransactionKey() {
		return transactionKey;
	}

	public void setTransactionKey(String transactionKey) {
		this.transactionKey = transactionKey;
	}

}