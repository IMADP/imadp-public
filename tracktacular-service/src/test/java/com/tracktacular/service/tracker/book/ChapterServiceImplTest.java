package com.tracktacular.service.tracker.book;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * EpisodeServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ChapterServiceImplTest extends TracktacularServiceTestCase {
	Chapter chapter;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();

		Book book = new Book();
		book.setTitle("title");
		book.setAuthor("author");
		book.setTag("tag");
		book.setRating(5);
		book.setTargetDate(new DateTime());
		book.setUser(user);

		chapter = new Chapter();
		chapter.setTitle("title");
		chapter.setBook(book);
		chapter.setTargetDate(new DateTime());
		chapter.setUser(user);

		userService.save(user);
		book_bookService.save(book);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(chapter, book_chapterService);
	}

}
