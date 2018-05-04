package com.imadp.service.commerce.card;

import java.util.List;

import org.apache.commons.validator.CreditCardValidator;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.SimpleValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * CardValidator
 * 
 * Ensures that the card number, expirationDate, and ccv are valid.
 * 
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.number.invalidNull
 *       {objectName}.number.invalidNumber
 *       {objectName}.expirationMonth.invalidNull
 *       {objectName}.expirationYear.invalidNull
 *       {objectName}.invalidExpirationDate
 *       {objectName}.cvv.invalidNull
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class CardValidator extends AbstractValidator<Card> {
	
	// key
	private static final String INVALID_NUMBER = "invalidNumber";
	private static final String INVALID_EXPIRATION_DATE = "invalidExpirationDate";
	
	// constructor
	public CardValidator(String objectName, Card card) {
		super(objectName, card);
	}
	
	@Override
	protected void validate(String objectName, Card card, List<ValidationFailure> failures) {
		String numberName = join(objectName, Card.NUMBER);
		String expirationMonthName = join(objectName, Card.EXPIRATION_MONTH);
		String expirationYearName = join(objectName, Card.EXPIRATION_YEAR);
		String cvvName = join(objectName, Card.CVV);
		
		// add all validation failures for a null card
		if(card == null)
		{
			addValidationResult(NotNullValidator.invalidNull(numberName));
			addValidationResult(NotNullValidator.invalidNull(expirationMonthName));
			addValidationResult(NotNullValidator.invalidNull(expirationYearName));
			addValidationResult(NotNullValidator.invalidNull(cvvName));
			return;
		}
		
		// validate the card number
		boolean validNull = addValidationResult(new NotNullValidator(numberName, card.getNumber()));
		
		if(validNull)
		{
			boolean validNumber = new CreditCardValidator().isValid(card.getNumber());
			addValidationResult(new SimpleValidator(validNumber, join(numberName, INVALID_NUMBER), numberName));		
		}
		
		// validate the card expiration
		boolean validMonth = addValidationResult(new NotNullValidator(expirationMonthName, card.getExpirationMonth()));
		boolean validYear  = addValidationResult(new NotNullValidator(expirationYearName, card.getExpirationYear()));
		
		if(validMonth && validYear)
		{	
			boolean validExpirationDate = !card.isExpired();
			addValidationResult(new SimpleValidator(validExpirationDate, 
					join(objectName, INVALID_EXPIRATION_DATE), expirationMonthName, expirationYearName));
		}
		
		// validate the cvv field
		addValidationResult(new NotNullValidator(cvvName, card.getCvv()));
	}
	
}