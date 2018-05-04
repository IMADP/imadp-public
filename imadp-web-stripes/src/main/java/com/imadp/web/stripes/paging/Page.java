package com.imadp.web.stripes.paging;

import java.util.Collections;
import java.util.List;

import com.imadp.dao.criteria.Results;
import com.imadp.web.stripes.AbstractActionBean;
import com.imadp.web.stripes.AbstractActionBeanContext;

/**
 * Page
 *
 * An immutable page of data, provided by a pageProvider.
 *
 * @param <T>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public final class Page<T extends AbstractActionBean<? extends AbstractActionBeanContext<?>>, V> {
    private final long itemCount;
    private final long itemsPerPage;
    private final long pageCount;
    private final long pageNumber;
    private final List<V> items;

    // constructor
    @SuppressWarnings("unchecked")
	Page(PageProvider<T, V> pageProvider) {
    	this.itemCount = pageProvider.findItemCount();
        this.itemsPerPage = pageProvider.getItemsPerPage();
        this.pageCount = itemCount > 0 ? findPageCount() : 0;
        this.pageNumber = itemCount > 0 ? findPageNumber(pageProvider) : 0;
        this.items = itemCount > 0 ? pageProvider.findItems(findResults()) : Collections.EMPTY_LIST;
    }

    /**
	 * Finds the total count of pages of items.
	 *
	 * @return long
	 */
	private long findPageCount() {
		long pages = itemCount/itemsPerPage;

		// add an extra page if we have any items left over
		if(itemCount % itemsPerPage != 0)
			pages++;

		return pages;
	}

	/**
	 * Finds the current pageNumber, protecting against values that are out of range for the page set.
	 *
	 * @param pageProvider
	 * @return long
	 */
	private long findPageNumber(PageProvider<T, V> pageProvider) {
		long pageNumber = pageProvider.getPageNumber();

		// catch page numbers below the minimum page number
		if(pageNumber < 1)
			pageNumber = 1;

		// catch page numbers above the total page count
		if(pageNumber > pageCount)
			pageNumber = pageCount;

		return pageNumber;
	}

	/**
	 * Finds the result object for the current page.
	 *
	 * @return Results
	 */
	private Results findResults() {
		// find the first and max results
		long firstResult = ((pageNumber - 1) * itemsPerPage);
		long maxResults = itemsPerPage;

		// trim the max results if they are greater than the itemCount
		if(maxResults > (itemCount - firstResult))
			maxResults = itemCount - firstResult  + 1;

		return new Results(firstResult, maxResults);
	}

    /**
     * Returns true if there are previous pages according to the current pageNumber, false otherwise.
     *
     * @return boolean
     */
    public boolean isPreviousPage() {
        return pageNumber > 1;
    }

    /**
     * Returns true if there are next pages according to the current pageNumber, false otherwise.
     *
     * @return boolean
     */
    public boolean isNextPage() {
        return pageNumber < pageCount;
    }

    /**
     * Returns the first page number.
     *
     * @return long
     */
    public long getFirstPageNumber() {
        return 1;
    }

    /**
     * Returns the previous page number.
     *
     * @return long
     */
    public long getPreviousPageNumber() {
        return isPreviousPage() ? pageNumber - 1 : getFirstPageNumber();
    }

    /**
     * Returns the next page number.
     *
     * @return long
     */
    public long getNextPageNumber() {
        return isNextPage() ? pageNumber + 1 : getLastPageNumber();
    }

    /**
     * Returns the last page number.
     *
     * @return long
     */
    public long getLastPageNumber() {
        return pageCount;
    }

    // getters
    public long getPageNumber() {
        return pageNumber;
    }

    public long getPageCount() {
        return pageCount;
    }

    public long getItemsPerPage() {
        return itemsPerPage;
    }

    public long getItemCount() {
        return itemCount;
    }

    public List<V> getItems() {
        return items;
    }

}