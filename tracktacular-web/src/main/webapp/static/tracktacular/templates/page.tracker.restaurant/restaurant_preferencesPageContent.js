
	{#contentBody}	
	
		<h2>Preferences</h2>
		
		{<trackerPreferencesBody}			
			
			<section class="center" style="padding-bottom: 0;">
				{#. object="preferences.trackers.restaurantTrackerPreferences.alertOnTargetDates"}{>inputCheckbox/}{/.}
				<label for="preferences.trackers.restaurantTrackerPreferences.alertOnTargetDates">
					Create an alert for restaurant/meal target dates.
				</label>
			</section>
			
		{/trackerPreferencesBody}	
		
		{>trackerPreferences/}		
		
		<div class="box-shadow preferences">		
			
			<h2>Import Data</h2>
			
			<div class="center" style="margin: 25px 0;">
				<a href="{importUrl}" class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all">Import Restaurants From File</a>
			</div>
			
		</div>
		
	{/contentBody}