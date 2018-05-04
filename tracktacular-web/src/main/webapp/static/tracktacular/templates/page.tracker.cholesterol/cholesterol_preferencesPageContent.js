
	{#contentBody}	
	
		<h2>Preferences</h2>
		
		{<trackerPreferencesBody}			
			
			<section class="center" style="padding-bottom: 0;">
				<label for="preferences.trackers.cholesterolTrackerPreferences.alertRecurrence.length" class="primary" style="display:inline;">Create an alert reminder for your next test every {~s}</label>
				{#. object="preferences.trackers.cholesterolTrackerPreferences.alertRecurrence.length" min="0" style="display:inline; width:65px;"}{>inputNumber/}{/.}
				{#. object="preferences.trackers.cholesterolTrackerPreferences.alertRecurrence.type" options=recurrenceTypes optionName="name" optionValue="value"}{>inputSelect/}{/.}
			</section>
		
		{/trackerPreferencesBody}	
		
		{>trackerPreferences/}	
		
		<div class="box-shadow preferences">		
			
			<h2>Import Data</h2>
			
			<div class="center" style="margin: 25px 0;">
				<a href="{importUrl}" class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all">Import Cholesterol From File</a>
			</div>
			
		</div>		
		
	{/contentBody}