package com.imadp.service.commerce.order.shipping;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.service.commerce.order.Order;
import com.imadp.service.user.AbstractPersistableUser;


/**
 * ShippingTransaction
 * 
 * The ShippingTransaction class holds the details of a shipping transaction.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class ShippingTransaction extends AbstractPersistableUser {

	// static Properties
	public static final Property<ShippingTransaction, Order> ORDER = Property.of("order");
	public static final Property<ShippingTransaction, DateTime> DATE = Property.of("date");
	public static final Property<ShippingTransaction, String> TRANSACTION_ID = Property.of("transactionId");
	public static final Property<ShippingTransaction, String> DATA = Property.of("data");
	public static final Property<ShippingTransaction, String> CUSTOM_DATA = Property.of("customData");
	public static final Property<ShippingTransaction, String> CARRIER = Property.of("carrier");
	public static final Property<ShippingTransaction, String> TRACKING_NUMBER = Property.of("trackingNumber");
	public static final Property<ShippingTransaction, ShippingTransactionStatus> STATUS = Property.of("status");

	// properties
	private Order order;
	private DateTime date;
	private String transactionId;
	private String data;
	private String customData;
	private String carrier;
	private String trackingNumber;
	private ShippingTransactionStatus status;

	// constructor
	public ShippingTransaction() {

	}

	// getters and setters			
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCustomData() {
		return customData;
	}

	public void setCustomData(String customData) {
		this.customData = customData;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public ShippingTransactionStatus getStatus() {
		return status;
	}

	public void setStatus(ShippingTransactionStatus status) {
		this.status = status;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	/**
	 * ShippingTransactionStatus
	 * 
	 * Enumerated values of a shipping transaction status.
	 * 
	 * @version 1.0
	 * @author Anthony DePalma
	 */
	public enum ShippingTransactionStatus {
		REQUESTED, IN_TRANSIT, DELIVERED, UNKNOWN
	}

}