package com.imadp.service.commerce.order.report;

import java.util.List;

import org.joda.time.DateTime;

import com.imadp.service.commerce.order.Order;
import com.imadp.service.report.ReportService;

/**
 * OrderReportService
 *
 * Provides common retrieval operations for OrderReport objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface OrderReportService extends ReportService<OrderReport> {

	/**
	 * Returns a list of all Orders with a REQUESTED shipping status.
	 *
	 * @return List<Order>
	 */
	public List<Order> getRequestedOrders();

	/**
	 * Generates an order report for the previous full day.
	 *
	 * @return OrderReport
	 */
	public OrderReport generateDailyOrderReport();

	/**
	 * Generates an order report for the previous full week.
	 *
	 * @return OrderReport
	 */
	public OrderReport generateWeeklyOrderReport();

	/**
	 * Generates an order report for the previous full month.
	 *
	 * @return OrderReport
	 */
	public OrderReport generateMonthlyOrderReport();

	/**
	 * Generates an order report for the previous full year.
	 *
	 * @return OrderReport
	 */
	public OrderReport generateAnnualOrderReport();

	/**
	 * Generates an OrderReport from the given time range.
	 *
	 * @param start
	 * @param end
	 * @return OrderReport
	 */
	public OrderReport generateOrderReport(DateTime start, DateTime end);

}