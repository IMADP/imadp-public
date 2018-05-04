package com.imadp.service.location.address;

import com.imadp.core.Property;
import com.imadp.service.location.country.Country;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;

/**
 * Address
 * 
 * The Address primarily identifies a user's physical location, as well as the user's email address.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class Address extends AbstractPersistableUser {	
	
	// static Properties
	public static final Property<Address, String> ADDRESS_1 = Property.of("address1");
	public static final Property<Address, String> ADDRESS_2 = Property.of("address2");	
	public static final Property<Address, String> CITY = Property.of("city");		
	public static final Property<Address, String> STATE = Property.of("state");		
	public static final Property<Address, String> POSTAL_CODE = Property.of("postalCode");		
	public static final Property<Address, String> HOME_PHONE = Property.of("homePhone");		
	public static final Property<Address, Country> COUNTRY = Property.of("country");
		
	// properties
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String postalCode;
	private String homePhone;
	private Country country;
	
	// constructor
	public Address() {
		
	}			
	
	// constructor
	public Address(User user) {
		this.user = user;
	}
	
	// constructor
	public Address(Country country) {
		this.country = country;
	}
	
	/**
	 * Returns the full address, combining the address1 and address2 fields together.
	 * 
	 * @return String
	 */
	public String getFullAddress() {
		return address1 + (address2 == null ? "" : " " + address2);
	}
	
	// getters and setters	
	public String getAddress1() {
		return address1;
	}
	
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	public String getAddress2() {
		return address2;
	}
	
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}	
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}
	
	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	
}