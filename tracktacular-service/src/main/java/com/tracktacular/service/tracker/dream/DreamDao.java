package com.tracktacular.service.tracker.dream;

import com.imadp.dao.PersistableDao;
import com.imadp.service.user.User;


/**
 * DreamDao
 *
 * An extention of the PersistableDao which provides additional Dream queries.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface DreamDao extends PersistableDao<Dream> {

	/**
     * Returns the DreamTrackerReport for a given user.
     *
     * @param user
     * @return DreamTrackerReport
     */
    public DreamTrackerReport getDreamTrackerReport(User user);

}