package com.imadp.service.tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.springframework.util.CollectionUtils;

import com.imadp.core.AbstractSerializable;


/**
 * TagCloud
 *
 * An object encapsulating a tag cloud creater out of a list of tag frequencies.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TagCloud extends AbstractSerializable {
	private final List<TagCloudItem> tagCloudItems;

	// constructor
	public TagCloud(List<TagFrequency> tagFrequencies, double minWeight, double maxWeight) {

		// handle empty tag cloud
		if(CollectionUtils.isEmpty(tagFrequencies))
		{
			this.tagCloudItems = Collections.emptyList();
			return;
		}

		SummaryStatistics summaryStatistics = new SummaryStatistics();

		for(TagFrequency tagFrequency : tagFrequencies)
			summaryStatistics.addValue(tagFrequency.getFrequency());

		List<TagCloudItem> tagCloudItems = new ArrayList<>(tagFrequencies.size());

		double min = summaryStatistics.getMin();
		double max = summaryStatistics.getMax();
		double multiplier = max == min ? 1 : (maxWeight - minWeight) / (max - min);
		double offset = minWeight - min * multiplier;

		// create tag cloud items
		for(TagFrequency tagFrequency : tagFrequencies)
		{
			String nameSlug = tagFrequency.getNameSlug();
			long frequency = tagFrequency.getFrequency();
			double weight = frequency * multiplier + offset;
			tagCloudItems.add(new TagCloudItem(nameSlug, frequency, weight));
		}

		this.tagCloudItems = Collections.unmodifiableList(tagCloudItems);
	}

	/**
	 * Returns the count of tag cloud items.
	 *
	 * @return int
	 */
	public int getTagCloudItemCount() {
		return tagCloudItems.size();
	}

	// getters and setters
	public List<TagCloudItem> getTagCloudItems() {
		return tagCloudItems;
	}

}