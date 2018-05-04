package com.tracktacular.service.tracker.exercise;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * WorkoutValidator
 *
 * Ensures that the workout is valid.
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
public class WorkoutValidator extends AbstractValidator<Workout> {

	// length
	private static final int MAX_NAME_LENGTH = 35;
	private static final int MAX_NOTES_LENGTH = 25000;

	// constructor
	public WorkoutValidator(String objectName, Workout workout) {
		super(objectName, workout);
	}

	@Override
	protected void validate(String objectName, Workout workout, List<ValidationFailure> failures) {
		String dateName = join(objectName, Workout.DATE);
		String nameName = join(objectName, Workout.NAME);
		String notesName = join(objectName, Workout.NOTES);

		// add all validation failures for a null workout
		if(workout == null)
		{
			addValidationResult(NotNullValidator.invalidNull(nameName));
			addValidationResult(NotNullValidator.invalidNull(dateName));
			return;
		}

		// validate the journal
		addValidationResult(new NotNullValidator(nameName, workout.getName()));
		addValidationResult(new NotNullValidator(dateName, workout.getDate()));
		addValidationResult(new MaxLengthValidator(nameName, workout.getName(), MAX_NAME_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, workout.getNotes(), MAX_NOTES_LENGTH));
	}

}