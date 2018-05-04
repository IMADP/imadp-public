package com.imadp.dao;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import com.imadp.core.AbstractSerializable;
import com.imadp.core.Property;
import com.imadp.core.encryption.Base62;

/**
 * AbstractPersistable
 *
 * An abstract class providing the implementation of the Persistable interface.
 * All persistable objects should extend this class, or any class subclassing this one.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractPersistable extends AbstractSerializable implements Persistable {

	// static Properties
	public static final Property<AbstractPersistable, Long> ID = Property.of("id");
	public static final Property<AbstractPersistable, String> UID = Property.of("uid");
	public static final Property<AbstractPersistable, Integer> VERSION = Property.of("version");
	public static final Property<AbstractPersistable, Long> TIME_CREATED = Property.of("timeCreated");
	public static final Property<AbstractPersistable, Long> TIME_MODIFIED = Property.of("timeModified");
	public static final Property<AbstractPersistable, PersistableState> PERSISTABLE_STATE = Property.of("persistableState");
	public static final Property<AbstractPersistable, DateTime> PERSISTABLE_STATE_DATE = Property.of("persistableStateDate");

	/*
	 * The id represents the surrogate key that uniquely identifies this object.
	 *
	 */
	protected Long id;

	/*
	 * The version number represents the current persistent version of this object.
	 * It is initially set to 1, because it is only incremented on updates rather than saves.
	 *
	 */
	protected int version = 1;

	/*
	 * The uid represents a globally unique identifier for this object.
	 * It is important that this identifier is created immediately to retain immutability.
	 *
	 */
	protected String uid = newUid();

	/*
	 * The timeCreated captures when an object was stored for the first time.
	 * The timeModified captures when the object was last modified and stored.
	 *
	 */
	protected Long timeCreated;
	protected Long timeModified;

	/*
	 * The persistable state represents the state of the persistable object.
	 *
	 */
	protected PersistableState persistableState;

	/*
	 * The persistableStateDate holds the date of the latest persistableState.
	 *
	 */
	protected DateTime persistableStateDate;

	// constructor
	public AbstractPersistable() {

	}

	// constructor
	public AbstractPersistable(PersistableState persistableState) {
		setPersistableState(persistableState);
	}

	@Override
	public final boolean isUnsaved() {
		return id == null;
	}

	/**
	 * Returns a DateTime instance of the timeCreated.
	 *
	 * @return DateTime
	 */
	public final DateTime getTimeCreatedDate() {
		return new DateTime(timeCreated);
	}

	/**
	 * Returns a DateTime instance of the timeModified.
	 *
	 * @return DateTime
	 */
	public final DateTime getTimeModifiedDate() {
		return new DateTime(timeModified);
	}

	/**
	 * Returns true if the object was created today, false otherwise.
	 *
	 * @return boolean
	 */
	public final boolean isCreatedToday() {
		return new LocalDate().isEqual(new LocalDate(timeCreated));
	}

	/**
	 * Returns true if the object was last modified today, false otherwise.
	 *
	 * @return boolean
	 */
	public final boolean isModifiedToday() {
		return new LocalDate().isEqual(new LocalDate(timeModified));
	}

	/**
	 * Returns true if the object is in a pending persistable state.
	 *
	 * @return boolean
	 */
	public final boolean isPendingState() {
		return PersistableState.PENDING == persistableState;
	}

	/**
	 * Returns the pending date.
	 *
	 * @return DateTime
	 */
	public DateTime getPendingDate() {
		return isPendingState() ? persistableStateDate : null;
	}

	/**
	 * Returns true if the object is in an active persistable state.
	 *
	 * @return boolean
	 */
	public final boolean isActiveState() {
		return PersistableState.ACTIVE == persistableState;
	}

	/**
	 * Returns the active date.
	 *
	 * @return DateTime
	 */
	public DateTime getActiveDate() {
		return isActiveState() ? persistableStateDate : null;
	}

	/**
	 * Returns true if the object is in an archived persistable state.
	 *
	 * @return boolean
	 */
	public final boolean isArchivedState() {
		return PersistableState.ARCHIVED == persistableState;
	}

	/**
	 * Returns the archived date.
	 *
	 * @return DateTime
	 */
	public DateTime getArchivedDate() {
		return isArchivedState() ? persistableStateDate : null;
	}

	/**
	 * Returns true if the object is in a deleted persistable state.
	 *
	 * @return boolean
	 */
	public final boolean isDeletedState() {
		return PersistableState.DELETED == persistableState;
	}

	/**
	 * Returns the deleted date.
	 *
	 * @return DateTime
	 */
	public DateTime getDeletedDate() {
		return isDeletedState() ? persistableStateDate : null;
	}

	// getters and setters

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getEid() {
		return Base62.encode(id);
	}

	@Override
	public String getUid() {
		return uid;
	}

	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public Long getTimeCreated() {
		return timeCreated;
	}

	@Override
	public Long getTimeModified() {
		return timeModified;
	}

	@Override
	public void setTimeModified(Long timeModified) {
		this.timeModified = timeModified;
	}

	@Override
	public void setTimeCreated(Long timeCreated) {
		this.timeCreated = timeCreated;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public DateTime getPersistableStateDate() {
		return persistableStateDate;
	}

	public void setPersistableStateDate(DateTime persistableStateDate) {
		this.persistableStateDate = persistableStateDate;
	}

	@Override
	public PersistableState getPersistableState() {
		return persistableState;
	}

	public void setPersistableState(PersistableState persistableState) {
		this.persistableState = persistableState;
		this.persistableStateDate = new DateTime();
		onPersistableStateChange(persistableState);
	}

	/**
	 * Provides callback hooks allowing objects to make additional changes on persistable state changes.
	 *
	 * @param persistableState
	 */
	private void onPersistableStateChange(PersistableState persistableState) {
		if(persistableState != null)
			switch(persistableState)
			{
				case PENDING:  onPendingStateChange();  break;
				case ACTIVE:   onActiveStateChange();   break;
				case ARCHIVED: onArchivedStateChange(); break;
				case DELETED:  onDeletedStateChange();  break;
			}
	}

	/**
	 * Hook into when a object changes into a pending state.
	 *
	 */
	protected void onPendingStateChange() {

	}

	/**
	 * Hook into when a object changes into a active state.
	 *
	 */
	protected void onActiveStateChange() {

	}

	/**
	 * Hook into when a object changes into an archived state.
	 *
	 */
	protected void onArchivedStateChange() {

	}

	/**
	 * Hook into when a object changes into a deleted state.
	 *
	 */
	protected void onDeletedStateChange() {

	}

	/**
     * Constructs a new url safe uid.
     *
     * @return String
     */
    private static String newUid() {
    	UUID uuid = UUID.randomUUID();
    	ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    	bb.putLong(uuid.getMostSignificantBits());
    	bb.putLong(uuid.getLeastSignificantBits());
    	return Base64.encodeBase64URLSafeString(bb.array());
    }

	/**
	 * This implementation relies exclusively on the uid to generate the hashcode for this object.
	 * Since the uid is generated at object creation, as well as persisted, we are guaranteed to
	 * have an immutable and unique key for any object.
	 *
	 * @return int
	 */
	@Override
	public final int hashCode() {
		return 31 * 1 + ((uid == null) ? 0 : uid.hashCode());
	}

	/**
	 * Like hashCode, this implementation relies exclusively on the uid to determine object
	 * equality. This guarantees an immutable and unique property to base equality on.
	 *
	 * @param obj
	 * @return boolean
	 */
	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractPersistable other = (AbstractPersistable) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

}