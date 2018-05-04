package com.tracktacular.service.tracker.book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import com.imadp.core.AbstractSerializable;


/**
 * Books
 *
 * A collection of a books.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Books extends AbstractSerializable {

	// book count
	private final int bookCount;

	// list of all books
	private final List<Book> books;

	// list of books by category
	private final Collection<BookCategory> bookCategories;

	// constructor
	public Books(List<Book> books, String sortProperty) {
		this.books = books;
		this.bookCount = books.size();
		this.bookCategories = getBookCategories(books, sortProperty);
	}

	/**
	 * Categorizes and returns a list of BookCategories based on the sortProperty.
	 *
	 * @param books
	 * @param sortProperty
	 * @return Collection<BookCategory>
	 */
	private Collection<BookCategory> getBookCategories(List<Book> books, String sortProperty) {

		// author
		if(Book.AUTHOR.getName().equalsIgnoreCase(sortProperty))
			return getBookCategoriesByAuthor(books);

		// tag
		if(Book.TAG.getName().equalsIgnoreCase(sortProperty))
			return getBookCategoriesByTag(books);

		// rating
		if(Book.RATING.getName().equalsIgnoreCase(sortProperty))
			return getBookCategoriesByRating(books);

		// author
		return getBookCategoriesByTitle(books);
	}

	/**
	 * Returns a collection of book categories by title.
	 *
	 * @param books
	 * @return Collection<BookCategory>
	 */
	private Collection<BookCategory> getBookCategoriesByTitle(List<Book> books) {
		Collections.sort(books, Book.TITLE_COMPARATOR);

		Map<String, BookCategory> categoryMap = new LinkedHashMap<>();

		for(Book book : books)
			getBookCategory(categoryMap, book.getTitle().substring(0, 1)).addItem(book);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of book categories by author.
	 *
	 * @param books
	 * @return Collection<BookCategory>
	 */
	private Collection<BookCategory> getBookCategoriesByAuthor(List<Book> books) {
		Collections.sort(books, Book.AUTHOR_COMPARATOR);

		Map<String, BookCategory> categoryMap = new LinkedHashMap<>();

		for(Book book : books)
			getBookCategory(categoryMap, book.getAuthor()).addItem(book);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of book categories by tag.
	 *
	 * @param books
	 * @return Collection<BookCategory>
	 */
	private Collection<BookCategory> getBookCategoriesByTag(List<Book> books) {
		Collections.sort(books, Book.TITLE_COMPARATOR);

		Map<String, BookCategory> categoryMap = new LinkedHashMap<>();

		for(Book book : books)
			if(!StringUtils.isBlank(book.getTag()))
				for(String tag : book.getTag().split(","))
					if(!StringUtils.isBlank(tag))
						getBookCategory(categoryMap, tag).addItem(book);

		List<BookCategory> bookCategories = new ArrayList<>(categoryMap.values());
		Collections.sort(bookCategories);
		return bookCategories;
	}

	/**
	 * Returns a collection of book categories by rating.
	 *
	 * @param books
	 * @return Collection<BookCategory>
	 */
	private Collection<BookCategory> getBookCategoriesByRating(List<Book> books) {
		Collections.sort(books, Book.RATING_COMPARATOR);

		Map<String, BookCategory> categoryMap = new LinkedHashMap<>();
		getBookCategory(categoryMap, "Unrated");

		for(Book book : books)
		{
			String categoryName = "Unrated";

			if(book.isCompleted())
				categoryName = book.getRating() + "/10";

			getBookCategory(categoryMap, categoryName).addItem(book);
		}

		return categoryMap.values();
	}

	/**
	 * Returns a BookCategory matching the category name.
	 *
	 * @param categoryMap
	 * @param categoryName
	 * @return BookCategory
	 */
	private BookCategory getBookCategory(Map<String, BookCategory> categoryMap, String categoryName) {
		BookCategory bookCategory = categoryMap.get(categoryName.toLowerCase().trim());

		if(bookCategory == null)
		{
			bookCategory = new BookCategory(WordUtils.capitalize(categoryName.trim()));
			categoryMap.put(categoryName.toLowerCase().trim(), bookCategory);
		}

		return bookCategory;
	}

	/**
	 * Returns a list of unrated books.
	 *
	 * @return List<Book>
	 */
	public List<Book> getUnratedBooks() {
		List<Book> unratedBooks = new ArrayList<>();

		for(Book book : books)
			if(!book.isCompleted())
				unratedBooks.add(book);

		Collections.sort(unratedBooks, Book.TITLE_COMPARATOR);

		return unratedBooks;
	}

	/**
	 * Returns the count of all books.
	 *
	 * @return int
	 */
	public int getBookCount() {
		return bookCount;
	}

	/**
	 * Returns a collection of BookCategories.
	 *
	 * @return Collection<BookCategory>
	 */
	public Collection<BookCategory> getBookCategories() {
		return bookCategories;
	}

	/**
	 * Returns true if the given book was found in the books collection, according to title and author.
	 *
	 * @param otherBook
	 * @return boolean
	 */
	public boolean hasDuplicate(Book otherBook) {
		for(Book book : books)
			if(StringUtils.equals(toSlug(book.getTitle()), toSlug(otherBook.getTitle())))
				if(StringUtils.equals(toSlug(book.getAuthor()), toSlug(otherBook.getAuthor())))
					return !book.equals(otherBook);

		return false;
	}

}