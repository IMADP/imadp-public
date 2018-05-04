package com.tracktacular.service.tracker.book;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * ChapterValidator
 *
 * Ensures that the chapter is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.title.invalidNull
 *       {objectName}.title.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ChapterValidator extends AbstractValidator<Chapter> {

	// length
	private static final int MAX_TITLE_LENGTH = 256;
	private static final int MAX_NOTES_LENGTH = 25000;

	// constructor
	public ChapterValidator(String objectName, Chapter chapter) {
		super(objectName, chapter);
	}

	@Override
	protected void validate(String objectName, Chapter chapter, List<ValidationFailure> failures) {
		String titleName = join(objectName, Chapter.TITLE);
		String notesName = join(objectName, Chapter.NOTES);

		// add all validation failures for a null chapter
		if(chapter == null)
		{
			addValidationResult(NotNullValidator.invalidNull(titleName));
			return;
		}

		// validate the journal
		addValidationResult(new NotNullValidator(titleName, chapter.getTitle()));
		addValidationResult(new MaxLengthValidator(titleName, chapter.getTitle(), MAX_TITLE_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, chapter.getNotes(), MAX_NOTES_LENGTH));
	}

}