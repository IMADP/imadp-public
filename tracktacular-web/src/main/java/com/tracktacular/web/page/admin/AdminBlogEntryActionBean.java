package com.tracktacular.web.page.admin;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.service.blog.BlogEntry;


/**
 * AdminBlogEntryActionBean
 *
 * The ActionBean for the admin page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/admin/blog-entry")
public class AdminBlogEntryActionBean extends AbstractAdminActionBean {

	// views
	private static final String ADMIN_BLOG_ENTRY_PAGE_VIEW = "/WEB-INF/jsp/page.admin/adminBlogEntryPage.jsp";

	// properties
	private BlogEntry blogEntry;

	@DefaultHandler
	public Resolution index() {
		return new ForwardResolution(ADMIN_BLOG_ENTRY_PAGE_VIEW);
	}

	/**
	 * Saves a blog entry.
	 *
	 * @return Resolution
	 */
	public final Resolution saveBlogEntry() {
		BlogEntry blogEntry = getBlogEntry();
		adminFacade.saveBlogEntry(blogEntry);
		getContext().addSuccessMessage("admin.saveBlogEntry.success");
		return new RedirectResolution(AdminBlogEntryActionBean.class);
	}

	/**
	 * Saves a blog entry.
	 *
	 * @return Resolution
	 */
	public final Resolution sendBlogEntryNotification() {
		BlogEntry blogEntry = getBlogEntry();
		int blogEntryNotifications = adminFacade.sendBlogEntryNotification(blogEntry);
		getContext().addSuccessMessage("admin.sendBlogEntryNotification.success", blogEntryNotifications);
		return new RedirectResolution(AdminBlogEntryActionBean.class);
	}

	/**
	 * Deletes a blog entry.
	 *
	 * @return Resolution
	 */
	public final Resolution deleteBlogEntry() {
		BlogEntry blogEntry = getBlogEntry();
		adminFacade.deleteBlogEntry(blogEntry);
		getContext().addSuccessMessage("admin.deleteBlogEntry.success");
		return new RedirectResolution(AdminBlogEntryActionBean.class);
	}

	// getters and setters
	public BlogEntry getBlogEntry() {
		return blogEntry;
	}

	public void setBlogEntry(BlogEntry blogEntry) {
		this.blogEntry = blogEntry;
	}

}