package com.tracktacular.service.tracker.book;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.service.user.PersistableUserServiceImpl;


/**
 * BookServiceImpl
 *
 * The standard implementation of the BookService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class BookServiceImpl extends PersistableUserServiceImpl<Book> implements BookService {
	private ChapterService chapterService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(chapterService);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveChapters(List<Chapter> chapters) {
		for(Chapter chapter : chapters)
			saveChapter(chapter);
	}

	@Override
	protected void onBeforeDelete(Book book) {
		super.onBeforeDelete(book);

		chapterService.delete(book.getChapters());
	}

	@Override
	public void saveChapter(Chapter chapter) {
		chapterService.save(chapter);

		clearUserCaches(chapter.getUser());
	}

	@Override
	public void deleteChapter(Chapter chapter) {
		chapterService.delete(chapter);

		clearUserCaches(chapter.getUser());
	}

	// getters and setters
	public ChapterService getChapterService() {
		return chapterService;
	}

	public void setChapterService(ChapterService chapterService) {
		this.chapterService = chapterService;
	}

}