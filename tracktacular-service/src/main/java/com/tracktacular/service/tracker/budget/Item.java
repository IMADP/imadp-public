package com.tracktacular.service.tracker.budget;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.service.Sortable;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Item
 *
 * A representation of a budget item.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Item extends AbstractPersistableUser implements Sortable {

	// static Properties
	public static final Property<Item, ItemCategory> CATEGORY = Property.of("category");
	public static final Property<Item, String> NAME = Property.of("name");
	public static final Property<Item, String> NOTES = Property.of("notes");
	public static final Property<Item, Integer> SORT = Property.of("sort");
	public static final Property<Item, DateTime> DATE = Property.of("date");
	public static final Property<Item, Integer> QUANTITY = Property.of("quantity");
	public static final Property<Item, BigDecimal> AMOUNT = Property.of("amount");

	// properties
	private ItemCategory category;
	private String name;
	private String notes;
	private DateTime date;
	private BigDecimal amount;
	private Integer quantity;
	private int sort;

	// constructor
	public Item() {
		this(null, null);
	}

	// constructor
	public Item(User user, ItemCategory category) {
		this(user, category, null, null);
	}

	// constructor
	public Item(User user, ItemCategory category, String name, BigDecimal amount) {
		super(user);
		this.category = category;
		this.name = name;
		this.amount = amount;
	}

	/**
	 * Returns the total item amount as the quantity * amount.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getTotalItemAmount() {
		return quantity == null ? amount : amount.multiply(new BigDecimal(quantity));
	}

	/**
	 * Returns true if the item is considered an income or expense otherwise.
	 *
	 * @return boolean
	 */
	public boolean isIncome() {
		return getTotalItemAmount().doubleValue() >= 0;
	}

	/**
	 * Returns the budget.
	 *
	 * @return Budget
	 */
	public Budget getBudget() {
		return category.getBudget();
	}

	// getters and setters
	public ItemCategory getCategory() {
		return category;
	}

	public void setCategory(ItemCategory category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public int getSort() {
		return sort;
	}

	@Override
	public void setSort(int sort) {
		this.sort = sort;
	}

}