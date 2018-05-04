package com.tracktacular.service.blog;

import com.imadp.service.blog.BlogCategories;



/**
 * TracktacularBlogFacade
 *
 * Facade encapsulating the blog operations for Tracktacular.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface TracktacularBlogFacade {

	/**
	 * Returns a collection of BlogCategories.
	 *
	 * @return BlogCategories
	 */
	public BlogCategories getBlogCategories();

}