package com.tracktacular.service.tracker.cholesterol;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * CholesterolValidator
 *
 * Ensures that the cholesterol is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.ldlCholesterol.invalidNull
 *       {objectName}.hdlCholesterol.invalidNull
 *       {objectName}.totalCholesterol.invalidNull
 *       {objectName}.triglycerides.invalidNull
 *       {objectName}.notes.invalidMaxLength
 *       {objectName}.date.invalidNull
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CholesterolValidator extends AbstractValidator<Cholesterol> {

	// length
	private static final int MAX_NOTES_LENGTH = 25000;

	// constructor
	public CholesterolValidator(String objectName, Cholesterol cholesterol) {
		super(objectName, cholesterol);
	}

	@Override
	protected void validate(String objectName, Cholesterol cholesterol, List<ValidationFailure> failures) {
		String notesName = join(objectName, Cholesterol.NOTES);
		String totalCholesterolName = join(objectName, Cholesterol.TOTAL_CHOLESTEROL);
		String ldlCholesterolName = join(objectName, Cholesterol.LDL_CHOLESTEROL);
		String hdlCholesterolName = join(objectName, Cholesterol.HDL_CHOLESTEROL);
		String triglyceridesName = join(objectName, Cholesterol.TRIGLYCERIDES);
		String dateName = join(objectName, Cholesterol.DATE);


		// add all validation failures for a null cholesterol
		if(cholesterol == null)
		{
			addValidationResult(NotNullValidator.invalidNull(totalCholesterolName));
			addValidationResult(NotNullValidator.invalidNull(ldlCholesterolName));
			addValidationResult(NotNullValidator.invalidNull(hdlCholesterolName));
			addValidationResult(NotNullValidator.invalidNull(triglyceridesName));
			addValidationResult(NotNullValidator.invalidNull(dateName));
			return;
		}

		// validate that something is tracked
		if(!cholesterol.hasEntry())
		{
			addValidationResult(NotNullValidator.invalidNull(totalCholesterolName));
			addValidationResult(NotNullValidator.invalidNull(ldlCholesterolName));
			addValidationResult(NotNullValidator.invalidNull(hdlCholesterolName));
			addValidationResult(NotNullValidator.invalidNull(triglyceridesName));
		}

		addValidationResult(new NotNullValidator(dateName, cholesterol.getDate()));
		addValidationResult(new MaxLengthValidator(notesName, cholesterol.getNotes(), MAX_NOTES_LENGTH));
	}

}