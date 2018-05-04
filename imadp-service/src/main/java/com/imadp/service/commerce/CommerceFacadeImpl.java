package com.imadp.service.commerce;

import java.io.IOException;
import java.io.Reader;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.stream.JsonReader;
import com.imadp.core.concurrent.GlobalMutex;
import com.imadp.service.FacadeComponent;
import com.imadp.service.commerce.event.EventService;
import com.stripe.Stripe;
import com.stripe.model.Account;
import com.stripe.model.Charge;
import com.stripe.model.Coupon;
import com.stripe.model.Customer;
import com.stripe.model.Discount;
import com.stripe.model.Dispute;
import com.stripe.model.Event;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceItem;
import com.stripe.model.Plan;
import com.stripe.model.StripeObject;
import com.stripe.model.Subscription;
import com.stripe.model.Transfer;

/**
 * CommerceFacadeImpl
 *
 * Provides common functionality such as event handling for commerce facades.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CommerceFacadeImpl extends FacadeComponent implements CommerceFacade {
	private String stripeApiKey;
	private EventService eventService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(stripeApiKey);
		Validate.notNull(eventService);

		// set the stripe api key
		Stripe.apiKey = StringUtils.trim(stripeApiKey);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public final void processEvent(Reader reader) throws IOException {

		// retrieve the event id
		String eventId = null;
		JsonReader jsonReader = new JsonReader(reader);
		jsonReader.beginObject();

		while(jsonReader.hasNext())
		{
			String name = jsonReader.nextName();

			// retrieve the id
			if("id".equalsIgnoreCase(name))
			{
				eventId = jsonReader.nextString();
			}

			// return for test events
			else if("livemode".equalsIgnoreCase(name))
			{
				if(!jsonReader.nextBoolean())
					return;
			}

			// skip other values
			else
			{
				jsonReader.skipValue();
			}
		}

		// throw an exception if no event id was found
		if(eventId == null)
			throw new IllegalArgumentException("No id found for event");

		try
		{
			// synchronize on the event id to prevent concurrent processing
			synchronized(GlobalMutex.getMutex(getClass(), "processEvent", eventId))
			{
				// retrieve the event
				Event event = Event.retrieve(eventId);
				String eventType = event.getType();
				String eventData = Event.GSON.toJson(event);
				StripeObject object = event.getData().getObject();

				logger.debug("Received event with id [{}] and type [{}]", eventId, eventType);

				// save the event to prevent concurrent processing
				eventService.save(new com.imadp.service.commerce.event.Event(eventId, eventType, eventData));

				// process the event
				switch(eventType)
				{
					case "account.updated" : 						onAccountUpdated(event, (Account) object); return;
					case "account.application.deauthorized" : 		onAccountApplicationDeauthorized(event); return;
					case "balance.available" : 						onBalanceAvailable(event); return;
					case "charge.captured" : 						onChargeCaptured(event, (Charge) object); return;
					case "charge.succeeded" : 						onChargeSucceeded(event, (Charge) object); return;
					case "charge.failed" : 							onChargeFailed(event, (Charge) object); return;
					case "charge.refunded" : 						onChargeRefunded(event, (Charge) object); return;
					case "charge.dispute.created" : 				onChargeDisputeCreated(event, (Dispute) object); return;
					case "charge.dispute.updated" : 				onChargeDisputeUpdated(event, (Dispute) object); return;
					case "charge.dispute.closed" : 					onChargeDisputeClosed(event, (Dispute) object); return;
					case "customer.created" : 						onCustomerCreated(event, (Customer) object); return;
					case "customer.updated" : 						onCustomerUpdated(event, (Customer) object); return;
					case "customer.deleted" : 						onCustomerDeleted(event, (Customer) object); return;
					case "customer.subscription.created" : 			onCustomerSubscriptionCreated(event, (Subscription) object); return;
					case "customer.subscription.updated" : 			onCustomerSubscriptionUpdated(event, (Subscription) object); return;
					case "customer.subscription.deleted" : 			onCustomerSubscriptionDeleted(event, (Subscription) object); return;
					case "customer.subscription.trial_will_end" :	onCustomerSubscriptionTrialWillEnd(event, (Subscription) object); return;
					case "customer.discount.created" : 				onCustomerDiscountCreated(event, (Discount) object); return;
					case "customer.discount.updated" : 				onCustomerDiscountUpdated(event, (Discount) object); return;
					case "customer.discount.deleted" : 				onCustomerDiscountDeleted(event, (Discount) object); return;
					case "invoice.created" : 						onInvoiceCreated(event, (Invoice) object); return;
					case "invoice.updated" : 						onInvoiceUpdated(event, (Invoice) object); return;
					case "invoice.payment_succeeded" : 				onInvoicePaymentSucceeded(event, (Invoice) object); return;
					case "invoice.payment_failed" : 				onInvoicePaymentFailed(event, (Invoice) object); return;
					case "invoiceitem.created" : 					onInvoiceItemCreated(event, (InvoiceItem) object); return;
					case "invoiceitem.updated" : 					onInvoiceItemUpdated(event, (InvoiceItem) object); return;
					case "invoiceitem.deleted" : 					onInvoiceItemDeleted(event, (InvoiceItem) object); return;
					case "plan.created" : 							onPlanCreated(event, (Plan) object); return;
					case "plan.updated" : 							onPlanUpdated(event, (Plan) object); return;
					case "plan.deleted" : 							onPlanDeleted(event, (Plan) object); return;
					case "coupon.created" :							onCouponCreated(event, (Coupon) object); return;
					case "coupon.deleted" : 						onCouponDeleted(event, (Coupon) object); return;
					case "transfer.created" : 						onTransferCreated(event, (Transfer) object); return;
					case "transfer.updated" : 						onTransferUpdated(event, (Transfer) object); return;
					case "transfer.paid" : 							onTransferPaid(event, (Transfer) object); return;
					case "transfer.failed" : 						onTransferFailed(event, (Transfer) object); return;
					case "ping" : 									onPing(); return;
				}

				logger.warn("Unknown event received with id [{}] and type [{}]", event.getId(), event.getType());
			}

		}
		catch(Exception exception)
		{
			onEventException(eventId, exception);
		}

	}

	/**
	 * Account updated event.
	 *
	 * @param event
	 * @param account
	 */
	protected void onAccountUpdated(Event event, Account account) {

	}

	/**
	 * Account application deauthorized.
	 *
	 * @param event
	 */
	protected void onAccountApplicationDeauthorized(Event event) {

	}

	/**
	 * Balance available event.
	 *
	 * @param event
	 */
	protected void onBalanceAvailable(Event event) {

	}

	/**
	 * Charge captured event.
	 *
	 * @param event
	 * @param charge
	 */
	protected void onChargeCaptured(Event event, Charge charge) {

	}

	/**
	 * Charge dispute closed event.
	 *
	 * @param event
	 * @param dispute
	 */
	protected void onChargeDisputeClosed(Event event, Dispute dispute) {

	}

	/**
	 * Charge dispute created event.
	 *
	 * @param event
	 * @param dispute
	 */
	protected void onChargeDisputeCreated(Event event, Dispute dispute) {

	}

	/**
	 * Charge dispute updated event.
	 *
	 * @param event
	 * @param dispute
	 */
	protected void onChargeDisputeUpdated(Event event, Dispute dispute) {

	}

	/**
	 * Charge failed event.
	 *
	 * @param event
	 * @param charge
	 */
	protected void onChargeFailed(Event event, Charge charge) {

	}

	/**
	 * Charge refunded event.
	 *
	 * @param event
	 * @param charge
	 */
	protected void onChargeRefunded(Event event, Charge charge) {

	}

	/**
	 * Charge succeeded event.
	 *
	 * @param event
	 * @param charge
	 */
	protected void onChargeSucceeded(Event event, Charge charge) {

	}

	/**
	 * Coupon created event.
	 *
	 * @param event
	 * @param coupon
	 */
	protected void onCouponCreated(Event event, Coupon coupon) {

	}

	/**
	 * Coupon deleted event.
	 *
	 * @param event
	 * @param coupon
	 */
	protected void onCouponDeleted(Event event, Coupon coupon) {

	}

	/**
	 * Customer created event.
	 *
	 * @param event
	 * @param customer
	 */
	protected void onCustomerCreated(Event event, Customer customer) {

	}

	/**
	 * Customer deleted event.
	 *
	 * @param event
	 * @param customer
	 */
	protected void onCustomerDeleted(Event event, Customer customer) {

	}

	/**
	 * Customer discount created.
	 *
	 * @param event
	 * @param discount
	 */
	protected void onCustomerDiscountCreated(Event event, Discount discount) {

	}

	/**
	 * Customer discount deleted.
	 *
	 * @param event
	 * @param discount
	 */
	protected void onCustomerDiscountDeleted(Event event, Discount discount) {

	}

	/**
	 * Customer discount updated.
	 *
	 * @param event
	 * @param discount
	 */
	protected void onCustomerDiscountUpdated(Event event, Discount discount) {

	}

	/**
	 * Customer subscription created event.
	 *
	 * @param event
	 * @param subscription
	 */
	protected void onCustomerSubscriptionCreated(Event event, Subscription subscription) {

	}

	/**
	 * Customer subscription deleted event.
	 *
	 * @param event
	 * @param subscription
	 */
	protected void onCustomerSubscriptionDeleted(Event event, Subscription subscription) {

	}

	/**
	 * Customer subscription trial will end event.
	 *
	 * @param event
	 * @param subscription
	 */
	protected void onCustomerSubscriptionTrialWillEnd(Event event, Subscription subscription) {

	}

	/**
	 * Customer subscription updated event.
	 *
	 * @param event
	 * @param subscription
	 */
	protected void onCustomerSubscriptionUpdated(Event event, Subscription subscription) {

	}

	/**
	 * Customer updated event.
	 *
	 * @param event
	 * @param customer
	 */
	protected void onCustomerUpdated(Event event, Customer customer) {

	}

	/**
	 * Invoice created event.
	 *
	 * @param event
	 * @param invoice
	 */
	protected void onInvoiceCreated(Event event, Invoice invoice) {

	}

	/**
	 * Invoice item created event.
	 *
	 * @param event
	 * @param invoiceItem
	 */
	protected void onInvoiceItemCreated(Event event, InvoiceItem invoiceItem) {

	}

	/**
	 * Invoice item deleted event.
	 *
	 * @param event
	 * @param invoiceItem
	 */
	protected void onInvoiceItemDeleted(Event event, InvoiceItem invoiceItem) {

	}

	/**
	 * Invoice item updated event.
	 *
	 * @param event
	 * @param invoiceItem
	 */
	protected void onInvoiceItemUpdated(Event event, InvoiceItem invoiceItem) {

	}

	/**
	 * Invoice payment failed event.
	 *
	 * @param event
	 * @param invoice
	 */
	protected void onInvoicePaymentFailed(Event event, Invoice invoice) {

	}

	/**
	 * Invoice payment succeeded event.
	 *
	 * @param event
	 * @param invoice
	 */
	protected void onInvoicePaymentSucceeded(Event event, Invoice invoice) {

	}

	/**
	 * Invoice updated event.
	 *
	 * @param event
	 * @param invoice
	 */
	protected void onInvoiceUpdated(Event event, Invoice invoice) {

	}

	/**
	 * Ping event.
	 *
	 */
	protected void onPing() {

	}

	/**
	 * Plan created event.
	 *
	 * @param event
	 * @param plan
	 */
	protected void onPlanCreated(Event event, Plan plan) {

	}

	/**
	 * Plan deleted event.
	 *
	 * @param event
	 * @param plan
	 */
	protected void onPlanDeleted(Event event, Plan plan) {

	}

	/**
	 * Plan updated event.
	 *
	 * @param event
	 * @param plan
	 */
	protected void onPlanUpdated(Event event, Plan plan) {

	}

	/**
	 * Transfer created event.
	 *
	 * @param event
	 * @param transfer
	 */
	protected void onTransferCreated(Event event, Transfer transfer) {

	}

	/**
	 * Transfer failed event.
	 *
	 * @param event
	 * @param transfer
	 */
	protected void onTransferFailed(Event event, Transfer transfer) {

	}

	/**
	 * Transfer paid event.
	 *
	 * @param event
	 * @param transfer
	 */
	protected void onTransferPaid(Event event, Transfer transfer) {

	}

	/**
	 * Transfer updated event.
	 *
	 * @param event
	 * @param transfer
	 */
	protected void onTransferUpdated(Event event, Transfer transfer) {

	}

	/**
	 * Called whenever an exception occurs during a event processing.
	 *
	 * @param eventId
	 * @param exception
	 */
	protected void onEventException(String eventId, Exception exception) {

	}

	// getters and setters
	public String getStripeApiKey() {
		return stripeApiKey;
	}

	public void setStripeApiKey(String stripeApiKey) {
		this.stripeApiKey = stripeApiKey;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

}