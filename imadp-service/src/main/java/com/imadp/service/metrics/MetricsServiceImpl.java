package com.imadp.service.metrics;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.stat.descriptive.SynchronizedSummaryStatistics;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.core.task.AsyncTaskExecutor;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalListeners;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.Lists;
import com.imadp.service.PersistableServiceImpl;

/**
 * MetricsServiceImpl
 *
 * The standard implementation of the MetricsService.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class MetricsServiceImpl extends PersistableServiceImpl<Metrics> implements MetricsService {

	// task exectutor
	private AsyncTaskExecutor taskExecutor;

	// interval statistics cache
	private LoadingCache<Interval, LoadingCache<String, SummaryStatistics>> statisticsCache;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(taskExecutor);

		this.statisticsCache = createStatisticsCache();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		for(Interval interval : statisticsCache.asMap().keySet())
			saveMetrics(interval, statisticsCache.getUnchecked(interval).asMap());

		this.statisticsCache = null;
	}

	@Override
	public void addMetric(String name, long duration) {
		Interval interval = getHourInterval();
		LoadingCache<String, SummaryStatistics> summaryStatisticsCache = statisticsCache.getUnchecked(interval);
		SummaryStatistics summaryStatistics = summaryStatisticsCache.getUnchecked(name);
		summaryStatistics.addValue(duration);
	}

	/**
	 * Saves metrics with the given interval and summary statistics map.
	 *
	 * @param interval
	 * @param summaryStatisticsMap
	 */
	private void saveMetrics(Interval interval, Map<String, SummaryStatistics> summaryStatisticsMap) {
		List<Metrics> metricsList = Lists.newArrayList();

		// create metrics out of all summary statistics
		for(String name : summaryStatisticsMap.keySet())
		{
			Metrics metrics = new Metrics();
			metrics.setName(name);
			metrics.setStartTime(interval.getStart());
			metrics.setEndTime(interval.getEnd());
			metrics.setSummaryStatistics(summaryStatisticsMap.get(name));
			metricsList.add(metrics);
		}

		// save all metrics
		save(metricsList);
	}

	/**
	 * Returns the interval of the current hour.
	 *
	 * @return Interval
	 */
	private Interval getHourInterval() {
		int hourOfDay = new DateTime().getHourOfDay();
		DateTime date = new DateMidnight().toDateTime().withHourOfDay(hourOfDay);
		return new Interval(date, date.plusHours(1));
	}

	/**
	 * Creates a summary statistics cache with name as the key and a synchronized summary statistics as the value.
	 *
	 * @return LoadingCache<String, SummaryStatistics>
	 */
	private LoadingCache<String, SummaryStatistics> createSummaryStatisticsCache() {
		return CacheBuilder.newBuilder()
				.build(new CacheLoader<String, SummaryStatistics>() {
					@Override
					public SummaryStatistics load(String name) throws Exception {
						return new SynchronizedSummaryStatistics();
					}
				});
	}

	/**
	 * Creates an hourly statistics cache with the hourly interval as the key and summary statistics cache as the value.
	 *
	 * @return LoadingCache<Interval, LoadingCache<String, SummaryStatistics>>
	 */
	private LoadingCache<Interval, LoadingCache<String, SummaryStatistics>> createStatisticsCache() {
		return CacheBuilder.newBuilder()
				.maximumSize(1)
				.removalListener(createRemovalListener())
				.build(new CacheLoader<Interval, LoadingCache<String, SummaryStatistics>>() {
					@Override
					public LoadingCache<String, SummaryStatistics> load(Interval interval) throws Exception {
						return createSummaryStatisticsCache();
					}
				});
	}

	/**
	 * Creates a removal listener to automatically flush all SummaryStatistics to the database as Metrics objects.
	 *
	 * @return RemovalListener<Interval, LoadingCache<String, SummaryStatistics>>
	 */
	private RemovalListener<Interval, LoadingCache<String, SummaryStatistics>> createRemovalListener() {
		return RemovalListeners.asynchronous(new RemovalListener<Interval, LoadingCache<String,SummaryStatistics>>() {
			@Override
			public void onRemoval(RemovalNotification<Interval, LoadingCache<String, SummaryStatistics>> notification) {
				saveMetrics(notification.getKey(), notification.getValue().asMap());
			}
		}, taskExecutor);
	}

	// getters and setters
	public AsyncTaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(AsyncTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

}