package com.imadp.service.metrics;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.imadp.core.AbstractSerializable;


/**
 * MetricsSummaries
 *
 * Contains a summary report for a collection of Metrics.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class MetricsSummaries extends AbstractSerializable {

	// properties
	private final List<MetricsSummary> summaries;

	// constructor
	public MetricsSummaries(List<Metrics> allMetrics) {
		Multimap<String, Metrics> metricsMap = ArrayListMultimap.create();

		for(Metrics metrics : allMetrics)
			metricsMap.put(metrics.getName(), metrics);

		summaries = Lists.newArrayListWithExpectedSize(metricsMap.size());

		for(String name : metricsMap.keySet())
			summaries.add(new MetricsSummary(metricsMap.get(name)));

		Collections.sort(summaries);
	}

	// getters
	public List<MetricsSummary> getSummaries() {
		return summaries;
	}

}