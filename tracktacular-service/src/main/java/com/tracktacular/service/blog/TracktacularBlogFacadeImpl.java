package com.tracktacular.service.blog;

import org.apache.commons.lang.Validate;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.FacadeComponent;
import com.imadp.service.blog.BlogCategories;
import com.imadp.service.blog.BlogCategory;
import com.imadp.service.blog.BlogCategoryService;


/**
 * TracktacularBlogFacadeImpl
 *
 * The standard implementation of the TracktacularBlogFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class TracktacularBlogFacadeImpl extends FacadeComponent implements TracktacularBlogFacade {

	// properties
	private BlogCategoryService blogCategoryService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(blogCategoryService);
	}

	@Override
	public BlogCategories getBlogCategories() {
		return new BlogCategories(blogCategoryService.findBy(CriteriaParams.<BlogCategory>of(Results.ALL, Order.asc(BlogCategory.SORT))));
	}

	// getters and setters
	public BlogCategoryService getBlogCategoryService() {
		return blogCategoryService;
	}

	public void setBlogCategoryService(BlogCategoryService blogCategoryService) {
		this.blogCategoryService = blogCategoryService;
	}

}