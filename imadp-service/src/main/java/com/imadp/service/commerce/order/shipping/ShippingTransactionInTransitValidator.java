package com.imadp.service.commerce.order.shipping;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;
import com.imadp.service.commerce.order.Order;
import com.imadp.service.commerce.order.OrderService;


/**
 * ShippingTransactionInTransitValidator
 * 
 * Ensures that the ShippingTransactionInTransit is valid.
 * 
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.orderInvoiceNumber.invalidNull
 *       {objectName}.orderInvoiceNumber.invalidOrderInvoiceNumber
 *       {objectName}.carrier.invalidNull
 *       {objectName}.trackingNumber.invalidNull
 *       
 * @version 1.0
 * @author Anthony DePalma
 */
public class ShippingTransactionInTransitValidator extends AbstractValidator<ShippingTransactionInTransit> {

	// key
	private static final String INVALID_ORDER_INVOICE_NUMBER = "invalidOrderInvoiceNumber";

	private OrderService orderService;

	// constructor
	public ShippingTransactionInTransitValidator(String objectName, 
			ShippingTransactionInTransit transaction, OrderService orderService) {
		super(objectName, transaction);
		this.orderService = orderService;
	}

	@Override
	protected void validate(String objectName, ShippingTransactionInTransit transaction, 
			List<ValidationFailure> failures) {

		String orderInvoiceNumberName = join(objectName, "orderInvoiceNumber");
		String carrierName = join(objectName, "carrier");
		String trackingNumberName = join(objectName, "trackingNumber");

		// add all validation failures for a null transaction
		if(transaction == null)
		{
			addValidationResult(NotNullValidator.invalidNull(orderInvoiceNumberName));
			addValidationResult(NotNullValidator.invalidNull(carrierName));
			addValidationResult(NotNullValidator.invalidNull(trackingNumberName));
			return;
		}

		String invoiceNumber = transaction.getOrderInvoiceNumber();
		boolean notNullInvoiceNumber = addValidationResult(new NotNullValidator(orderInvoiceNumberName, invoiceNumber));

		if(notNullInvoiceNumber)
		{
			Long orderId = Order.getId(invoiceNumber);

			if(orderId == null || orderService.get(orderId) == null)
				failures.add(new ValidationFailure(join(orderInvoiceNumberName, INVALID_ORDER_INVOICE_NUMBER),
						orderInvoiceNumberName));

		}

		addValidationResult(new NotNullValidator(carrierName, transaction.getCarrier()));		
		addValidationResult(new NotNullValidator(trackingNumberName, transaction.getTrackingNumber()));
	}

}