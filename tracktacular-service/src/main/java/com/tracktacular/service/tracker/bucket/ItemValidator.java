package com.tracktacular.service.tracker.bucket;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * ItemValidator
 *
 * Ensures that the item is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.name.invalidNull
 *       {objectName}.name.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *       {objectName}.description.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ItemValidator extends AbstractValidator<Item> {

	// length
	private static final int MAX_NAME_LENGTH = 256;
	private static final int MAX_NOTES_LENGTH = 25000;
	private static final int MAX_DESCRIPTION_LENGTH = 256;

	// constructor
	public ItemValidator(String objectName, Item item) {
		super(objectName, item);
	}

	@Override
	protected void validate(String objectName, Item item, List<ValidationFailure> failures) {
		String nameName = join(objectName, Item.NAME);
		String descriptionName = join(objectName, Item.DESCRIPTION);
		String notesName = join(objectName, Item.NOTES);

		// add all validation failures for a null item
		if(item == null)
		{
			addValidationResult(NotNullValidator.invalidNull(nameName));
			return;
		}

		// validate the item
		addValidationResult(new NotNullValidator(nameName, item.getName()));
		addValidationResult(new MaxLengthValidator(nameName, item.getName(), MAX_NAME_LENGTH));
		addValidationResult(new MaxLengthValidator(descriptionName, item.getDescription(), MAX_DESCRIPTION_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, item.getNotes(), MAX_NOTES_LENGTH));
	}

}