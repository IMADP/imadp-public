package com.imadp.service.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.imadp.core.Property;
import com.imadp.service.Sortable;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * AbstractCategory
 *
 * Defines an abstract simple category type, useful in grouping or organizing different entities.
 * Categories can be hierarchical through the use of parent and child categories.
 *
 * @note This class is genericized to allow parent and child categories to be of the same type as extending classes.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public class AbstractCategory<T extends AbstractCategory<T>> extends AbstractPersistableUser
	implements Sortable, Comparable<AbstractCategory<T>> {

	// static Properties
	public static final Property<AbstractCategory<?>, String> NAME = Property.of("name");
	public static final Property<AbstractCategory<?>, Integer> SORT = Property.of("sort");
	public static final Property<AbstractCategory<?>, AbstractCategory<?>> PARENT_CATEGORY = Property.of("parentCategory");

	// properties
	private T parentCategory;
	private Set<T> childCategories;
	private String name;
	private int sort;

	// constructor
	public AbstractCategory() {

	}

	// constructor
	public AbstractCategory(User user) {
		this.user = user;
	}

	// constructor
	public AbstractCategory(User user, T parentCategory) {
		this.user = user;
		this.parentCategory = parentCategory;
	}

	/**
	 * Returns the categories as a List.
	 *
	 * @return List<T>
	 */
	public List<T> getChildCategoriesAsList() {
		 return new ArrayList<>(childCategories);
	}

	/**
	 * Returns true if the root categories are sortable as determined by more than one value, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isChildCategoriesSortable() {
		return childCategories != null && childCategories.size() > 1;
	}

	/**
	 * Returns the total number of categories and child categories.
	 * Returns <code>1</code> if no child categories are present.
	 *
	 * @return int
	 */
	public int getTotalCategoryCount() {
		if(childCategories == null || childCategories.isEmpty())
			return 1;

		int count = 1;

		for(T category : childCategories)
			count += category.getTotalCategoryCount();

		return count;
	}

	/**
	 * Returns the depth of this category, specifically the number of parents this category
	 * has before reaching the root parent (a category with no parent).
	 *
	 * If this category is the parent, <code>1</code> is returned.
	 *
	 * @return int
	 */
	public int getDepth() {
		int depth = 1;

		AbstractCategory<T> category = this;

		while(!category.isRoot())
		{
			category = category.getParentCategory();
			depth++;
		}

		return depth;
	}

	/**
	 * Returns true if this category is a root category, or in other words has no parents.
	 *
	 * @return boolean
	 */
	public boolean isRoot() {
		return getParentCategory() == null;
	}

	/**
	 * Returns the root category.
	 *
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public T getRootCategory() {
		T category = (T) this;

		while(!category.isRoot())
			category = category.getParentCategory();

		return category;
	}

	/**
	 * Returns true if this category has child categories, false otherwise.
	 *
	 * @return true
	 */
	public boolean hasChildCategories() {
		return childCategories != null && !childCategories.isEmpty();
	}

	/**
	 * Returns a flattened list of all child categories.
	 *
	 * For example, given this hierarchy:
	 *
	 * CurrentCategory
	 * -ChildCategory
	 * --SubCategory1
	 * --SubCategory2
	 *
	 * the return list will consist of
	 *  CurrentCategory
	 *  ChildCategory
	 *  SubCategory1
	 *  SubCategory2
	 *
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public final List<T> getFlattenedCategories() {
		List<T> flattenedChildCategories = new ArrayList<>();
		flattenedChildCategories.add((T) this);

		for(T child : childCategories)
			addRecursiveFlattenedChildCategories(child, flattenedChildCategories);

		return flattenedChildCategories;
	}

	/**
	 * Recursively adds a category and all child categories to the flattenedChildCategories list.
	 *
	 * @param category
	 * @param flattenedChildCategories
	 */
	private void addRecursiveFlattenedChildCategories(T category, List<T> flattenedChildCategories) {
		flattenedChildCategories.add(category);

		for(T child : category.getChildCategories())
			addRecursiveFlattenedChildCategories(child, flattenedChildCategories);
	}

	/**
	 * Returns the path of this category, beginning with the parent category, concatenating child
	 * categories with an arrow, and ending with this category name.
	 *
	 * For example, given this hierarchy:
	 *
	 * ParentCategory
	 * -ChildCategory
	 * --SubCategory
	 *
	 *  SubCategory's getPath() would return <code>ParentCategory > ChildCategory > SubCategory</code>
	 *
	 * @return String
	 */
	public String getPath() {
		StringBuilder sb = new StringBuilder();
		recursivePathName(this, sb);
		return sb.toString();
	}

	/**
	 * Recursively traverses the category hierarchy, appending path names.
	 *
	 * @param category
	 * @param sb
	 */
	private void recursivePathName(AbstractCategory<T> category, StringBuilder sb) {
		if(category.isRoot())
		{
			sb.append(category.name);
			return;
		}

		recursivePathName(category.getParentCategory(), sb);

		sb.append(" > ");
		sb.append(category.name);
	}

	/**
	 * Returns a slug from the name property.
	 *
	 * @return String
	 */
	public String getNameSlug() {
		return toSlug(name);
	}

	@Override
	public int compareTo(AbstractCategory<T> category) {
		return getNameSlug().compareTo(category.getNameSlug());
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getSort() {
		return sort;
	}

	@Override
	public void setSort(int sort) {
		this.sort = sort;
	}

	public T getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(T parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Set<T> getChildCategories() {
		return childCategories;
	}

	public void setChildCategories(Set<T> childCategories) {
		this.childCategories = childCategories;
	}

}