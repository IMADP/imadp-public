package com.imadp.service.blog;

import com.imadp.service.category.CategoryService;

/**
 * BlogCategoryService
 *
 * Provides common retrieval operations for BlogCategory objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface BlogCategoryService extends CategoryService<BlogCategory> {

	/**
	 * Saves a blogEntry.
	 *
	 * @param blogEntry
	 */
	public void saveBlogEntry(BlogEntry blogEntry);

	/**
	 * Deletes a blogEntry.
	 *
	 * @param blogEntry
	 */
	public void deleteBlogEntry(BlogEntry blogEntry);

}