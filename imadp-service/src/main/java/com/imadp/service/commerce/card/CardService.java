package com.imadp.service.commerce.card;

import java.util.List;

import com.imadp.service.PersistableService;


/**
 * ICardService
 *
 * Provides common retrieval operations for Card objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface CardService extends PersistableService<Card> {

	/**
	 * Returns a list of all possible card expiration months, 01-12.
	 *
	 * @return List<Integer>
	 */
	public List<Integer> getExpirationMonths();

	/**
	 * Returns a list of all possible card expiration years, current year + 10 years.
	 *
	 * @return List<Integer>
	 */
	public List<Integer> getExpirationYears();

}