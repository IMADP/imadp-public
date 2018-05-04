package com.tracktacular.service.tracker.book;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * ShowServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BookServiceImplTest extends TracktacularServiceTestCase {
	Book book;
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		book = new Book(user);
		book.setTitle("title");
		book.setAuthor("author");
		book.setTag("tag");
		book.setRating(5);
		book.setTargetDate(new DateTime());

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(book, book_bookService);
	}

}