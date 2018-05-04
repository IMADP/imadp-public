package com.tracktacular.service.tracker.book;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.joda.time.Interval;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.tracker.AbstractTrackerFacade;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TrackerDemo;
import com.tracktacular.service.tracker.TrackerReport;
import com.tracktacular.service.tracker.calendar.CalendarEntry;


/**
 * BookTrackerFacadeImpl
 *
 * The standard implementation of the BookTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class BookTrackerFacadeImpl extends AbstractTrackerFacade
	implements BookTrackerFacade {

	private BookService bookService;
	private ChapterService chapterService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(bookService);
		Validate.notNull(chapterService);
	}

	@Override
	public Book getBook(User user, String uid) {
		return bookService.findFirstByUser(user, uid);
	}

	@Override
	public void saveBook(Book book) {
		new BookValidator("book", book, findActiveBooks(book.getUser(), Book.TITLE.getName())).validate();
		bookService.save(book);
	}

	@Override
	public void deleteBook(Book book) {
		bookService.delete(book);
	}

	@Override
	public Chapter getChapter(User user, String uid) {
		return chapterService.findFirstByUser(user, uid);
	}

	@Override
	public void saveChapter(Chapter chapter) {
		validateChapter(chapter);
		bookService.saveChapter(chapter);
	}

	@Override
	public void saveChapters(List<Chapter> chapters) {
		for(Chapter chapter : chapters)
			validateChapter(chapter);

		bookService.saveChapters(chapters);
	}

	/**
	 * Validates a Chapter.
	 *
	 * @param chapter
	 */
	private void validateChapter(Chapter chapter) {
		new ChapterValidator("chapter", chapter).validate();
	}

	@Override
	public void deleteChapter(Chapter chapter) {
		bookService.deleteChapter(chapter);
	}

	@Override
	public Books findActiveBooks(User user, String sortProperty) {
		List<Book> activeBooks = bookService.findByUser(
				user, PersistableState.ACTIVE, CriteriaParams.<Book>of(Results.ALL));

		return new Books(activeBooks, sortProperty);
	}

	@Override
	public List<Book> findDeletedBooks(User user, CriteriaParams<Book> criteriaParams) {
		return bookService.findByUser(user, PersistableState.DELETED, criteriaParams);
	}

	@Override
	public long findDeletedBookCount(User user) {
		return bookService.findCountByUser(user, PersistableState.DELETED);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<Book> books = bookService.findByUser(user, CriteriaParams.<Book>of(Results.ALL));

		for(Book book : books)
			deleteBook(book);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new BookTrackerDemo(this);
	}

	@Override
	public TrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		Books books = findActiveBooks(user, Book.TITLE.getName());
		return new BookTrackerReport(preferences.getTrackers().getBookTrackerPreferences(), books);
	}

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		List<CalendarEntry> calendarEntries = new ArrayList<>();

		// active books
		Books books = findActiveBooks(user, Book.TITLE.getName());

		for(BookCategory category : books.getBookCategories())
			for(Book book : category.getItems())
			{
				if(book.getTargetDate() != null)
				{
					CalendarEntry calendarEntry = new CalendarEntry(user, book.getTargetDate());
					calendarEntry.setTracker(Tracker.BOOK);
					calendarEntry.setName(String.format("Book: %s", book.getTitle()));
					calendarEntry.setDescription(book.getAuthor());
					calendarEntries.add(calendarEntry);
				}

				if(book.getChapters() != null)
					for(Chapter chapter : book.getChapters())
					{
						if(chapter.getTargetDate() != null)
						{
							CalendarEntry calendarEntry = new CalendarEntry(user, chapter.getTargetDate());
							calendarEntry.setTracker(Tracker.BOOK);
							calendarEntry.setName(chapter.getTitle());
							calendarEntry.setDescription(book.getTitle());
							calendarEntries.add(calendarEntry);
						}
					}
			}

		return calendarEntries;
	}

	// getters and setters
	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public ChapterService getChapterService() {
		return chapterService;
	}

	public void setChapterService(ChapterService chapterService) {
		this.chapterService = chapterService;
	}

}