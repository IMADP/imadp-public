package com.tracktacular.service.tracker.exercise;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * RoutineValidator
 *
 * Ensures that the routine is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.startDate.invalidNull
 *       {objectName}.name.invalidNull
 *       {objectName}.name.invalidMaxLength
 *       {objectName}.description.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class RoutineValidator extends AbstractValidator<Routine> {

	// length
	private static final int MAX_NAME_LENGTH = 35;
	private static final int MAX_NOTES_LENGTH = 25000;
	private static final int MAX_DESCRIPTION_LENGTH = 256;

	// constructor
	public RoutineValidator(String objectName, Routine routine) {
		super(objectName, routine);
	}

	@Override
	protected void validate(String objectName, Routine routine, List<ValidationFailure> failures) {
		String startDateName = join(objectName, Routine.START_DATE);
		String descriptionName = join(objectName, Routine.DESCRIPTION);
		String nameName = join(objectName, Routine.NAME);
		String notesName = join(objectName, Routine.NOTES);

		// add all validation failures for a null routine
		if(routine == null)
		{
			addValidationResult(NotNullValidator.invalidNull(nameName));
			addValidationResult(NotNullValidator.invalidNull(startDateName));
			return;
		}

		// validate the journal
		addValidationResult(new NotNullValidator(nameName, routine.getName()));
		addValidationResult(new NotNullValidator(startDateName, routine.getStartDate()));
		addValidationResult(new MaxLengthValidator(nameName, routine.getName(), MAX_NAME_LENGTH));
		addValidationResult(new MaxLengthValidator(descriptionName, routine.getDescription(), MAX_DESCRIPTION_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, routine.getNotes(), MAX_NOTES_LENGTH));
	}

}