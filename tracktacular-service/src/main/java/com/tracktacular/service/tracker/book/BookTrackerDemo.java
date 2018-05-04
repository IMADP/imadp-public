package com.tracktacular.service.tracker.book;

import org.joda.time.DateTime;

import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;



/**
 * BookTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BookTrackerDemo extends AbstractTrackerDemo {

	private BookTrackerFacade trackerFacade;

	// constructor
	public BookTrackerDemo(BookTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		Book book;
		Chapter chapter;

		// target date
		addBook(user, "Cat's Cradle", "Kurt Vonnegut, Jr.", "Science Fiction, Satire", null, new DateTime().plusWeeks(1));

		// uncompleted
		addBook(user, "The Demon Haunted World", "Carl Sagan", "Science, Fiction", null, null);

		// completed
		addBook(user, "The Sirens of Titan", "Kurt Vonnegut, Jr.", "Science Fiction, Satire", 8, null);
		addBook(user, "Breakfast of Champions", "Kurt Vonnegut, Jr.", "Novel, Satire", 8, null);
		addBook(user, "Deadeye Dick", "Kurt Vonnegut, Jr.", "Novel", 6, null);

		addBook(user, "Fight Club", "Chuck Palahniuk", "Fiction", 9, null);
		addBook(user, "Rant", "Chuck Palahniuk", "Fiction", 7, null);
		addBook(user, "Invisible Monsters", "Chuck Palahniuk", "Fiction", 5, null);
		addBook(user, "Damned", "Chuck Palahniuk", "Satire", 6, null);
		addBook(user, "Choke", "Chuck Palahniuk", "Fiction", 5, null);

		addBook(user, "Pale Blue Dot", "Carl Sagan", "Science", 7, null);
		addBook(user, "Contact", "Carl Sagan", "Science Fiction", 7, null);
		addBook(user, "Billions and Billions", "Carl Sagan", "Science, Mathematics", 4, null);

		book = addBook(user, "Cosmos", "Carl Sagan", "Science", 10, null);
		book.setNotes("Cosmos is one of the few books that should be required reading for all humankind.");
		trackerFacade.saveBook(book);

		book = addBook(user, "Pygmy", "Chuck Palahniuk", "Satire", 7, null);
		book.setNotes("Once you learn to handle the odd style of writing this book is extremely funny.");
		trackerFacade.saveBook(book);

		// chapters
		book = addBook(user, "Bagombo Snuff Box", "Kurt Vonnegut, Jr.", "Anthology", 8, null);

		chapter = new Chapter(user, book);
		chapter.setTitle("Thanasphere");
		chapter.setRating(7);
		trackerFacade.saveChapter(chapter);

		chapter = new Chapter(user, book);
		chapter.setTitle("Ambitious Sophomore");
		chapter.setRating(2);
		trackerFacade.saveChapter(chapter);

		chapter = new Chapter(user, book);
		chapter.setTitle("This Son of Mine");
		chapter.setRating(3);
		trackerFacade.saveChapter(chapter);

		book = addBook(user, "Welcome to the Monkey House", "Kurt Vonnegut, Jr.", "Anthology", 5, null);

		chapter = new Chapter(user, book);
		chapter.setTitle("Harrison Bergeron");
		chapter.setRating(9);
		trackerFacade.saveChapter(chapter);

		chapter = new Chapter(user, book);
		chapter.setTitle("Miss Temptation");
		chapter.setRating(9);
		trackerFacade.saveChapter(chapter);

		chapter = new Chapter(user, book);
		chapter.setTitle("EPICAC");
		chapter.setRating(9);
		trackerFacade.saveChapter(chapter);

		chapter = new Chapter(user, book);
		chapter.setTitle("Tom Edison's Shaggy Dog");
		chapter.setRating(7);
		trackerFacade.saveChapter(chapter);

		chapter = new Chapter(user, book);
		chapter.setTitle("More Stately Mansions");
		chapter.setRating(3);
		trackerFacade.saveChapter(chapter);
	}

	/**
	 * Adds a book.
	 *
	 * @param user
	 * @param title
	 * @param author
	 * @param tag
	 * @param rating
	 * @param targetDate
	 * @return book
	 */
	private Book addBook(User user, String title, String author, String tag, Integer rating, DateTime targetDate) {
		Book book = new Book(user);
		book.setTitle(title);
		book.setAuthor(author);
		book.setTag(tag);
		book.setRating(rating);
		book.setTargetDate(targetDate);

		trackerFacade.saveBook(book);

		return book;
	}

}
