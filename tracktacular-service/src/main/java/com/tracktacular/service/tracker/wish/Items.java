package com.tracktacular.service.tracker.wish;

import java.util.Collections;
import java.util.List;

import com.imadp.core.AbstractSerializable;
import com.imadp.core.money.Money;


/**
 * Items
 *
 * A collection of a items.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Items extends AbstractSerializable {

	// item count
	private final int itemCount;

	// list of all items
	private final List<Item> items;

	// constructor
	public Items(List<Item> items) {
		Collections.sort(items);
		this.items = items;
		this.itemCount = items.size();
	}

	/**
	 * Returns the count of all items.
	 *
	 * @return int
	 */
	public int getItemCount() {
		return itemCount;
	}

	/**
	 * Returns the total price of all wish list items.
	 *
	 * @return Money
	 */
	public Money getTotalPrice() {
		Money money = Money.ZERO;

		for(Item item : items)
			if(item.getPrice() != null)
				money = money.add(item.getPrice());

		return money;
	}

	/**
	 * Returns the list of items.
	 *
	 * @return List<Item>
	 */
	public List<Item> getItems() {
		return items;
	}

}