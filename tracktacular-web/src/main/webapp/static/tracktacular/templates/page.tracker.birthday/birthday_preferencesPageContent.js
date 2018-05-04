
	{#contentBody}	
	
		<h2>Preferences</h2>
		
		{<trackerPreferencesBody}			
			
			<section class="center" style="padding-bottom: 0;">
				{#. object="preferences.trackers.birthdayTrackerPreferences.alertOnBirthdays"}{>inputCheckbox/}{/.}
				<label for="preferences.trackers.birthdayTrackerPreferences.alertOnBirthdays">
					Create an alert whenever it's someone's birthday
				</label>
			</section>
			
		{/trackerPreferencesBody}	
		
		{>trackerPreferences/}	
		
	{/contentBody}