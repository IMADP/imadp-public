package com.tracktacular.service.tracker.task;

import com.imadp.dao.PersistableDao;
import com.imadp.service.user.User;


/**
 * ITaskDao
 * 
 * An extention of the PersistableDao which provides additional Task queries.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public interface TaskDao extends PersistableDao<Task> {

	/**
     * Returns the TaskReportCompleted for a given user.
     * 
     * @param user
     * @return TaskReportCompleted
     */
    public CompletedTaskStatistics getTaskReportCompleted(User user);

}