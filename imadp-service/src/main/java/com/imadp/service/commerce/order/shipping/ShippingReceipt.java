package com.imadp.service.commerce.order.shipping;

import java.util.ArrayList;
import java.util.List;

import com.imadp.core.Property;
import com.imadp.service.commerce.order.shipping.ShippingTransaction.ShippingTransactionStatus;
import com.imadp.service.user.AbstractPersistableUser;

/**
 * ShippingReceipt
 *
 * An object containing receipt details of a shipping event.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ShippingReceipt extends AbstractPersistableUser {

	// static Properties
	public static final Property<ShippingReceipt, ShippingTransactionStatus> SHIPPING_STATUS = Property.of("shippingStatus");

	// properties
	private List<ShippingTransaction> transactions;

	// constructor
	public ShippingReceipt() {

	}

	/**
	 * Returns the latest ShippingTransactionStatus.
	 *
	 * @return ShippingTransactionStatus
	 */
	public ShippingTransactionStatus getShippingStatus() {
		ShippingTransaction transaction = getLastTransaction();
		return transaction == null ? null : transaction.getStatus();
	}

	/**
	 * Returns the most recent shipping transaction.
	 *
	 * @return ShippingTransaction
	 */
	public ShippingTransaction getLastTransaction() {
		if(transactions == null || transactions.isEmpty())
			return null;

		return transactions.get(transactions.size() - 1);
	}

	/**
	 * Adds a ShippingTransaction to the shipping receipt.
	 *
	 * @param shippingTransaction
	 */
	public void addTransaction(ShippingTransaction shippingTransaction) {
		if(transactions == null)
			transactions = new ArrayList<>();

		transactions.add(shippingTransaction);
	}

	/**
	 * Returns true if shipping has been requested, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isShippingRequested() {
		if(transactions != null)
				for(ShippingTransaction transaction : transactions)
				if(ShippingTransactionStatus.REQUESTED == transaction.getStatus())
					return true;

		return false;
	}

	/**
	 * Returns true if shipping is in transit, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isShippingInTransit() {
		if(transactions != null)
			for(ShippingTransaction transaction : transactions)
				if(ShippingTransactionStatus.IN_TRANSIT == transaction.getStatus())
					return true;

		return false;
	}

	/**
	 * Returns true if shipping has been delivered, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isShippingDelivered() {
		if(transactions != null)
			for(ShippingTransaction transaction : transactions)
				if(ShippingTransactionStatus.DELIVERED == transaction.getStatus())
					return true;

		return false;
	}

	// getters and setters
	public List<ShippingTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<ShippingTransaction> transactions) {
		this.transactions = transactions;
	}

	@SuppressWarnings("unused")
	private void setShippingStatus(ShippingTransactionStatus shippingStatus) {

	}

}