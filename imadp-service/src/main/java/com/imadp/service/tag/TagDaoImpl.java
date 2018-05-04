package com.imadp.service.tag;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.imadp.dao.Persistable;
import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.hibernate.PersistableDaoImpl;
import com.imadp.service.user.User;

/**
 * TagDaoImpl
 *
 * Hibernate implementation of the TagDao.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public class TagDaoImpl<T extends AbstractTag<? extends Persistable>> extends PersistableDaoImpl<T>
	implements TagDao<T> {

	@Override
	public List<TagFrequency> findTagFrequencies(
			User user, PersistableState persistableState, CriteriaParams<TagFrequency> criteriaParams) {
		logger.debug("[Dao] Finding tag frequencies by User [{}] and persistableState [{}] criteriaParams [{}]",
				new Object[] {user.getId(), persistableState, criteriaParams});

		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);

		// add projections
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.count(T.NAME_SLUG.getName()), TagFrequency.FREQUENCY.getName());
		projectionList.add(Projections.groupProperty(T.NAME_SLUG.getName()), TagFrequency.NAME_SLUG.getName());

		criteria.setProjection(projectionList);

		// add restrictions
		criteria.add(Restrictions.eq(T.USER.getName(), user));

		if(persistableState != null)
			criteria.add(Restrictions.eq(T.PERSISTABLE_STATE.getName(), persistableState));

		// add dynamic ordering
		addOrdering(criteria, criteriaParams.getOrders());

		// set the result transformer to TagFrequencies
		criteria.setResultTransformer(Transformers.aliasToBean(TagFrequency.class));

		return findByCriteria(criteria, criteriaParams.getResults());
	}

	@Override
	public long findTagFrequencyCount(User user, PersistableState persistableState) {
		logger.debug("[Dao] Finding tag frequency count by User [{}]", user.getId());

		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);

		// add projection
		criteria.setProjection(Projections.countDistinct(T.NAME_SLUG.getName()));

		// add restrictions
		criteria.add(Restrictions.eq(T.USER.getName(), user));

		if(persistableState != null)
			criteria.add(Restrictions.eq(T.PERSISTABLE_STATE.getName(), persistableState));

		List<Long> results = findByCriteria(criteria, Results.ALL);
		return !CollectionUtils.isEmpty(results) && results.get(0) != null ? results.get(0) : 0;
	}

}
