package com.tracktacular.service.tracker.journal;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.user.User;
import com.imadp.service.user.PersistableUserServiceImpl;


/**
 * EntryServiceImpl
 *
 * The standard implementation of the EntryService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class EntryServiceImpl extends PersistableUserServiceImpl<Entry>
	implements EntryService {

	@Override
	public List<Entry> findByUser(User user, Journal journal, CriteriaParams<Entry> criteriaParams){
		FindCriteria<Entry> findCriteria = findCriteriaBuilder(criteriaParams)
			.whereEqualTo(Entry.JOURNAL, journal).build();

		return findByUser(user, findCriteria);
	}

	@Override
	public long findCountByUser(User user, Journal journal){
		FindCriteria<Entry> findCriteria = dao.findCriteriaBuilder(Results.ALL)
			.whereEqualTo(Entry.JOURNAL, journal).build();

		return findCountByUser(user, findCriteria);
	}

}
