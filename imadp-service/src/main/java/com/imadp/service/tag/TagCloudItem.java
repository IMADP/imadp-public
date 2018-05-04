package com.imadp.service.tag;

import com.imadp.core.AbstractSerializable;


/**
 * TagCloudItem
 *
 * Represents a tag cloud item.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TagCloudItem extends AbstractSerializable {
	private final String nameSlug;
	private final long frequency;
	private final double weight;

	// constructor
	public TagCloudItem(String nameSlug, long frequency, double weight) {
		this.nameSlug = nameSlug;
		this.frequency = frequency;
		this.weight = weight;
	}

	// getters and setters
	public String getNameSlug() {
		return nameSlug;
	}

	public long getFrequency() {
		return frequency;
	}

	public double getWeight() {
		return weight;
	}

}
