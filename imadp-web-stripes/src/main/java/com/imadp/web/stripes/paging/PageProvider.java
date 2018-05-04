package com.imadp.web.stripes.paging;

import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Locale;

import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.integration.spring.SpringHelper;

import com.imadp.dao.criteria.Results;
import com.imadp.web.stripes.AbstractActionBean;
import com.imadp.web.stripes.AbstractActionBeanContext;
import com.imadp.web.stripes.ActionBeanAware;
import com.imadp.web.stripes.link.Link;

/**
 * PageProvider
 *
 * A page provider displays a subset of a larger dataset by providing pages of data.
 * A page provider must be initialized with the number of items to display per page,
 * and this value cannot be changed after the page provider is constructed.
 *
 * The class is abstract and provides three methods, findItemCount() and findItems(Results results),
 * createPageLink(Class linkClass, long page) which subclasses must implement to provide the paginated data.
 *
 * The page provider is used by calling getPage(), which provides a snapshot of paginated data computed
 * according to the current page and the findItemCount() and findItems(Results results) methods.
 * The setPageNumber() method can be used to change the current page.
 *
 * The page provider is not thread-safe, it should not be shared among multiple threads for any reason.
 *
 * @param <T>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class PageProvider<T extends AbstractActionBean<? extends AbstractActionBeanContext<?>>, V>
	implements Serializable, ActionBeanAware<T> {

	// default values
	private static final int DEFAULT_ITEMS_PER_PAGE = 10;
	private static final int DEFAULT_PRIMARY_NAVIGATOR_LINKS = 10;
	private static final int DEFAULT_SECONDARY_NAVIGATOR_LINKS = 10;

	// the current page number
	private long pageNumber;

	// the number of items per page
	private long itemsPerPage;

	// the number of links on the page navigators
	private int primaryPageNavigatorLinks;
	private int secondaryPageNavigatorLinks;

	// the class of the page to use in the PageNavigator links
	private Class<? extends T> linkClass;

	// a reference to the page
	private transient Reference<Page<T, V>> pageReference;

	// a reference to the page navigators
	private transient Reference<PageNavigator<T, V>> primaryPageNavigatorReference;
	private transient Reference<PageNavigator<T, V>> secondaryPageNavigatorReference;

	// TODO: Call this when used with threadlocal when 1.6 is released (and remove iactionbeanaware)
	private Locale locale;

	@Override
	public void initialize(T actionBean) {
		this.locale = actionBean.getLocale();
	}

	// constructor
	public PageProvider(Class<? extends T> linkClass) {
		this(linkClass, DEFAULT_ITEMS_PER_PAGE, DEFAULT_PRIMARY_NAVIGATOR_LINKS, DEFAULT_SECONDARY_NAVIGATOR_LINKS);
	}

	// constructor
	public PageProvider(Class<? extends T> linkClass, long itemsPerPage) {
		this(linkClass, itemsPerPage, DEFAULT_PRIMARY_NAVIGATOR_LINKS, DEFAULT_SECONDARY_NAVIGATOR_LINKS);
	}

	// constructor
	public PageProvider(Class<? extends T> linkClass,long itemsPerPage, int primaryPageNavigatorLinks) {
		this(linkClass, itemsPerPage, primaryPageNavigatorLinks, DEFAULT_SECONDARY_NAVIGATOR_LINKS);
	}

	// constructor
	public PageProvider(Class<? extends T> linkClass, long itemsPerPage, int primaryPageNavigatorLinks, int secondaryPageNavigatorLinks) {

		// throw an illegal argument exception if the itemsPerPage was invalid
		if(itemsPerPage < 1)
			throw new IllegalArgumentException("ItemsPerPage must be greater than 0");

		Configuration configuration = StripesFilter.getConfiguration();

		if(configuration != null)
			SpringHelper.injectBeans(this, configuration.getServletContext());

		this.linkClass = linkClass;
		this.itemsPerPage = itemsPerPage;
		this.primaryPageNavigatorLinks = primaryPageNavigatorLinks;
		this.secondaryPageNavigatorLinks = secondaryPageNavigatorLinks;
	}

	/**
	 * Returns the count of all items.
	 * Subclasses should provide the implementation necessary.
	 *
	 * @return long
	 */
	protected abstract long findItemCount();

	/**
	 * Returns the items to display on a page.
	 * Subclasses should provide the implementation necessary.
	 *
	 * @param results
	 * @return List<V>
	 */
	protected abstract List<V> findItems(Results results);

	/**
	 * Creates a page link out of the given page Number.
	 * Subclasses should provide the implementation necessary.
	 *
	 * @note Subclasses should not provide labels for the created links, as any labels would be
	 *       overridden by the localized resources found via the PageProvider key constants.
	 *
	 * @param pageNumber
	 * @return Link
	 */
	protected abstract Link createPageLink(Class<? extends T> linkClass, long pageNumber);

	/**
	 * Returns a page of data according to the current page number.
	 *
	 * @return Page
	 */
	public synchronized Page<T, V> getPage() {
		if(pageReference == null || pageReference.get() == null)
			pageReference = new SoftReference<>(new Page<>(this));

		return pageReference.get();
	}

	/**
	 * Returns the primary PageNavigator.
	 *
	  * @return PageNavigator<T, V>
	 */
	public PageNavigator<T, V> getPrimaryPageNavigator() {
		if(primaryPageNavigatorReference == null || primaryPageNavigatorReference.get() == null)
			primaryPageNavigatorReference = new SoftReference<>(
					new PageNavigator<>(this, primaryPageNavigatorLinks));

		return primaryPageNavigatorReference.get();
	}

	/**
	 * Returns the secondary PageNavigator.
	 *
	  * @return PageNavigator<T, V>
	 */
	public PageNavigator<T, V> getSecondaryPageNavigator() {
		if(secondaryPageNavigatorReference == null || secondaryPageNavigatorReference.get() == null)
			secondaryPageNavigatorReference = new SoftReference<>(
					new PageNavigator<>(this, secondaryPageNavigatorLinks));

		return secondaryPageNavigatorReference.get();
	}

	// getters and setters
	public void setPageNumber(long pageNumber) {
		this.pageNumber = pageNumber;
	}

	public long getPageNumber() {
		return pageNumber < 1 ? 1 : pageNumber;
	}

	long getItemsPerPage() {
		return itemsPerPage;
	}

	Class<? extends T> getLinkClass() {
		return linkClass;
	}

	Locale getLocale() {
		return locale;
	}

}