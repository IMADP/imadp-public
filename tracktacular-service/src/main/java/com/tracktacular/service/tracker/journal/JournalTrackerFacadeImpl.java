package com.tracktacular.service.tracker.journal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.joda.time.Interval;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.tracker.AbstractTrackerFacade;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TrackerDemo;
import com.tracktacular.service.tracker.calendar.CalendarEntry;


/**
 * JournalTrackerFacadeImpl
 *
 * The standard implementation of the JournalTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class JournalTrackerFacadeImpl extends AbstractTrackerFacade
	implements JournalTrackerFacade {

	private JournalService journalService;
	private EntryService entryService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(journalService);
		Validate.notNull(entryService);
	}

	@Override
	public Journal getJournal(User user, String uid) {
		return journalService.findFirstByUser(user, uid);
	}

	@Override
	public List<JournalDto> findActiveJournals(User user, int recentEntries, CriteriaParams<Journal> criteriaParams) {
		return getJournalDtos(user, recentEntries, journalService.findByUser(user, PersistableState.ACTIVE, criteriaParams));
	}

	@Override
	public long findActiveJournalCount(User user) {
		return journalService.findCountByUser(user, PersistableState.ACTIVE);
	}

	@Override
	public List<JournalDto> findCompletedJournals(User user, CriteriaParams<Journal> criteriaParams) {
		return getJournalDtos(user, journalService.findByUser(user, PersistableState.ARCHIVED, criteriaParams));
	}

	@Override
	public long findCompletedJournalCount(User user) {
		return journalService.findCountByUser(user, PersistableState.ARCHIVED);
	}

	@Override
	public List<JournalDto> findDeletedJournals(User user, CriteriaParams<Journal> criteriaParams) {
		return getJournalDtos(user, journalService.findByUser(user, PersistableState.DELETED, criteriaParams));
	}

	@Override
	public long findDeletedJournalCount(User user) {
		return journalService.findCountByUser(user, PersistableState.DELETED);
	}

	/**
	 * Gets a list of JournalDtos from a list of journals with no recentEntries.
	 *
	 * @param user
	 * @param journals
	 * @return List<JournalDto>
	 */
	private List<JournalDto> getJournalDtos(User user, List<Journal> journals) {
		return getJournalDtos(user, 0, journals);
	}

	/**
	 * Gets a list of JournalDtos from a list of journals.
	 *
	 * @param user
	 * @param recentEntriesCount
	 * @param journals
	 * @return List<JournalDto>
	 */
	private List<JournalDto> getJournalDtos(User user, int recentEntriesCount, List<Journal> journals) {
		List<JournalDto> journalDtos = new ArrayList<>(journals.size());

		for(Journal journal : journals)
		{
			List<Entry> recentEntries = getRecentEntries(recentEntriesCount, user, journal);
			long entryCount = entryService.findCountByUser(user, journal);
			journalDtos.add(new JournalDto(journal, recentEntries, entryCount));
		}

		return journalDtos;
	}

	/**
	 * Returns a list of recent entries for a given journal.
	 *
	 * @param maxRecentEntries
	 * @param user
	 * @param journal
	 * @return List<Entry>
	 */
	private List<Entry> getRecentEntries(int maxRecentEntries, User user, Journal journal) {
		if(maxRecentEntries == 0)
			return Collections.emptyList();

		CriteriaParams<Entry> criteriaParams = CriteriaParams.of(new Results(0, maxRecentEntries), Order.desc(Entry.DATE));
		List<Entry> recentEntries = entryService.findByUser(user, journal, criteriaParams);
		return recentEntries;
	}

	@Override
	public void saveJournal(Journal journal) {
		new JournalValidator("journal", journal).validate();
		journalService.save(journal);
	}

	@Override
	public void deleteJournal(Journal journal) {
		List<Entry> entries = entryService.findByUser(journal.getUser(), journal, CriteriaParams.<Entry>of(Results.ALL));
		entryService.delete(entries);
		journalService.delete(journal);
	}

	@Override
	public Entry getEntry(User user, String uid) {
		return entryService.findFirstByUser(user, uid);
	}

	@Override
	public List<Entry> findEntries(User user, Journal journal, CriteriaParams<Entry> criteriaParams) {
		return entryService.findByUser(user, journal, criteriaParams);
	}

	@Override
	public long findEntriesCount(User user, Journal journal) {
		return entryService.findCountByUser(user, journal);
	}

	@Override
	public void saveEntry(Entry entry) {
		new EntryValidator("entry", entry).validate();
		entryService.save(entry);
	}

	@Override
	public void deleteEntry(Entry entry) {
		entryService.delete(entry);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<Journal> journals = journalService.findByUser(user, CriteriaParams.<Journal>of(Results.ALL));

		for(Journal journal : journals)
			deleteJournal(journal);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new JournalTrackerDemo(this);
	}

	@Override
	public JournalTrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		List<Journal> activeJournalsList = journalService.findByUser(user, PersistableState.ACTIVE,
				CriteriaParams.<Journal>of(Results.ALL));

		return new JournalTrackerReport(getJournalDtos(user, 1, activeJournalsList));
	}

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		List<CalendarEntry> calendarEntries = new ArrayList<>();

		// active routines
		for(JournalDto journalDto : findActiveJournals(user, 5, CriteriaParams.<Journal>of(Results.ALL)))
		{
			// starting routines
			if(interval.contains(journalDto.getJournal().getTimeCreatedDate()))
			{
				CalendarEntry calendarEntry = new CalendarEntry(user, journalDto.getJournal().getTimeCreatedDate());
				calendarEntry.setTracker(Tracker.JOURNAL);
				calendarEntry.setName(String.format("Journal started: %s", journalDto.getJournal().getName()));
				calendarEntry.setDescription(journalDto.getJournal().getDescription());
				calendarEntries.add(calendarEntry);
			}

		}

		return calendarEntries;
	}


	// getters and setters
	public JournalService getJournalService() {
		return journalService;
	}

	public void setJournalService(JournalService journalService) {
		this.journalService = journalService;
	}

	public EntryService getEntryService() {
		return entryService;
	}

	public void setEntryService(EntryService entryService) {
		this.entryService = entryService;
	}

}
