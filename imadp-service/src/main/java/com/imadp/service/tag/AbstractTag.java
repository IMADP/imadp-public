package com.imadp.service.tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.imadp.core.Property;
import com.imadp.dao.Persistable;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * AbstractTag
 *
 * The base class for tag objects.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractTag<T extends Persistable> extends AbstractPersistableUser {

	/**
	 * Returns a collection of tags as a comma separated list.
	 *
	 * @param tagNames
	 * @return String
	 */
	public static String getTagNamesAsString(Set<? extends AbstractTag<?>> tagNames) {
		if(CollectionUtils.isEmpty(tagNames))
			return null;

		StringBuilder bs = new StringBuilder();

		for(Iterator<? extends AbstractTag<?>> it = tagNames.iterator(); it.hasNext();)
		{
			bs.append(it.next().getName());

			if(it.hasNext())
				bs.append(", ");
		}

		return bs.toString();
	}

	/**
	 * Returns a comma separated list of tags as a set.
	 *
	 * @param tagNamesAsString
	 * @return Set<String>
	 */
	public static Set<String> getTagsNamesAsSet(String tagNamesAsString) {
		if(StringUtils.isBlank(tagNamesAsString))
			return Collections.emptySet();

		Set<String> tags = new HashSet<>();

		for(String name : StringUtils.split(tagNamesAsString, ','))
			if(!StringUtils.isBlank(name))
				tags.add(name.trim());

		return tags;
	}

	// static Properties
	public static final Property<AbstractTag<?>, String> NAME = Property.of("name");
	public static final Property<AbstractTag<?>, String> NAME_SLUG = Property.of("nameSlug");
	public static final Property<AbstractTag<?>, Persistable> TAGGABLE = Property.of("taggable");

	// properties
	protected String name;
	protected String nameSlug;
	protected T taggable;

	// constructor
	public AbstractTag() {

	}

	// constructor
	public AbstractTag(User user) {
		this(user, null, null);
	}

	// constructor
	public AbstractTag(User user, T taggable) {
		this(user, taggable, null);
	}

	// constructor
	public AbstractTag(User user, T taggable, String name) {
		this(user, taggable, name, null);
	}

	// constructor
	public AbstractTag(User user, T taggable, String name, PersistableState persistableState) {
		super(user);
		this.name = name;
		this.taggable = taggable;
		setPersistableState(persistableState);
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameSlug() {
		return toSlug(getName());
	}

	@SuppressWarnings("unused")
	private void setNameSlug(String nameSlug) {

	}

	public T getTaggable() {
		return taggable;
	}

	public void setTaggable(T taggable) {
		this.taggable = taggable;
	}

}