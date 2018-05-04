package com.imadp.web.markup;


/**
 * MarkupTag
 *
 * Enumerated value of markup tags.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public enum MarkupTag {
	BOLD('*', '*'),
	ITALICS('~', '~'),
	UNDERLINE('_', '_'),
	STRIKE('-', '-'),
	LINK('[', ']');

	// properties
	private char start;
	private char end;

	// constructor
	private MarkupTag(char start, char end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * Returns true if this character is a match for the tag.
	 *
	 * @param lastChar
	 * @param thisChar
	 * @return boolean
	 */
	public boolean isMatch(char lastChar, char thisChar) {
		return (Character.isWhitespace(lastChar) && thisChar == start) || (lastChar == end && Character.isWhitespace(thisChar));
	}

}