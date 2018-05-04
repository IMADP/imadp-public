package com.imadp.service.commerce.order.billing;

import java.util.ArrayList;
import java.util.List;

import com.imadp.core.Property;
import com.imadp.service.commerce.order.billing.BillingTransaction.BillingTransactionStatus;
import com.imadp.service.user.AbstractPersistableUser;


/**
 * BillingReceipt
 *
 * An object containing receipt details of a billing event.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BillingReceipt extends AbstractPersistableUser {

	// static Properties
	public static final Property<BillingReceipt, BillingTransactionStatus> BILLING_STATUS = Property.of("billingStatus");

	// properties
	private List<BillingTransaction> transactions;

	// constructor
	public BillingReceipt() {

	}

	/**
	 * Returns the latest BillingTransactionStatus.
	 *
	 * @return BillingTransactionStatus
	 */
	public BillingTransactionStatus getBillingStatus() {
		BillingTransaction transaction = getLastTransaction();
		return transaction == null ? null : transaction.getStatus();
	}

	/**
	 * Returns the most recent billing transaction.
	 *
	 * @return BillingTransaction
	 */
	public BillingTransaction getLastTransaction() {
		if(transactions == null || transactions.isEmpty())
			return null;

		return transactions.get(transactions.size() - 1);
	}

	/**
	 * Adds a BillingTransaction to the billing receipt.
	 *
	 * @param billingTransaction
	 */
	public void addTransaction(BillingTransaction billingTransaction) {
		if(transactions == null)
			transactions = new ArrayList<>();

		transactions.add(billingTransaction);
	}

	/**
	 * Returns true if payment was captured, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isPaymentCaptured() {
		if(transactions != null)
			for(BillingTransaction transaction : transactions)
				if(BillingTransactionStatus.PAYMENT_CAPTURED == transaction.getStatus())
					return true;

		return false;
	}

	/**
	 * Returns true if payment was declined, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isPaymentDeclined() {
		if(transactions != null)
			for(BillingTransaction transaction : transactions)
				if(BillingTransactionStatus.PAYMENT_DECLINED == transaction.getStatus())
					return true;

		return false;
	}

	// getters and setters
	public List<BillingTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<BillingTransaction> transactions) {
		this.transactions = transactions;
	}

	@SuppressWarnings("unused")
	private void setBillingStatus(BillingTransactionStatus billingStatus) {

	}

}