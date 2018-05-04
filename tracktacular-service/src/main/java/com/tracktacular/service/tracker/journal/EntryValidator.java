package com.tracktacular.service.tracker.journal;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * EntryValidator
 *
 * Ensures that the entry is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.date.invalidNull
 *       {objectName}.content.invalidNull
 *       {objectName}.content.invalidMaxLength
 *       {objectName}.title.invalidNull
 *       {objectName}.title.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class EntryValidator extends AbstractValidator<Entry> {

	// length
	private static final int MAX_CONTENT_LENGTH = 25000;
	private static final int MAX_TITLE_LENGTH = 256;
	private static final int MAX_NOTES_LENGTH = 25000;

	// constructor
	public EntryValidator(String objectName, Entry entry) {
		super(objectName, entry);
	}

	@Override
	protected void validate(String objectName, Entry entry, List<ValidationFailure> failures) {
		String dateName = join(objectName, Entry.DATE);
		String contentName = join(objectName, Entry.CONTENT);
		String titleName = join(objectName, Entry.TITLE);
		String notesName = join(objectName, Entry.NOTES);

		// add all validation failures for a null entry
		if(entry == null)
		{
			addValidationResult(NotNullValidator.invalidNull(titleName));
			addValidationResult(NotNullValidator.invalidNull(dateName));
			addValidationResult(NotNullValidator.invalidNull(contentName));
			return;
		}

		// validate the journal
		addValidationResult(new NotNullValidator(titleName, entry.getTitle()));
		addValidationResult(new NotNullValidator(dateName, entry.getDate()));
		addValidationResult(new NotNullValidator(contentName, entry.getContent()));
		addValidationResult(new MaxLengthValidator(contentName, entry.getContent(), MAX_CONTENT_LENGTH));
		addValidationResult(new MaxLengthValidator(titleName, entry.getTitle(), MAX_TITLE_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, entry.getNotes(), MAX_NOTES_LENGTH));
	}

}