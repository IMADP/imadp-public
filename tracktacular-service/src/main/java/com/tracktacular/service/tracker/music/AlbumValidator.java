package com.tracktacular.service.tracker.music;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * AlbumValidator
 *
 * Ensures that the album is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.invalidUnique
 *       {objectName}.title.invalidNull
 *       {objectName}.title.invalidMaxLength
 *       {objectName}.artist.invalidNull
 *       {objectName}.artist.invalidMaxLength
 *       {objectName}.tag.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class AlbumValidator extends AbstractValidator<Album> {

	// length
	private static final int MAX_TITLE_LENGTH = 256;
	private static final int MAX_ARTIST_LENGTH = 256;
	private static final int MAX_TAG_LENGTH = 256;
	private static final int MAX_NOTES_LENGTH = 25000;

	// keys
	private static final String INVALID_UNIQUE = "invalidUnique";

	// properties
	private Albums albums;

	// constructor
	public AlbumValidator(String objectName, Album album, Albums albums) {
		super(objectName, album);
		this.albums = albums;
	}

	@Override
	protected void validate(String objectName, Album album, List<ValidationFailure> failures) {
		String titleName = join(objectName, Album.TITLE);
		String artistName = join(objectName, Album.ARTIST);
		String tagName = join(objectName, Album.TAG);
		String notesName = join(objectName, Album.NOTES);

		// add all validation failures for a null album
		if(album == null)
		{
			addValidationResult(NotNullValidator.invalidNull(titleName));
			addValidationResult(NotNullValidator.invalidNull(artistName));
			return;
		}

		// validate the album
		if(albums.hasDuplicate(album))
			failures.add(new ValidationFailure(join(objectName, INVALID_UNIQUE), titleName, artistName));

		addValidationResult(new NotNullValidator(titleName, album.getTitle()));
		addValidationResult(new NotNullValidator(artistName, album.getArtist()));
		addValidationResult(new MaxLengthValidator(titleName, album.getTitle(), MAX_TITLE_LENGTH));
		addValidationResult(new MaxLengthValidator(artistName, album.getArtist(), MAX_ARTIST_LENGTH));
		addValidationResult(new MaxLengthValidator(tagName, album.getTag(), MAX_TAG_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, album.getNotes(), MAX_NOTES_LENGTH));
	}

}