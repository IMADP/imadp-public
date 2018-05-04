package com.tracktacular.web.page.admin;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.service.blog.BlogCategory;


/**
 * AdminBlogCategoryActionBean
 *
 * The ActionBean for the admin page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/admin/blog-category")
public class AdminBlogCategoryActionBean extends AbstractAdminActionBean {

	// views
	private static final String ADMIN_BLOG_CATEGORY_PAGE_VIEW = "/WEB-INF/jsp/page.admin/adminBlogCategoryPage.jsp";

	// properties
	private BlogCategory blogCategory;

	@DefaultHandler
	public Resolution index() {
		return new ForwardResolution(ADMIN_BLOG_CATEGORY_PAGE_VIEW);
	}

	/**
	 * Saves a blog category.
	 *
	 * @return Resolution
	 */
	public final Resolution saveBlogCategory() {
		BlogCategory blogCategory = getBlogCategory();
		adminFacade.saveBlogCategory(blogCategory);
		getContext().addSuccessMessage("admin.saveBlogCategory.success");
		return new RedirectResolution(AdminBlogCategoryActionBean.class);
	}

	/**
	 * Deletes a blog category.
	 *
	 * @return Resolution
	 */
	public final Resolution deleteBlogCategory() {
		BlogCategory blogCategory = getBlogCategory();
		adminFacade.deleteBlogCategory(blogCategory);
		getContext().addSuccessMessage("admin.deleteBlogCategory.success");
		return new RedirectResolution(AdminBlogCategoryActionBean.class);
	}

	// getters and setters
	public BlogCategory getBlogCategory() {
		return blogCategory;
	}

	public void setBlogCategory(BlogCategory blogCategory) {
		this.blogCategory = blogCategory;
	}

}