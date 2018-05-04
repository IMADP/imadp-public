package com.tracktacular.service.tracker.goal;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * ObjectiveValidator
 *
 * Ensures that the objective is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.targetDate.invalidNull
 *       {objectName}.name.invalidNull
 *       {objectName}.name.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ObjectiveValidator extends AbstractValidator<Objective> {

	// length
	private static final int MAX_NOTES_LENGTH = 25000;
	private static final int MAX_NAME_LENGTH = 256;

	// constructor
	public ObjectiveValidator(String objectName, Objective objective) {
		super(objectName, objective);
	}

	@Override
	protected void validate(String objectName, Objective objective, List<ValidationFailure> failures) {
		String targetDateName = join(objectName, Objective.TARGET_DATE);
		String nameName = join(objectName, Objective.NAME);
		String notesName = join(objectName, Objective.NOTES);

		// add all validation failures for a null objective
		if(objective == null)
		{
			addValidationResult(NotNullValidator.invalidNull(targetDateName));
			addValidationResult(NotNullValidator.invalidNull(nameName));
			return;
		}

		// validate the entry
		addValidationResult(new NotNullValidator(targetDateName, objective.getTargetDate()));
		addValidationResult(new NotNullValidator(nameName, objective.getName()));
		addValidationResult(new MaxLengthValidator(nameName, objective.getName(), MAX_NAME_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, objective.getNotes(), MAX_NOTES_LENGTH));
	}

}