package com.imadp.service.commerce.order.shipping;

import java.util.List;

import com.imadp.core.email.EmailValidator;
import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.ValidationFailure;
import com.imadp.service.location.address.AddressValidator;
import com.imadp.service.person.PersonValidator;


/**
 * ShippingDetailsValidator
 * 
 * Ensures that the shipping details are valid.
 * 
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.firstName.invalidNull
 *       {objectName}.lastName.invalidNull
 *       {objectName}.address1.invalidNull
 *       {objectName}.city.invalidNull
 *       {objectName}.state.invalidNull
 *       {objectName}.country.invalidNull
 *       {objectName}.postalCode.invalidNull
 *       {objectName}.email.invalidNull
 *       {objectName}.email.invalidEmail
 * @version 1.0
 * @author Anthony DePalma
 */
public class ShippingDetailsValidator extends AbstractValidator<ShippingDetails> {
	
	// constructor
	public ShippingDetailsValidator(String objectName, ShippingDetails shippingDetails) {
		super(objectName, shippingDetails);
	}
	
	@Override
	protected void validate(String objectName, ShippingDetails shippingDetails, List<ValidationFailure> failures) {
		String emailName = join(objectName, "email");
				
		// add all validation failures for a null address
		if(shippingDetails == null)
		{
			addValidationResult(new PersonValidator(objectName, null));
			addValidationResult(new AddressValidator(objectName, null));			
			addValidationResult(new EmailValidator(emailName, null));			
			return;
		}

		addValidationResult(new PersonValidator(objectName, shippingDetails.getPerson()));
		addValidationResult(new AddressValidator(objectName, shippingDetails.getAddress()));	
		addValidationResult(new EmailValidator(emailName, shippingDetails.getEmail()));
	}
		
}