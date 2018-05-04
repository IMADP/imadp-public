
	{#contentBody}	
	
		<h2>Preferences</h2>
		
		{<trackerPreferencesBody}			
		
			<section class="center" style="padding-bottom: 0;">
				{#. object="preferences.trackers.goalTrackerPreferences.alertOnGoalTargetDates"}{>inputCheckbox/}{/.}
				<label for="preferences.trackers.goalTrackerPreferences.alertOnGoalTargetDates">
					Create an alert for goals not completed on their target date
				</label>
				
				<br/>
				
				{#. object="preferences.trackers.goalTrackerPreferences.alertOnObjectiveTargetDates"}{>inputCheckbox/}{/.}
				<label for="preferences.trackers.goalTrackerPreferences.alertOnObjectiveTargetDates">
					Create an alert for objectives not completed on their target date
				</label>
			</section>
			
		{/trackerPreferencesBody}	
		
		{>trackerPreferences/}	
		
	{/contentBody}