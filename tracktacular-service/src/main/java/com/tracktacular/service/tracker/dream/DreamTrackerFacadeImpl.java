package com.tracktacular.service.tracker.dream;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.joda.time.Interval;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.tag.TagCloud;
import com.imadp.service.tag.TagFrequency;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.tracker.AbstractTrackerFacade;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TrackerDemo;
import com.tracktacular.service.tracker.calendar.CalendarEntry;


/**
 * DreamTrackerFacadeImpl
 *
 * The standard implementation of the DreamTrackerFacade.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class DreamTrackerFacadeImpl extends AbstractTrackerFacade
	implements DreamTrackerFacade {

	private DreamService dreamService;
	private DreamsignService dreamsignService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(dreamService);
		Validate.notNull(dreamsignService);
	}

	@Override
	public Dream getDream(User user, String uid) {
		return dreamService.findFirstByUser(user, uid);
	}

	@Override
	public void saveDream(Dream dream) {
		new DreamValidator("dream", dream).validate();
		dreamService.save(dream);
	}

	@Override
	public void deleteDream(Dream dream) {
		dreamService.delete(dream);
	}

	@Override
	public List<Dream> findDreams(User user, CriteriaParams<Dream> criteriaParams) {
		return dreamService.findByUser(user, PersistableState.ACTIVE, criteriaParams);
	}

	@Override
	public long findDreamCount(User user) {
		return dreamService.findCountByUser(user, PersistableState.ACTIVE);
	}

	@Override
	public List<Dream> findLucidDreams(User user, CriteriaParams<Dream> criteriaParams) {
		return dreamService.findLucidByUser(user, PersistableState.ACTIVE, criteriaParams);
	}

	@Override
	public long findLucidDreamCount(User user) {
		return dreamService.findLucidCountByUser(user, PersistableState.ACTIVE);
	}

	@Override
	public List<Dream> findFavoriteDreams(User user, CriteriaParams<Dream> criteriaParams) {
		return dreamService.findFavoriteByUser(user, PersistableState.ACTIVE, criteriaParams);
	}

	@Override
	public long findFavoriteDreamCount(User user) {
		return dreamService.findFavoriteCountByUser(user, PersistableState.ACTIVE);
	}

	@Override
	public List<Dreamsign> findDreamsigns(User user, String name, CriteriaParams<Dreamsign> criteriaParams) {
		return dreamsignService.findByUser(user, name, PersistableState.ACTIVE, criteriaParams);
	}

	@Override
	public long findDreamsignCount(User user, String name) {
		return dreamsignService.findCountByUser(user, name, PersistableState.ACTIVE);
	}

	@Override
	public List<Dream> findDeletedDreams(User user, CriteriaParams<Dream> criteriaParams) {
		return dreamService.findByUser(user, PersistableState.DELETED, criteriaParams);
	}

	@Override
	public long findDeletedDreamCount(User user) {
		return dreamService.findCountByUser(user, PersistableState.DELETED);
	}

	@Override
	public TagCloud findDreamsignTagCloud(User user, int tagCount, double minWeight, double maxWeight) {
		List<TagFrequency> dreamsigns = dreamsignService.findTagFrequencies(user, PersistableState.ACTIVE,
				CriteriaParams.of(new Results(0, tagCount), Order.desc(TagFrequency.FREQUENCY)));

		return new TagCloud(dreamsigns, minWeight, maxWeight);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<Dream> dreams = dreamService.findByUser(user, CriteriaParams.<Dream>of(Results.ALL));

		for(Dream dream : dreams)
			deleteDream(dream);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new DreamTrackerDemo(this);
	}

	@Override
	public DreamTrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		return dreamService.getDreamTrackerReport(user);
	}

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		List<CalendarEntry> calendarEntries = new ArrayList<>();

		// lucid dreams
		for(Dream dream : findLucidDreams(user, CriteriaParams.<Dream>of(Results.ALL)))
		{
			if(interval.contains(dream.getDate()))
			{
				CalendarEntry calendarEntry = new CalendarEntry(user, dream.getDate());
				calendarEntry.setTracker(Tracker.DREAM);
				calendarEntry.setName("Lucid Dream");
				calendarEntry.setDescription(dream.getTitle());
				calendarEntries.add(calendarEntry);
			}
		}

		return calendarEntries;
	}

	// getters and setters
	public DreamService getDreamService() {
		return dreamService;
	}

	public void setDreamService(DreamService dreamService) {
		this.dreamService = dreamService;
	}

	public DreamsignService getDreamsignService() {
		return dreamsignService;
	}

	public void setDreamsignService(DreamsignService dreamsignService) {
		this.dreamsignService = dreamsignService;
	}

}
