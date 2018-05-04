package com.tracktacular.service.tracker.body;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Body
 *
 * A representation of body measurement.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Body extends AbstractPersistableUser {

	// static Properties
	public static final Property<Body, Double> WEIGHT = Property.of("weight");
	public static final Property<Body, Double> HEIGHT = Property.of("height");
	public static final Property<Body, Double> NECK = Property.of("neck");
	public static final Property<Body, Double> CHEST = Property.of("chest");
	public static final Property<Body, Double> WAIST = Property.of("waist");
	public static final Property<Body, Double> HIPS = Property.of("hips");
	public static final Property<Body, Double> BICEPS = Property.of("biceps");
	public static final Property<Body, Double> FOREARMS = Property.of("forearms");
	public static final Property<Body, Double> WRISTS = Property.of("wrists");
	public static final Property<Body, Double> THIGHS = Property.of("thighs");
	public static final Property<Body, Double> CALVES = Property.of("calves");
	public static final Property<Body, Double> ANKLES = Property.of("ankles");
	public static final Property<Body, Double> FEET = Property.of("feet");
	public static final Property<Body, Double> BODY_FAT = Property.of("bodyFat");
	public static final Property<Body, String> NOTES = Property.of("notes");
	public static final Property<Body, DateTime> DATE = Property.of("date");

	// properties
	private Double weight;
	private Double height;
	private Double neck;
	private Double chest;
	private Double waist;
	private Double hips;
	private Double biceps;
	private Double forearms;
	private Double wrists;
	private Double thighs;
	private Double calves;
	private Double ankles;
	private Double feet;
	private Double bodyFat;
	private String notes;
	private DateTime date;

	// constructor
	public Body() {
		this(null);
	}

	// constructor
	public Body(User user) {
		super(user);
		setPersistableState(PersistableState.ACTIVE);
	}

	/**
	 * Returns true if any values have been entered, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean hasEntry() {
		return weight != null ||
				height != null ||
				neck != null ||
				chest != null ||
				waist != null ||
				hips != null ||
				biceps != null ||
				forearms != null ||
				wrists != null ||
				thighs != null ||
				calves != null ||
				ankles != null ||
				feet != null ||
				bodyFat != null;
	}

	// getters and setters
	public String getDateString() {
		return toDateString(date);
	}

	public void setDateString(String dateString) {
		this.date = fromDateString(dateString);
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getNeck() {
		return neck;
	}

	public void setNeck(Double neck) {
		this.neck = neck;
	}

	public Double getChest() {
		return chest;
	}

	public void setChest(Double chest) {
		this.chest = chest;
	}

	public Double getWaist() {
		return waist;
	}

	public void setWaist(Double waist) {
		this.waist = waist;
	}

	public Double getHips() {
		return hips;
	}

	public void setHips(Double hips) {
		this.hips = hips;
	}

	public Double getBiceps() {
		return biceps;
	}

	public void setBiceps(Double biceps) {
		this.biceps = biceps;
	}

	public Double getForearms() {
		return forearms;
	}

	public void setForearms(Double forearms) {
		this.forearms = forearms;
	}

	public Double getWrists() {
		return wrists;
	}

	public void setWrists(Double wrists) {
		this.wrists = wrists;
	}

	public Double getThighs() {
		return thighs;
	}

	public void setThighs(Double thighs) {
		this.thighs = thighs;
	}

	public Double getCalves() {
		return calves;
	}

	public void setCalves(Double calves) {
		this.calves = calves;
	}

	public Double getAnkles() {
		return ankles;
	}

	public void setAnkles(Double ankles) {
		this.ankles = ankles;
	}

	public Double getFeet() {
		return feet;
	}

	public void setFeet(Double feet) {
		this.feet = feet;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Double getBodyFat() {
		return bodyFat;
	}

	public void setBodyFat(Double bodyFat) {
		this.bodyFat = bodyFat;
	}

}