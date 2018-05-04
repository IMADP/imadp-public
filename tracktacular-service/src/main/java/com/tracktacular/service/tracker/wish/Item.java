package com.tracktacular.service.tracker.wish;

import java.math.BigDecimal;

import com.imadp.core.Property;
import com.imadp.core.money.Money;
import com.imadp.dao.PersistableState;
import com.imadp.service.Sortable;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Item
 *
 * A representation of a wish list item.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Item extends AbstractPersistableUser implements Sortable, Comparable<Item> {

	// static Properties
	public static final Property<Item, String> NAME = Property.of("name");
	public static final Property<Item, String> NOTES = Property.of("notes");
	public static final Property<Item, String> URL = Property.of("url");
	public static final Property<Item, String> DESCRIPTION = Property.of("description");
	public static final Property<Item, Integer> SORT = Property.of("sort");
	public static final Property<Item, BigDecimal> PRICE_AMOUNT = Property.of("price", Money.AMOUNT);
	public static final Property<Item, String> PRICE_CURRENCY = Property.of("price", Money.CURRENCY);

	// properties
	private String url;
	private String name;
	private String description;
	private String notes;
	private Money price;
	private int sort;

	// constructor
	public Item() {
		this(null);
	}

	// constructor
	public Item(User user) {
		super(user);
		setPersistableState(PersistableState.ACTIVE);
	}

	/**
	 * Returns a slug from the name property.
	 *
	 * @return String
	 */
	public String getNameSlug() {
		return toSlug(name);
	}

	// getters and setters
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int getSort() {
		return sort;
	}

	@Override
	public void setSort(int sort) {
		this.sort = sort;
	}

	public Money getPrice() {
		return price;
	}

	public void setPrice(Money price) {
		this.price = price;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public int compareTo(Item o) {
		return Integer.valueOf(sort).compareTo(o.sort);
	}

}