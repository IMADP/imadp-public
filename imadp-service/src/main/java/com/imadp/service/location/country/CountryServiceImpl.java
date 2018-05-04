package com.imadp.service.location.country;

import org.apache.commons.lang.Validate;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.PersistableServiceImpl;

/**
 * CountryServiceImpl
 *
 * The standard implementation of the CountryService.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CountryServiceImpl extends PersistableServiceImpl<Country> implements CountryService {
	private String defaultCountryCode;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(defaultCountryCode);
	}

	@Override
	public Country getDefault() {
		FindCriteria<Country> findCriteria = dao.findCriteriaBuilder(Results.ONE)
				.whereEqualTo(Country.CODE, defaultCountryCode).build();

		return findFirstBy(findCriteria);
	}

	@Override
	public Country findByCode(String code) {
		FindCriteria<Country> findCriteria = findCriteriaBuilder(CriteriaParams.<Country>of(Results.ONE))
			.whereEqualTo(Country.CODE, code).build();

		return findFirstBy(findCriteria);
	}

	// getters and setters
	public String getDefaultCountryCode() {
		return defaultCountryCode;
	}

	public void setDefaultCountryCode(String defaultCountryCode) {
		this.defaultCountryCode = defaultCountryCode;
	}

}
