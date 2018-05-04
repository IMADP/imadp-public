package com.tracktacular.service.tracker;

import java.io.FileOutputStream;

import org.junit.Test;

import com.imadp.service.account.credentials.Credentials;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * TracktacularTrackersFacadeTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TracktacularTrackersFacadeTest extends TracktacularServiceTestCase {
	Credentials credentials;
	Preferences preferences;
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();
		user.setUid(accountFacade.getDemoUserUid());
		userService.save(user);

		credentials = new Credentials(user);
		credentials.setEmail("a@b.com");
		credentials.setUsername("username");

		preferences = new Preferences(user);

		for(Tracker tracker : Tracker.values())
		{
			preferences.getTrackers().getTrackerPreferences(tracker).setTrackerEnabled(true);
			preferences.getTrackers().getTrackerPreferences(tracker).setTrackerPublic(true);
		}
	}

	@Test
	public void generateTracktacularReport() {
		trackersFacade.generateTracktacularReport(credentials.getUsername(), preferences, false);
	}

	@Test
	public void getTrackerExport() throws Exception {
		adminFacade.insertTrackerDemoData();
		trackersFacade.getTrackerExport(accountFacade.getDemoUser(), new FileOutputStream(System.getProperty("java.io.tmpdir") + "tmp.zip"));
	}

}