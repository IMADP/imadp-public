package com.imadp.service.blog;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import com.imadp.core.AbstractSerializable;
import com.imadp.core.encryption.Base62;


/**
 * BlogCategories
 *
 * A collection of blog categories.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class BlogCategories extends AbstractSerializable {
	private final List<BlogEntry> blogEntries;
	private final List<BlogCategory> categories;

	// constructor
	public BlogCategories(List<BlogCategory> categories) {
		this.categories = categories;
		this.blogEntries = Lists.newArrayListWithExpectedSize(getBlogEntryCount());

		for(BlogCategory blogCategory : categories)
			blogEntries.addAll(blogCategory.getBlogEntries());

		Collections.sort(blogEntries, BlogEntry.DATE_COMPARATOR);
	}

	/**
	 * Returns the total blogEntry count.
	 *
	 * @return int
	 */
	public int getBlogEntryCount() {
		int blogEntryCount = 0;

		for(BlogCategory blogCategory : categories)
			blogEntryCount += blogCategory.getBlogEntryCount();

		return blogEntryCount;
	}

	/**
	 * Finds a blog entry matching the endoded id, null otherwise.
	 *
	 * @param eid
	 * @return BlogEntry
	 */
	public BlogEntry getBlogEntry(String eid) {
		for(BlogEntry entry : blogEntries)
			if(entry.getId().equals(Base62.decode(eid)))
				return entry;

		return null;
	}

	/**
	 * Finds a blog category matching the endoded id, null otherwise.
	 *
	 * @param eid
	 * @return BlogCategory
	 */
	public BlogCategory getBlogCategory(String eid) {
		for(BlogCategory blogCategory : categories)
			if(blogCategory.getId().equals(Base62.decode(eid)))
				return blogCategory;

		return null;
	}

	/**
	 * Finds the most recent blog entry.
	 *
	 * @return BlogEntry
	 */
	public BlogEntry getRecentBlogEntry() {
		return blogEntries.isEmpty() ? null : blogEntries.get(0);
	}

	/**
	 * Returns a list of the most recent blog entries.
	 *
	 * @param count
	 * @return List<BlogEntry>
	 */
	public List<BlogEntry> getRecentBlogEntries(int count) {
		return blogEntries.size() < count ? blogEntries : blogEntries.subList(0, count);
	}

	// getters
	public List<BlogCategory> getCategories() {
		return categories;
	}

	public List<BlogEntry> getBlogEntries() {
		return blogEntries;
	}

}