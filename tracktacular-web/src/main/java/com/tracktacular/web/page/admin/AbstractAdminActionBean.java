package com.tracktacular.web.page.admin;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import net.sourceforge.stripes.integration.spring.SpringBean;

import com.google.common.collect.Lists;
import com.imadp.service.blog.BlogCategories;
import com.imadp.service.blog.BlogCategory;
import com.imadp.service.blog.BlogEntry;
import com.imadp.web.stripes.link.Link;
import com.tracktacular.service.admin.TracktacularAdminFacade;
import com.tracktacular.service.blog.TracktacularBlogFacade;
import com.tracktacular.web.TracktacularActionBean;


/**
 * AbstractAdminActionBean
 *
 * The ActionBean for the admin page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@RolesAllowed(value={"admin"})
public class AbstractAdminActionBean extends TracktacularActionBean {

	@SpringBean
	protected TracktacularBlogFacade blogFacade;

	@SpringBean
	protected TracktacularAdminFacade adminFacade;

	// properties
	private BlogCategories blogCategories;

	@Override
	public boolean isMobile() {
		return false;
	}

	@Override
	public List<Link> getContentHeaderLinks() {
		List<Link> links = Lists.newArrayList();
		links.add(new Link(AdminActionBean.class).setLabel("Admin"));
		links.add(new Link(AdminMetricsActionBean.class).setLabel("Metrics"));
		links.add(new Link(AdminStatusReportActionBean.class).setLabel("Status Report"));
		links.add(new Link(AdminBlogCategoryActionBean.class).setLabel("Blog Categories"));
		links.add(new Link(AdminBlogEntryActionBean.class).setLabel("Blog Entries"));
		return links;
	}

	/**
	 * Returns the BlogCategories collection.
	 *
	 * @return BlogCategories
	 */
	public BlogCategories getBlogCategories() {
		if(blogCategories == null)
			blogCategories = blogFacade.getBlogCategories();

		return blogCategories;
	}

	/**
	 * Converts a blog category.
	 *
	 * @param input
	 * @return BlogCategory
	 */
	public BlogCategory convertBlogCategory(String input) {
		return getBlogCategories().getBlogCategory(input);
	}

	/**
	 * Converts a blog entry.
	 *
	 * @param input
	 * @return BlogEntry
	 */
	public BlogEntry convertBlogEntry(String input) {
		return getBlogCategories().getBlogEntry(input);
	}

}