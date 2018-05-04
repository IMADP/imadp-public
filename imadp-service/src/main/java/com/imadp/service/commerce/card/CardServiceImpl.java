package com.imadp.service.commerce.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;

import com.imadp.service.PersistableServiceImpl;

/**
 * CardServiceImpl
 *
 * The standard implementation of the CardService.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CardServiceImpl extends PersistableServiceImpl<Card> implements CardService {
	private List<Integer> expirationMonths;
	private List<Integer> expirationYears;

	@Override
	protected void onInit() {
		super.onInit();

		prepareExpirationMonths();
		prepareExpirationYears();
	}

	@Override
	public List<Integer> getExpirationMonths() {
		return expirationMonths;
	}

	@Override
	public List<Integer> getExpirationYears() {
		return expirationYears;
	}

	/**
	 * Prepare the expirationMonths list.
	 *
	 */
	private void prepareExpirationMonths() {
		expirationMonths = new ArrayList<>(12);

		for(int i=1; i<=12; i++)
			expirationMonths.add(i);

		expirationMonths = Collections.unmodifiableList(expirationMonths);
	}

	/**
	 * Prepare the expirationYears list.
	 *
	 */
	private void prepareExpirationYears() {
		expirationYears = new ArrayList<>(10);
		DateTime dateTime = new DateTime();

		for(int i=0; i<10; i++)
			expirationYears.add(dateTime.getYear() + i);

		expirationYears = Collections.unmodifiableList(expirationYears);
	}

}