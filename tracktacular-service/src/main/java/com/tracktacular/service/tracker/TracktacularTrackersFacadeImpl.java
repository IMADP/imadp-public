package com.tracktacular.service.tracker;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.joda.time.DateTime;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import au.com.bytecode.opencsv.CSVWriter;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.imadp.core.Property;
import com.imadp.core.reflection.Methods;
import com.imadp.dao.AbstractPersistable;
import com.imadp.dao.PersistableDao;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.FacadeComponent;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;


/**
 * TracktacularTrackersFacadeImpl
 *
 * The standard implementation of the TracktacularTrackersFacade.
 *
 * @note This dao is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class TracktacularTrackersFacadeImpl extends FacadeComponent implements TracktacularTrackersFacade,
ApplicationContextAware {

	private Multimap<Tracker, PersistableDao<AbstractPersistableUser>> trackerDaoMap;
	private Map<Tracker, TrackerFacade> trackerFacadeMap;
	private ApplicationContext context;

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void onInit() {
		super.onInit();

		this.trackerFacadeMap = Maps.newHashMap();
		this.trackerDaoMap = ArrayListMultimap.create();

		for(Tracker tracker : Tracker.values())
			trackerFacadeMap.put(tracker, context.getBean(tracker.getName() + "TrackerFacade", TrackerFacade.class));

		Map<String, PersistableDao> persistableDaoMap = context.getBeansOfType(PersistableDao.class);

		for(String name : persistableDaoMap.keySet())
		{
			if(!name.contains("_"))
				continue;

			Tracker tracker = Tracker.valueOf(name.split("_")[0].toUpperCase());
			trackerDaoMap.put(tracker, persistableDaoMap.get(name));
		}
	}

	@Override
	public Collection<TrackerFacade> getTrackerFacades() {
		return trackerFacadeMap.values();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <V extends TrackerFacade> V getTrackerFacade(Tracker tracker) {
		return (V) trackerFacadeMap.get(tracker);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Set<User> findDistinctUsers(Tracker tracker) {
		logger.info("Finding distinct user count for tracker [{}]", tracker);

		Set<User> trackerUsers = Sets.newHashSet();

		// add all the unique, recent users for each dao associated with the tracker
		for(PersistableDao<AbstractPersistableUser> dao : trackerDaoMap.get(tracker))
		{
			List<AbstractPersistableUser> abstractPersistableUsers = dao.findAll();

			for(AbstractPersistableUser abstractPersistableUser : abstractPersistableUsers)
				trackerUsers.add(abstractPersistableUser.getUser());
		}

		return trackerUsers;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Set<User> findRecentDistinctUsers(Tracker tracker) {
		logger.info("Finding recent distinct user count for tracker [{}]", tracker);

		DateTime date = new DateTime();
		long startTime = date.minusDays(1).getMillis();
		long endTime = date.getMillis();
		Set<User> trackerUsers = Sets.newHashSet();

		// add all the unique, recent users for each dao associated with the tracker
		for(PersistableDao<AbstractPersistableUser> dao : trackerDaoMap.get(tracker))
		{
			FindCriteria<AbstractPersistableUser> findCriteria = dao.findCriteriaBuilder(Results.ALL)
					.whereGreaterThanOrEqualTo(AbstractPersistableUser.TIME_MODIFIED, startTime)
					.whereLessThanOrEqualTo(AbstractPersistableUser.TIME_MODIFIED, endTime).build();

			List<AbstractPersistableUser> abstractPersistableUsers = dao.findBy(findCriteria);

			for(AbstractPersistableUser abstractPersistableUser : abstractPersistableUsers)
				trackerUsers.add(abstractPersistableUser.getUser());
		}

		return trackerUsers;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public TracktacularReport generateTracktacularReport(String username, Preferences preferences, boolean publicOnly) {
		logger.info("Generating Tracktacular Report for user [{}]", username);

		List<TrackerReport> trackerReports = Lists.newArrayList();

		for(Tracker tracker : preferences.getTrackers().getTrackersWithReports(publicOnly))
		{
			TrackerFacade trackerFacade = getTrackerFacade(tracker);
			TrackerReport trackerReport = trackerFacade.getTrackerReport(preferences.getUser(), preferences, publicOnly);

			if(trackerReport.isEnabled())
				trackerReports.add(trackerReport);
		}

		return new TracktacularReport(username, trackerReports);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public void getTrackerExport(User user, OutputStream outputStream) throws IOException {
		logger.info("Exporting data for user [{}]", user.getId());

		ZipOutputStream zip = new ZipOutputStream(outputStream);

		try
		{
			for(Tracker tracker : trackerDaoMap.keySet())
			{
				// pull out all daos that track data for this tracker
				Collection<PersistableDao<AbstractPersistableUser>> daos = trackerDaoMap.get(tracker);

				for(PersistableDao<AbstractPersistableUser> dao : daos)
				{
					List<Property<AbstractPersistableUser, ?>> properties = Property.getStaticProperties(dao.getEntityClass());
					List<AbstractPersistableUser> entities = dao.findBy(dao.findCriteriaBuilder(Results.ALL).
							whereEqualTo(AbstractPersistableUser.USER, user).build());

					if(entities.isEmpty())
						continue;

					exportDataAsCsv(zip, tracker, dao.getEntityClass(), properties, entities);
				}
			}
		}
		finally
		{
			zip.close();
		}
	}

	/**
	 * Exports the data as a csv for a list of entities into a zip file.
	 *
	 * @param zip
	 * @param tracker
	 * @param entityClass
	 * @param properties
	 * @param entities
	 * @throws IOException
	 */
	private void exportDataAsCsv(ZipOutputStream zip, Tracker tracker, Class<?> entityClass,
			List<Property<AbstractPersistableUser, ?>> properties, List<AbstractPersistableUser> entities) throws IOException {

		String fileName = tracker.getCapitalizedName()+ "/" + entityClass.getSimpleName() + ".csv";
		zip.putNextEntry(new ZipEntry(fileName));

		CSVWriter writer = new CSVWriter(new OutputStreamWriter(zip));

		// create property headers
		String[] propertyHeaders = new String[properties.size()];

		for(int i = 0; i < properties.size(); i++)
			propertyHeaders[i] = properties.get(i).getName();

		// write property values
		writer.writeNext(propertyHeaders);

		// create property values
		for(AbstractPersistableUser entity : entities)
		{
			String[] values = new String[properties.size()];

			for(int i = 0; i < properties.size(); i++)
			{
				Object object = Methods.getProperty(entity, properties.get(i).getName());

				if(object instanceof AbstractPersistable)
					values[i] = String.valueOf(((AbstractPersistable)object).getId());
				else
					values[i] = String.valueOf(object);
			}

			writer.writeNext(values);
		}

		writer.flush();
		zip.closeEntry();
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

}