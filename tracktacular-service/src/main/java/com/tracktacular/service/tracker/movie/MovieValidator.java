package com.tracktacular.service.tracker.movie;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * MovieValidator
 *
 * Ensures that the movie is valid.
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
public class MovieValidator extends AbstractValidator<Movie> {

	// length
	private static final int MAX_TITLE_LENGTH = 256;
	private static final int MAX_TAG_LENGTH = 256;
	private static final int MAX_NOTES_LENGTH = 25000;

	// keys
	private static final String INVALID_UNIQUE = "invalidUnique";

	// properties
	private Movies movies;

	// constructor
	public MovieValidator(String objectName, Movie movie, Movies movies) {
		super(objectName, movie);
		this.movies = movies;
	}

	@Override
	protected void validate(String objectName, Movie movie, List<ValidationFailure> failures) {
		String titleName = join(objectName, Movie.TITLE);
		String tagName = join(objectName, Movie.TAG);
		String notesName = join(objectName, Movie.NOTES);

		// add all validation failures for a null movie
		if(movie == null)
		{
			addValidationResult(NotNullValidator.invalidNull(titleName));
			return;
		}

		// validate the movie
		if(movies.hasDuplicate(movie))
			failures.add(new ValidationFailure(join(objectName, INVALID_UNIQUE), titleName));

		addValidationResult(new NotNullValidator(titleName, movie.getTitle()));
		addValidationResult(new MaxLengthValidator(titleName, movie.getTitle(), MAX_TITLE_LENGTH));
		addValidationResult(new MaxLengthValidator(tagName, movie.getTag(), MAX_TAG_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, movie.getNotes(), MAX_NOTES_LENGTH));
	}

}