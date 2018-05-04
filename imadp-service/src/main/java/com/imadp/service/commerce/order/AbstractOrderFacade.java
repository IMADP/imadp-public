package com.imadp.service.commerce.order;

import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;

import com.imadp.core.concurrent.GlobalMutex;
import com.imadp.service.ServiceComponent;
import com.imadp.service.commerce.card.Card;
import com.imadp.service.commerce.order.billing.BillingService;
import com.imadp.service.commerce.order.billing.BillingTransaction;
import com.imadp.service.commerce.order.billing.BillingTransaction.BillingTransactionStatus;
import com.imadp.service.commerce.order.shipping.ShippingService;
import com.imadp.service.commerce.order.shipping.ShippingTransaction;
import com.imadp.service.commerce.order.shipping.ShippingTransaction.ShippingTransactionStatus;
import com.imadp.service.commerce.order.shipping.ShippingTransactionInTransit;
import com.imadp.service.commerce.order.shipping.ShippingTransactionInTransitValidator;


/**
 * AbstractOrderFacade
 *
 * A facade containing the critical logic for placing an order. Subclasses are encouraged to override
 * this class and utilize its entry points and order workflow hooks to process orders safely.
 *
 * The order workflow is designed to be accessed through multiple entry points, but still participate
 * in unified order processing with duplicate submission protection, workflow hooks, and a single error handler.
 *
 * The general architecture of the order workflow in this class is as follows:
 *
 * Exposed synchronous order workflow entry points, called by subclasses to start the order workflow
 *  protected Order billAndShipOrder(Order order, Card card);
 *  protected Order billingTransaction(Order order, BillingTransaction billingTransaction);
 *  protected Order shippingTransaction(Order order, ShippingTransaction shippingTransaction);
 *
 * The actual order workflow methods
 *  private Order billOrder(Order order, Card card);
 *  private Order billOrderTransaction(Order order, BillingTransaction billingTransaction);
 *  private Order shipOrder(Order order);
 *  private Order shipOrderTransaction(Order order, ShippingTransaction shippingTransaction);
 *
 * Hooks into order workflow events
 *  protected void onBillingTransactionPaymentCaptured(Order order);
 *  protected void onBillingTransactionPaymentDeclined(Order order);
 *  protected void onShippingTransactionRequested(Order order);
 *  protected void onShippingTransactionInTransit(Order order);
 *  protected void onShippingTransactionDelivered(Order order);
 *  protected void onOrderWorkflowError(Order order, Exception exception);
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractOrderFacade extends ServiceComponent {

	// static properties
	private static final String ORDER_DOMAIN = "OrderWorkflow";
	private static final String SHIPPING_TRANSACTION_IN_TRANSIT_VALIDATION_OBJECT_NAME = "shippingTransactionInTransit";

	// properties
	protected OrderService orderService;
	protected BillingService billingService;
	protected ShippingService shippingService;

	@Override
	protected void onInit() {
		super.onInit();

		// billingService is optional if only remote orders are processed
		Validate.notNull(orderService);
		Validate.notNull(shippingService);
	}

	/**
	 * Entry point for subclasses to process a bill and ship order workflow. This is the standard entry point,
	 * attempting to bill the supplied card, and if successful, ship the order.
	 *
	 * Any exceptions processing this workflow will invoke the onOrderWorkflowError hook to allow
	 * subclasses to raise an alert or notification.
	 *
	 * @param order
	 * @param card
	 * @return Order
	 * @throws OrderException
	 */
	protected Order billAndShipOrder(final Order order, final Card card) {
		try
		{
			logger.info("Order Workflow [#{}]: Entering doBillAndShipOrder", order.getId());

			if(order.isUnsaved())
				throw new IllegalArgumentException("Order must be saved before entering the order workflow");

			Validate.notNull(billingService);

			// synchronize on the orderId to prevent duplicate processing
			synchronized(GlobalMutex.getMutex(getClass(), ORDER_DOMAIN, order.getId()))
			{
				return billOrder(order, card);
			}
		}
		catch(RuntimeException exception)
		{
			String orderId = order == null ? "null" : String.valueOf(order.getId());
			logger.error("Order Workflow [#"+orderId+"]: Exception thrown processing doBillAndShipOrder", exception);
			logger.error("Order Workflow [#"+orderId+"]: [{}]", order);

			// hook into the order workflow error
			onOrderWorkflowError(order, exception);

			throw new OrderException("Error processing doBillAndShipOrder", exception, order);
		}

	}

	/**
	 * Entry point for subclasses to process a bill and ship order workflow. This entry point allows subclasses
	 * to process their own billingTransaction. This may be useful for administrative functions, or to fulfil an order
	 * than has been paid for through some entirely external payment (assuming the transaction is a payment capture).
	 *
	 * The order hooks into the workflow through the billOrderTransaction method, and will not bill an order.
	 *
	 * Any exceptions processing this workflow will invoke the onOrderWorkflowError hook to allow
	 * subclasses to raise an alert or notification.
	 *
	 * @param billingTransaction
	 * @return Order
	 * @throws OrderException
	 */
	protected Order billingTransaction(final BillingTransaction billingTransaction) {
		// a billing transaction may be null if a remote transaction was ignored
		if(billingTransaction == null)
		{
			logger.info("A null billingTransaction was received");
			return null;
		}

		final Order order = billingTransaction.getOrder();

		try
		{
			logger.info("Order Workflow [#{}]: Entering doBillingTransaction", order.getId());

			if(order.isUnsaved())
				throw new IllegalArgumentException("Order must be saved before entering the order workflow");

			// synchronize on the orderId to prevent duplicate processing
			synchronized(GlobalMutex.getMutex(getClass(), ORDER_DOMAIN, order.getId()))
			{
				return billOrderTransaction(order, billingTransaction);
			}
		}
		catch(RuntimeException exception)
		{
			String orderId = order == null ? "null" : String.valueOf(order.getId());
			logger.error("Order Workflow [#"+orderId+"]: Exception thrown processing doBillingTransaction", exception);
			logger.error("Order Workflow [#"+orderId+"]: [{}]", order);

			// hook into the order workflow error
			onOrderWorkflowError(order, exception);

			throw new OrderException("Error processing doBillingTransaction", exception, order);
		}
	}

	/**
	 * Entry point for subclasses to process a bill and ship order workflow. This entry point allows subclasses
	 * to process their own shippingTransaction. This is generally used for shipping status updates for shipping
	 * services that do not provide any notifications.
	 *
	 * The order hooks into the workflow through the shipOrderTransaction method, and will not bill an order.
	 *
	 * Any exceptions processing this workflow will invoke the onOrderWorkflowError hook to allow
	 * subclasses to raise an alert or notification.
	 *
	 * @param shippingTransaction
	 * @return Order
	 * @throws OrderException
	 */
	protected Order shippingTransaction(final ShippingTransaction shippingTransaction) {
		// a shipping transaction may be null if a remote transaction was ignored
		if(shippingTransaction == null)
		{
			logger.info("A null shippingTransaction was received");
			return null;
		}

		final Order order = shippingTransaction.getOrder();

		try
		{
			logger.info("Order Workflow [#{}]: Entering doShippingTransaction", order.getId());

			if(order.isUnsaved())
				throw new IllegalArgumentException("Order must be saved before entering the order workflow");

			// synchronize on the orderId to prevent duplicate processing
			synchronized(GlobalMutex.getMutex(getClass(), ORDER_DOMAIN, order.getId()))
			{
				return shipOrderTransaction(order, shippingTransaction);
			}

		}
		catch(RuntimeException exception)
		{
			String orderId = order == null ? "null" : String.valueOf(order.getId());
			logger.error("Order Workflow [#"+orderId+"]: Exception thrown processing doShippingTransaction", exception);
			logger.error("Order Workflow [#"+orderId+"]: [{}]", order);

			// hook into the order workflow error
			onOrderWorkflowError(order, exception);

			throw new OrderException("Error processing doShippingTransaction", exception, order);
		}
	}

	/**
	 * Attempts to charge the given card for the order supplied, assuming the order was not previously
	 * billed. The result is delegated to the billOrderTransaction to continue the workflow.
	 *
	 * @param order
	 * @param card
	 * @return Order
	 */
	private Order billOrder(Order order, Card card) {
		logger.info("Order Workflow [#{}]: Entering billOrder method", order.getId());

		/*
		 *  If order was already billed, terminate the workflow as this is likely a duplicate submission.
		 *  We don't want to throw an exception here, since if this is the result of a double submit, we
		 *  still want the user to see the confirmation of his order. In general, this should only occur
		 *  if double form submission handling was omitted or incorrectly implemented.
		 *
		 *  We check the object and the database to be absolutely sure the order was not previously billed.
		 */
		if(order.getBillingReceipt().isPaymentCaptured() || orderService.get(order.getId()).getBillingReceipt().isPaymentCaptured())
		{
			logger.warn("BillOrder encountered an order that was already billed, orderId=[{}]", order.getId());
			return order;
		}

		logger.info("Order Workflow [#{}]: BillOrder attempting to save order", order.getId());

		/*
		 * This save serves two purposes. First, it gives us a little insurance - if there is some problem
		 * with the state of the order, we would rather have this fail on this save before we start billing the customer
		 * rather than afterwards. Second, the billing and shipping details will be saved now in case the
		 * actual bill capture does go through and the post save fails.
		 *
		 */
		orderService.save(order);

		// attempt to bill order
		logger.info("Order Workflow [#{}]: BillOrder attempting a card capture for card=[{}]",
				order.getId(), card.getCardNumberEnding());

		return billOrderTransaction(order, billingService.cardCapture(order, card));
	}

	/**
	 * Interprets the billingTransaction provided and takes the appropriate workflow direction. The order
	 * workflow assumes the billingTransaction can have a valid status of PAYMENT_DECLINED, or PAYMENT_CAPTURED.
	 * Any other transaction type is not supported and will terminate the workflow.
	 *
	 * Null billing transactions are possible for notifications that are non-critical from the billing service.
	 *
	 * @param order
	 * @param billingTransaction
	 * @return Order
	 */
	private Order billOrderTransaction(Order order, BillingTransaction billingTransaction) {
		logger.info("Order Workflow [#{}]: Entering billOrderTransaction method", order.getId());

		// if the billing transaction was null, return and ignore
		if(billingTransaction == null)
		{
			logger.info("Order Workflow [#{}]: BillOrderTransaction received an empty billingTransaction", order.getId());
			return order;
		}

		// if the billing transaction was PAYMENT_DECLINED, return and end the workflow after saving the transaction
		if(BillingTransactionStatus.PAYMENT_DECLINED == billingTransaction.getStatus())
		{
			logger.info("Order Workflow [#{}]: BillOrderTransaction received a declined payment transaction",
					order.getId());

			logger.info("Order Workflow [#{}]: BillOrderTransaction attempting to save order", order.getId());

			// we should not check for duplicates, as multiple payment declines are possible for a single order

			// add the billing transaction and save the order
			order.getBillingReceipt().addTransaction(billingTransaction);
			orderService.save(order);

			// allow subclasses to hook into the workflow
			onBillingTransactionPaymentDeclined(order);

			return order;
		}

		// if the billing transaction was PAYMENT_CAPTURED, continue with the order workflow
		if(BillingTransactionStatus.PAYMENT_CAPTURED == billingTransaction.getStatus())
		{
			logger.info("Order Workflow [#{}]: BillOrderTransaction received a payment capture transaction", order.getId());

			// check to make sure this is not a duplicate payment capture
			if(order.getBillingReceipt().isPaymentCaptured() || orderService.get(order.getId()).getBillingReceipt().isPaymentCaptured())
			{
				logger.warn("Order Workflow [#{}]: BillOrderTransaction encountered an order that was already billed", order.getId());
				return order;
			}

			logger.info("Order Workflow [#{}]: BillOrderTransaction attempting to save order", order.getId());

			// add the billing transaction and save the order
			order.getBillingReceipt().addTransaction(billingTransaction);
			orderService.save(order);

			// allow subclasses to hook into the workflow
			onBillingTransactionPaymentCaptured(order);

			return shipOrder(order);
		}

		// if the billing transaction was UNKNOWN, terminate the order workflow
		if(BillingTransactionStatus.UNKNOWN == billingTransaction.getStatus())
		{
			logger.info("Order Workflow [#{}]: BillOrderTransaction received an unknown transaction", order.getId());

			return order;
		}

		// if we have reached here, a billing service implementation has violated its contract
		throw new UnsupportedOperationException("An unsupported billingTransaction was received with " +
				"transactionId=["+billingTransaction.getTransactionId()+"] " +
				"and status=["+billingTransaction.getStatus()+"]");
	}

	/**
	 * Attempts to initiate shipping for the order supplied, assuming the order was not previously
	 * shipped. The result is delegated to the shipOrderTransaction to continue the workflow.
	 *
	 * @param order
	 * @param card
	 * @return Order
	 */
	private Order shipOrder(Order order) {
		logger.info("Order Workflow [#{}]: Entering shipOrder method", order.getId());

		/*
		 *  If order was already shipped, terminate the workflow as this could only happen if the order
		 *  was somehow prepared incorrectly.
		 *
		 *  We check the object and the database to be absolutely sure the order was not previously shipped.
		 */
		if(order.getShippingReceipt().isShippingRequested() || orderService.get(order.getId()).getShippingReceipt().isShippingRequested())
		{
			logger.warn("ShipOrder encountered an order that was already shipped, orderId=[{}]", order.getId());
			return order;
		}

		// attempt to ship order
		logger.info("Order Workflow [#{}]: ShipOrder attempting to ship the order", order.getId());

		return shipOrderTransaction(order, shippingService.requestShipping(order));
	}

	/**
	 * Inteprets the shippingTransaction provided and takes the appropriate workflow direction. The order
	 * workflow assumes the shippingTransaction can have a valid status of REQUESTED, IN_TRANSIT, or DELIVERED.
	 * Any other transaction type is not supported and will terminate the workflow.
	 *
	 * Null shipping transactions are possible for notifications that are non-critical from the shipping service.
	 *
	 * @param order
	 * @param shippingTransaction
	 * @return Order
	 */
	private Order shipOrderTransaction(Order order, ShippingTransaction shippingTransaction) {
		logger.info("Order Workflow [#{}]: Entering shipOrderTransaction method", order.getId());

		// if the shipping transaction was null, return and ignore.
		if(shippingTransaction == null)
		{
			logger.info("Order Workflow [#{}]: ShipOrderTransaction received an empty shippingTransaction", order.getId());
			return order;
		}

		// if the shipping transaction was REQUESTED, return and end the workflow after saving the transaction
		if(ShippingTransactionStatus.REQUESTED == shippingTransaction.getStatus())
		{
			logger.info("Order Workflow [#{}]: ShipOrderTransaction received a requested shipping transaction", order.getId());

			// check to make sure this is not a duplicate request notification
			if(order.getShippingReceipt().isShippingRequested() || orderService.get(order.getId()).getShippingReceipt().isShippingRequested())
			{
				logger.warn("Order Workflow [#{}]: ShipOrderTransaction encountered an order that was already requested", order.getId());
				return order;
			}

			logger.info("Order Workflow [#{}]: ShipOrderTransaction attempting to save order", order.getId());

			// add the shipping transaction and save the order
			order.getShippingReceipt().addTransaction(shippingTransaction);
			orderService.save(order);

			// allow subclasses to hook into the workflow
			onShippingTransactionRequested(order);

			return order;
		}

		// if the shipping transaction was IN_TRANSIT, return and end the workflow after saving the transaction
		if(ShippingTransactionStatus.IN_TRANSIT == shippingTransaction.getStatus())
		{
			logger.info("Order Workflow [#{}]: ShipOrderTransaction received an in-transit shipping transaction", order.getId());

			// check to make sure this is not a duplicate in transit notification
			if(order.getShippingReceipt().isShippingInTransit() || orderService.get(order.getId()).getShippingReceipt().isShippingInTransit())
			{
				logger.info("Order Workflow [#{}]: ShipOrderTransaction encountered an order that was already in transit", order.getId());
				return order;
			}

			logger.info("Order Workflow [#{}]: ShipOrderTransaction attempting to save order", order.getId());

			// add the shipping transaction and save the order
			order.getShippingReceipt().addTransaction(shippingTransaction);
			orderService.save(order);

			// allow subclasses to hook into the workflow
			onShippingTransactionInTransit(order);

			return order;
		}

		// if the shipping transaction was DELIVERED, return and end the workflow after saving the transaction
		if(ShippingTransactionStatus.DELIVERED == shippingTransaction.getStatus())
		{
			logger.info("Order Workflow [#{}]: ShipOrderTransaction received a delivered shipping transaction", order.getId());

			// check to make sure this is not a duplicate in transit notification
			if(order.getShippingReceipt().isShippingDelivered() || orderService.get(order.getId()).getShippingReceipt().isShippingDelivered())
			{
				logger.info("Order Workflow [#{}]: ShipOrderTransaction encountered an order that was already delivered", order.getId());
				return order;
			}

			logger.info("Order Workflow [#{}]: ShipOrderTransaction attempting to save order", order.getId());

			// add the shipping transaction and save the order
			order.getShippingReceipt().addTransaction(shippingTransaction);
			orderService.save(order);

			// allow subclasses to hook into the workflow
			onShippingTransactionDelivered(order);

			return order;
		}

		// if the billing transaction was UNKNOWN, terminate the order workflow
		if(ShippingTransactionStatus.UNKNOWN == shippingTransaction.getStatus())
		{
			logger.info("Order Workflow [#{}]: ShipOrderTransaction received an unknown transaction", order.getId());

			return order;
		}

		// if we have reached here, a shipping service implementation has violated its contract
		throw new UnsupportedOperationException("An unsupported shippingTransaction was received with " +
				"transactionId=["+shippingTransaction.getTransactionId()+"] " +
				"and status=["+shippingTransaction.getStatus()+"]");
	}

	/**
	 * Handles a shipping in transit transaction.
	 *
	 * @param shippingTransactionInTransit
	 * @return Order
	 */
	public final Order shippingTransactionInTransit(ShippingTransactionInTransit shippingTransactionInTransit) {
		new ShippingTransactionInTransitValidator(SHIPPING_TRANSACTION_IN_TRANSIT_VALIDATION_OBJECT_NAME,
				shippingTransactionInTransit, orderService).validate();

		Order order = orderService.get(Order.getId(shippingTransactionInTransit.getOrderInvoiceNumber()));

		// prepare the shipping transaction
		ShippingTransaction shippingTransaction = new ShippingTransaction();
		shippingTransaction.setOrder(order);
		shippingTransaction.setCarrier(shippingTransactionInTransit.getCarrier());
		shippingTransaction.setTrackingNumber(shippingTransactionInTransit.getTrackingNumber());
		shippingTransaction.setDate(new DateTime());
		shippingTransaction.setStatus(ShippingTransactionStatus.IN_TRANSIT);
		shippingTransaction(shippingTransaction);

		return order;
	}

	/**
	 * Hook called when a BillingTransaction was received with a PAYMENT_DECLINED status.
	 *
	 * @note Subclasses should take care not to throw any exceptions that may interrupt the order workflow
	 *
	 * @param order
	 */
	protected void onBillingTransactionPaymentDeclined(Order order) {

	}

	/**
	 * Hook called when a BillingTransaction was received with a PAYMENT_CAPTURED status.
	 *
	 * @note Subclasses should take care not to throw any exceptions that may interrupt the order workflow
	 *
	 * @param order
	 */
	protected void onBillingTransactionPaymentCaptured(Order order) {

	}

	/**
	 * Hook called when a ShippingTransaction was received with a REQUESTED status.
	 *
	 * @note Subclasses should take care not to throw any exceptions that may interrupt the order workflow
	 *
	 * @param order
	 */
	protected void onShippingTransactionRequested(Order order) {

	}

	/**
	 * Hook called when a ShippingTransaction was received with an IN_TRANSIT status.
	 *
	 * @note Subclasses should take care not to throw any exceptions that may interrupt the order workflow
	 *
	 * @param order
	 */
	protected void onShippingTransactionInTransit(Order order) {

	}

	/**
	 * Hook called when a ShippingTransaction was received with a DELIVERED status.
	 * This effectively concludes the order placement workflow.
	 *
	 * @note Subclasses should take care not to throw any exceptions that may interrupt the order workflow
	 *
	 * @param order
	 */
	protected void onShippingTransactionDelivered(Order order) {

	}

	/**
	 * Hook called when an error occurred during the processing of the order workflow.
	 * In the worst case, a customer could have been billed without the successful shipping of a product.
	 *
	 * Subclasses are encouraged to override this method to raise some sort of alert or notification,
	 * as any error during the order workflow is likely to require manual intervention to analyze or correct.
	 *
	 * @note The exception has already been logged prior to this method invocation
	 * @note Take care not to throw any additional exceptions here and ruin your alerts!
	 *
	 * @param order - The order object
	 * @param exception - The exception thrown during the order workflow processing
	 */
	protected void onOrderWorkflowError(Order order, Exception exception) {

	}

	// getters and setters
	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public BillingService getBillingService() {
		return billingService;
	}

	public void setBillingService(BillingService billingService) {
		this.billingService = billingService;
	}

	public ShippingService getShippingService() {
		return shippingService;
	}

	public void setShippingService(ShippingService shippingService) {
		this.shippingService = shippingService;
	}

}