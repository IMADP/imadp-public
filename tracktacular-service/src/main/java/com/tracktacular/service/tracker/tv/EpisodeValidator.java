package com.tracktacular.service.tracker.tv;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * EpisodeValidator
 *
 * Ensures that the episode is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.title.invalidNull
 *       {objectName}.title.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class EpisodeValidator extends AbstractValidator<Episode> {

	// length
	private static final int MAX_TITLE_LENGTH = 256;
	private static final int MAX_NOTES_LENGTH = 25000;

	// constructor
	public EpisodeValidator(String objectName, Episode episode) {
		super(objectName, episode);
	}

	@Override
	protected void validate(String objectName, Episode episode, List<ValidationFailure> failures) {
		String titleName = join(objectName, Episode.TITLE);
		String notesName = join(objectName, Episode.NOTES);

		// add all validation failures for a null episode
		if(episode == null)
		{
			addValidationResult(NotNullValidator.invalidNull(titleName));
			return;
		}

		// validate the journal
		addValidationResult(new NotNullValidator(titleName, episode.getTitle()));
		addValidationResult(new MaxLengthValidator(titleName, episode.getTitle(), MAX_TITLE_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, episode.getNotes(), MAX_NOTES_LENGTH));
	}

}