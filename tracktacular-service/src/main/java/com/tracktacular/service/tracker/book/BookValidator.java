package com.tracktacular.service.tracker.book;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * BookValidator
 *
 * Ensures that the book is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.invalidUnique
 *       {objectName}.author.invalidNull
 *       {objectName}.author.invalidMaxLength
 *       {objectName}.title.invalidNull
 *       {objectName}.title.invalidMaxLength
 *       {objectName}.tag.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BookValidator extends AbstractValidator<Book> {

	// length
	private static final int MAX_AUTHOR_LENGTH = 256;
	private static final int MAX_TITLE_LENGTH = 256;
	private static final int MAX_TAG_LENGTH = 256;
	private static final int MAX_NOTES_LENGTH = 25000;

	// keys
	private static final String INVALID_UNIQUE = "invalidUnique";

	// properties
	private Books books;

	// constructor
	public BookValidator(String objectName, Book book, Books books) {
		super(objectName, book);
		this.books = books;
	}

	@Override
	protected void validate(String objectName, Book book, List<ValidationFailure> failures) {
		String authorName = join(objectName, Book.AUTHOR);
		String titleName = join(objectName, Book.TITLE);
		String tagName = join(objectName, Book.TAG);
		String notesName = join(objectName, Book.NOTES);

		// add all validation failures for a null book
		if(book == null)
		{
			addValidationResult(NotNullValidator.invalidNull(authorName));
			addValidationResult(NotNullValidator.invalidNull(titleName));
			return;
		}

		// validate the book
		if(books.hasDuplicate(book))
			failures.add(new ValidationFailure(join(objectName, INVALID_UNIQUE), titleName, authorName));

		addValidationResult(new NotNullValidator(authorName, book.getAuthor()));
		addValidationResult(new NotNullValidator(titleName, book.getTitle()));
		addValidationResult(new MaxLengthValidator(authorName, book.getAuthor(), MAX_AUTHOR_LENGTH));
		addValidationResult(new MaxLengthValidator(titleName, book.getTitle(), MAX_TITLE_LENGTH));
		addValidationResult(new MaxLengthValidator(tagName, book.getTag(), MAX_TAG_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, book.getNotes(), MAX_NOTES_LENGTH));
	}

}