package com.tracktacular.web.page;

import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.imadp.service.blog.BlogEntry;
import com.tracktacular.service.blog.TracktacularBlogFacade;
import com.tracktacular.web.TracktacularActionBean;


/**
 * BlogFeedActionBean
 *
 * The ActionBean for the blog feed page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/blog-feed")
public class BlogFeedActionBean extends TracktacularActionBean {

	@SpringBean
	private TracktacularBlogFacade blogFacade;

	// views
	private static final String BLOG_FEED_PAGE_VIEW = "/WEB-INF/jsp/page/blogFeedPage.jsp";

	// properties
	private List<BlogEntry> blogEntries;

	@DefaultHandler
	public Resolution index() {
		blogEntries = blogFacade.getBlogCategories().getBlogEntries();
		return new ForwardResolution(BLOG_FEED_PAGE_VIEW);
	}

	// getters and setters
	public List<BlogEntry> getBlogEntries() {
		return blogEntries;
	}

}