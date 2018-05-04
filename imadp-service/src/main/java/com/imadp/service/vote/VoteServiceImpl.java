package com.imadp.service.vote;

import org.apache.commons.lang.Validate;

import com.imadp.core.cache.Cache;
import com.imadp.core.cache.CreateValue;
import com.imadp.dao.Persistable;
import com.imadp.dao.PersistableDao;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.user.PersistableUserServiceImpl;
import com.imadp.service.user.User;

/**
 * VoteServiceImpl
 *
 * The standard implementation of the VoteService.
 *
 * @param <T>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public class VoteServiceImpl<T extends AbstractVote<V>, V extends Persistable> extends PersistableUserServiceImpl<T>
	implements VoteService<T, V> {

	private VoteDao<T> voteDao;

	// optional caches
	private Cache<V, Long> findTallyByVotableCache;
	private Cache<V, Long> findCountByVotableCache;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(voteDao);
	}

	@Override
	public PersistableDao<T> getPersistableDao() {
		return voteDao;
	}

	@Override
	public final long findCountBy(V votable) {
		return customFindCount(findCountByVotableCache, votable, new CreateValue<V, Long>() {
			@Override
			public Long create(V votable) {
				FindCriteria<T> findCriteria = dao.findCriteriaBuilder(Results.ALL)
					.whereEqualTo(T.VOTABLE, votable).build();

				return voteDao.findCountBy(findCriteria);
			}
		});
	}

	@Override
	public final long findTallyBy(V votable) {
		return customFindCount(findTallyByVotableCache, votable, new CreateValue<V, Long>() {
			@Override
			public Long create(V votable) {
				return voteDao.findRatingSumBy(votable);
			}
		});
	}

	@Override
	public boolean isFoundByUser(User user, V votable) {
		FindCriteria<T> findCriteria = dao.findCriteriaBuilder(Results.ALL)
			.whereEqualTo(T.USER, user)
			.whereEqualTo(T.VOTABLE, votable).build();

		return findCountByUser(user, findCriteria) != 0;
	}

	@Override
	protected void onAfterSave(T vote, boolean initialSave) {
		super.onAfterSave(vote, initialSave);

		// remove the findTallyByVotableCache
		if(findTallyByVotableCache != null)
			findTallyByVotableCache.remove(vote.getVotable());

		// remove the findCountByVotableCache
		if(findCountByVotableCache != null)
			findCountByVotableCache.remove(vote.getVotable());
	}

	@Override
	protected void onAfterDelete(T vote) {
		super.onAfterDelete(vote);

		// remove the findTallyByVotableCache
		if(findTallyByVotableCache != null)
			findTallyByVotableCache.remove(vote.getVotable());

		// remove the findCountByVotableCache
		if(findCountByVotableCache != null)
			findCountByVotableCache.remove(vote.getVotable());
	}

	// getters and setters
	public Cache<V, Long> getFindTallyByVotableCache() {
		return findTallyByVotableCache;
	}

	public void setFindTallyByVotableCache(Cache<V, Long> findTallyByVotableCache) {
		this.findTallyByVotableCache = findTallyByVotableCache;
	}

	public Cache<V, Long> getFindCountByVotableCache() {
		return findCountByVotableCache;
	}

	public void setFindCountByVotableCache(Cache<V, Long> findCountByVotableCache) {
		this.findCountByVotableCache = findCountByVotableCache;
	}

	public VoteDao<T> getVoteDao() {
		return voteDao;
	}

	public void setVoteDao(VoteDao<T> voteDao) {
		this.voteDao = voteDao;
	}

}
