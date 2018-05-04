package com.imadp.service.commerce.order.report;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.imadp.core.Property;
import com.imadp.core.money.Money;
import com.imadp.service.commerce.order.Order;
import com.imadp.service.report.Report;


/**
 * OrderReport
 * 
 * A report including information about the type and cause of an application error.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class OrderReport extends Report  {
	
	// static Properties
	public static final Property<OrderReport, DateTime> START_DATE = Property.of("startDate");
	public static final Property<OrderReport, DateTime> END_DATE = Property.of("endDate");
	public static final Property<Order, BigDecimal> TOTAL_DISCOUNTS_PRICE_AMOUNT = Property.of("totalDiscountsPrice.amount");
	public static final Property<Order, String> TOTAL_DISCOUNTS_PRICE_CURRENCY = Property.of("totalDiscountsPrice.currency");
	public static final Property<Order, BigDecimal> TOTAL_ITEMS_PRICE_AMOUNT = Property.of("totalItemsPrice.amount");
	public static final Property<Order, String> TOTAL_ITEMS_PRICE_CURRENCY = Property.of("totalItemsPrice.currency");
	public static final Property<Order, BigDecimal> TOTAL_SHIPPING_PRICE_AMOUNT = Property.of("totalShippingPrice.amount");
	public static final Property<Order, String> TOTAL_SHIPPING_PRICE_CURRENCY = Property.of("totalShippingPrice.currency");
	public static final Property<Order, BigDecimal> TOTAL_TAX_PRICE_AMOUNT = Property.of("totalTaxPrice.amount");
	public static final Property<Order, String> TOTAL_TAX_PRICE_CURRENCY = Property.of("totalTaxPrice.currency");
	public static final Property<Order, BigDecimal> TOTAL_PRICE_AMOUNT = Property.of("totalPrice.amount");
	public static final Property<Order, String> TOTAL_PRICE_CURRENCY = Property.of("totalPrice.currency");
	public static final Property<OrderReport, Long> ORDER_COUNT = Property.of("orderCount");
	
	// properties
	private DateTime startDate;
	private DateTime endDate;
	private Money totalDiscountsPrice;
	private Money totalItemsPrice;
	private Money totalShippingPrice;
	private Money totalTaxPrice;
	private Money totalPrice;
	private Map<String, AtomicLong> billingProcessors;
	private long orderCount;
	
	// constructor
	public OrderReport() { 
		
	}
	
	/**
	 * Returns the formatted start date.
	 * 
	 * @return String
	 */
	public String getStartDateFormatted() {
		return DateTimeFormat.fullDateTime().print(startDate);
	}
	
	/**
	 * Returns the formatted end date.
	 * 
	 * @return String
	 */
	public String getEndDateFormatted() {
		return DateTimeFormat.fullDateTime().print(endDate);
	}
	
	// getters and setters
	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public long getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(long orderCount) {
		this.orderCount = orderCount;
	}

	public Money getTotalDiscountsPrice() {
		return totalDiscountsPrice;
	}

	public void setTotalDiscountsPrice(Money totalDiscountsPrice) {
		this.totalDiscountsPrice = totalDiscountsPrice;
	}

	public Money getTotalItemsPrice() {
		return totalItemsPrice;
	}

	public void setTotalItemsPrice(Money totalItemsPrice) {
		this.totalItemsPrice = totalItemsPrice;
	}

	public Money getTotalShippingPrice() {
		return totalShippingPrice;
	}

	public void setTotalShippingPrice(Money totalShippingPrice) {
		this.totalShippingPrice = totalShippingPrice;
	}

	public Money getTotalTaxPrice() {
		return totalTaxPrice;
	}

	public void setTotalTaxPrice(Money totalTaxPrice) {
		this.totalTaxPrice = totalTaxPrice;
	}

	public Money getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Money totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Map<String, AtomicLong> getBillingProcessors() {
		return billingProcessors;
	}

	public void setBillingProcessors(Map<String, AtomicLong> billingProcessors) {
		this.billingProcessors = billingProcessors;
	}	
	
}