package com.tracktacular.web.page;

import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.imadp.service.blog.BlogCategories;
import com.imadp.service.blog.BlogEntry;
import com.tracktacular.service.blog.TracktacularBlogFacade;
import com.tracktacular.web.TracktacularActionBean;


/**
 * BlogActionBean
 *
 * The ActionBean for the blog page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/blog/{eid}/{titleSlug}")
public class BlogActionBean extends TracktacularActionBean {

	@SpringBean
	private TracktacularBlogFacade blogFacade;

	private static final int RECENT_BLOG_ENTRIES = 3;

	// views
	private static final String BLOG_PAGE_VIEW = "/WEB-INF/jsp/page/blogPage.jsp";
	private static final String BLOG_MOBILE_PAGE_VIEW = "/WEB-INF/jsp/page/blogPageMobile.jsp";

	// properties
	private BlogEntry blogEntry;
	private BlogCategories blogCategories;
	private String eid;

	@DefaultHandler
	public Resolution index() {
		blogCategories = blogFacade.getBlogCategories();

		// if an encoded id was provided, lookup the entry by id
		if(eid != null)
		{
			blogEntry = blogCategories.getBlogEntry(eid);

			if(blogEntry == null)
				getContext().addErrorMessage("blog.entryNotFound");
		}

		// if no encoded id was provided, or the blog entry couldn't be found, return the most recent entry
		if(blogEntry == null)
			blogEntry = blogCategories.getRecentBlogEntry();

		return new ForwardResolution(isMobile() ? BLOG_MOBILE_PAGE_VIEW : BLOG_PAGE_VIEW);
	}

	/**
	 * Returns a list of recent blog entries.
	 *
	 * @return List<BlogEntry>
	 */
	public List<BlogEntry> getRecentBlogEntries() {
		return blogCategories.getRecentBlogEntries(RECENT_BLOG_ENTRIES);
	}

	// getters and setters
	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public BlogEntry getBlogEntry() {
		return blogEntry;
	}

	public BlogCategories getBlogCategories() {
		return blogCategories;
	}

}