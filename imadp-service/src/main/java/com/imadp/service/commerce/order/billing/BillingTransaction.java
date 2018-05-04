package com.imadp.service.commerce.order.billing;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.service.commerce.order.Order;
import com.imadp.service.user.AbstractPersistableUser;


/**
 * BillingTransaction
 * 
 * The BillingTransaction class holds the details of a billing transaction.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class BillingTransaction extends AbstractPersistableUser {
	
	// static Properties
	public static final Property<BillingTransaction, Order> ORDER = Property.of("order");
	public static final Property<BillingTransaction, DateTime> DATE = Property.of("date");
	public static final Property<BillingTransaction, String> TRANSACTION_ID = Property.of("transactionId");
	public static final Property<BillingTransaction, String> DATA = Property.of("data");
	public static final Property<BillingTransaction, String> CUSTOM_DATA = Property.of("customData");
	public static final Property<BillingTransaction, String> PROCESSOR = Property.of("processor");
	public static final Property<BillingTransaction, BillingTransactionStatus> STATUS = Property.of("status");
	
	// properties
	private Order order;
	private DateTime date;
    private String transactionId;
	private String data;
	private String customData;
	private String processor;
	private BillingTransactionStatus status;
	
	// constructor
	public BillingTransaction() {
		
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

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public BillingTransactionStatus getStatus() {
		return status;
	}

	public void setStatus(BillingTransactionStatus status) {
		this.status = status;
	}
	
	/**
	 * BillingTransactionStatus
	 * 
	 * Enumerated values of a billing transaction status.
	 * 
	 * @version 1.0
	 * @author Anthony DePalma
	 */
	public enum BillingTransactionStatus {
		PAYMENT_DECLINED, PAYMENT_CAPTURED, UNKNOWN
	}
	
}