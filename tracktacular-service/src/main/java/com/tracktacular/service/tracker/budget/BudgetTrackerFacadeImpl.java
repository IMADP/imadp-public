package com.tracktacular.service.tracker.budget;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.joda.time.Interval;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.tracker.AbstractTrackerFacade;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TrackerDemo;
import com.tracktacular.service.tracker.TrackerReport;
import com.tracktacular.service.tracker.calendar.CalendarEntry;


/**
 * BudgetTrackerFacadeImpl
 *
 * The standard implementation of the BudgetTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class BudgetTrackerFacadeImpl extends AbstractTrackerFacade
	implements BudgetTrackerFacade {

	private BudgetService budgetService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(budgetService);
	}

	@Override
	public Budget getBudget(User user, String uid) {
		return budgetService.findFirstByUser(user, uid);
	}

	@Override
	public void saveBudget(Budget budget) {
		new BudgetValidator("budget", budget).validate();
		budgetService.save(budget);
	}

	@Override
	public void deleteBudget(Budget budget) {
		budgetService.delete(budget);
	}

	@Override
	public List<Budget> findActiveBudgets(User user) {
		return budgetService.findByUser(user, PersistableState.ACTIVE, CriteriaParams.<Budget>of(
				Results.ALL, Order.asc(Budget.START_DATE)));
	}

	@Override
	public long findActiveBudgetCount(User user) {
		return budgetService.findCountByUser(user, PersistableState.ACTIVE);
	}

	@Override
	public List<Budget> findCompletedBudgets(User user, CriteriaParams<Budget> criteriaParams) {
		return budgetService.findByUser(user, PersistableState.ARCHIVED, criteriaParams);
	}

	@Override
	public long findCompletedBudgetCount(User user) {
		return budgetService.findCountByUser(user, PersistableState.ARCHIVED);
	}

	@Override
	public List<Budget> findDeletedBudgets(User user, CriteriaParams<Budget> criteriaParams) {
		return budgetService.findByUser(user, PersistableState.DELETED, criteriaParams);
	}

	@Override
	public long findDeletedBudgetCount(User user) {
		return budgetService.findCountByUser(user, PersistableState.DELETED);
	}

	@Override
	public ItemCategory getItemCategory(User user, String uid) {
		return budgetService.getItemCategory(user, uid);
	}

	@Override
	public void saveItemCategory(ItemCategory category) {
		validateItemCategory(category);
		budgetService.saveItemCategory(category);
	}

	@Override
	public void saveItemCategories(List<ItemCategory> itemCategories) {
		for(ItemCategory itemCategory : itemCategories)
			validateItemCategory(itemCategory);

		budgetService.saveItemCategories(itemCategories);
	}

	/**
	 * Validates a ItemCategory.
	 *
	 * @param itemCategory
	 */
	private void validateItemCategory(ItemCategory itemCategory) {
		new ItemCategoryValidator("itemCategory", itemCategory, budgetService).validate();
	}

	@Override
	public void deleteItemCategory(ItemCategory category) {
		budgetService.deleteItemCategory(category);
	}

	@Override
	public Item getItem(User user, String uid) {
		return budgetService.getItem(user, uid);
	}

	@Override
	public void saveItem(Item item) {
		validateItem(item);
		budgetService.saveItem(item);
	}

	@Override
	public void saveItems(List<Item> items) {
		for(Item item : items)
			validateItem(item);

		budgetService.saveItems(items);
	}

	/**
	 * Validates a Item.
	 *
	 * @param item
	 */
	private void validateItem(Item item) {
		new ItemValidator("item", item).validate();
	}

	@Override
	public void deleteItem(Item item) {
		budgetService.deleteItem(item);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<Budget> budgets = budgetService.findByUser(user, CriteriaParams.<Budget>of(Results.ALL));

		for(Budget budget : budgets)
			deleteBudget(budget);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new BudgetTrackerDemo(this);
	}

	@Override
	public TrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		return new BudgetTrackerReport(findActiveBudgets(user));
	}

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		List<CalendarEntry> calendarEntries = new ArrayList<>();

		// active budgets
		for(Budget budget : findActiveBudgets(user))
		{
			// starting budgets
			if(interval.contains(budget.getStartDate()))
			{
				CalendarEntry calendarEntry = new CalendarEntry(user, budget.getStartDate());
				calendarEntry.setTracker(Tracker.BUDGET);
				calendarEntry.setName(String.format("Budget started: %s", budget.getName()));
				calendarEntry.setDescription(budget.getDescription());
				calendarEntries.add(calendarEntry);
			}

			// ending budgets
			if(interval.contains(budget.getEndDate()))
			{
				CalendarEntry calendarEntry = new CalendarEntry(user, budget.getEndDate());
				calendarEntry.setTracker(Tracker.BUDGET);
				calendarEntry.setName(String.format("Budget ended: %s", budget.getName()));
				calendarEntry.setDescription(budget.getDescription());
				calendarEntries.add(calendarEntry);
			}

		}

		return calendarEntries;
	}

	// getters and setters
	public BudgetService getBudgetService() {
		return budgetService;
	}

	public void setBudgetService(BudgetService budgetService) {
		this.budgetService = budgetService;
	}

}