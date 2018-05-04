package com.imadp.service.account.authority;

import org.springframework.security.core.GrantedAuthority;

import com.imadp.core.Property;
import com.imadp.dao.AbstractPersistable;


/**
 * Authority
 * 
 * Represents a level of permission granted to authenticated users.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class Authority extends AbstractPersistable implements GrantedAuthority {
	
	// static Properties
	public static final Property<Authority, String> NAME = Property.of("name");
	
	// properties
	private String name;	

	// constructor
	public Authority() { 
		
	}
	
	// constructor
	public Authority(String name) { 
		this.name = name;
	}
	
	// constructor
	public Authority(Authority authority) {
		this.id = authority.id;
		this.uid = authority.uid;
		this.version = authority.version;
		this.timeCreated = authority.timeCreated;
		this.timeModified = authority.timeModified;
	    this.name = authority.name;
	}
	
	// getters and setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getAuthority() {
		return name;
	}

}
