package com.imadp.service.category;

import java.util.ArrayList;
import java.util.List;

import com.imadp.service.user.User;


/**
 * ItemCategory
 *
 * Defines a simple concrete category, useful in grouping or organizing different entities.
 * Categories can be hierarchical through the use of parent and child categories.
 * Category items are stored in a list, and the class is generic to allow  items of any kind.
 *
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public class ItemCategory<T> extends AbstractCategory<ItemCategory<T>> {
	private List<T> items;

	// constructor
	public ItemCategory() {

	}

	// constructor
	public ItemCategory(User user) {
		super(user);
	}

	// constructor
	public ItemCategory(String name) {
		setName(name);
	}

	/**
	 * Adds a new item to this category.
	 *
	 * @param item
	 */
	public void addItem(T item) {
		if(items == null)
			items = new ArrayList<>();

		items.add(item);
	}

	/**
	 * Returns the count of all items in this category.
	 *
	 * @return int
	 */
	public int getItemCount() {
		return items == null ? 0 : items.size();
	}

	// getters and setters
	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

}