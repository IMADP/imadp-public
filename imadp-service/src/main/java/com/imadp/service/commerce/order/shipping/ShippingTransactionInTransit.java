package com.imadp.service.commerce.order.shipping;

import com.imadp.core.AbstractSerializable;


/**
 * ShippingTransactionInTransit
 * 
 * Holds the fields for an in transit shipping transaction.
 *       
 * @version 1.0
 * @author Anthony DePalma
 */
public class ShippingTransactionInTransit extends AbstractSerializable {
	private String orderInvoiceNumber;
	private String carrier;
	private String trackingNumber;

	// constructor
	public ShippingTransactionInTransit() {

	}

	// getters and setters		
	public String getOrderInvoiceNumber() {
		return orderInvoiceNumber;
	}

	public void setOrderInvoiceNumber(String orderInvoiceNumber) {
		this.orderInvoiceNumber = orderInvoiceNumber;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

}