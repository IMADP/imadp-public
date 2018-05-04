package com.imadp.service.metrics;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.dao.AbstractPersistable;


/**
 * Metrics
 *
 * The Metrics class holds statistics for a collection of execution times.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Metrics extends AbstractPersistable implements Comparable<Metrics> {

	// static Properties
	public static final Property<Metrics, String> NAME = Property.of("name");
	public static final Property<Metrics, DateTime> START_TIME = Property.of("startTime");
	public static final Property<Metrics, DateTime> END_TIME = Property.of("endTime");
	public static final Property<Metrics, Long> METRIC_COUNT = Property.of("metricCount");
	public static final Property<Metrics, Double> SUM_DURATION = Property.of("sumDuration");
	public static final Property<Metrics, Double> MIN_DURATION = Property.of("minDuration");
	public static final Property<Metrics, Double> MAX_DURATION = Property.of("maxDuration");
	public static final Property<Metrics, Double> MEAN_DURATION = Property.of("meanDuration");
	public static final Property<Metrics, Double> VARIANCE = Property.of("variance");
	public static final Property<Metrics, Double> STANDARD_DEVIATION = Property.of("standardDeviation");
	public static final Property<Metrics, Double> SECOND_MOMENT = Property.of("secondMoment");
	public static final Property<Metrics, Double> SUM_OF_SQUARES = Property.of("sumOfSquares");
	public static final Property<Metrics, Double> SUM_OF_LOGS = Property.of("sumOfLogs");

	// properties
	private String name;
	private DateTime startTime;
	private DateTime endTime;
	private long metricCount;
	private double sumDuration;
	private double minDuration;
	private double maxDuration;
	private double meanDuration;
	private double variance;
	private double standardDeviation;
	private double secondMoment;
	private double sumOfSquares;
	private double sumOfLogs;

	// constructor
	public Metrics() {

	}

	/**
	 * Sets all the values from a summary statistics object into this metrics object.
	 *
	 * @param summaryStatistics
	 */
	public void setSummaryStatistics(SummaryStatistics summaryStatistics) {
		setMetricCount(summaryStatistics.getN());
		setSumDuration(summaryStatistics.getSum());
		setMinDuration(summaryStatistics.getMin());
		setMaxDuration(summaryStatistics.getMax());
		setMeanDuration(summaryStatistics.getMean());
		setVariance(summaryStatistics.getVariance());
		setStandardDeviation(summaryStatistics.getStandardDeviation());
		setSecondMoment(summaryStatistics.getSecondMoment());
		setSumOfLogs(summaryStatistics.getSumOfLogs());
		setSumOfSquares(summaryStatistics.getSumsq());
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}

	public DateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(DateTime endTime) {
		this.endTime = endTime;
	}

	public double getSumDuration() {
		return sumDuration;
	}

	public void setSumDuration(double sumDuration) {
		this.sumDuration = sumDuration;
	}

	public double getMinDuration() {
		return minDuration;
	}

	public void setMinDuration(double minDuration) {
		this.minDuration = minDuration;
	}

	public double getMaxDuration() {
		return maxDuration;
	}

	public void setMaxDuration(double maxDuration) {
		this.maxDuration = maxDuration;
	}

	public double getMeanDuration() {
		return meanDuration;
	}

	public void setMeanDuration(double meanDuration) {
		this.meanDuration = meanDuration;
	}

	public long getMetricCount() {
		return metricCount;
	}

	public void setMetricCount(long metricCount) {
		this.metricCount = metricCount;
	}

	public double getVariance() {
		return variance;
	}

	public void setVariance(double variance) {
		this.variance = variance;
	}

	public double getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(double standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

	public double getSecondMoment() {
		return secondMoment;
	}

	public void setSecondMoment(double secondMoment) {
		this.secondMoment = secondMoment;
	}

	public double getSumOfSquares() {
		return sumOfSquares;
	}

	public void setSumOfSquares(double sumOfSquares) {
		this.sumOfSquares = sumOfSquares;
	}

	public double getSumOfLogs() {
		return sumOfLogs;
	}

	public void setSumOfLogs(double sumOfLogs) {
		this.sumOfLogs = sumOfLogs;
	}

	@Override
	public int compareTo(Metrics o) {
		return startTime.compareTo(o.getStartTime());
	}

}