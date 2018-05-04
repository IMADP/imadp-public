package com.imadp.web.stripes.paging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.imadp.web.stripes.AbstractActionBean;
import com.imadp.web.stripes.AbstractActionBeanContext;
import com.imadp.web.stripes.link.Link;

/**
 * PageNavigator
 *
 * A type-safe immutable object providing page navigation.
 *
 * @param <T>
 * @param <V>
 * @author Anthony DePalma
 * @version 1.0
 */
public final class PageNavigator<T extends AbstractActionBean<? extends AbstractActionBeanContext<?>>, V> {

	// paginator resource keys
	private static final String PAGE_NAVIGATOR_FIRST_PAGE_LINK_LABEL_KEY    = "pageNavigator.firstPageLinkLabel";
	private static final String PAGE_NAVIGATOR_PREVIOUS_PAGE_LINK_LABEL_KEY = "pageNavigator.previousPageLinkLabel";
	private static final String PAGE_NAVIGATOR_NEXT_PAGE_LINK_LABEL_KEY 	= "pageNavigator.nextPageLinkLabel";
	private static final String PAGE_NAVIGATOR_LAST_PAGE_LINK_LABEL_KEY 	= "pageNavigator.lastPageLinkLabel";

	// properties
	private final Page<T, V> page;
	private final Link firstPageLink;
	private final Link previousPageLink;
	private final Link nextPageLink;
	private final Link lastPageLink;
	private final List<Link> pageLinks;

	// constructor
	PageNavigator(PageProvider<T, V> pageProvider, int pageNavigationLinks) {
		this.page = pageProvider.getPage();
		this.firstPageLink = findFirstPageLink(pageProvider);
		this.previousPageLink = findPreviousPageLink(pageProvider);
		this.nextPageLink = findNextPageLink(pageProvider);
		this.lastPageLink = findLastPageLink(pageProvider);
		this.pageLinks = findPageLinks(pageProvider, page.getPageCount(), pageNavigationLinks);
	}

	/**
     * Returns true if page navigator is visible.
     *
     * @return boolean
     */
    public boolean isVisible() {
    	return page.getPageCount() > 1;
    }

	/**
	 * Finds the first page link.
	 *
	 * @param pageProvider
	 * @return Link
	 */
	private Link findFirstPageLink(PageProvider<T, V> pageProvider) {
		Link link = pageProvider.createPageLink(pageProvider.getLinkClass(), page.getFirstPageNumber());
		link.setLabel(pageProvider.getLocale(), PAGE_NAVIGATOR_FIRST_PAGE_LINK_LABEL_KEY);

		if(!page.isPreviousPage())
			link.setDisabled(true);

		return link;
	}

	/**
	 * Finds the previous page link.
	 *
	 * @param pageProvider
	 * @return Link
	 */
	private Link findPreviousPageLink(PageProvider<T, V> pageProvider) {
		Link link = pageProvider.createPageLink(pageProvider.getLinkClass(), page.getPreviousPageNumber());
		link.setLabel(pageProvider.getLocale(), PAGE_NAVIGATOR_PREVIOUS_PAGE_LINK_LABEL_KEY);

		if(!page.isPreviousPage())
			link.setDisabled(true);

		return link;
	}

	/**
	 * Finds the next page link.
	 *
	 * @param pageProvider
	 * @return Link
	 */
	private Link findNextPageLink(PageProvider<T, V> pageProvider) {
		Link link = pageProvider.createPageLink(pageProvider.getLinkClass(), page.getNextPageNumber());
		link.setLabel(pageProvider.getLocale(), PAGE_NAVIGATOR_NEXT_PAGE_LINK_LABEL_KEY);

		if(!page.isNextPage())
			link.setDisabled(true);

		return link;
	}

	/**
	 * Finds the last page link.
	 *
	 * @param pageProvider
	 * @return Link
	 */
	private Link findLastPageLink(PageProvider<T, V> pageProvider) {
		Link link = pageProvider.createPageLink(pageProvider.getLinkClass(), page.getLastPageNumber());
		link.setLabel(pageProvider.getLocale(), PAGE_NAVIGATOR_LAST_PAGE_LINK_LABEL_KEY);

		if(!page.isNextPage())
			link.setDisabled(true);

		return link;
	}

	/**
	 * Returns a List of page numbers for the given pageLinkCount.
	 * This method attempts to return a list containing the page numbers of an equal amount
	 * above and below the current page. If a boundary is hit, as in the first or last page,
	 * the remaining pages are added to the opposite side of the list.
	 *
	 * @param pageProvider
	 * @param pageCount
	 * @param pageLinkCount
	 * @return List<Long>
	 */
	private List<Link> findPageLinks(PageProvider<T, V> pageProvider, long pageCount, int pageLinkCount) {
		// if the range is 0, we simply return an empty list
		if(pageLinkCount < 1)
			return Collections.emptyList();

		List<Long> range = new ArrayList<>(pageLinkCount);
		range.add(page.getPageNumber());

		// loop through the paginator range and add the pages to the list
		for(int i = 1; i < pageLinkCount; i++)
		{
			// add pages below the current page
			if(i % 2 != 0)
			{
				if(!addPageBelowPageNumber(range))
					addPageAbovePageNumber(range, pageCount);
			}

			// add pages above the current page
			else
			{
				if(!addPageAbovePageNumber(range, pageCount))
					addPageBelowPageNumber(range);
			}

		}

		List<Link> pageLinks = new ArrayList<>(range.size());

		for(Long page : range)
		{
			Link link = pageProvider.createPageLink(pageProvider.getLinkClass(), page);
			link.setLabel(page);
			link.setDisabled(page == pageProvider.getPageNumber());
			pageLinks.add(link);
		}

		return pageLinks;
	}

	/**
	 * Adds a page to the range if the lower boundary of the range was not hit.
	 * Returns true if a page was added, false otherwise.
	 *
	 * @param range
	 * @return boolean
	 */
	private boolean addPageBelowPageNumber(List<Long> range) {
		long lowerPage = range.get(0);

		if(lowerPage <= 1)
			return false;

		// add the lower page to the beginning of the list
		range.add(0, lowerPage - 1);
		return true;
	}

	/**
	 * Adds a page to the range if the upper boundary of the range was not hit.
	 * Returns true if a page was added, false otherwise.
	 *
	 * @param range
	 * @param pageCount
	 * @return boolean
	 */
	private boolean addPageAbovePageNumber(List<Long> range, long pageCount) {
		long higherPage = range.get(range.size()-1);

		if(higherPage >= pageCount)
			return false;

		// add the higher page to the end of the list
		range.add(higherPage + 1);
		return true;
	}

	// getters
	public Page<T, V> getPage() {
		return page;
	}

	public Link getFirstPageLink() {
		return firstPageLink;
	}

	public Link getPreviousPageLink() {
		return previousPageLink;
	}

	public Link getNextPageLink() {
		return nextPageLink;
	}

	public Link getLastPageLink() {
		return lastPageLink;
	}

	public List<Link> getPageLinks() {
		return pageLinks;
	}

}