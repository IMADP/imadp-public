package com.imadp.service.commerce.product;

import java.math.BigDecimal;

import com.imadp.core.Property;
import com.imadp.core.money.Money;
import com.imadp.dao.AbstractPersistable;


/**
 * Product
 * 
 * The Product class holds the identifiable attributes of a product.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class Product extends AbstractPersistable {
	
	// static Properties
	public static final Property<Product, String> NAME = Property.of("name");
	public static final Property<Product, String> CODE = Property.of("code");
	public static final Property<Product, String> DESCRIPTION = Property.of("description");
	public static final Property<Product, BigDecimal> BASE_PRICE_AMOUNT = Property.of("basePrice.amount");
	public static final Property<Product, String> BASE_PRICE_CURRENCY = Property.of("basePrice.currency");
	public static final Property<Product, Boolean> ACTIVE = Property.of("active");
		
	// properties
	private String name;
	private String code;
	private String description;
	private Money basePrice;
	private boolean active;
	
	// constructor
	public Product() { 
		
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Money getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Money basePrice) {
		this.basePrice = basePrice;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}