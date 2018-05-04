package com.tracktacular.web.page.tracker.book;

import java.util.List;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.book.Book;
import com.tracktacular.service.tracker.book.Chapter;
import com.tracktacular.service.tracker.book.BookTrackerFacade;
import com.tracktacular.service.tracker.book.BookTrackerPreferences;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * TvTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class BookTrackerActionBean extends TrackerActionBean<BookTrackerFacade, BookTrackerPreferences> {
	private Book book;
	private Chapter chapter;
	private String sortedChapters;

	@Override
	public Tracker getTracker() {
		return Tracker.BOOK;
	}

	/**
	 * Save or updates a Book.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveBook() {
		Book book = getBook();
		populatePersistableUser(book);
		getTrackerFacade().saveBook(book);

		if(book.isActiveState())
			getContext().addSuccessMessage("book.saveBook.success", book.getTitle());

		else if(book.isDeletedState())
			getContext().addSuccessMessage("book.deleteBook.success", book.getTitle());

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Book.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteBook() {
		Book book = getBook();
		getTrackerFacade().deleteBook(book);
		getContext().addSuccessMessage("book.deleteBook.success", book.getTitle());
		return getAjaxSourceResolution();
	}

	/**
	 * Save or updates a Chapter.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveChapter() {
		Chapter chapter = getChapter();
		populatePersistableUser(chapter);
		getTrackerFacade().saveChapter(chapter);
		getContext().addSuccessMessage("book.saveChapter.success", chapter.getTitle());
		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Chapter.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteChapter() {
		Chapter chapter = getChapter();
		getTrackerFacade().deleteChapter(chapter);
		getContext().addSuccessMessage("book.deleteChapter.success", chapter.getTitle());
		return getAjaxSourceResolution();
	}

	/**
	 * Sorts Chapters.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution sortChapters() {
		List<Chapter> sortedCategoriesList = convertObjectList(sortedChapters, Chapter.class);
		List<Chapter> sortedList = getResortedList(sortedCategoriesList);
		getTrackerFacade().saveChapters(sortedList);
		getContext().addSuccessMessage("book.sortChapters.success");
		return getAjaxSourceResolution();
	}

	// getters and setters
	public void setBook(Book book) {
		this.book = book;
	}

	public Book getBook() {
		return book;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public String getSortedChapters() {
		return sortedChapters;
	}

	public void setSortedChapters(String sortedChapters) {
		this.sortedChapters = sortedChapters;
	}

}