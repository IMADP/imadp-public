package com.tracktacular.service.tracker.music;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * SongValidator
 *
 * Ensures that the song is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.title.invalidNull
 *       {objectName}.title.invalidMaxLength
 *       {objectName}.artist.invalidNull
 *       {objectName}.artist.invalidMaxLength
 *       {objectName}.album.invalidMaxLength
 *       {objectName}.tag.invalidMaxLength
 *       {objectName}.instrument.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class SongValidator extends AbstractValidator<Song> {

	// length
	private static final int MAX_TITLE_LENGTH = 256;
	private static final int MAX_ARTIST_LENGTH = 256;
	private static final int MAX_ALBUM_LENGTH = 256;
	private static final int MAX_TAG_LENGTH = 256;
	private static final int MAX_INSTRUMENT_LENGTH = 256;
	private static final int MAX_NOTES_LENGTH = 25000;

	// constructor
	public SongValidator(String objectName, Song song) {
		super(objectName, song);
	}

	@Override
	protected void validate(String objectName, Song song, List<ValidationFailure> failures) {
		String titleName = join(objectName, Song.TITLE);
		String albumName = join(objectName, Song.ALBUM);
		String artistName = join(objectName, Song.ARTIST);
		String tagName = join(objectName, Song.TAG);
		String instrumentName = join(objectName, Song.INSTRUMENT);
		String notesName = join(objectName, Song.NOTES);

		// add all validation failures for a null song
		if(song == null)
		{
			addValidationResult(NotNullValidator.invalidNull(titleName));
			addValidationResult(NotNullValidator.invalidNull(artistName));
			return;
		}

		// validate the song
		addValidationResult(new NotNullValidator(titleName, song.getTitle()));
		addValidationResult(new NotNullValidator(artistName, song.getArtist()));
		addValidationResult(new MaxLengthValidator(titleName, song.getTitle(), MAX_TITLE_LENGTH));
		addValidationResult(new MaxLengthValidator(artistName, song.getArtist(), MAX_ARTIST_LENGTH));
		addValidationResult(new MaxLengthValidator(albumName, song.getAlbum(), MAX_ALBUM_LENGTH));
		addValidationResult(new MaxLengthValidator(tagName, song.getTag(), MAX_TAG_LENGTH));
		addValidationResult(new MaxLengthValidator(instrumentName, song.getInstrument(), MAX_INSTRUMENT_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, song.getNotes(), MAX_NOTES_LENGTH));
	}

}