package com.imadp.service.vote;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.imadp.dao.Persistable;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.hibernate.PersistableDaoImpl;

/**
 * VoteDaoImpl
 *
 * Hibernate implementation of the VoteDao.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public class VoteDaoImpl<T extends AbstractVote<? extends Persistable>> extends PersistableDaoImpl<T> implements VoteDao<T> {

	@Override
	public long findRatingSumBy(Persistable votable) {
		logger.debug("[Dao] Finding [{}] rating sum by votable [{}] ", entityName, votable.getId());
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		criteria.add(Restrictions.eq(T.VOTABLE.getName(), votable));
		criteria.setProjection(Projections.sum(T.RATING.getName()));
		Object result = findByCriteria(criteria, Results.ALL).get(0);
		return result == null ? 0 : ((Long)result);
	}

}