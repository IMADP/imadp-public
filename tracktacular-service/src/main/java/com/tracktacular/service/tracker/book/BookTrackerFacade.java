package com.tracktacular.service.tracker.book;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * BookTrackerFacade
 *
 * Provides functionality for book tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface BookTrackerFacade extends TrackerFacade {

	/**
	 * Gets a book by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Book
	 */
	public Book getBook(User user, String uid);

	/**
	 * Saves a book.
	 *
	 * @param book
	 */
	public void saveBook(Book book);

	/**
	 * Deletes a book.
	 *
	 * @param book
	 */
	public void deleteBook(Book book);

	/**
	 * Gets a chapter by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Chapter
	 */
	public Chapter getChapter(User user, String uid);

	/**
	 * Saves a chapter.
	 *
	 * @param chapter
	 */
	public void saveChapter(Chapter chapter);

	/**
	 * Saves a list of chapters.
	 *
	 * @param chapters
	 */
	public void saveChapters(List<Chapter> chapters);

	/**
	 * Deletes a chapter.
	 *
	 * @param chapter
	 */
	public void deleteChapter(Chapter chapter);

	/**
	 * Finds a List of active Books for a User.
	 *
	 * @param user
	 * @param sortProperty
	 * @return Books
	 */
	public Books findActiveBooks(User user, String sortProperty);

	/**
	 * Finds a List of deleted Books for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Book>
	 */
	public List<Book> findDeletedBooks(User user, CriteriaParams<Book> criteriaParams);

	/**
	 * Finds the count of all deleted Books for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedBookCount(User user);

}