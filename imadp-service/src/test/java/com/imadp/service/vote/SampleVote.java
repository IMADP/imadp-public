package com.imadp.service.vote;

import com.imadp.service.user.User;


/**
 * SampleVote
 *
 * A generic sample vote.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class SampleVote extends AbstractVote<SampleVotable> {

	// constructor
	public SampleVote() {

	}

	// constructor
	public SampleVote(User user, SampleVotable votable, boolean up) {
		super(user, votable, up ? 1 : -1);
	}

	// constructor
	public SampleVote(User user, SampleVotable votable, int rating) {
		super(user, votable, rating);
	}

}