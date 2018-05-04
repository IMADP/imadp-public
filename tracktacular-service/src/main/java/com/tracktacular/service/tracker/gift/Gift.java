package com.tracktacular.service.tracker.gift;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.core.money.Money;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Gift
 *
 * A representation of a gift.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Gift extends AbstractPersistableUser {

	// static Properties
	public static final Property<Gift, String> NAME = Property.of("name");
	public static final Property<Gift, String> SENDER = Property.of("sender");
	public static final Property<Gift, String> RECEIVER = Property.of("receiver");
	public static final Property<Gift, String> OCCASION = Property.of("occasion");
	public static final Property<Gift, String> NOTES = Property.of("notes");
	public static final Property<Gift, BigDecimal> PRICE_AMOUNT = Property.of("price", Money.AMOUNT);
	public static final Property<Gift, String> PRICE_CURRENCY = Property.of("price", Money.CURRENCY);
	public static final Property<Gift, DateTime> DATE = Property.of("date");
	public static final Property<Gift, Boolean> FAVORITE = Property.of("favorite");

	// properties
	private String name;
	private String sender;
	private String receiver;
	private String occasion;
	private String notes;
	private Money price;
	private DateTime date;
	private boolean favorite;

	// constructor
	public Gift() {
		this(null);
	}

	// constructor
	public Gift(User user) {
		super(user);
		setPersistableState(PersistableState.ACTIVE);
	}

	/**
	 * Returns true if the gift was received by you, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isReceived() {
		return receiver == null;
	}

	/**
	 * Returns a slug from the name property.
	 *
	 * @return String
	 */
	public String getNameSlug() {
		return toSlug(name);
	}

	// getters and setters
	public String getDateString() {
		return toDateString(date);
	}

	public void setDateString(String DateString) {
		this.date = fromDateString(DateString);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getOccasion() {
		return occasion;
	}

	public void setOccasion(String occasion) {
		this.occasion = occasion;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Money getPrice() {
		return price;
	}

	public void setPrice(Money price) {
		this.price = price;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

}