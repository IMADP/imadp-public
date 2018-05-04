package com.imadp.service.vote;

import com.imadp.core.Property;
import com.imadp.dao.Persistable;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * AbstractVote
 *
 * The base class for vote objects.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractVote<T extends Persistable> extends AbstractPersistableUser {

	// static Properties
	public static final Property<AbstractVote<?>, Integer> RATING = Property.of("rating");
	public static final Property<AbstractVote<?>, Persistable> VOTABLE = Property.of("votable");

	// properties
	protected int rating;
	protected T votable;

	// constructor
	public AbstractVote() {

	}

	// constructor
	public AbstractVote(User user) {
		this(user, null, 0);
	}

	// constructor
	public AbstractVote(User user, T votable) {
		this(user, votable, 0);
	}

	// constructor
	public AbstractVote(User user, T votable, int rating) {
		super(user);
		this.votable = votable;
		this.rating = rating;
	}

	// getters and setters
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setVotable(T votable) {
		this.votable = votable;
	}

	public T getVotable() {
		return votable;
	}

}