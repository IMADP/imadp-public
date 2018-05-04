package com.imadp.service.metrics;

import com.imadp.service.PersistableService;


/**
 * MetricsService
 *
 * Provides common retrieval operations for Metrics objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface MetricsService extends PersistableService<Metrics> {

	/**
	 * Collects a metric.
	 *
	 * @param name
	 * @param duration
	 */
	public void addMetric(String name, long duration);

}