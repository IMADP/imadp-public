package com.tracktacular.web.page.tracker.book;

import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.service.tracker.book.Books;


/**
 * ShowsActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-book/books/by-{sortProperty=title}")
public class BooksActionBean extends BookTrackerActionBean {

	// properties
	private String sortProperty;
	private Books books;

	/**
	 * Returns a list of books.
	 *
	 * @return Books
	 */
	public Books getBooks() {
		if(books == null)
			books = getTrackerFacade().findActiveBooks(getTrackerUser(), sortProperty);

		return books;
	}

	// getter and setters
	public String getSortProperty() {
		return sortProperty;
	}

	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}

}