package com.imadp.dao.hibernate;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.dao.AbstractPersistable;


/**
 * SamplePersistable	
 * 
 * A sample persistable object.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class SamplePersistable extends AbstractPersistable {
	
	// static Properties
	public static final Property<SamplePersistable, String> NAME = Property.of("name");
	public static final Property<SamplePersistable, Integer> INT_NUMBER = Property.of("intNumber");
	public static final Property<SamplePersistable, Long> LONG_NUMBER = Property.of("longNumber");
	public static final Property<SamplePersistable, DateTime> START_DATE = Property.of("startDate");
	public static final Property<SamplePersistable, DateTime> END_DATE = Property.of("endDate");
	public static final Property<SamplePersistable, String> DESCRIPTION = Property.of("description");	

	// properties
	private String name;
	private DateTime startDate;
	private DateTime endDate;
	private int intNumber;
	private long longNumber;
	private String description;
		
	// constructor
	public SamplePersistable() { 
		
	}
		
	// constructor
	public SamplePersistable(String name) { 
		this.name = name;
	}
		
	// constructor
	public SamplePersistable(Long id) { 
		this.id = id;
	}
		
	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIntNumber() {
		return intNumber;
	}

	public void setIntNumber(int intNumber) {
		this.intNumber = intNumber;
	}

	public long getLongNumber() {
		return longNumber;
	}

	public void setLongNumber(long longNumber) {
		this.longNumber = longNumber;
	}
	
	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	// TODO: remove these methods if the documentId inheritance bug is fixed
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}	
	
}