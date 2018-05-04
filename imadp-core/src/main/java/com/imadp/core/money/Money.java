package com.imadp.core.money;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import com.imadp.core.AbstractSerializable;
import com.imadp.core.Property;

/**
 * Money
 *
 * A representation of Money, consisting of a BigDecimal amount and a Currency.
 * Contains convenience methods for performing common money calculations.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class Money extends AbstractSerializable implements Comparable<Money> {

	/**
	 * Returns a Money instance from pennies.
	 *
	 * @param pennies
	 * @return Money
	 */
	public static Money fromPennies(int pennies) {
		return new Money(new BigDecimal(pennies)).divide(100);
	}

	// static Money objects using the default currency
	public static final Money ZERO = new Money(BigDecimal.ZERO);
	public static final Money ONE  = new Money(BigDecimal.ONE);

	// static properties
	public static final Property<Money, BigDecimal> AMOUNT = Property.of("amount");
	public static final Property<Money, String> CURRENCY = Property.of("currency");

	// properties
	private final BigDecimal amount;
	private final Currency currency;

	// constructor
	protected Money() {
		this(null, null);
	}

	// constructor
	public Money(String amount) {
		this(new BigDecimal(amount.replaceAll("\\$", "")));
	}

	// constructor
	public Money(BigDecimal amount) {
		this(amount, Currency.getInstance(Locale.getDefault()));
	}

	// constructor
	public Money(BigDecimal amount, Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}

	/**
	 * Adds the other Money amount to this object and returns a new instance containing the result.
	 * The currencies must match for the calculation to take place.
	 *
	 * @param other
	 * @return Money
	 */
	public Money add(Money other){
		assertCurrency(other);
		return new Money(amount.add(other.amount), other.currency);
	}

	/**
	 * Subtracts the other Money amount from this object and returns a new instance containing the result.
	 * The currencies must match for the calculation to take place.
	 *
	 * @param other
	 * @return Money
	 */
	public Money subtract(Money other){
		assertCurrency(other);
		return new Money(amount.subtract(other.amount), other.currency);
	}

	/**
	 * Multiplies by the factor provided to this object and returns a new instance containing the result.
	 *
	 * @param factor
	 * @return Money
	 */
	public Money multiply(int factor){
		BigDecimal newAmount = amount.multiply(new BigDecimal(factor));
		return new Money(newAmount, currency);
	}

	/**
	 * Multiplies by the factor provided to this object and returns a new instance containing the result.
	 * Sets the scale the default for the provided currency.
	 *
	 * @param factor
	 * @return Money
	 */
	public Money multiply(double factor){
		BigDecimal newAmount = amount.multiply(new BigDecimal(Double.toString(factor)));
		newAmount = newAmount.setScale(currency.getDefaultFractionDigits(), BigDecimal.ROUND_HALF_EVEN);
		return  new Money(newAmount, currency);
	}

	/**
	 * Divides by the divisor provided to this object and returns a new instance containing the result.
	 *
	 * @param divisor
	 * @return Money
	 */
	public Money divide(int divisor){
		BigDecimal newAmount = amount.divide(new BigDecimal(divisor), BigDecimal.ROUND_HALF_EVEN);
		return new Money(newAmount, currency);
	}

	/**
	 * Divides by the divisor provided to this object and returns a new instance containing the result.
	 * Sets the scale the default for the provided currency.
	 *
	 * @param divisor
	 * @return Money
	 */
	public Money divide(double divisor){
		BigDecimal newAmount = amount.divide(new BigDecimal(Double.toString(divisor)), BigDecimal.ROUND_HALF_EVEN);
		newAmount = newAmount.setScale(currency.getDefaultFractionDigits(), BigDecimal.ROUND_HALF_EVEN);
		return new Money(newAmount, currency);
	}

	// getters
	public String getFormattedAmount() {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
		return numberFormat.format(amount);
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	/**
	 * Throws an IllegalArgumentException if this currency does not match the other Money object.
	 *
	 * @param other
	 */
	private void assertCurrency(Money other) {
		if(!currency.equals(other.currency))
			throw new IllegalArgumentException("Cannot compare money objects of difference currencies " +
					"["+currency.getCurrencyCode()+"] vs ["+other.currency.getCurrencyCode()+"]");
	}

	@Override
	public int compareTo(Money other) {
		assertCurrency(other);
		return amount.compareTo(other.amount);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result	+ ((currency == null) ? 0 : currency.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		return true;
	}

}