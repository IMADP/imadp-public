package com.tracktacular.service.tracker.task;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.core.cache.Cache;
import com.imadp.core.cache.CreateValue;
import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.dao.criteria.find.FindCriteriaBuilder;
import com.imadp.service.user.User;
import com.imadp.service.user.PersistableUserServiceImpl;
import com.tracktacular.service.tracker.task.Task.TaskPriority;


/**
 * TaskServiceImpl
 *
 * The standard implementation of the TaskService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class TaskServiceImpl extends PersistableUserServiceImpl<Task> implements TaskService {
	private TaskDao taskDao;
	private Cache<User, CompletedTaskStatistics> getTaskReportCompletedCache;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(taskDao);
		Validate.notNull(getTaskReportCompletedCache);
	}

	@Override
	protected com.imadp.dao.PersistableDao<Task> getPersistableDao() {
		return taskDao;
	}

	@Override
	protected void onAfterSave(Task task, boolean initialSave) {
		super.onAfterSave(task, initialSave);

		getTaskReportCompletedCache.remove(task.getUser());
	}

	@Override
	protected void onAfterDelete(Task task) {
		super.onAfterDelete(task);

		getTaskReportCompletedCache.remove(task.getUser());
	}

	@Override
	public List<Task> findByUser(User user, PersistableState state, TaskPriority priority, CriteriaParams<Task> criteriaParams) {
		return findByUser(user, getFindCriteria(user, state, priority, criteriaParams));
	}

	@Override
	public long findCountByUser(User user, PersistableState state, TaskPriority priority) {
		return findCountByUser(user, getFindCriteria(user, state, priority, CriteriaParams.<Task>of(Results.ONE)));
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public CompletedTaskStatistics getTaskReportCompleted(User user) {
		return getTaskReportCompletedCache.getOrPut(user, new CreateValue<User, CompletedTaskStatistics>() {
			@Override
			public CompletedTaskStatistics create(User user) {
				return taskDao.getTaskReportCompleted(user);
			}
		});
	}

	/**
	 * Returns the FindCriteria by user, state, priority, and criteriaParams.
	 *
	 * @param user
	 * @param state
	 * @param priority
	 * @param criteriaParams
	 * @return FindCriteria<Task>
	 */
	private FindCriteria<Task> getFindCriteria(User user, PersistableState state, TaskPriority priority,
			CriteriaParams<Task> criteriaParams) {

		FindCriteriaBuilder<Task> builder = findCriteriaBuilder(criteriaParams);
		builder.whereEqualTo(Task.USER, user);

		if(state != null)
			builder.whereEqualTo(Task.PERSISTABLE_STATE, state);

		if(priority != null)
			builder.whereEqualTo(Task.PRIORITY, priority);

		return builder.build();
	}

	// getters and setters
	public TaskDao getTaskDao() {
		return taskDao;
	}

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	public Cache<User, CompletedTaskStatistics> getGetTaskReportCompletedCache() {
		return getTaskReportCompletedCache;
	}

	public void setGetTaskReportCompletedCache(Cache<User, CompletedTaskStatistics> getTaskReportCompletedCache) {
		this.getTaskReportCompletedCache = getTaskReportCompletedCache;
	}

}