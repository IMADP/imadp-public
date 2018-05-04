	package com.tracktacular.web.page.tracker.book;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.core.Property;
import com.imadp.core.validation.ValidationResult;
import com.imadp.service.user.AbstractPersistableUser;
import com.tracktacular.service.tracker.book.Book;
import com.tracktacular.service.tracker.book.BookValidator;
import com.tracktacular.service.tracker.book.Books;
import com.tracktacular.web.TracktacularActionBean;


/**
 * ImportBooksActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-book/import-books")
public class ImportBooksActionBean extends BookTrackerActionBean {

	@Override
	public String getTrackerPageTitle() {
		return "Import Books";
	}

	@Override
	public Resolution index() {
		if(isPublicMode())
			return new RedirectResolution(getDefaultActionBean());

		return super.index();
	}

	@Override
	protected Class<? extends TracktacularActionBean> getUserToggleClass() {
		return getDefaultActionBean();
	}

	@Override
	public Class<? extends AbstractPersistableUser> getImportClass() {
		return Book.class;
	}

	@Override
	public Property<?,?>[] getImportProperties() {
		return new Property[] {Book.AUTHOR, Book.TITLE, Book.TAG, Book.RATING, Book.NOTES};
	}

	@Override
	protected ValidationResult getImportValidationResult(AbstractPersistableUser item) {
		Books books = getTrackerFacade().findActiveBooks(getTrackerUser(), Book.TITLE.getName());
		return new BookValidator("book", (Book) item, books).getValidationResult();
	}

}