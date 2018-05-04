package com.tracktacular.service.tracker.dream;

import org.joda.time.DateTime;

import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;


/**
 * DreamTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class DreamTrackerDemo implements TrackerDemo {
	private DreamTrackerFacade trackerFacade;

	// constructor
	public DreamTrackerDemo(DreamTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		Dream dream;

		dream = new Dream(user);
		dream.setTitle("The End is Nigh");
		dream.setContent("By way of cosmic coincidence a small black hole had suddenly formed near the coast of southern new jersey. The implications were profound, and the speed at which it sucked in matter was painstakingly slow, so that everyone in the world knew their time was slowly coming to an end. \n\nIn its infancy I watched it slowly tear apart the crust of the earth until it was deep enough to start pulling in the ocean. It began with the lagoon, reversing it's current and creating a powerful and deadly rip tide. \n\nEveryone knew the end was near - at most we were expected to have a day or two to live. But the manner at which it would take lives was the most alarming. First its pull would become so strong that it would take every ounce of strength to simply hold your ground. Afterwards it would slowly drag you towards it. \n\nI went back to check on the black hole, and the water was no longer trickling in. Instead the lagoon had pooled over the top of it in a giant ring disappearing in a massive vortex. I mentioned that it was getting bad, and soon we would start to feel its pull...");
		dream.setAnalysis("This dream occurred one day after the discovery of the Higgs Boson. It's likely influenced by the initial fears of the Large Hadron Collider creating a miniature black hole.");
		dream.setDreamsignsAsString("apocalypse, shore, lagoon, space");
		dream.setDate(new DateTime());
		dream.setFavorite(true);
		trackerFacade.saveDream(dream);

		dream = new Dream(user);
		dream.setTitle("The Unwaking Life");
		dream.setContent("I looked at my alarm clock which indicated it was 4:14 AM. However something felt odd, and after glancing away and back again the alarm now read 11:11 AM. I realized instantly that I was dreaming and excitedly jumped out of bed.\n\nI had prepared a checklist of tasks to accomplish while dreaming a few days beforehand, and I tried to recall as many as them as possible. First I checked my shorts, which appeared to look exactly like the shorts I wore to bed. Then I looked for a mirror to see how my reflection would look in a dream.\n\nI walked into my living room, knowing there was a mirror on the wall. However, the reflection was not what I had expected. I saw my face, but it was distorted in a strange way and the reflected image didn't mimic any of the motions I made with my hands.\n\nI finally opened the door to my backyard and looked outside. The neighborhood was remarkably accurate in my mind's recreation of it. A swarm of birds flew a few feet over my head before landing on the deck. I walked down to the lagoon with the intention of diving into it and exploring underwater, but as soon as I hit the surface I woke up.");
		dream.setAnalysis("This lucid dream was invoked by a simple reality check from looking at a clock earlier in the day.");
		dream.setDreamsignsAsString("shore, lagoon");
		dream.setDate(new DateTime());
		dream.setLucid(true);
		trackerFacade.saveDream(dream);

		dream = new Dream(user);
		dream.setTitle("The Tipping Point");
		dream.setContent("I had always been wary of the shed behind my house. I recalled memories of running away from it in fear and barely jumping over the small fence to my backyard with bloody hands.\n\nIt was getting dark and my birthday party was just starting across the street. My uncle had brought me a suit to change into outside. He noticed I left a light on in the basement, but I told him I wouldn't go back to the house unless he stood outside the shed and waited for me.\n\nHe agreed, and I climbed the stairs through my basement door to close up the house. When I did, I saw a virtual message that my uncle had 'Facebook Disliked' a robot that I had built and left in the shed. That was the last straw - the robot came to life, doubled in size and killed him on the spot. I ran up the stairs and peeked down to see the robot eating a small animal it had trapped.\n\nI knew the house was dangerous so I climbed down the balcony and escaped. The police had begun to barricade the house. Even though I had escaped, the situation was far worse outside. All over the world technology had begun to rise up and rebel. \n\nI realized now that I was in a simulated world, and all the events were scripted to happen. All of my fears vanished and I was amazed by the potential of the future of video games.");
		dream.setAnalysis("This dream draws inspiration from the book Ready Player One, specifically the futuristic concept of a fully interactive simulated world.");
		dream.setDreamsignsAsString("video games, virtual reality, apocalypse");
		dream.setDate(new DateTime().minusMonths(1));
		trackerFacade.saveDream(dream);

		dream = new Dream(user);
		dream.setTitle("The Vast Ocean Floor");
		dream.setContent("I was wading patiently at a floating ocean bus stop with some other passengers, when I decided to pass the time by exploring the ocean floor. It wasn't deep and it didn't take long to stumble across a package that appeared to have been lost from the US Postal Service. \n\nI sifted through its contents trying to salvage anything of value. I found a broken digital camera with its flash card intact, and I couldn't wait to see what was on it. There were a few other trinkets that looked valuable enough, but another passenger oversaw what I was doing and came over to speak to me. \n\nHe told me that the British Government was closing its borders to sea traffic because of rumors of a vast treasure strewn across the ocean floor. But they were misinformed, and the treasure was in fact much closer to the bus stop we were currently floating around. \n\nIt was then I realized that many of the passengers looked very suspicious. Some were reading books about diving, others with tinkering with equipment that could light up the ocean floor. I decided to skip my bus and explore a little further. \n\nThe ocean floor was littered with USPS boxes, and I imagined that a merchant ship had sunk recently, leaving behind a wealth of unopened packages. The ground was occasionally lit up by a massive flash of light from a machine one of the other passengers was using. I began sifting through the boxes. \n\nSuddenly I looked up to see two great white sharks swimming straight towards me. I froze in fear, but right before they reached me, giant chains attached to their fins held them back. I swam to up to see what was happening, but I woke up right as I reached the surface.");
		dream.setAnalysis("This dream is probably loosely influenced from watching American Pickers the night before. The concept of treasure hunting was probably merged with a setting more interesting to me.");
		dream.setDreamsignsAsString("shore, ocean");
		dream.setDate(new DateTime().minusMonths(2));
		dream.setFavorite(true);
		trackerFacade.saveDream(dream);
	}

}
