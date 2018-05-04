package com.tracktacular.service.tracker.exercise;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * ExerciseValidator
 *
 * Ensures that the exercise is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.trackCalories.invalidNull
 *       {objectName}.trackDistance.invalidNull
 *       {objectName}.trackDuration.invalidNull
 *       {objectName}.trackRepetitions.invalidNull
 *       {objectName}.trackWeight.invalidNull
 *       {objectName}.name.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ExerciseValidator extends AbstractValidator<Exercise> {

	// length
	private static final int MAX_NAME_LENGTH = 70;
	private static final int MAX_NOTES_LENGTH = 25000;

	// constructor
	public ExerciseValidator(String objectName, Exercise exercise) {
		super(objectName, exercise);
	}

	@Override
	protected void validate(String objectName, Exercise exercise, List<ValidationFailure> failures) {
		String nameName = join(objectName, Exercise.NAME);
		String notesName = join(objectName, Exercise.NOTES);
		String trackCaloriesName = join(objectName, Exercise.TRACK_CALORIES);
		String trackDistanceName = join(objectName, Exercise.TRACK_DISTANCE);
		String trackDurationName = join(objectName, Exercise.TRACK_DURATION);
		String trackRepetitionsName = join(objectName, Exercise.TRACK_REPETITIONS);
		String trackWeightName = join(objectName, Exercise.TRACK_WEIGHT);

		// add all validation failures for a null exercise
		if(exercise == null)
		{
			addValidationResult(NotNullValidator.invalidNull(nameName));
			addValidationResult(NotNullValidator.invalidNull(trackCaloriesName));
			addValidationResult(NotNullValidator.invalidNull(trackDistanceName));
			addValidationResult(NotNullValidator.invalidNull(trackDurationName));
			addValidationResult(NotNullValidator.invalidNull(trackRepetitionsName));
			addValidationResult(NotNullValidator.invalidNull(trackWeightName));
			return;
		}

		// validate the journal
		addValidationResult(new NotNullValidator(nameName, exercise.getName()));
		addValidationResult(new MaxLengthValidator(nameName, exercise.getName(), MAX_NAME_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, exercise.getNotes(), MAX_NOTES_LENGTH));

		// validate that something is tracked
		if(exercise.getTrackCount() == 0)
		{
			addValidationResult(NotNullValidator.invalidNull(trackCaloriesName));
			addValidationResult(NotNullValidator.invalidNull(trackDistanceName));
			addValidationResult(NotNullValidator.invalidNull(trackDurationName));
			addValidationResult(NotNullValidator.invalidNull(trackRepetitionsName));
			addValidationResult(NotNullValidator.invalidNull(trackWeightName));
		}

	}

}