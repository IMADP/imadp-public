package com.imadp.service.category;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.user.PersistableUserServiceImpl;
import com.imadp.service.user.User;


/**
 * CategoryServiceImpl
 *
 * The standard implementation of the CategoryService.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public class CategoryServiceImpl<T extends AbstractCategory<T>> extends PersistableUserServiceImpl<T>
	implements CategoryService<T> {

	/**
	 * Finds the first category by name.
	 *
	 * @param name
	 * @return T
	 */
	protected final T findFirstBy(String name) {
		return findFirstBy(AbstractCategory.NAME, name);
	}

	@Override
	public final boolean isNameInUse(String name) {
		return findFirstBy(name) != null;
	}

	@Override
	public final List<T> findByParentByUser(User user, T parentCategory, CriteriaParams<T> criteriaParams) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(criteriaParams)
			.whereEqualTo(AbstractCategory.PARENT_CATEGORY, parentCategory)
			.whereEqualTo(AbstractCategory.USER, user).build();

		return findByUser(user, findCriteria);
	}

	@Override
	public final long findCountByParentByUser(User user, T parentCategory) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(CriteriaParams.<T>of(Results.ONE))
			.whereEqualTo(AbstractCategory.PARENT_CATEGORY, parentCategory)
			.whereEqualTo(AbstractCategory.USER, user).build();

		return findCountByUser(user, findCriteria);
	}

	@Override
	public List<T> findByUser(User user, String name, T parent, CriteriaParams<T> criteriaParams) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(criteriaParams)
			.whereEqualTo(AbstractCategory.NAME, name)
			.whereEqualTo(AbstractCategory.USER, user)
			.whereEqualTo(AbstractCategory.PARENT_CATEGORY, parent).build();

		return findByUser(user, findCriteria);
	}

	@Override
	public long findCountByUser(User user, String name, T parent) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(CriteriaParams.<T>of(Results.ONE))
			.whereEqualTo(AbstractCategory.NAME, name)
			.whereEqualTo(AbstractCategory.USER, user)
			.whereEqualTo(AbstractCategory.PARENT_CATEGORY, parent).build();

		return findCountByUser(user, findCriteria);
	}

}