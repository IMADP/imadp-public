package com.tracktacular.service.tracker.dream;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.dao.PersistableState;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * DreamDaoImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@Transactional
public class DreamDaoImplTest extends TracktacularServiceTestCase {
	Dream dream;
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		userService.save(user);
	}

	/**
     * Sample Data
     *
     * // deleted data, should be ignored
     * Dream(DELETED,    true,    true,    currentDate)
     * Dream(DELETED,    true,    true,    currentDate)
     * Dream(DELETED,    true,    true,    currentDate)
     *
     * // older than one year, should be ignored
     * Dream(ACTIVE,    true,    true,    currentDate - 2 years)
     * Dream(ACTIVE,    true,    true,    currentDate - 2 years)
     * Dream(ACTIVE,    true,    true,    currentDate - 2 years)
     *
     * // january dreams
     * Dream(ACTIVE,    false,    false,    jan)
     * Dream(ACTIVE,    true,     false,    jan)
     * Dream(ACTIVE,    true,     true,     jan)
     *
     * // march dreams
     * Dream(ACTIVE,    false,   false,     march)
     * Dream(ACTIVE,    true,    false,     march)
     * Dream(ACTIVE,    true,    true,      march)
     * Dream(ACTIVE,    true,    true,      march)
     *
     * // april dreams
     * Dream(ACTIVE,    false,    false,    april)
     *
     * // expected query resuts
     *  month  year  allCount  favoriteCount  lucidCount
     *    1    2012    3             2           1
     *    3    2012    4             3           2
     *    4    2012    1             0           0
     */
    @Test
    public void test_getDreamTrackerReport() {

        // active data, should be ignored
        saveDream(PersistableState.DELETED,  true, true,   new DateTime().withDayOfMonth(new Random().nextInt(10) + 5));
        saveDream(PersistableState.DELETED,  true, true,   new DateTime().withDayOfMonth(new Random().nextInt(10) + 5));
        saveDream(PersistableState.DELETED,  true, true,   new DateTime().withDayOfMonth(new Random().nextInt(10) + 5));

        // older than one year, should be ignored
        saveDream(PersistableState.ACTIVE,   true, true,   new DateTime().minusYears(2).withDayOfMonth(new Random().nextInt(10) + 5));
        saveDream(PersistableState.ACTIVE,   true, true,   new DateTime().minusYears(2).withDayOfMonth(new Random().nextInt(10) + 5));
        saveDream(PersistableState.ACTIVE,   true, true,   new DateTime().minusYears(2).withDayOfMonth(new Random().nextInt(10) + 5));

        // january dreams
        saveDream(PersistableState.ACTIVE,   false, false, new DateTime().withMonthOfYear(1).withDayOfMonth(new Random().nextInt(10) + 5));
        saveDream(PersistableState.ACTIVE,   true, false,  new DateTime().withMonthOfYear(1).withDayOfMonth(new Random().nextInt(10) + 5));
        saveDream(PersistableState.ACTIVE,   true, true,   new DateTime().withMonthOfYear(1).withDayOfMonth(new Random().nextInt(10) + 5));

        // march dreams
        saveDream(PersistableState.ACTIVE,   false, false, new DateTime().withMonthOfYear(3).withDayOfMonth(new Random().nextInt(10) + 5));
        saveDream(PersistableState.ACTIVE,   true, false,  new DateTime().withMonthOfYear(3).withDayOfMonth(new Random().nextInt(10) + 5));
        saveDream(PersistableState.ACTIVE,   true, true,   new DateTime().withMonthOfYear(3).withDayOfMonth(new Random().nextInt(10) + 5));
        saveDream(PersistableState.ACTIVE,   true, true,   new DateTime().withMonthOfYear(3).withDayOfMonth(new Random().nextInt(10) + 5));

        // april dreams
        saveDream(PersistableState.ACTIVE,   false, false, new DateTime().withMonthOfYear(4).withDayOfMonth(new Random().nextInt(10) + 5));

        DreamTrackerReport report = dream_dreamDao.getDreamTrackerReport(user);
        DateTime currentDate = new DateTime();

        assertEquals(11, report.getDreamCount());

        assertEquals(3, report.getDreamsMonthList().size());
        assertEquals(1, new DateTime(report.getDreamsMonthList().get(0)).getMonthOfYear());
        assertEquals(currentDate.getYear(), new DateTime(report.getDreamsMonthList().get(0)).getYear());
        assertEquals(3, new DateTime(report.getDreamsMonthList().get(1)).getMonthOfYear());
        assertEquals(currentDate.getYear(), new DateTime(report.getDreamsMonthList().get(1)).getYear());
        assertEquals(4, new DateTime(report.getDreamsMonthList().get(2)).getMonthOfYear());
        assertEquals(currentDate.getYear(), new DateTime(report.getDreamsMonthList().get(2)).getYear());

        assertEquals(3, report.getAllDreamsByMonthList().size());
        assertEquals(Integer.valueOf(3), report.getAllDreamsByMonthList().get(0));
        assertEquals(Integer.valueOf(4), report.getAllDreamsByMonthList().get(1));
        assertEquals(Integer.valueOf(1), report.getAllDreamsByMonthList().get(2));

        assertEquals(3, report.getFavoriteDreamsByMonthList().size());
        assertEquals(Integer.valueOf(2), report.getFavoriteDreamsByMonthList().get(0));
        assertEquals(Integer.valueOf(3), report.getFavoriteDreamsByMonthList().get(1));
        assertEquals(Integer.valueOf(0), report.getFavoriteDreamsByMonthList().get(2));

        assertEquals(3, report.getLucidDreamsByMonthList().size());
        assertEquals(Integer.valueOf(1), report.getLucidDreamsByMonthList().get(0));
        assertEquals(Integer.valueOf(2), report.getLucidDreamsByMonthList().get(1));
        assertEquals(Integer.valueOf(0), report.getLucidDreamsByMonthList().get(2));
    }

	/**
     * Saves a dream with the given parameters.
     *
     * @param state
     * @param favorite
     * @param lucid
     * @param date
     */
    private void saveDream(PersistableState state, boolean favorite, boolean lucid, DateTime date) {
        Dream dream = new Dream(user);
        dream.setTitle("title");
        dream.setContent("content");
        dream.setPersistableState(state);
        dream.setFavorite(favorite);
        dream.setLucid(lucid);
        dream.setDate(date);

        dream_dreamService.save(dream);
    }

}