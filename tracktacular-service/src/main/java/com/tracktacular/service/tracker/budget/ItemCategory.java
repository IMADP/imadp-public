package com.tracktacular.service.tracker.budget;

import java.math.BigDecimal;
import java.util.Set;

import com.imadp.core.Property;
import com.imadp.service.category.AbstractCategory;
import com.imadp.service.user.User;


/**
 * ItemCategory
 *
 * A category of items.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ItemCategory extends AbstractCategory<ItemCategory> {

	// static Properties
	public static final Property<ItemCategory, Boolean> COLLAPSED = Property.of("collapsed");
	public static final Property<ItemCategory, Budget> BUDGET = Property.of("budget");

	// properties
	private Budget budget;
	private Set<Item> items;
	private boolean collapsed;

	// constructor
	public ItemCategory() {

	}

	// constructor
	public ItemCategory(User user, Budget budget) {
		super(user);
		setBudget(budget);
	}

	// constructor
	public ItemCategory(User user, Budget budget, ItemCategory parentCategory) {
		super(user, parentCategory);
		setBudget(budget);
	}

	// constructor
	public ItemCategory(User user, Budget budget, ItemCategory parentCategory, String name) {
		super(user, parentCategory);
		setBudget(budget);
		setName(name);
	}

	// constructor
	public ItemCategory(User user, Budget budget, String name) {
		super(user);
		setBudget(budget);
		setName(name);
	}

	/**
	 * Returns a new item for this category.
	 *
	 * @return Item
	 */
	public Item getNewItem() {
		return new Item(user, this);
	}

	/**
	 * Returns a new sub category.
	 *
	 * @return ItemCategory
	 */
	public ItemCategory getNewSubItemCategory() {
		return new ItemCategory(user, budget, this);
	}

	/**
	 * Returns the net item amount in pennies within this category and child categories.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getNetItemAmount() {
		BigDecimal amount = BigDecimal.ZERO;

		if(items != null)
			for(Item item : items)
				amount = amount.add(item.getTotalItemAmount());

		if(hasChildCategories())
			for(ItemCategory category : getChildCategories())
				amount = amount.add(category.getNetItemAmount());

		return amount;
	}

	/**
	 * Returns true if the net item amount is considered an income or expense otherwise.
	 *
	 * @return boolean
	 */
	public boolean isIncome() {
		return getNetItemAmount().doubleValue() >= 0;
	}

	/**
	 * Returns true if this category has items, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean hasItems() {
		return items != null && !items.isEmpty();
	}

	/**
	 * Returns true if the items are sortable.
	 *
	 * @return boolean
	 */
	public boolean isItemsSortable() {
		return items != null && items.size() > 1;
	}

	// getters and setters
	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public boolean isCollapsed() {
		return collapsed;
	}

	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}

}