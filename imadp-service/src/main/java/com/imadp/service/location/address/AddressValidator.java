package com.imadp.service.location.address;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * AddressValidator
 * 
 * Ensures that the address is valid.
 * 
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.address1.invalidNull
 *       {objectName}.city.invalidNull
 *       {objectName}.state.invalidNull
 *       {objectName}.country.invalidNull
 *       {objectName}.postalCode.invalidNull
 *       
 * @version 1.0
 * @author Anthony DePalma
 */
public class AddressValidator extends AbstractValidator<Address> {
	
	// constructor
	public AddressValidator(String objectName, Address address) {
		super(objectName, address);
	}
	
	@Override
	protected void validate(String objectName, Address address, List<ValidationFailure> failures) {
		String address1Name = join(objectName, Address.ADDRESS_1);
		String cityName = join(objectName, Address.CITY);
		String stateName = join(objectName, Address.STATE);
		String countryName = join(objectName, Address.COUNTRY);
		String postalCodeName = join(objectName, Address.POSTAL_CODE);
		
		// add all validation failures for a null address
		if(address == null)
		{
			addValidationResult(NotNullValidator.invalidNull(address1Name));
			addValidationResult(NotNullValidator.invalidNull(cityName));
			addValidationResult(NotNullValidator.invalidNull(stateName));
			addValidationResult(NotNullValidator.invalidNull(countryName));			
			return;
		}
		
		// validate the address
		addValidationResult(new NotNullValidator(address1Name, address.getAddress1()));
		addValidationResult(new NotNullValidator(cityName, address.getCity()));
		addValidationResult(new NotNullValidator(stateName, address.getState()));
		boolean validCountry = addValidationResult(new NotNullValidator(countryName, address.getCountry()));
		
		// validate the postal code for US users
		if(validCountry && address.getCountry().isUnitedStates())
			addValidationResult(new NotNullValidator(postalCodeName, address.getPostalCode()));
	}
	
}