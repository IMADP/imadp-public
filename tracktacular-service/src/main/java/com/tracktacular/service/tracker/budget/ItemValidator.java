package com.tracktacular.service.tracker.budget;

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
 *       {objectName}.amount.invalidNull
 *       {objectName}.name.invalidNull
 *       {objectName}.name.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ItemValidator extends AbstractValidator<Item> {

	// length
	private static final int MAX_NAME_LENGTH = 256;
	private static final int MAX_NOTES_LENGTH = 25000;

	// constructor
	public ItemValidator(String objectName, Item item) {
		super(objectName, item);
	}

	@Override
	protected void validate(String objectName, Item item, List<ValidationFailure> failures) {
		String nameName = join(objectName, Item.NAME);
		String amountName = join(objectName, Item.AMOUNT);
		String notesName = join(objectName, Item.NOTES);

		// add all validation failures for a null item
		if(item == null)
		{
			addValidationResult(NotNullValidator.invalidNull(nameName));
			addValidationResult(NotNullValidator.invalidNull(amountName));
			return;
		}

		// validate the item
		addValidationResult(new NotNullValidator(amountName, item.getAmount()));
		addValidationResult(new NotNullValidator(nameName, item.getName()));
		addValidationResult(new MaxLengthValidator(nameName, item.getName(), MAX_NAME_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, item.getNotes(), MAX_NOTES_LENGTH));
	}

}