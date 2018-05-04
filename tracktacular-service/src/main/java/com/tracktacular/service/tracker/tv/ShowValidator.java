package com.tracktacular.service.tracker.tv;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * ShowValidator
 *
 * Ensures that the show is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.invalidUnique
 *       {objectName}.title.invalidNull
 *       {objectName}.title.invalidMaxLength
 *       {objectName}.tag.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ShowValidator extends AbstractValidator<Show> {

	// length
	private static final int MAX_TITLE_LENGTH = 256;
	private static final int MAX_TAG_LENGTH = 256;
	private static final int MAX_NOTES_LENGTH = 25000;

	// keys
	private static final String INVALID_UNIQUE = "invalidUnique";

	// properties
	private Shows shows;

	// constructor
	public ShowValidator(String objectName, Show show, Shows shows) {
		super(objectName, show);
		this.shows = shows;
	}

	@Override
	protected void validate(String objectName, Show show, List<ValidationFailure> failures) {
		String titleName = join(objectName, Show.TITLE);
		String tagName = join(objectName, Show.TAG);
		String notesName = join(objectName, Show.NOTES);

		// add all validation failures for a null show
		if(show == null)
		{
			addValidationResult(NotNullValidator.invalidNull(titleName));
			return;
		}

		// validate the show
		if(shows.hasDuplicate(show))
			failures.add(new ValidationFailure(join(objectName, INVALID_UNIQUE), titleName));

		addValidationResult(new NotNullValidator(titleName, show.getTitle()));
		addValidationResult(new MaxLengthValidator(titleName, show.getTitle(), MAX_TITLE_LENGTH));
		addValidationResult(new MaxLengthValidator(tagName, show.getTag(), MAX_TAG_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, show.getNotes(), MAX_NOTES_LENGTH));
	}

}