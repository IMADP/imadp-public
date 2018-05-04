package com.tracktacular.service.tracker.blood;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * BloodPressureValidator
 *
 * Ensures that the bloodPressure is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.systolic.invalidNull
 *       {objectName}.diastolic.invalidNull
 *       {objectName}.notes.invalidMaxLength
 *       {objectName}.date.invalidNull
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BloodPressureValidator extends AbstractValidator<BloodPressure> {

	// length
	private static final int MAX_NOTES_LENGTH = 25000;

	// constructor
	public BloodPressureValidator(String objectName, BloodPressure bloodPressure) {
		super(objectName, bloodPressure);
	}

	@Override
	protected void validate(String objectName, BloodPressure bloodPressure, List<ValidationFailure> failures) {
		String notesName = join(objectName, BloodPressure.NOTES);
		String systolicName = join(objectName, BloodPressure.SYSTOLIC);
		String diastolicName = join(objectName, BloodPressure.DIASTOLIC);
		String dateName = join(objectName, BloodPressure.DATE);

		// add all validation failures for a null bloodPressure
		if(bloodPressure == null)
		{
			addValidationResult(NotNullValidator.invalidNull(systolicName));
			addValidationResult(NotNullValidator.invalidNull(diastolicName));
			addValidationResult(NotNullValidator.invalidNull(dateName));
			return;
		}

		addValidationResult(new NotNullValidator(systolicName, bloodPressure.getSystolic()));
		addValidationResult(new NotNullValidator(diastolicName, bloodPressure.getDiastolic()));
		addValidationResult(new NotNullValidator(dateName, bloodPressure.getDate()));
		addValidationResult(new MaxLengthValidator(notesName, bloodPressure.getNotes(), MAX_NOTES_LENGTH));
	}

}