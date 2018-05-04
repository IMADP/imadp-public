package com.imadp.service.location.country;

import com.imadp.core.Property;
import com.imadp.dao.AbstractPersistable;


/**
 * Country
 * 
 * The Country represents a country by name and ISO 3166-1 Alpha 2 country code.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class Country extends AbstractPersistable {
	
	// country codes
	private static final String UNITED_STATES_CODE = "US";
	
	// static Properties
	public static final Property<Country, String> NAME = Property.of("name");
	public static final Property<Country, String> CODE = Property.of("code");
	
	// properties
	private String name;
	private String code;	
		
	// constructor
	public Country() {
		
	}
	
	// constructor
	public Country(Country country) {
		this.id = country.id;
		this.uid = country.uid;
		this.version = country.version;
		this.timeCreated = country.timeCreated;
		this.timeModified = country.timeModified;
	    this.name = country.name;
	    this.code = country.code;
	}
	
	/**
	 * Returns true if this country represents the United States, false otherwise.
	 * 
	 * @return boolean
	 */
	public boolean isUnitedStates() {
		return UNITED_STATES_CODE.equalsIgnoreCase(code);
	}
	
	// getters and setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
		
}