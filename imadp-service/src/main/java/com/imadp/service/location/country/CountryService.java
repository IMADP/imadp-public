package com.imadp.service.location.country;

import com.imadp.service.PersistableService;


/**
 * ICountryService
 * 
 * Provides common retrieval operations for Country objects.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public interface CountryService extends PersistableService<Country> {

	/**
	 * Returns the applications's default Country.
	 * 
	 * @return Country
	 */
	public Country getDefault();
	
	/**
	 * Finds a country by code, or null if none was found.
	 * 
	 * @param code
	 * @return Country
	 */
	public Country findByCode(String code);
	
}
 