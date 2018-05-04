package com.imadp.service.person;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * PersonValidator
 * 
 * Ensures that the person is valid.
 * 
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.firstName.invalidNull
 *       {objectName}.lastName.invalidNull
 *       
 * @version 1.0
 * @author Anthony DePalma
 */
public class PersonValidator extends AbstractValidator<Person> {
	
	// constructor
	public PersonValidator(String objectName, Person person) {
		super(objectName, person);
	}
	
	@Override
	protected void validate(String objectName, Person person, List<ValidationFailure> failures) {
		String firstNameName = join(objectName, Person.FIRST_NAME);
		String lastNameName = join(objectName, Person.LAST_NAME);
		
		// add all validation failures for a null person
		if(person == null)
		{
			addValidationResult(NotNullValidator.invalidNull(firstNameName));
			addValidationResult(NotNullValidator.invalidNull(lastNameName));
			return;
		}
		
		// validate the person
		addValidationResult(new NotNullValidator(firstNameName, person.getFirstName()));
		addValidationResult(new NotNullValidator(lastNameName, person.getLastName()));
	}
	
}