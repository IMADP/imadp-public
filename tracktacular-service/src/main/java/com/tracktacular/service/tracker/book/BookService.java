package com.tracktacular.service.tracker.book;

import java.util.List;

import com.imadp.service.user.PersistableUserService;

/**
 * BookService
 *
 * Provides common retrieval operations for Book objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface BookService extends PersistableUserService<Book> {

	/**
	 * Saves an chapter.
	 *
	 * @param chapter
	 */
	public void saveChapter(Chapter chapter);

	/**
	 * Saves a list of book chapters.
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

}