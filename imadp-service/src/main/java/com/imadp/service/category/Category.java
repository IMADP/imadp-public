package com.imadp.service.category;

import com.imadp.service.user.User;


/**
 * Category
 *
 * Defines a simple concrete category, useful in grouping or organizing different entities.
 * Categories can be hierarchical through the use of parent and child categories.
 * However, category items are not stored here, unlike the ItemCategory.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Category extends AbstractCategory<Category> {

	// constructor
	public Category() {

	}

	// constructor
	public Category(User user) {
		super(user);
	}

}