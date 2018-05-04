package com.imadp.service.metrics;

import java.math.RoundingMode;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues;
import org.joda.time.DateTime;

import com.google.common.math.LongMath;
import com.imadp.core.AbstractSerializable;


/**
 * MetricsSummary
 *
 * Contains an aggregate summary of a collection of identically named Metrics.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class MetricsSummary extends AbstractSerializable implements Comparable<MetricsSummary> {

	// properties
	private final String name;
	private final DateTime startTime;
	private final DateTime endTime;
	private final long metricCount;
	private final long sumDuration;
	private final long minDuration;
	private final long maxDuration;
	private final long meanDuration;
	private final double variance;
	private final double standardDeviation;

	// constructor
	public MetricsSummary(Collection<Metrics> metricsGroup) {

		if(metricsGroup.isEmpty())
			throw new IllegalArgumentException("Metrics group should not be empty");

		String name = null;
		DateTime startTime = new DateTime(Long.MAX_VALUE);
		DateTime endTime = new DateTime(Long.MIN_VALUE);
		StatisticalSummary statisticalSummary = getStatisticalSummary(metricsGroup);

		for(Metrics metrics : metricsGroup)
		{
			if(name == null)
				name = metrics.getName();

			if(!name.equalsIgnoreCase(metrics.getName()))
				throw new IllegalArgumentException("Metrics name ["+name+"] did not match metrics ["+metrics.getName()+"]");

			if(metrics.getStartTime().isBefore(startTime))
				startTime = metrics.getStartTime();

			if(metrics.getEndTime().isAfter(endTime))
				endTime = metrics.getEndTime();
		}

		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.metricCount = statisticalSummary.getN();
		this.sumDuration = getDuration(statisticalSummary.getSum());
		this.minDuration = getDuration(statisticalSummary.getMin());
		this.maxDuration = getDuration(statisticalSummary.getMax());
		this.meanDuration = getDuration(statisticalSummary.getMean());
		this.variance = statisticalSummary.getVariance();
		this.standardDeviation = statisticalSummary.getStandardDeviation();
	}

	/**
	 * Returns the duration in milliseconds.
	 *
	 * @param value
	 * @return Long
	 */
	private Long getDuration(double value) {
		return LongMath.divide((long) value, 1000000L, RoundingMode.HALF_UP);
	}

	/**
	 * Returns a statistical summary for a collection of metrics.
	 * This method was retrofitted from AggregateSummaryStatistics.aggregate to work with Metrics.
	 *
	 * @param metrics
	 * @return StatisticalSummary
	 */
	private static StatisticalSummary getStatisticalSummary(Collection<Metrics> metrics) {
		Iterator<Metrics> iterator = metrics.iterator();
        Metrics current = iterator.next();
        long n = current.getMetricCount();
        double min = current.getMinDuration();
        double sum = current.getSumDuration();
        double max = current.getMaxDuration();
        double m2 = current.getSecondMoment();
        double mean = current.getMeanDuration();

        while (iterator.hasNext())
        {
            current = iterator.next();

            if (current.getMinDuration() < min || Double.isNaN(min))
                min = current.getMinDuration();

            if (current.getMaxDuration() > max || Double.isNaN(max))
                max = current.getMaxDuration();

            sum += current.getSumDuration();
            final double oldN = n;
            final double curN = current.getMetricCount();
            n += curN;
            final double meanDiff = current.getMeanDuration() - mean;
            mean = sum / n;
            m2 = m2 + current.getSecondMoment() + meanDiff * meanDiff * oldN * curN / n;
        }

        final double variance;

        if (n == 0)
            variance = Double.NaN;

        else if (n == 1)
            variance = 0d;

        else
            variance = m2 / (n - 1);

        return new StatisticalSummaryValues(mean, variance, n, max, min, sum);
    }

	// getters
	public String getName() {
		return name;
	}

	public DateTime getStartTime() {
		return startTime;
	}

	public DateTime getEndTime() {
		return endTime;
	}

	public long getMetricCount() {
		return metricCount;
	}

	public double getSumDuration() {
		return sumDuration;
	}

	public double getMinDuration() {
		return minDuration;
	}

	public double getMaxDuration() {
		return maxDuration;
	}

	public double getMeanDuration() {
		return meanDuration;
	}

	public double getVariance() {
		return variance;
	}

	public double getStandardDeviation() {
		return standardDeviation;
	}

	@Override
	public int compareTo(MetricsSummary o) {
		return Double.compare(o.getMeanDuration(), getMeanDuration());
	}

}