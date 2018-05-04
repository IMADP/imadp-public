package com.tracktacular.service.tracker.body;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * BodyValidator
 *
 * Ensures that the body is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.weight.invalidNull
 *       {objectName}.height.invalidNull
 *       {objectName}.neck.invalidNull
 *       {objectName}.chest.invalidNull
 *       {objectName}.waist.invalidNull
 *       {objectName}.hips.invalidNull
 *       {objectName}.biceps.invalidNull
 *       {objectName}.forearms.invalidNull
 *       {objectName}.wrists.invalidNull
 *       {objectName}.thighs.invalidNull
 *       {objectName}.calves.invalidNull
 *       {objectName}.ankles.invalidNull
 *       {objectName}.feet.invalidNull
 *       {objectName}.bodyFat.invalidNull
 *       {objectName}.notes.invalidMaxLength
 *       {objectName}.date.invalidNull
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BodyValidator extends AbstractValidator<Body> {

	// length
	private static final int MAX_NOTES_LENGTH = 25000;

	// constructor
	public BodyValidator(String objectName, Body body) {
		super(objectName, body);
	}

	@Override
	protected void validate(String objectName, Body body, List<ValidationFailure> failures) {
		String weightName = join(objectName, Body.WEIGHT);
		String heightName = join(objectName, Body.HEIGHT);
		String neckName = join(objectName, Body.NECK);
		String chestName = join(objectName, Body.CHEST);
		String waistName = join(objectName, Body.WAIST);
		String hipsName = join(objectName, Body.HIPS);
		String bicepsName = join(objectName, Body.BICEPS);
		String forearmsName = join(objectName, Body.FOREARMS);
		String wristsName = join(objectName, Body.WRISTS);
		String thighsName = join(objectName, Body.THIGHS);
		String calvesName = join(objectName, Body.CALVES);
		String anklesName = join(objectName, Body.ANKLES);
		String feetName = join(objectName, Body.FEET);
		String bodyFatName = join(objectName, Body.BODY_FAT);
		String notesName = join(objectName, Body.NOTES);
		String dateName = join(objectName, Body.DATE);

		// add all validation failures for a null body
		if(body == null)
		{
			addValidationResult(NotNullValidator.invalidNull(weightName));
			addValidationResult(NotNullValidator.invalidNull(heightName));
			addValidationResult(NotNullValidator.invalidNull(neckName));
			addValidationResult(NotNullValidator.invalidNull(chestName));
			addValidationResult(NotNullValidator.invalidNull(waistName));
			addValidationResult(NotNullValidator.invalidNull(hipsName));
			addValidationResult(NotNullValidator.invalidNull(bicepsName));
			addValidationResult(NotNullValidator.invalidNull(forearmsName));
			addValidationResult(NotNullValidator.invalidNull(wristsName));
			addValidationResult(NotNullValidator.invalidNull(thighsName));
			addValidationResult(NotNullValidator.invalidNull(calvesName));
			addValidationResult(NotNullValidator.invalidNull(anklesName));
			addValidationResult(NotNullValidator.invalidNull(feetName));
			addValidationResult(NotNullValidator.invalidNull(bodyFatName));
			addValidationResult(NotNullValidator.invalidNull(dateName));
			return;
		}

		// validate that something is tracked
		if(!body.hasEntry())
		{
			addValidationResult(NotNullValidator.invalidNull(weightName));
			addValidationResult(NotNullValidator.invalidNull(heightName));
			addValidationResult(NotNullValidator.invalidNull(neckName));
			addValidationResult(NotNullValidator.invalidNull(chestName));
			addValidationResult(NotNullValidator.invalidNull(waistName));
			addValidationResult(NotNullValidator.invalidNull(hipsName));
			addValidationResult(NotNullValidator.invalidNull(bicepsName));
			addValidationResult(NotNullValidator.invalidNull(forearmsName));
			addValidationResult(NotNullValidator.invalidNull(wristsName));
			addValidationResult(NotNullValidator.invalidNull(thighsName));
			addValidationResult(NotNullValidator.invalidNull(calvesName));
			addValidationResult(NotNullValidator.invalidNull(anklesName));
			addValidationResult(NotNullValidator.invalidNull(feetName));
			addValidationResult(NotNullValidator.invalidNull(bodyFatName));
		}

		addValidationResult(new NotNullValidator(dateName, body.getDate()));
		addValidationResult(new MaxLengthValidator(notesName, body.getNotes(), MAX_NOTES_LENGTH));
	}

}