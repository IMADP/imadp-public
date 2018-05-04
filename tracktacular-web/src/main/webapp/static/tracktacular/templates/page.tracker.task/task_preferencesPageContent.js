
	{#contentBody}	
	
		<h2>Preferences</h2>
		
		{<trackerPreferencesBody}			
			
			<section class="center" style="padding-bottom: 0;">
				{#. object="preferences.trackers.taskTrackerPreferences.alertOnTargetDates"}{>inputCheckbox/}{/.}
				<label for="preferences.trackers.taskTrackerPreferences.alertOnTargetDates">
					Create an alert for tasks not completed on their target date
				</label>
			</section>
			
		{/trackerPreferencesBody}	
		
		{>trackerPreferences/}	
		
	{/contentBody}