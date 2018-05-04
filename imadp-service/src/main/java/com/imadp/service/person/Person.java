package com.imadp.service.person;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Person
 * 
 * The Person class holds the identifiable attributes of a person.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class Person extends AbstractPersistableUser {
	
	// static Properties
	public static final Property<Person, String> PREFIX = Property.of("prefix");
	public static final Property<Person, String> FIRST_NAME = Property.of("firstName");
	public static final Property<Person, String> MIDDLE_NAME = Property.of("middleName");
	public static final Property<Person, String> LAST_NAME = Property.of("lastName");
	public static final Property<Person, Gender> GENDER = Property.of("gender");
	public static final Property<Person, DateTime> BIRTHDATE = Property.of("birthdate");
	
	// properties
	private String prefix;
	private String firstName;
	private String middleName;
	private String lastName;
	private Gender gender;
	private DateTime birthdate;
	
	// constructor
	public Person() { 
		
	}
	
	// constructor
	public Person(User user) {
		this.user = user;
	}
		
	/**
	 * Returns the full name of this person by appending the prefix, firstName, middleName, and lastName if not null.
	 * 
	 * @return String
	 */
	public String getFullName() {
		StringBuilder sb = new StringBuilder("");
		sb.append(prefix != null ? prefix + " ": "");
		sb.append(firstName != null ? firstName + " " : "");
		sb.append(middleName != null ? middleName + " " : "");
		sb.append(lastName != null ? lastName + " " : "");
		return sb.toString().trim();
	}
	
	// getters and setters
	public String getPrefix() {
		return prefix;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
		
	public DateTime getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(DateTime birthdate) {
		this.birthdate = birthdate;
	}
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * Gender
	 * 
	 * Enumerated values of a genders.
	 * 
	 * @version 1.0
	 * @author Anthony DePalma
	 */
	public enum Gender {
		MALE, FEMALE
	}
	
}