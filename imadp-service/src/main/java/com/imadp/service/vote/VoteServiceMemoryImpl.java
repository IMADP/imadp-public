package com.imadp.service.vote;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.Validate;

import com.imadp.dao.Persistable;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.user.User;

/**
 * VoteServiceMemoryImpl
 *
 * An enhanced implementation of the VoteService designed for scenarios where multiple isFoundBy()
 * vote requests occur in succession. Instead of finding votes individually, all votes by the user
 * are found, cached via the findByUserFindCriteriaCache, and then binary searched in memory
 * to find the result.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @param <T>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public final class VoteServiceMemoryImpl<T extends AbstractVote<V>, V extends Persistable> extends VoteServiceImpl<T, V> {

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(getFindByUserFindCriteriaCache());
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean isFoundByUser(User user, final V votable) {

		// find the list of all votes by this user in ascending object sort order
		FindCriteria<T> findCriteria = dao.findCriteriaBuilder(Results.ALL)
			.whereEqualTo(T.USER, user)
			.orderByAscending(T.VOTABLE).build();

		List<T> votes = findByUser(user, findCriteria);

		// the vote we want to find
		T vote = (T) new AbstractVote<Persistable>(user, votable) {

		};

		// return true if the index is positive, or found, and false otherwise.
		return Collections.binarySearch(votes, vote, voteObjectIdComparator) >= 0;
	}

	// vote object id comparator
	private Comparator<T> voteObjectIdComparator = new Comparator<T>() {
		@Override
		public int compare(T v1, T v2) {
			return v1.getVotable().getId().compareTo(v2.getVotable().getId());
		}
	};

}
