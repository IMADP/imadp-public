package com.imadp.service.vote;

import java.text.DecimalFormat;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.imadp.dao.AbstractPersistable;
import com.imadp.dao.Persistable;

/**
 * Votes
 *
 * An object providing the current count of votes for a given votable object.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public final class Votes<T extends Persistable> extends AbstractPersistable {
    private final T votable;
    private final long voteCount;
    private final long voteTally;
    private final double voteAverage;
    private final boolean votingAllowed;

    // constructor
    public Votes(T votable, long voteCount, long voteTally, boolean votingAllowed) {
    	this.votable = votable;
    	this.voteCount = voteCount;
    	this.voteTally = voteTally;
    	this.votingAllowed = votingAllowed;
    	this.voteAverage = findVoteAverage();
    }

    /**
     * Returns the vote average rounded to an integer value.
     *
     * @return int
     */
    public int getRoundedVoteAverage() {
    	return (int)(voteAverage + .5);
    }

    /**
     * Finds the vote average for the given votes.
     *
     * @return double
     */
    private double findVoteAverage() {
		if(voteCount == 0)
			return 0;

		return Double.valueOf(new DecimalFormat("#.#").format(
				Double.valueOf(voteTally)/Double.valueOf(voteCount)));
	}

    // getters
	public T getVotable() {
		return votable;
	}

	public long getVoteCount() {
		return voteCount;
	}

	public long getVoteTally() {
		return voteTally;
	}

	public double getVoteAverage() {
		return voteAverage;
	}

	public boolean isVotingAllowed() {
		return votingAllowed;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}