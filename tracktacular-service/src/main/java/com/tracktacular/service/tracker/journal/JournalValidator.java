package com.tracktacular.service.tracker.journal;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * JournalValidator
 *
 * Ensures that the journal is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.name.invalidNull
 *       {objectName}.name.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class JournalValidator extends AbstractValidator<Journal> {

	// length
	private static final int MAX_NAME_LENGTH = 35;
	private static final int MAX_NOTES_LENGTH = 25000;
	private static final int MAX_DESCRIPTION_LENGTH = 256;

	// constructor
	public JournalValidator(String objectName, Journal journal) {
		super(objectName, journal);
	}

	@Override
	protected void validate(String objectName, Journal journal, List<ValidationFailure> failures) {
		String nameName = join(objectName, Journal.NAME);
		String descriptionName = join(objectName, Journal.DESCRIPTION);
		String notesName = join(objectName, Journal.NOTES);

		// add all validation failures for a null journal
		if(journal == null)
		{
			addValidationResult(NotNullValidator.invalidNull(nameName));
			return;
		}

		addValidationResult(new NotNullValidator(nameName, journal.getName()));
		addValidationResult(new MaxLengthValidator(nameName, journal.getName(), MAX_NAME_LENGTH));
		addValidationResult(new MaxLengthValidator(descriptionName, journal.getDescription(), MAX_DESCRIPTION_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, journal.getNotes(), MAX_NOTES_LENGTH));
	}

}