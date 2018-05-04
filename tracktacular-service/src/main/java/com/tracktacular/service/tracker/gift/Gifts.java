package com.tracktacular.service.tracker.gift;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.WordUtils;

import com.google.common.base.Objects;
import com.imadp.core.AbstractSerializable;
import com.imadp.service.category.ItemCategory;


/**
 * Gifts
 *
 * A collection of a gifts.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Gifts extends AbstractSerializable {

	// gift count
	private final int giftCount;

	// list of gifts by category
	private final Collection<GiftCategory> giftCategories;

	// list of gifts in chart format
	private final Collection<GiftCategory> chartGiftCategories;

	// constructor
	public Gifts(List<Gift> allGifts, boolean received, String sortProperty) {
		List<Gift> gifts = new ArrayList<>();

		for(Gift gift : allGifts)
			if(received && gift.isReceived())
				gifts.add(gift);
			else if(!received && !gift.isReceived())
				gifts.add(gift);

		Map<String, GiftCategory> occasionCategoryMap = new LinkedHashMap<>();

    	for(Gift gift : allGifts)
			if(!received && !gift.isReceived())
			{
				GiftCategory occasionCategory = getGiftCategory(occasionCategoryMap, gift.getOccasion());
				occasionCategory.addItem(gift);

				ItemCategory<Gift> receiverCategory = getReceiverCategory(occasionCategory, gift.getReceiver());
				receiverCategory.addItem(gift);
			}
			else if(received && gift.isReceived())
			{
				GiftCategory occasionCategory = getGiftCategory(occasionCategoryMap, gift.getOccasion());
				occasionCategory.addItem(gift);

				ItemCategory<Gift> senderCategory = getSenderCategory(occasionCategory, gift.getSender());
				senderCategory.addItem(gift);
			}

    	this.chartGiftCategories = occasionCategoryMap.values();
		this.giftCount = gifts.size();
		this.giftCategories = getGiftCategories(gifts, sortProperty);
	}

	/**
	 * Categorizes and returns a list of GiftCategories based on the sortProperty.
	 *
	 * @param gifts
	 * @param sortProperty
	 * @return Collection<GiftCategory>
	 */
	private Collection<GiftCategory> getGiftCategories(List<Gift> gifts, String sortProperty) {

		// sender
		if(Gift.SENDER.getName().equalsIgnoreCase(sortProperty))
			return getGiftCategoriesBySender(gifts);

		// receiver
		if(Gift.RECEIVER.getName().equalsIgnoreCase(sortProperty))
			return getGiftCategoriesByReceiver(gifts);

		// occasion
		return getGiftCategoriesByOccasion(gifts);
	}

	/**
	 * Returns a collection of gift categories by occasion.
	 *
	 * @param gifts
	 * @return Collection<GiftCategory>
	 */
	private Collection<GiftCategory> getGiftCategoriesByOccasion(List<Gift> gifts) {
		Collections.sort(gifts, OCCASION_COMPARATOR);

		Map<String, GiftCategory> categoryMap = new LinkedHashMap<>();

		for(Gift gift : gifts)
			getGiftCategory(categoryMap, gift.getOccasion() + " (" + gift.getDate().getYear() + ")").addItem(gift);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of gift categories by sender.
	 *
	 * @param gifts
	 * @return Collection<GiftCategory>
	 */
	private Collection<GiftCategory> getGiftCategoriesBySender(List<Gift> gifts) {
		Collections.sort(gifts, SENDER_COMPARATOR);

		Map<String, GiftCategory> categoryMap = new LinkedHashMap<>();

		for(Gift gift : gifts)
			getGiftCategory(categoryMap, gift.getSender()).addItem(gift);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of gift categories by receiver.
	 *
	 * @param gifts
	 * @return Collection<GiftCategory>
	 */
	private Collection<GiftCategory> getGiftCategoriesByReceiver(List<Gift> gifts) {
		Collections.sort(gifts, RECEIVER_COMPARATOR);

		Map<String, GiftCategory> categoryMap = new LinkedHashMap<>();

		for(Gift gift : gifts)
			getGiftCategory(categoryMap, gift.getReceiver()).addItem(gift);

		return categoryMap.values();
	}

	/**
	 * Returns a GiftCategory matching the category name.
	 *
	 * @param categoryMap
	 * @param categoryName
	 * @return GiftCategory
	 */
	private GiftCategory getGiftCategory(Map<String, GiftCategory> categoryMap, String categoryName) {
		GiftCategory giftCategory = categoryMap.get(categoryName.toLowerCase().trim());

		if(giftCategory == null)
		{
			giftCategory = new GiftCategory(WordUtils.capitalize(categoryName.trim()));
			giftCategory.setChildCategories(new HashSet<ItemCategory<Gift>>());
			categoryMap.put(categoryName.toLowerCase().trim(), giftCategory);
		}

		return giftCategory;
	}

	/**
     * Returns a child category by receiver.
     *
     * @param occasionCategory
     * @param receiver
     * @return ItemCategory<Gift>
     */
    private ItemCategory<Gift> getReceiverCategory(GiftCategory occasionCategory, String receiver) {
		if(occasionCategory.getChildCategories() == null)
			occasionCategory.setChildCategories(new HashSet<ItemCategory<Gift>>());

		for(ItemCategory<Gift> giftCategory : occasionCategory.getChildCategories())
			if(giftCategory.getName().equalsIgnoreCase(receiver))
				return giftCategory;

		ItemCategory<Gift> category = new ItemCategory<>(receiver);
		occasionCategory.getChildCategories().add(category);
		return category;
	}

    /**
     * Returns a child category by sender.
     *
     * @param occasionCategory
     * @param sender
     * @return ItemCategory<Gift>
     */
    private ItemCategory<Gift> getSenderCategory(GiftCategory occasionCategory, String sender) {
		if(occasionCategory.getChildCategories() == null)
			occasionCategory.setChildCategories(new HashSet<ItemCategory<Gift>>());

		for(ItemCategory<Gift> giftCategory : occasionCategory.getChildCategories())
			if(giftCategory.getName().equalsIgnoreCase(sender))
				return giftCategory;

		ItemCategory<Gift> category = new ItemCategory<>(sender);
		occasionCategory.getChildCategories().add(category);
		return category;
	}

	/**
	 * Returns the count of all gifts.
	 *
	 * @return int
	 */
	public int getGiftCount() {
		return giftCount;
	}

	/**
	 * Returns a collection of GiftCategories.
	 *
	 * @return Collection<GiftCategory>
	 */
	public Collection<GiftCategory> getGiftCategories() {
		return giftCategories;
	}

	/**
	 * Returns a collection of chart GiftCategories.
	 *
	 * @return Collection<GiftCategory>
	 */
	public Collection<GiftCategory> getChartGiftCategories() {
		return chartGiftCategories;
	}

	// occasion comparator
	private static final Comparator<Gift> OCCASION_COMPARATOR = new Comparator<Gift>() {
		@Override
		public int compare(Gift b1, Gift b2) {
			if(Objects.equal(b1.getOccasion(), b2.getOccasion()))
				return b2.getDate().compareTo(b1.getDate());

			return b1.getOccasion().compareToIgnoreCase(b2.getOccasion());
		}
	};

	// sender comparator
	private static final Comparator<Gift> SENDER_COMPARATOR = new Comparator<Gift>() {
		@Override
		public int compare(Gift b1, Gift b2) {
			if(Objects.equal(b1.getSender(), b2.getSender()))
				return b2.getDate().compareTo(b1.getDate());

			return b1.getSender().compareToIgnoreCase(b2.getSender());
		}
	};

	// receiver comparator
	private static final Comparator<Gift> RECEIVER_COMPARATOR = new Comparator<Gift>() {
		@Override
		public int compare(Gift b1, Gift b2) {
			if(Objects.equal(b1.getReceiver(), b2.getReceiver()))
					return b2.getDate().compareTo(b1.getDate());

			return b1.getReceiver().compareToIgnoreCase(b2.getReceiver());
		}
	};

}