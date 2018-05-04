package com.imadp.service.vote;

import com.imadp.dao.AbstractPersistable;


/**
 * SampleVotable	
 * 
 * A sample votable persistable object.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class SampleVotable extends AbstractPersistable {
	private long voteTally;
	private long voteCount;
	
	// constructor
	public SampleVotable() { 
		
	}
		
	// constructor
	public SampleVotable(Long id) { 
		this.id = id;
	}

	// getters and setters
	public long getVoteTally() {
		return voteTally;
	}

	public void setVoteTally(long voteTally) {
		this.voteTally = voteTally;
	}

	public long getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(long voteCount) {
		this.voteCount = voteCount;
	}	
	
}