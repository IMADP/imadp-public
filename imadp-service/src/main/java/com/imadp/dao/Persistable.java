package com.imadp.dao;

import java.io.Serializable;

import org.joda.time.DateTime;

/**
 * IPersistable
 *
 * Describes the properties of any persistable object in a repository.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface Persistable extends Serializable {

	/**
	 * Returns the id that identifies this object in a repository,
	 * or <code>null</code> if the object is unsaved and does not yet have one.
	 *
	 * @return Long
	 */
	public Long getId();

	/**
	 * Returns the Base62 encoded id that identifies this object in a repository,
	 * or <code>null</code> if the object is unsaved and does not yet have one.
	 *
	 * @return String
	 */
	public String getEid();

	/**
	 * Returns the uid that represents a globally unique identifier for this object.
	 *
	 * @return String
	 */
	public String getUid();

	/**
	 * Returns the int representing the current version of this object.
	 *
	 * @return int
	 */
	public int getVersion();

	/**
	 * Returns the time that this object was first persisted,
	 * or <code>null</code> if the property is not used.
	 *
	 * @return Long
	 */
	public Long getTimeCreated();

	/**
	 * Returns the time that this object was last modified and persisted,
	 * or <code>null</code> if the property is not used.
	 *
	 * @return Long
	 */
	public Long getTimeModified();

	/**
	 * Sets the time the object was created.
	 *
	 * @param timeCreated
	 */
	public void setTimeCreated(Long timeCreated);

	/**
	 * Sets the last time the object was modified.
	 *
	 * @param timeModified
	 */
	public void setTimeModified(Long timeModified);

	/**
	 * Returns the PersistableState of the object.
	 *
	 * @return PersistableState
	 */
	public PersistableState getPersistableState();

	/**
	 * Returns the date of the last PersistableState;
	 *
	 * @return DateTime
	 */
	public DateTime getPersistableStateDate();

	/**
	 * Returns true if the object is currently unsaved, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isUnsaved();

}
