package com.imadp.service.commerce.order;

import java.math.BigDecimal;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.imadp.core.Property;
import com.imadp.service.commerce.order.billing.BillingDetails;
import com.imadp.service.commerce.order.billing.BillingReceipt;
import com.imadp.service.commerce.order.billing.BillingTransaction.BillingTransactionStatus;
import com.imadp.service.commerce.order.shipping.ShippingDetails;
import com.imadp.service.commerce.order.shipping.ShippingReceipt;
import com.imadp.service.commerce.order.shipping.ShippingTransaction.ShippingTransactionStatus;
import com.imadp.service.location.country.Country;
import com.imadp.service.user.AbstractPersistableUser;


/**
 * Order
 *
 * A representation of a purchase order.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Order extends AbstractPersistableUser {

	// invoice number offset
	private static final long INVOICE_NUMBER_OFFSET = 900;

	// static Properties
	public static final Property<Order, DateTime> DATE = Property.of("date");
	public static final Property<Order, Locale> LOCALE = Property.of("locale");
	public static final Property<Order, String> GOOGLE_CLICK_ID = Property.of("googleClickId");
	public static final Property<Order, String> GOOGLE_CONVERSION_ID = Property.of("googleConversionId");
	public static final Property<Order, Boolean> GOOGLE_COMMERCE_TRACKING = Property.of("googleCommerceTracking");

	// static OrderInvoice Properties
	public static final Property<Order, BigDecimal> ORDER_INVOICE_ITEMS_PRICE_AMOUNT = Property.of("orderInvoice", OrderInvoice.ITEMS_PRICE_AMOUNT);
	public static final Property<Order, String> ORDER_INVOICE_ITEMS_PRICE_CURRENCY = Property.of("orderInvoice", OrderInvoice.ITEMS_PRICE_CURRENCY);
	public static final Property<Order, BigDecimal> ORDER_INVOICE_SHIPPING_PRICE_AMOUNT = Property.of("orderInvoice", OrderInvoice.SHIPPING_PRICE_AMOUNT);
	public static final Property<Order, String> ORDER_INVOICE_SHIPPING_PRICE_CURRENCY = Property.of("orderInvoice", OrderInvoice.SHIPPING_PRICE_CURRENCY);
	public static final Property<Order, BigDecimal> ORDER_INVOICE_TAX_PRICE_AMOUNT = Property.of("orderInvoice", OrderInvoice.TAX_PRICE_AMOUNT);
	public static final Property<Order, String> ORDER_INVOICE_TAX_PRICE_CURRENCY = Property.of("orderInvoice", OrderInvoice.TAX_PRICE_CURRENCY);
	public static final Property<Order, BigDecimal> ORDER_INVOICE_DISCOUNTS_PRICE_AMOUNT = Property.of("orderInvoice", OrderInvoice.DISCOUNTS_PRICE_AMOUNT);
	public static final Property<Order, String> ORDER_INVOICE_DISCOUNTS_PRICE_CURRENCY = Property.of("orderInvoice", OrderInvoice.DISCOUNTS_PRICE_CURRENCY);
	public static final Property<Order, BigDecimal> ORDER_INVOICE_TOTAL_PRICE_AMOUNT = Property.of("orderInvoice", OrderInvoice.TOTAL_PRICE_AMOUNT);
	public static final Property<Order, String> ORDER_INVOICE_TOTAL_PRICE_CURRENCY = Property.of("orderInvoice", OrderInvoice.TOTAL_PRICE_CURRENCY);

	// static BillingDetails Properties
	public static final Property<Order, String> BILLING_DETAILS_FIRST_NAME = Property.of("billingDetails", BillingDetails.FIRST_NAME);
	public static final Property<Order, String> BILLING_DETAILS_LAST_NAME = Property.of("billingDetails", BillingDetails.LAST_NAME);
	public static final Property<Order, String> BILLING_DETAILS_ADDRESS_1 = Property.of("billingDetails", BillingDetails.ADDRESS_1);
	public static final Property<Order, String> BILLING_DETAILS_ADDRESS_2 = Property.of("billingDetails", BillingDetails.ADDRESS_2);
	public static final Property<Order, String> BILLING_DETAILS_CITY = Property.of("billingDetails", BillingDetails.CITY);
	public static final Property<Order, String> BILLING_DETAILS_STATE = Property.of("billingDetails", BillingDetails.STATE);
	public static final Property<Order, String> BILLING_DETAILS_POSTAL_CODE = Property.of("billingDetails", BillingDetails.POSTAL_CODE);
	public static final Property<Order, String> BILLING_DETAILS_HOME_PHONE = Property.of("billingDetails", BillingDetails.HOME_PHONE);
	public static final Property<Order, String> BILLING_DETAILS_EMAIL = Property.of("billingDetails", BillingDetails.EMAIL);
	public static final Property<Order, Country> BILLING_DETAILS_COUNTRY = Property.of("billingDetails", BillingDetails.COUNTRY);

	// static BillingReceipt Properties
	public static final Property<Order, BillingTransactionStatus> BILLING_RECEIPT_BILLING_STATUS = Property.of("billingReceipt", BillingReceipt.BILLING_STATUS);

	// static ShippingDetails Properties
	public static final Property<Order, String> SHIPPING_DETAILS_FIRST_NAME = Property.of("shippingDetails", ShippingDetails.FIRST_NAME);
	public static final Property<Order, String> SHIPPING_DETAILS_LAST_NAME = Property.of("shippingDetails", ShippingDetails.LAST_NAME);
	public static final Property<Order, String> SHIPPING_DETAILS_ADDRESS_1 = Property.of("shippingDetails", ShippingDetails.ADDRESS_1);
	public static final Property<Order, String> SHIPPING_DETAILS_ADDRESS_2 = Property.of("shippingDetails", ShippingDetails.ADDRESS_2);
	public static final Property<Order, String> SHIPPING_DETAILS_CITY = Property.of("shippingDetails", ShippingDetails.CITY);
	public static final Property<Order, String> SHIPPING_DETAILS_STATE = Property.of("shippingDetails", ShippingDetails.STATE);
	public static final Property<Order, String> SHIPPING_DETAILS_POSTAL_CODE = Property.of("shippingDetails", ShippingDetails.POSTAL_CODE);
	public static final Property<Order, String> SHIPPING_DETAILS_HOME_PHONE = Property.of("shippingDetails", ShippingDetails.HOME_PHONE);
	public static final Property<Order, String> SHIPPING_DETAILS_EMAIL = Property.of("shippingDetails", ShippingDetails.EMAIL);
	public static final Property<Order, Country> SHIPPING_DETAILS_COUNTRY = Property.of("shippingDetails", ShippingDetails.COUNTRY);
	public static final Property<Order, String> SHIPPING_DETAILS_INSTRUCTIONS = Property.of("shippingDetails", ShippingDetails.INSTRUCTIONS);

	// static ShippingReceipt Properties
	public static final Property<Order, ShippingTransactionStatus> SHIPPING_RECEIPT_SHIPPING_STATUS = Property.of("shippingReceipt", ShippingReceipt.SHIPPING_STATUS);

	// adwords datetime formatter
	private static final DateTimeFormatter GOOGLE_CONVERSION_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

	// properties
	private DateTime date;
	private Locale locale;
	private String googleClickId;
	private String googleConversionId;
	private OrderInvoice orderInvoice;
	private BillingDetails billingDetails;
	private BillingReceipt billingReceipt;
	private ShippingDetails shippingDetails;
	private ShippingReceipt shippingReceipt;
	private boolean googleCommerceTracking;

	// constructor
	public Order() {
		this.orderInvoice = new OrderInvoice();
		this.billingDetails = new BillingDetails();
		this.billingReceipt = new BillingReceipt();
		this.shippingDetails = new ShippingDetails();
		this.shippingReceipt = new ShippingReceipt();
	}

	/**
	 * Returns an invoice number which can be displayed to customers as a quick reference to the order.
	 * The number consists of the prefix IN, following by the numerical value of the order id plus
	 * a value of 900 to break the obvious sequencing, following by a D and the day of the month of the order.
	 *
	 * @return String
	 */
	public String getInvoiceNumber() {
		if(id == null)
			return null;

		return "IN" + (id + INVOICE_NUMBER_OFFSET) + "D" + date.getDayOfMonth();
	}

	/**
	 * Returns the order id from the invoiceNumber, or null if no invoiceNumber was returned.
	 *
	 * @param invoiceNumber
	 * @return Long
	 */
	public static Long getId(String invoiceNumber){
		if(invoiceNumber == null)
			return null;

		try
		{
			String orderId = invoiceNumber.substring(2, invoiceNumber.indexOf("D"));
			return Long.valueOf(orderId) - INVOICE_NUMBER_OFFSET;
		}
		catch(Exception exception)
		{
			return null;
		}
	}

	/**
	 * Returns true if the order is associated with a google click id, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean hasGoogleClickId() {
		return StringUtils.isNotBlank(googleClickId);
	}

	/**
	 * Returns the conversion time for the order.
	 *
	 * @return String
	 */
	public String getGoogleConversionTime() {
		return GOOGLE_CONVERSION_TIME_FORMATTER.print(getDate());
	}

	/**
	 * Returns the date as a medium string.
	 *
	 * @return String
	 */
	public String getDateStringMedium() {
		return DateTimeFormat.mediumDate().print(date);
	}

	// getters and setters
	public OrderInvoice getOrderInvoice() {
		return orderInvoice;
	}

	public void setOrderInvoice(OrderInvoice orderInvoice) {
		this.orderInvoice = orderInvoice == null ? new OrderInvoice() : orderInvoice;
	}

	public BillingDetails getBillingDetails() {
		return billingDetails;
	}

	public void setBillingDetails(BillingDetails billingDetails) {
		this.billingDetails = billingDetails == null ? new BillingDetails() : billingDetails;
	}

	public BillingReceipt getBillingReceipt() {
		return billingReceipt;
	}

	public void setBillingReceipt(BillingReceipt billingReceipt) {
		this.billingReceipt = billingReceipt == null ? new BillingReceipt() : billingReceipt;
	}

	public ShippingDetails getShippingDetails() {
		return shippingDetails;
	}

	public void setShippingDetails(ShippingDetails shippingDetails) {
		this.shippingDetails = shippingDetails == null ? new ShippingDetails() : shippingDetails;
	}

	public ShippingReceipt getShippingReceipt() {
		return shippingReceipt;
	}

	public void setShippingReceipt(ShippingReceipt shippingReceipt) {
		this.shippingReceipt = shippingReceipt == null ? new ShippingReceipt() : shippingReceipt;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getGoogleClickId() {
		return googleClickId;
	}

	public void setGoogleClickId(String googleClickId) {
		this.googleClickId = googleClickId;
	}

	public String getGoogleConversionId() {
		return googleConversionId;
	}

	public void setGoogleConversionId(String googleConversionId) {
		this.googleConversionId = googleConversionId;
	}

	public boolean isGoogleCommerceTracking() {
		return googleCommerceTracking;
	}

	public void setGoogleCommerceTracking(boolean googleCommerceTracking) {
		this.googleCommerceTracking = googleCommerceTracking;
	}

}