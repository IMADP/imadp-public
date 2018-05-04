package com.imadp.service;

/**
 * Sortable
 *
 * An interface indicating that an object can be sorted by a specific ordering.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface Sortable {

	/**
	 * Returns the sort order.
	 *
	 * @return int
	 */
	public int getSort();

	/**
	 * Sets the sort order.
	 *
	 * @param sort
	 */
	public void setSort(int sort);

}