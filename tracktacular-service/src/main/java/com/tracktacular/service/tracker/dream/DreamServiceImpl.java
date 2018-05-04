package com.tracktacular.service.tracker.dream;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.imadp.core.cache.Cache;
import com.imadp.core.cache.CreateValue;
import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.user.User;
import com.imadp.service.user.PersistableUserServiceImpl;


/**
 * DreamServiceImpl
 *
 * The standard implementation of the DreamService.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class DreamServiceImpl extends PersistableUserServiceImpl<Dream> implements DreamService {
	private DreamDao dreamDao;
	private DreamsignService dreamsignService;
	private Cache<User, DreamTrackerReport> getDreamTrackerReportCache;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(dreamDao);
		Validate.notNull(dreamsignService);
		Validate.notNull(getDreamTrackerReportCache);
	}

	@Override
	protected com.imadp.dao.PersistableDao<Dream> getPersistableDao() {
		return dreamDao;
	}

	@Override
	protected void onAfterSave(Dream dream, boolean initialSave) {
		super.onAfterSave(dream, initialSave);

		getDreamTrackerReportCache.remove(dream.getUser());

		if(!initialSave)
			deleteDreamsigns(dream);

		if(!CollectionUtils.isEmpty(dream.getDreamsigns()))
			for(Dreamsign dreamsign : dream.getDreamsigns())
				dreamsignService.save(new Dreamsign(
						dream.getUser(), dream, dreamsign.getName(), dream.getPersistableState()));
	}

	@Override
	protected void onBeforeDelete(Dream dream) {
		super.onBeforeDelete(dream);

		getDreamTrackerReportCache.remove(dream.getUser());

		deleteDreamsigns(dream);
	}

	@Override
	public List<Dream> findLucidByUser(User user, PersistableState persistableState, CriteriaParams<Dream> criteriaParams) {
		FindCriteria<Dream> findCriteria = findCriteriaBuilder(criteriaParams)
				.whereEqualTo(Dream.USER, user)
				.whereEqualTo(Dream.PERSISTABLE_STATE, persistableState)
				.whereEqualTo(Dream.LUCID, true).build();

		return findByUser(user, findCriteria);
	}

	@Override
	public long findLucidCountByUser(User user, PersistableState persistableState) {
		FindCriteria<Dream> findCriteria = findCriteriaBuilder(CriteriaParams.<Dream>of(Results.ONE))
				.whereEqualTo(Dream.USER, user)
				.whereEqualTo(Dream.PERSISTABLE_STATE, persistableState)
				.whereEqualTo(Dream.LUCID, true).build();

		return findCountByUser(user, findCriteria);
	}

	@Override
	public List<Dream> findFavoriteByUser(User user, PersistableState persistableState, CriteriaParams<Dream> criteriaParams) {
		FindCriteria<Dream> findCriteria = findCriteriaBuilder(criteriaParams)
				.whereEqualTo(Dream.USER, user)
				.whereEqualTo(Dream.PERSISTABLE_STATE, persistableState)
				.whereEqualTo(Dream.FAVORITE, true).build();

		return findByUser(user, findCriteria);
	}

	@Override
	public long findFavoriteCountByUser(User user, PersistableState persistableState) {
		FindCriteria<Dream> findCriteria = findCriteriaBuilder(CriteriaParams.<Dream>of(Results.ONE))
				.whereEqualTo(Dream.USER, user)
				.whereEqualTo(Dream.PERSISTABLE_STATE, persistableState)
				.whereEqualTo(Dream.FAVORITE, true).build();

		return findCountByUser(user, findCriteria);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public DreamTrackerReport getDreamTrackerReport(User user) {
		return getDreamTrackerReportCache.getOrPut(user, new CreateValue<User, DreamTrackerReport>() {
			@Override
			public DreamTrackerReport create(User user) {
				return dreamDao.getDreamTrackerReport(user);
			}
		});
	}

	/**
	 * Deletes all dreamsigns associated with a dream.
	 *
	 * @param dream
	 */
	private void deleteDreamsigns(Dream dream) {
		List<Dreamsign> dreamsigns = dreamsignService.findBy(dream, CriteriaParams.<Dreamsign>of(Results.ALL));
		dreamsignService.delete(dreamsigns);
	}

	// getters and setters
	public DreamsignService getDreamsignService() {
		return dreamsignService;
	}

	public void setDreamsignService(DreamsignService dreamsignService) {
		this.dreamsignService = dreamsignService;
	}

	public DreamDao getDreamDao() {
		return dreamDao;
	}

	public void setDreamDao(DreamDao dreamDao) {
		this.dreamDao = dreamDao;
	}

	public Cache<User, DreamTrackerReport> getGetDreamTrackerReportCache() {
		return getDreamTrackerReportCache;
	}

	public void setGetDreamTrackerReportCache(Cache<User, DreamTrackerReport> getDreamTrackerReportCache) {
		this.getDreamTrackerReportCache = getDreamTrackerReportCache;
	}

}
