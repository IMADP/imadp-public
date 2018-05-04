package com.imadp.service.category;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.PersistableUserService;
import com.imadp.service.user.User;



/**
 * ICategoryService
 *
 * Provides common retrieval operations for Category objects.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public interface CategoryService<T extends AbstractCategory<T>> extends PersistableUserService<T> {

	/**
	 * Returns true if a category name is in use, false otherwise.
	 *
	 * @param name
	 * @return boolean
	 */
	public boolean isNameInUse(String name);

	/**
	 * Finds categories by user, parentCategory, and criteriaParams.
	 * Passing a null parent will find root categories.
	 *
	 * @param user
	 * @param parentCategory
	 * @param criteriaParams
	 * @return List<T>
	 */
	public List<T> findByParentByUser(User user, T parentCategory, CriteriaParams<T> criteriaParams);

	/**
	 * Finds the count of categories by user and parentCategory.
	 * Passing a null parent will find root categories.
	 *
	 * @param user
	 * @param parentCategory
	 * @return long
	 */
	public long findCountByParentByUser(User user, T parentCategory);

	/**
	 * Finds a list of categories by user, name, and parentCategory.
	 *
	 * @param user
	 * @param name
	 * @param parentCategory
	 * @param criteriaParams
	 * @return List<T>
	 */
	public List<T> findByUser(User user, String name, T parentCategory, CriteriaParams<T> criteriaParams);

	/**
	 * Finds the count of categories by user, name, and parentCategory.
	 *
	 * @param user
	 * @param name
	 * @param parentCategory
	 * @return long
	 */
	public long findCountByUser(User user, String name, T parentCategory);

}