
	{#contentBody}	
	
		<h2>Preferences</h2>
		
		{<trackerPreferencesBody}			
			
			<section class="center" style="padding-bottom: 0;">
				{#. object="preferences.trackers.bookTrackerPreferences.alertOnTargetDates"}{>inputCheckbox/}{/.}
				<label for="preferences.trackers.bookTrackerPreferences.alertOnTargetDates">
					Create an alert for book/chapter target dates.
				</label>
			</section>
			
		{/trackerPreferencesBody}	
		
		{>trackerPreferences/}	
		
		<div class="box-shadow preferences">		
			
			<h2>Import Data</h2>
			
			<div class="center" style="margin: 25px 0;">
				<a href="{importUrl}" class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all">Import Books From File</a>
			</div>
			
		</div>
		
	{/contentBody}