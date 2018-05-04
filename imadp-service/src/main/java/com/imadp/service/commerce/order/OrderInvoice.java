package com.imadp.service.commerce.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.imadp.core.Property;
import com.imadp.core.money.Money;
import com.imadp.service.user.AbstractPersistableUser;

/**
 * OrderInvoice
 *
 * An invoice for a given order.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class OrderInvoice extends AbstractPersistableUser {

	// static properties
	public static final Property<OrderInvoice, BigDecimal> ITEMS_PRICE_AMOUNT = Property.of("itemsPrice", Money.AMOUNT);
	public static final Property<OrderInvoice, String> ITEMS_PRICE_CURRENCY = Property.of("itemsPrice", Money.CURRENCY);
	public static final Property<OrderInvoice, BigDecimal> SHIPPING_PRICE_AMOUNT = Property.of("shippingPrice", Money.AMOUNT);
	public static final Property<OrderInvoice, String> SHIPPING_PRICE_CURRENCY = Property.of("shippingPrice", Money.CURRENCY);
	public static final Property<OrderInvoice, BigDecimal> TAX_PRICE_AMOUNT = Property.of("taxPrice", Money.AMOUNT);
	public static final Property<OrderInvoice, String> TAX_PRICE_CURRENCY = Property.of("taxPrice", Money.CURRENCY);
	public static final Property<OrderInvoice, BigDecimal> DISCOUNTS_PRICE_AMOUNT = Property.of("discountsPrice", Money.AMOUNT);
	public static final Property<OrderInvoice, String> DISCOUNTS_PRICE_CURRENCY = Property.of("discountsPrice", Money.CURRENCY);
	public static final Property<OrderInvoice, BigDecimal> TOTAL_PRICE_AMOUNT = Property.of("totalPrice", Money.AMOUNT);
	public static final Property<OrderInvoice, String> TOTAL_PRICE_CURRENCY = Property.of("totalPrice", Money.CURRENCY);

	// properties
	private Money shippingPrice;
	private Money taxPrice;
	private List<OrderItem> items;
	private List<OrderDiscount> discounts;

	// constructor
	public OrderInvoice() {

	}

	/**
	 * Add an order item.
	 *
	 * @param orderItem
	 */
	public void add(OrderItem orderItem) {
		if(items == null)
			items = new ArrayList<>();

		items.add(orderItem);
	}

	/**
	 * Add an order discount.
	 *
	 * @param orderDiscount
	 */
	public void add(OrderDiscount orderDiscount) {
		if(discounts == null)
			discounts = new ArrayList<>();

		discounts.add(orderDiscount);
	}

	/**
	 * Gets the items price.
	 *
	 * @return Money
	 */
	public Money getItemsPrice() {
		if(items == null || items.isEmpty())
			return Money.ZERO;

		Money itemsPrice = Money.ZERO;

		for(OrderItem orderItem : items)
			itemsPrice = itemsPrice.add(orderItem.getTotalPrice());

		return itemsPrice;
	}

	/**
	 * Gets the discounts price.
	 *
	 * @return Money
	 */
	public Money getDiscountsPrice() {
		if(discounts == null || discounts.isEmpty())
			return Money.ZERO;

		Money discountsPrice = Money.ZERO;

		for(OrderDiscount discount : discounts)
			discountsPrice = discountsPrice.add(discount.getPrice());

		return discountsPrice;
	}

	/**
	 * Gets the total price.
	 *
	 * @return Money
	 */
	public Money getTotalPrice() {
		Money totalPrice = Money.ZERO;
		Money itemsPrice = getItemsPrice();
		Money shippingPrice = getShippingPrice();
		Money taxPrice = getTaxPrice();
		Money discountsPrice = getDiscountsPrice();

		if(itemsPrice != null)
			totalPrice = totalPrice.add(itemsPrice);

		if(shippingPrice != null)
			totalPrice = totalPrice.add(shippingPrice);

		if(taxPrice != null)
			totalPrice = totalPrice.add(taxPrice);

		if(discountsPrice != null)
			totalPrice = totalPrice.subtract(discountsPrice);

		return totalPrice;
	}

	/**
	 * Returns the total quantity of all items contained in the order invoice.
	 *
	 * @return int
	 */
	public int getTotalItemQuantity() {
		if(items == null || items.isEmpty())
			return 0;

		int totalItemQuantity = 0;

		for(OrderItem item : items)
			totalItemQuantity += item.getQuantity();

		return totalItemQuantity;
	}

	/**
	 * Returns an OrderItem found by the given product code, or null if none was found.
	 *
	 * @param productCode
	 * @return OrderItem
	 */
	public OrderItem getOrderItem(String productCode) {
		if(items == null || items.isEmpty())
			return null;

		for(OrderItem orderItem : getItems())
			if(orderItem.getProduct().getCode().equalsIgnoreCase(productCode))
				return orderItem;

		return null;
	}

	// getters and setters
	public Money getShippingPrice() {
		return shippingPrice == null ? Money.ZERO : shippingPrice;
	}

	public void setShippingPrice(Money shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public Money getTaxPrice() {
		return taxPrice == null ? Money.ZERO : taxPrice;
	}

	public void setTaxPrice(Money taxPrice) {
		this.taxPrice = taxPrice;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public List<OrderDiscount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<OrderDiscount> discounts) {
		this.discounts = discounts;
	}

	@SuppressWarnings("unused")
	private void setItemsPrice(Money itemsPrice) {

	}

	@SuppressWarnings("unused")
	private void setTotalPrice(Money totalPrice) {

	}

	@SuppressWarnings("unused")
	private void setDiscountsPrice(Money discountsPrice) {

	}

}