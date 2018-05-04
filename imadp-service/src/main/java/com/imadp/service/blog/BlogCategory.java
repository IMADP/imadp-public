package com.imadp.service.blog;

import java.util.Set;

import com.imadp.service.category.AbstractCategory;
import com.imadp.service.user.User;


/**
 * BlogCategory
 *
 * A category grouping of blogEntries.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BlogCategory extends AbstractCategory<BlogCategory> {

	// properties
	private Set<BlogEntry> blogEntries;

	// constructor
	public BlogCategory() {

	}

	// constructor
	public BlogCategory(User user) {
		super(user);
	}

	// constructor
	public BlogCategory(User user, BlogCategory blogCategory) {
		super(user, blogCategory);
	}

	/**
	 * Returns true if blogEntries are present, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean hasBlogEntries() {
		return blogEntries != null && !blogEntries.isEmpty();
	}

	/**
	 * Returns the count of all blogEntries in this category and child categories.
	 *
	 * @return int
	 */
	public int getBlogEntryCount() {
		int blogCount = 0;

		if(hasBlogEntries())
			blogCount += blogEntries.size();

		for(BlogCategory blogCategory : getChildCategories())
			blogCount += blogCategory.getBlogEntryCount();

		return blogCount;
	}

	// getters and setters
	public Set<BlogEntry> getBlogEntries() {
		return blogEntries;
	}

	public void setBlogEntries(Set<BlogEntry> blogEntries) {
		this.blogEntries = blogEntries;
	}

}