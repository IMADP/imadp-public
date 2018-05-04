package com.imadp.service.tag;

import com.imadp.core.AbstractSerializable;
import com.imadp.core.Property;


/**
 * TagFrequency
 *
 * Represents a tag frequency.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TagFrequency extends AbstractSerializable {

	// static Properties
	public static final Property<TagFrequency, String> NAME_SLUG = Property.of("nameSlug");
	public static final Property<TagFrequency, Long> FREQUENCY = Property.of("frequency");

	// properties
	private String nameSlug;
	private long frequency;

	// constructor
	public TagFrequency() {

	}

	// constructor
	public TagFrequency(String nameSlug, long frequency) {
		this.nameSlug = nameSlug;
		this.frequency = frequency;
	}

	// getters and setters
	public String getNameSlug() {
		return nameSlug;
	}

	public void setNameSlug(String nameSlug) {
		this.nameSlug = nameSlug;
	}

	public long getFrequency() {
		return frequency;
	}

	public void setFrequency(long frequency) {
		this.frequency = frequency;
	}

}
