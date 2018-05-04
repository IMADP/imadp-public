package com.tracktacular.service.tracker.bucket;

import java.util.Collections;
import java.util.List;

import com.imadp.core.AbstractSerializable;


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
	 * Returns the list of items.
	 *
	 * @return List<Item>
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * Returns the count of unfinished items.
	 *
	 * @return int
	 */
	public int getUnfinishedItemCount() {
		int unfinishedItemCount = 0;

		for(Item item : getItems())
			if(!item.isCompleted())
				unfinishedItemCount++;

		return unfinishedItemCount;
	}

	/**
	 * Returns the count of finished items.
	 *
	 * @return int
	 */
	public int getFinishedItemCount() {
		int finishedItemCount = 0;

		for(Item item : getItems())
			if(item.isCompleted())
				finishedItemCount++;

		return finishedItemCount;
	}

	/**
	 * Returns the progress as a whole number percentage from 0 to 100 determined by the amount
	 * of completed items vs remaining items.
	 *
	 * @return int
	 */
	public int getProgress() {
		double totalItems = getItemCount();
		double finishedItems = getFinishedItemCount();

		if(totalItems == 0 || finishedItems == 0)
			return 0;

		return Double.valueOf(finishedItems / totalItems * 100).intValue();
	}

	/**
	 * Returns the percentage progress remaining.
	 *
	 * @return int
	 */
	public int getProgressRemaining() {
		return 100 - getProgress();
	}

}