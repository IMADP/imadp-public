package com.imadp.web.markup;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * MarkupProcessor
 *
 * A utility to convert a string into a list of markup tags.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class MarkupProcessor {

	public static void main(String[] a) {
		List<String> tags = process("This is a *test* sdf *t* *3* of -strike- delimited *-stuff* thats a [link] here it.");

		for(String t : tags)
			System.out.println("["+t+"]");
	}

	public static List<String> process(String text) {
		char[] charArray = text.toCharArray();
		char thisChar = ' ';
		char lastChar = ' ';
		int textIndex = 0;
		int tagStartIndex = 0;
		int tagEndIndex = 0;
		MarkupTag lastTag = null;
		List<String> tags = Lists.newArrayList();

		for(int i=0; i<charArray.length; i++)
		{
			thisChar = charArray[i];

			for(MarkupTag markupTag : MarkupTag.values())
			{
				if(markupTag.isMatch(lastChar, thisChar))
				{
					if(markupTag != lastTag)
					{
						tagStartIndex = i+1;
						lastTag = markupTag;
					}
					else
					{
						lastTag = null;
						tagEndIndex = i - 1;
						tags.add(text.substring(textIndex, tagStartIndex - 1));
						tags.add(text.substring(tagStartIndex, tagEndIndex));
						textIndex = tagEndIndex + 1;
					}
				}
			}

			lastChar = thisChar;

			if(thisChar == '\n')
				lastTag = null;
		}

		tags.add(text.substring(textIndex));
		return tags;
	}

}
