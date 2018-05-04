package com.imadp.service.metrics;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.joda.time.DateTime;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.imadp.service.ServiceTestUtil;
import com.imadp.service.test.IMADPServiceTestCase;


/**
 * MetricsServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class MetricsServiceImplTest extends IMADPServiceTestCase {
	Metrics metrics;

	@Override
	public void before() throws Exception {
		super.before();

		SummaryStatistics summaryStatistics = new SummaryStatistics();
		summaryStatistics.addValue(1);

		metrics = new Metrics();
		metrics.setName("name");
		metrics.setStartTime(new DateTime());
		metrics.setEndTime(new DateTime());
		metrics.setSummaryStatistics(summaryStatistics);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(metrics, metricsService);
	}

	@Test
	public void metricsSummary() throws Exception {
		List<Metrics> allMetrics = Lists.newArrayList();
		SummaryStatistics summaryStatistics;
		Metrics metrics;

		metrics = new Metrics();
		metrics.setName("name1");
		metrics.setStartTime(new DateTime());
		metrics.setEndTime(new DateTime());

		summaryStatistics = new SummaryStatistics();
		summaryStatistics.addValue(1);
		summaryStatistics.addValue(5);
		summaryStatistics.addValue(10);

		metrics.setSummaryStatistics(summaryStatistics);
		allMetrics.add(metrics);

		metrics = new Metrics();
		metrics.setName("name2");
		metrics.setStartTime(new DateTime());
		metrics.setEndTime(new DateTime());

		summaryStatistics = new SummaryStatistics();
		summaryStatistics.addValue(10);
		summaryStatistics.addValue(50);
		summaryStatistics.addValue(100);

		metrics.setSummaryStatistics(summaryStatistics);
		allMetrics.add(metrics);

		MetricsSummaries metricsSummaries = new MetricsSummaries(allMetrics);
		assertEquals(2, metricsSummaries.getSummaries().size());
		assertEquals(3, metricsSummaries.getSummaries().get(0).getMetricCount());
		assertEquals(3, metricsSummaries.getSummaries().get(1).getMetricCount());
	}

}