package com.imadp.service.commerce.card;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.service.user.AbstractPersistableUser;


/**
 * Card
 *
 * The Card class represents a form of payment.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Card extends AbstractPersistableUser {

	// static Properties
	public static final Property<Card, String> NUMBER = Property.of("number");
	public static final Property<Card, String> TYPE = Property.of("type");
	public static final Property<Card, String> CVV = Property.of("cvv");
	public static final Property<Card, String> EXPIRATION_MONTH = Property.of("expirationMonth");
	public static final Property<Card, String> EXPIRATION_YEAR = Property.of("expirationYear");

	// properties
	private String number;
	private String type;
	private String cvv;
	private Integer expirationMonth;
	private Integer expirationYear;

	// constructor
	public Card() {

	}

	/**
	 * Returns the card expiration date.
	 *
	 * @return DateTime
	 */
	public DateTime getExpirationDate() {
		return new DateTime(expirationYear, expirationMonth, 1, 0, 0, 0, 0);
	}

	/**
	 * Returns true if the card has currently expired, false otherwise.
	 * A credit card is still valid on the month of its expiration.
	 *
	 * @return boolean
	 */
	public boolean isExpired() {
		return getExpirationDate().plusMonths(1).isBeforeNow();
	}

	/**
	 * Returns a masked version of the card number, showing only the last 4 digits.
	 *
	 * @return String
	 */
	public String getMaskedNumber() {
		if(number == null)
			return null;

		return "************" + getCardNumberEnding();
	}

	/**
	 * Returns the last 4 digits of the card number.
	 *
	 * @return String
	 */
	public String getCardNumberEnding() {
		if(number == null || number.length() < 4)
			return null;

		return number.substring(number.length() - 4);
	}

	/**
	 * Returns the expirationMonth as a String padded with a 0 if the month is lower than 10.
	 *
	 * @return String
	 */
	public String getPaddedExpirationMonth() {
		if(expirationMonth == null)
			return "";

		return String.format("%02d", expirationMonth);
	}

	/**
	 * Returns the full expiration date as a 6-character string composed of
	 * the padded expiration month and the expiration year.
	 *
	 * @return String
	 */
	public String getFullExpirationDate() {
		return getPaddedExpirationMonth() + getExpirationYear();
	}

	// getters and setters
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Integer getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(Integer expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public Integer getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(Integer expirationYear) {
		this.expirationYear = expirationYear;
	}

}