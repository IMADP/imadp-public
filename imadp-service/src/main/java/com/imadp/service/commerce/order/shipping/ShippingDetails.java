package com.imadp.service.commerce.order.shipping;

import com.imadp.core.Property;
import com.imadp.service.commerce.order.billing.BillingDetails;
import com.imadp.service.location.address.Address;
import com.imadp.service.location.country.Country;
import com.imadp.service.person.Person;
import com.imadp.service.user.AbstractPersistableUser;

/**
 * ShippingDetails
 *
 * An object containing shipping details for an order.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ShippingDetails extends AbstractPersistableUser {

	// static properties
	public static final Property<ShippingDetails, String> FIRST_NAME = Property.of("firstName");
	public static final Property<ShippingDetails, String> LAST_NAME = Property.of("lastName");
	public static final Property<ShippingDetails, String> ADDRESS_1 = Property.of("address1");
	public static final Property<ShippingDetails, String> ADDRESS_2 = Property.of("address2");
	public static final Property<ShippingDetails, String> CITY = Property.of("city");
	public static final Property<ShippingDetails, String> STATE = Property.of("state");
	public static final Property<ShippingDetails, String> POSTAL_CODE = Property.of("postalCode");
	public static final Property<ShippingDetails, String> HOME_PHONE = Property.of("homePhone");
	public static final Property<ShippingDetails, String> EMAIL = Property.of("email");
	public static final Property<ShippingDetails, Country> COUNTRY = Property.of("country");
	public static final Property<ShippingDetails, String> INSTRUCTIONS = Property.of("instructions");

	// properties
	private Person person;
	private Address address;
	private String email;
	private String instructions;

	// constructor
	public ShippingDetails() {
		this.person = new Person();
		this.address = new Address();
	}

	// constuctor
	public ShippingDetails(BillingDetails billingDetails) {
		this.person = new Person();
		this.address = new Address();

		setAddress1(billingDetails.getAddress1());
		setAddress2(billingDetails.getAddress2());
		setCity(billingDetails.getCity());
		setCountry(billingDetails.getCountry());
		setEmail(billingDetails.getEmail());
		setFirstName(billingDetails.getFirstName());
		setHomePhone(billingDetails.getHomePhone());
		setLastName(billingDetails.getLastName());
		setPostalCode(billingDetails.getPostalCode());
		setState(billingDetails.getState());
	}

	// getters and setters
	public String getFullName() {
		return person.getFullName();
	}

	public String getFirstName() {
		return person.getFirstName();
	}

	public String getFullAddress() {
		return address.getFullAddress();
	}

	public void setFirstName(String firstName) {
		person.setFirstName(firstName);
	}

	public String getLastName() {
		return person.getLastName();
	}

	public void setLastName(String lastName) {
		person.setLastName(lastName);
	}

	public String getAddress1() {
		return address.getAddress1();
	}

	public void setAddress1(String address1) {
		address.setAddress1(address1);
	}

	public String getAddress2() {
		return address.getAddress2();
	}

	public void setAddress2(String address2) {
		address.setAddress2(address2);
	}

	public String getCity() {
		return address.getCity();
	}

	public void setCity(String city) {
		address.setCity(city);
	}

	public String getState() {
		return address.getState();
	}

	public void setState(String state) {
		address.setState(state);
	}

	public String getPostalCode() {
		return address.getPostalCode();
	}

	public void setPostalCode(String postalCode) {
		address.setPostalCode(postalCode);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Country getCountry() {
		return address.getCountry();
	}

	public void setCountry(Country country) {
		address.setCountry(country);
	}

	public String getHomePhone() {
		return address.getHomePhone();
	}

	public void setHomePhone(String homePhone) {
		address.setHomePhone(homePhone);
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	Person getPerson() {
		return person;
	}

	Address getAddress() {
		return address;
	}

}