package com.imadp.service.blog;

import org.apache.commons.lang.Validate;

import com.imadp.service.category.CategoryServiceImpl;


/**
 * BlogCategoryServiceImpl
 *
 * The standard implementation of the BlogCategoryService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class BlogCategoryServiceImpl extends CategoryServiceImpl<BlogCategory> implements BlogCategoryService {
	private BlogEntryService blogEntryService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(blogEntryService);
	}

	@Override
	protected void onBeforeDelete(BlogCategory category) {
		super.onBeforeDelete(category);

		blogEntryService.delete(category.getBlogEntries());

		if(category.hasChildCategories())
			for(BlogCategory blogCategory : category.getChildCategories())
				delete(blogCategory);
	}

	@Override
	public void saveBlogEntry(BlogEntry blogEntry) {
		blogEntryService.save(blogEntry);

		clearAllCaches();
	}

	@Override
	public void deleteBlogEntry(BlogEntry blogEntry) {
		blogEntryService.delete(blogEntry);

		clearAllCaches();
	}

	// getters and setters
	public BlogEntryService getBlogEntryService() {
		return blogEntryService;
	}

	public void setBlogEntryService(BlogEntryService blogEntryService) {
		this.blogEntryService = blogEntryService;
	}

}