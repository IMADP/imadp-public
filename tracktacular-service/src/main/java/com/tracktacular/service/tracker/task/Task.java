package com.tracktacular.service.tracker.task;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.dao.PersistableState;
import com.imadp.service.Sortable;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Task
 *
 * A representation of a pending task.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Task extends AbstractPersistableUser implements Sortable {

	// static Properties
	public static final Property<Task, String> NAME = Property.of("name");
	public static final Property<Task, String> NOTES = Property.of("notes");
	public static final Property<Task, String> CATEGORY_NAME = Property.of("categoryName");
	public static final Property<Task, String> CATEGORY_PATH = Property.of("categoryPath");
	public static final Property<Task, Integer> SORT = Property.of("sort");
	public static final Property<Task, TaskCategory> CATEGORY = Property.of("category");
	public static final Property<Task, TaskPriority> PRIORITY = Property.of("priority");
	public static final Property<Task, Integer> PRIORITY_SORT = Property.of("prioritySort");
	public static final Property<Task, DateTime> TARGET_DATE = Property.of("targetDate");

	// properties
	private String name;
	private String notes;
	private String categoryName;
	private String categoryPath;
	private TaskCategory category;
	private TaskPriority priority = TaskPriority.MEDIUM;
	private DateTime targetDate;
	private int sort;

	// constructor
	public Task() {
		this(null);
	}

	// constructor
	public Task(User user) {
		this(user, null);
	}

	// constructor
	public Task(User user, TaskCategory category) {
		super(user);
		this.category = category;
		setPersistableState(PersistableState.ACTIVE);
	}

	/**
	 * Returns true if the Task has a TaskCategory, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean hasCategory() {
		return category != null;
	}

	/**
	 * Returns the id of the category, or <code>null</code> if none is present.
	 *
	 * @return Long
	 */
	public Long getCategoryId() {
		return hasCategory() ? category.getId() : null;
	}

	@Override
	protected void onActiveStateChange() {
		this.categoryName = null;
		this.categoryPath = null;
	}

	@Override
	protected void onArchivedStateChange() {
		if(category != null)
		{
			this.categoryName = category.getName();
			this.categoryPath = category.getPath();
			this.category = null;
		}
	}

	@Override
	protected void onDeletedStateChange() {
		if(category != null)
		{
			this.categoryName = category.getName();
			this.categoryPath = category.getPath();
			this.category = null;
		}
	}

	/**
	 * Returns the sort orders for the priority values.
	 *
	 * @return int
	 */
	public int getPrioritySort() {
		switch (priority)
		{
			case LOW: 	 return 3;
	        case MEDIUM: return 2;
	        case HIGH:   return 1;
	        default:     return 0;
		}
	}

	/**
	 * Returns true if the objective is late, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isLate() {
		if(getTargetDate() == null)
			return false;

		return isActiveState() && new DateTime().isAfter(getTargetDate().plusDays(1));
	}

	// getters and setters
	public String getTargetDateString() {
		return toDateString(targetDate);
	}

	public void setTargetDateString(String dateString) {
		this.targetDate = fromDateString(dateString);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public TaskCategory getCategory() {
		return category;
	}

	public void setCategory(TaskCategory category) {
		this.category = category;
	}

	public DateTime getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(DateTime targetDate) {
		this.targetDate = targetDate;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	@SuppressWarnings("unused")
	private void setPrioritySort(int prioritySort) {

	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryPath() {
		return categoryPath;
	}

	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}

	@Override
	public int getSort() {
		return sort;
	}

	@Override
	public void setSort(int sort) {
		this.sort = sort;
	}

	/**
	 * TaskPriority
	 *
	 * Enumerated values of task priorities.
	 *
	 * @version 1.0
	 * @author Anthony DePalma
	 */
	public enum TaskPriority {
		HIGH, MEDIUM, LOW
	}

}