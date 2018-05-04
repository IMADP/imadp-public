package com.tracktacular.web.page.tracker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;

import au.com.bytecode.opencsv.CSVReader;

import com.google.common.collect.Maps;
import com.imadp.core.Property;
import com.imadp.core.reflection.Constructors;
import com.imadp.core.reflection.Methods;
import com.imadp.core.validation.ValidationResult;
import com.imadp.dao.AbstractPersistable;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;
import com.imadp.web.stripes.link.Link;
import com.imadp.web.stripes.paging.PageProvider;
import com.rits.cloning.Cloner;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TrackerFacade;
import com.tracktacular.service.tracker.TrackerPreferences;
import com.tracktacular.service.tracker.TrackerReport;
import com.tracktacular.service.tracker.TracktacularTrackersFacade;
import com.tracktacular.web.ImportAction;
import com.tracktacular.web.ImportedItem;
import com.tracktacular.web.TracktacularActionBean;
import com.tracktacular.web.page.PageNotFoundActionBean;
import com.tracktacular.web.page.account.SubscribeActionBean;


/**
 * TrackerActionBean
 *
 * The parent action bean for all trackers.
 *
 * All trackers should use the method getTrackerUser() when interacting with a user object.
 *
 * The tracker bean provides 2 useful methods for trackers:
 *  getTrackerFacade()      - returns the specific instance of the tracker facade.
 *  getTrackerPreferences() - returns the specific instance of the tracker preferences for the tracker user.
 *
 * Another important method is the convertObject method, which will automatically handle type conversion from
 * an encoded id to a persistable object specific to the user in session.
 *
 * @param <T>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
@HttpCache(allow=false)
public abstract class TrackerActionBean<T extends TrackerFacade, V extends TrackerPreferences>
	extends TracktacularActionBean {

	@SpringBean
	private TracktacularTrackersFacade trackersFacade;

	// general item page provider for paginated tracker pages
	private ItemsPageProvider itemsPageProvider = getPageProvider();

	// file bean for importing data
	private FileBean importedData;

	// action for importing data
	private ImportAction importAction;

	/**
	 * Handles a generic index resolution by forwarding to the default jsp view.
	 * Additionally provides security by first redirecting to a page not found if no public user exists.
	 * And finally, enforces privacy by redirecting if a user's page is not public.
	 *
	 * @return Resolution
	 */
	@DefaultHandler
	public Resolution index() {

		// if no tracker user was found, redirect to a page not found
		if(getTrackerUser() == null)
			return new ForwardResolution(PageNotFoundActionBean.class);

		// if we are not in public mode, check to make sure the account is not expired
		if(!isPublicMode() && getUserSubscription().isExpired())
		{
			getContext().addInfoMessage("tracktacular.accountExpired");
			return new RedirectResolution(SubscribeActionBean.class);
		}

		// if we are in public mode, check permissions and notify the users
		if(isPublicMode())
		{
			// if the user has not made this tracker public, redirect to the about page with a warning
			if(!isTrackerPublic())
			{
				getContext().addWarnMessage("tracktacular.trackerNotPublic", getTrackerTitle());

				// if this isnt the about page, redirect to it
				if(!isAboutPage())
					return new RedirectResolution(getAboutActionBean()).
						addParameter(TracktacularActionBean.TRACKER_USER_USERNAME_PARAM, getTrackerUserUsername());
			}

			// redirect away from deleted and preferences pages in public mode
			if(getTrackerPage() != null && ("DELETED".equals(getTrackerPage().name()) || "PREFERENCES".equals(getTrackerPage().name())))
				return new RedirectResolution(getDefaultActionBean()).
						addParameter(TracktacularActionBean.TRACKER_USER_USERNAME_PARAM, getTrackerUserUsername());
		}

		return new ForwardResolution(getTrackerViewLocation());
	}

	@Override
	public boolean isDisplayTrackerUserNotification() {
		return isAboutPage() ? false : isPublicMode();
	}

	/**
	 * Returns the tracker for the current page.
	 * This is used for various display operations such as the content header.
	 *
	 * @return Tracker
	 */
	@Override
	public abstract Tracker getTracker();

	/**
	 * Returns the enumerated values of the trackers pages.
	 * This should be overridden in the tracker's abstract action bean.
	 *
	 * @return TrackerPage[]
	 */
	public final TrackerPage[] getTrackerPages() {
		return getTrackerPages(getTracker());
	}

	/**
	 * Returns the specific tracker report for this tracker.
	 *
	 * @return TrackerReport
	 */
	public final TrackerReport getTrackerReport() {
		return getTrackerFacade().getTrackerReport(getTrackerUser(), getTrackerUserPreferences(), isPublicMode());
	}

	/**
	 * Returns the specific facade object for this tracker.
	 *
	 * @return T
	 */
	public final T getTrackerFacade() {
		return trackersFacade.getTrackerFacade(getTracker());
	}

	/**
	 * Returns the specific preferences object for this tracker.
	 * Subclasses should only interact with this preference object when displaying tracker data.
	 *
	 * @return V
	 */
	public final V getTrackerPreferences() {
		return getTrackerUserPreferences().getTrackers().getTrackerPreferences(getTracker());
	}

	@Override
	protected Class<? extends TracktacularActionBean> getUserToggleClass() {
		TrackerPage trackerPage = getTrackerPage();

		if(trackerPage == null)
			throw new UnsupportedOperationException("No userToggleClass could be found for the actionBean ["+getClassName()+"] ");

		// don't toggle to the deleted or preferences pages since they are not visible in public mode
		if("DELETED".equals(trackerPage.name()) || "PREFERENCES".equals(trackerPage.name()))
			return getDefaultActionBean();

		return trackerPage.getActionBean();
	}

	@Override
	public final List<Link> getContentHeaderLinks() {
		TrackerPage[] trackerPages = getTrackerPages();

		List<Link> trackerLinks = new ArrayList<>(trackerPages.length);

		for(TrackerPage trackerPage : trackerPages)
		{
			// if we are in public mode do not add deleted or preference pages
			if(isPublicMode() && ("DELETED".equals(trackerPage.name()) || "PREFERENCES".equals(trackerPage.name())))
				continue;

			Link link = new Link(trackerPage.getActionBean());
			link.addParameter(TRACKER_USER_USERNAME_PARAM, getTrackerUserUsername());
			link.setLabel(getLocale(), trackerPage.getTrackerPageKey());

			// if we are in public mode and the tracker is not public, disable all links except the about page
			if(isPublicMode() && !isTrackerPublic() && !"ABOUT".equals(trackerPage.name()))
				link.setDisabled(true);

			trackerLinks.add(link);
		}

		return trackerLinks;
	}

	/**
	 * A generic conversion method which will automatically convert and clone a persistable object
	 * from the given input (which should match the encoded id of the object we are retrieving).
	 *
	 * This method assumes the convention that a tracker facade has a method:
	 *  get{objectClass.simpleName}(User user, String uid) for the given objectClass.
	 *
	 * For example, a Task object being converted would invoke this method on the TaskTrackerFacade:
	 *  getTask(User user, String uid)
	 *
	 * @param <U>
	 * @param input
	 * @param objectClass
	 * @return U
	 */
	@SuppressWarnings("unchecked")
	public <U> U convertObject(String input, Class<U> objectClass) {
		try
		{
			T trackerFacade = getTrackerFacade();
			Method method = trackerFacade.getClass().getMethod("get" + objectClass.getSimpleName(), User.class, String.class);
			return (U) new Cloner().deepClone(method.invoke(trackerFacade, getTrackerUser(), input));
		}
		catch(Exception exception)
		{
			throw new RuntimeException(exception);
		}
	}

	/**
	 * Converts a comma separated list of inputs into an object list.
	 *
	 * @param <U>
	 * @param inputList
	 * @param objectClass
	 * @return List<U>
	 */
	public <U> List<U> convertObjectList(String inputList, Class<U> objectClass) {
		if(StringUtils.isBlank(inputList))
			return Collections.emptyList();

		List<U> objects = new ArrayList<>();

		for(String input : inputList.split(","))
			objects.add(convertObject(input.trim(), objectClass));

		return objects;
	}

	/**
	 * Returns the about actionBean for the tracker.
	 *
	 * @return Class<? extends TracktacularActionBean>
	 */
	public final Class<? extends TracktacularActionBean> getAboutActionBean() {
		for(TrackerPage trackerPage : getTrackerPages())
			if("ABOUT".equals(trackerPage.name()))
				return trackerPage.getActionBean();

		throw new IllegalArgumentException("No ABOUT tracker page was defined for tracker ["+getTracker()+"]");
	}

	/**
	 * Returns the report actionBean for the tracker.
	 *
	 * @return Class<? extends TracktacularActionBean>
	 */
	public final Class<? extends TracktacularActionBean> getReportActionBean() {
		for(TrackerPage trackerPage : getTrackerPages())
			if("REPORT".equals(trackerPage.name()))
				return trackerPage.getActionBean();

		throw new IllegalArgumentException("No REPORT tracker page was defined for tracker ["+getTracker()+"]");
	}

	/**
	 * Returns the preferences actionBean for the tracker.
	 *
	 * @return Class<? extends TracktacularActionBean>
	 */
	public final Class<? extends TracktacularActionBean> getPreferencesActionBean() {
		for(TrackerPage trackerPage : getTrackerPages())
			if("PREFERENCES".equals(trackerPage.name()))
				return trackerPage.getActionBean();

		throw new IllegalArgumentException("No PREFERENCES tracker page was defined for tracker ["+getTracker()+"]");
	}

	/**
	 * Returns the default actionBean for the tracker.
	 *
	 * @return Class<? extends TracktacularActionBean>
	 */
	public final Class<? extends TracktacularActionBean> getDefaultActionBean() {
		return getTrackerPages()[0].getDefault().getActionBean();
	}

	/**
	 * Returns the view location for the current tracker page.
	 * In the case of the default page, no templating is used, so we go directly to the base view name.
	 * In the case of the reports page, we go to the general trackerReportsPage.
	 * Finally for all other cases, we rely on the templating provided by the general trackerPage.
	 *
	 * @return String
	 */
	private String getTrackerViewLocation() {
		if(isAboutPage())
			return "/WEB-INF/jsp/page.tracker." + getTracker().getName() + "/" + getViewName() + ".jsp";

		if(isReportPage())
			return "/WEB-INF/jsp/page.tracker/trackerReportPage.jsp";

		return "/WEB-INF/jsp/page.tracker/trackerPage.jsp";
	}

	/**
	 * Returns true if the tracker is public for the current tracker user, false otherwise.
	 *
	 * @return boolean
	 */
	public final boolean isTrackerPublic() {
		return  getTrackerUserPreferences().getTrackers().getTrackerPreferences(getTracker()).isTrackerPublic();
	}

	/**
	 * Returns true if this is the default page, false otherwise.
	 *
	 * @return boolean
	 */
	public final boolean isAboutPage() {
		return getAboutActionBean().equals(getClass());
	}

	/**
	 * Returns true if this is the report page, false otherwise.
	 *
	 * @return boolean
	 */
	public final boolean isReportPage() {
		return getReportActionBean().equals(getClass());
	}

	/**
	 * Returns true if this is the preferences page, false otherwise.
	 *
	 * @return boolean
	 */
	public final boolean isPreferencesPage() {
		return getPreferencesActionBean().equals(getClass());
	}

	/**
	 * Returns true if this is the default page, false otherwise.
	 *
	 * @return boolean
	 */
	public final boolean isDefaultPage() {
		return getDefaultActionBean().equals(getClass());
	}

	/**
	 * Returns the title of the tracker.
	 *
	 * @return String
	 */
	public final String getTrackerTitle() {
		return new LocalizableMessage(getTracker().toString()).getMessage(getLocale());
	}

	/**
	 * Returns the title of the tracker page determined by the trackerPage, if it exists.
	 * If a trackerPage does not exist, an exception will be thrown to signal subclasses to provide a title.
	 *
	 * @return String
	 */
	public String getTrackerPageTitle() {
		TrackerPage trackerPage = getTrackerPage();

		if(getTrackerPage() == null)
			throw new IllegalArgumentException(
					"Tracker page title could not be determined, please override getTrackerPageTitle()");

		String trackerPageMessage = trackerPage.getClass().getSimpleName() + "." + trackerPage.toString();
		return new LocalizableMessage(trackerPageMessage).getMessage(getLocale());
	}

	/**
	 * Returns the tracker content body template for the given tracker, or null if this is the default page.
	 * Example: Task Tracker AboutActionBean > <code>null</code>
	 * Example: Task Tracker TasksActionBean > task_tasksContent
	 *
	 * @return String
	 */
	public final String getTrackerContentBodyTemplate() {
		if(isAboutPage())
			return null;

		return getTracker().getName() + "_" + getViewName() + "Content";
	}

	/**
	 * Returns the enumerated value of the current tracker page.
	 * This is used for various display operations such as standardizing the content header through the tracker,
	 * and identifying which tracker link is currently active in the tracker's navigation.
	 *
	 * @return TrackerPage
	 */
	public final TrackerPage getTrackerPage() {
		for(TrackerPage trackerPage : getTrackerPages())
			if(trackerPage.getActionBean().equals(getClass()))
				return trackerPage;

		return null;
	}

	/**
	 * Imports data for a tracker.
	 *
	 * @return Resolution
	 */
	public Resolution importData() {
		return importAction.act(this);
	}

	/**
	 * Imports data for a tracker.
	 *
	 * @return Resolution
	 */
	public Resolution importPreview() {
		try
		{
			String [] nextLine;
			boolean first = true;
			FileBean bean = getImportedData();
			CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(bean.getInputStream())));
			Map<Property<?, ?>, Integer> headerMap = Maps.newHashMap();
			getImportedItems().clear();

			// loop through each item
			while ((nextLine = reader.readNext()) != null)
			{
				if(first)
				{
					// create the header map
					for(int i = 0; i<nextLine.length; i++)
						for(Property<?, ?> property : getImportProperties())
							if(property.getName().equalsIgnoreCase(StringUtils.trim(nextLine[i])))
								headerMap.put(property, i);

					first = false;
					continue;
				}

				// create the items
				AbstractPersistableUser item = Constructors.newInstance(getImportClass());
				item.setUser(getUser());

				for(Property<?, ?> property : headerMap.keySet())
				{
					String nextValue = StringUtils.trim(nextLine[headerMap.get(property)]);

					if(StringUtils.isBlank(nextValue) || "null".equalsIgnoreCase(nextValue))
						continue;

					Class<?> type = BeanUtils.getPropertyDescriptor(getImportClass(), property.getName()).getPropertyType();
					Object value = parseImportedValue(nextValue, type);

					if(value != null)
						Methods.setProperty(item, property.getName(), value);
				}

				ValidationResult validationResult = getImportValidationResult(item);
				getImportedItems().add(new ImportedItem<>(item, validationResult, getLocale()));
			}

			bean.delete();
			getContext().addInfoMessage("tracktacular.importData.preview");
		}
		catch(Exception exception)
		{
			getContext().addErrorMessage("tracktacular.importData.error");
			logger.warn("Error importing external data", exception);
		}

		return new RedirectResolution(getClass()).addParameter(TRACKER_USER_USERNAME_PARAM, getTrackerUserUsername());
	}

	/**
	 * Attempts to parse the imported value into the given class type, or returns null if unable to do so.
	 *
	 * @param nextValue
	 * @param type
	 * @return Object
	 */
	private Object parseImportedValue(String nextValue, Class<?> type) {
		try
		{
			if(String.class.equals(type))
				return nextValue;

			if(Integer.class.equals(type))
				return Integer.parseInt(nextValue);

			if(Double.class.equals(type))
				return Double.parseDouble(nextValue);

			if(DateTime.class.equals(type))
			{
				if(nextValue.length() > AbstractPersistable.DATE_PATTERN.length())
					nextValue = nextValue.substring(0, AbstractPersistable.DATE_PATTERN.length() - 1);

				return AbstractPersistable.DATE_FORMATTER.parseDateTime(nextValue);
			}
		}
		catch(Exception exception)
		{
			// ignore and return null
		}

		return null;
	}

	/**
	 * Returns the validation result for an imported item.
	 *
	 * @param item
	 * @return ValidationResult
	 */
	protected ValidationResult getImportValidationResult(AbstractPersistableUser item) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the class for importing data.
	 *
	 * @return Class<? extends AbstractPersistableUser>
	 */
	public Class<? extends AbstractPersistableUser> getImportClass() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns a list of import properties.
	 *
	 * @return Property<?,?>[]
	 */
	public Property<?,?>[] getImportProperties() {
		return new Property[] {};
	}

	/**
	 * Returns the page provider, or null if no items per page were specified from subclasses.
	 *
	 * @return ItemsPageProvider
	 */
	private ItemsPageProvider getPageProvider() {
		return getItemsPerPage() == 0 ? null : new ItemsPageProvider();
	}

	/**
	 * Returns the items per page to display for paginated tracker pages.
	 *
	 * @return int
	 */
	protected int getItemsPerPage() {
		return 0;
	}

	/**
	 * Returns the item count to display for paginated tracker pages.
	 *
	 * @param user
	 * @return long
	 */
	protected long findItemCount(User user) {
		return 0;
	}

	/**
	 * Returns the items to display for paginated tracker pages.
	 *
	 * @param results
	 * @param user
	 * @return List<?>
	 */
	protected List<?> findItems(Results results, User user) {
		return null;
	}

	/**
	 * Adds parameters to the link created for paginated tracker pages.
	 *
	 * @param link
	 */
	protected void addLinkParameters(Link link) {

	}

	// getters and setters
	public ImportAction getImportAction() {
		return importAction;
	}

	public void setImportAction(ImportAction importAction) {
		this.importAction = importAction;
	}

	public ItemsPageProvider getItemsPageProvider() {
		return itemsPageProvider;
	}

	public FileBean getImportedData() {
		return importedData;
	}

	public void setImportedData(FileBean importedData) {
		this.importedData = importedData;
	}

	public List<ImportedItem<?>> getImportedItems() {
		return getContext().getSession().getImportedItems();
	}

	/**
	 * ItemsPageProvider
	 *
	 * PageProvider for tracker items.
	 *
	 * @version 1.0
	 * @author Anthony DePalma
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	private class ItemsPageProvider extends PageProvider {
		private static final String PAGE_NUMBER_PARAM = "itemsPageProvider.pageNumber";

		// constructor
		public ItemsPageProvider() {
			super(getBeanClass(), getItemsPerPage());
		}

		@Override
		protected long findItemCount() {
			return TrackerActionBean.this.findItemCount(getTrackerUser());
		}

		@Override
		protected List findItems(Results results) {
			return TrackerActionBean.this.findItems(results, getTrackerUser());
		}

		@Override
		protected Link createPageLink(Class linkClass, long pageNumber) {
			Link link = new Link(linkClass).
					addParameter(PAGE_NUMBER_PARAM, pageNumber).
					addParameter(TRACKER_USER_USERNAME_PARAM, getTrackerUserUsername());

			 TrackerActionBean.this.addLinkParameters(link);
			 return link;
		}

	}

}