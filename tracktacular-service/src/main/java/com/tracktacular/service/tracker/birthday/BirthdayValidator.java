package com.tracktacular.service.tracker.birthday;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * BirthdayValidator
 *
 * Ensures that the birthday is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.firstName.invalidNull
 *       {objectName}.firstName.invalidMaxLength
 *       {objectName}.middleName.invalidMaxLength
 *       {objectName}.lastName.invalidNull
 *       {objectName}.lastName.invalidMaxLength
 *       {objectName}.date.invalidNull
 *       {objectName}.notes.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BirthdayValidator extends AbstractValidator<Birthday> {

	// length
	private static final int MAX_NAME_LENGTH = 35;
	private static final int MAX_NOTES_LENGTH = 25000;

	// constructor
	public BirthdayValidator(String objectName, Birthday birthday) {
		super(objectName, birthday);
	}

	@Override
	protected void validate(String objectName, Birthday birthday, List<ValidationFailure> failures) {
		String firstNameName = join(objectName, Birthday.FIRST_NAME);
		String middleNameName = join(objectName, Birthday.MIDDLE_NAME);
		String lastNameName = join(objectName, Birthday.LAST_NAME);
		String dateName = join(objectName, Birthday.DATE);
		String notesName = join(objectName, Birthday.NOTES);

		// add all validation failures for a null birthday
		if(birthday == null)
		{
			addValidationResult(NotNullValidator.invalidNull(firstNameName));
			addValidationResult(NotNullValidator.invalidNull(lastNameName));
			addValidationResult(NotNullValidator.invalidNull(dateName));
			return;
		}

		// validate the birthday
		addValidationResult(new NotNullValidator(firstNameName, birthday.getFirstName()));
		addValidationResult(new NotNullValidator(lastNameName, birthday.getLastName()));
		addValidationResult(new NotNullValidator(dateName, birthday.getDate()));
		addValidationResult(new MaxLengthValidator(firstNameName, birthday.getFirstName(), MAX_NAME_LENGTH));
		addValidationResult(new MaxLengthValidator(middleNameName, birthday.getMiddleName(), MAX_NAME_LENGTH));
		addValidationResult(new MaxLengthValidator(lastNameName, birthday.getLastName(), MAX_NAME_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, birthday.getNotes(), MAX_NOTES_LENGTH));
	}

}