package com.tracktacular.service.tracker.budget;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Budget
 *
 * A representation of a budget.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Budget extends AbstractPersistableUser {

	// static Properties
	public static final Property<Budget, String> NAME = Property.of("name");
	public static final Property<Budget, String> NOTES = Property.of("notes");
	public static final Property<Budget, String> DESCRIPTION = Property.of("description");
	public static final Property<Budget, DateTime> START_DATE = Property.of("startDate");
	public static final Property<Budget, DateTime> END_DATE = Property.of("endDate");

	// properties
	private String name;
	private String notes;
	private String description;
	private DateTime startDate;
	private DateTime endDate;
	private Set<ItemCategory> itemCategories;

	// constructor
	public Budget() {
		this(null);
	}

	// constructor
	public Budget(User user) {
		this(user, null, null);
	}

	// constructor
	public Budget(User user, String name, String description) {
		super(user);
		setPersistableState(PersistableState.ACTIVE);
		this.startDate = new DateTime();
		this.name = name;
		this.description = description;
	}

	/**
	 * Returns true if the item is considered an income or expense otherwise.
	 *
	 * @return boolean
	 */
	public boolean isIncome() {
		return getNetAmount().doubleValue() >= 0;
	}

	/**
	 * Returns the net amount of all items in all categories.
	 *
	 * @return long
	 */
	public BigDecimal getNetAmount() {
		if(!hasItemCategories())
			return BigDecimal.ZERO;

		BigDecimal amount = BigDecimal.ZERO;

		for(ItemCategory itemCategory : itemCategories)
			amount = amount.add(itemCategory.getNetItemAmount());

		return amount;
	}

	/**
	 * Returns the net amount of all income in all categories.
	 *
	 * @return long
	 */
	public BigDecimal getNetIncomeAmount() {
		if(!hasItemCategories())
			return BigDecimal.ZERO;

		BigDecimal amount = BigDecimal.ZERO;

		for(ItemCategory itemCategory : itemCategories)
			if(itemCategory.isIncome())
				amount = amount.add(itemCategory.getNetItemAmount());

		return amount;
	}

	/**
	 * Returns the net amount of all expenses in all categories.
	 *
	 * @return long
	 */
	public BigDecimal getNetExpenseAmount() {
		if(!hasItemCategories())
			return BigDecimal.ZERO;

		BigDecimal amount = BigDecimal.ZERO;

		for(ItemCategory itemCategory : itemCategories)
			if(!itemCategory.isIncome())
				amount = amount.add(itemCategory.getNetItemAmount());

		return amount;
	}

	/**
	 * Returns true if categories were found, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean hasItemCategories() {
		return itemCategories != null;
	}

	/**
	 * Returns a flattened list of all categories in the budget.
	 *
	 * @return List<ItemCategory>
	 */
	public List<ItemCategory> getAllCategories() {
		List<ItemCategory> allCategories = new ArrayList<>(itemCategories.size());

		for(ItemCategory category : itemCategories)
			allCategories.addAll(category.getFlattenedCategories());

		return allCategories;
	}

	/**
	 * Returns true if the root categories are sortable as determined by more than one value, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isRootCategoriesSortable() {
		Set<ItemCategory> rootCategories = getItemCategories();
		return rootCategories != null && rootCategories.size() > 1;
	}

	@Override
	protected void onActiveStateChange() {
		this.endDate = null;
	}

	@Override
	protected void onArchivedStateChange() {
		this.endDate = new DateTime();
	}

	// getters and setters
	public String getStartDateString() {
		return toDateString(startDate);
	}

	public void setStartDateString(String dateString) {
		this.startDate = fromDateString(dateString);
	}

	public String getEndDateString() {
		return toDateString(endDate);
	}

	public void setEndDateString(String dateString) {
		this.endDate = fromDateString(dateString);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public Set<ItemCategory> getItemCategories() {
		return itemCategories;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public void setItemCategories(Set<ItemCategory> itemCategories) {
		this.itemCategories = itemCategories;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNameSlug() {
		return toSlug(getName());
	}

	@SuppressWarnings("unused")
	private void setNameSlug(String nameSlug) {

	}

}