package com.imadp.service.commerce.order;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.math.IntRange;

import com.imadp.core.money.Money;
import com.imadp.service.user.AbstractPersistableUser;

/**
 * OrderItemPriceTier
 *
 * An object returning the price of an item based on a tiered quantity scale.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class OrderItemPriceTier extends AbstractPersistableUser {
	private Map<Money, IntRange> priceTiers;

	// constructor
	public OrderItemPriceTier(Map<Money, IntRange> priceTiers) {
		this.priceTiers = Collections.unmodifiableMap(priceTiers);
	}

	/**
	 * Gets the price of an orderItem based on its quantity.
	 *
	 * @param quantity
	 * @return Money
	 */
	public Money getPrice(int quantity) {
		for(Entry<Money, IntRange> entry : priceTiers.entrySet())
			if(entry.getValue().containsInteger(quantity))
				return entry.getKey();

		throw new IllegalArgumentException("No price tier was found for quantity ["+quantity+"]");
	}

}