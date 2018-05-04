package com.tracktacular.service.tracker.book;

import java.util.List;

import com.google.common.collect.Lists;
import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * BookTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class BookTrackerReport extends AbstractTrackerReport {
	private final Books books;
	private final List<Book> unratedBooks;
	private final List<Book> targetDateBooks;
	private final List<Chapter> targetDateChapters;

	// constructor
    public BookTrackerReport(BookTrackerPreferences preferences, Books books) {
    	this.books = books;
    	this.unratedBooks = books.getUnratedBooks();
    	this.targetDateBooks = Lists.newArrayList();
    	this.targetDateChapters = Lists.newArrayList();

    	if(preferences.isAlertOnTargetDates())
    	{
	    	for(BookCategory bookCategory : books.getBookCategories())
	    		for(Book book : bookCategory.getItems())
	    		{
	    			if(isCurrentDate(book.getTargetDate()))
	    				targetDateBooks.add(book);

	    			if(book.getChapters() != null)
		    			for(Chapter chapter : book.getChapters())
		    				if(isCurrentDate(chapter.getTargetDate()))
		    					targetDateChapters.add(chapter);
	    		}
    	}
    }

    @Override
	public boolean isEnabled() {
    	return getBookCount() > 0;
    }

    @Override
    public int getAlertCount() {
    	return targetDateBooks.size() + targetDateChapters.size();
    }

    /**
     * Returns the count of all books.
     *
     * @return int
     */
    public int getBookCount() {
		return books.getBookCount();
	}

    /**
     * Returns the count of unrated books.
     *
     * @return int
     */
    public int getUnratedBookCount() {
		return unratedBooks.size();
	}

    // getters
	public List<Book> getUnratedBooks() {
		return unratedBooks;
	}

	public List<Book> getTargetDateBooks() {
		return targetDateBooks;
	}

	public List<Chapter> getTargetDateChapters() {
		return targetDateChapters;
	}

}