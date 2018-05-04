
	{#contentBody}	
	
		<h2>Preferences</h2>
		
		{<trackerPreferencesBody}			
			
			<section class="center" style="padding-bottom: 0;">
				{#. object="preferences.trackers.gameTrackerPreferences.alertOnTargetDates"}{>inputCheckbox/}{/.}
				<label for="preferences.trackers.gameTrackerPreferences.alertOnTargetDates">
					Create an alert for game target dates.
				</label>
				
				<article class="center item" style="background: #E4ECF1; margin: 20px 35px 0 35px; padding: 15px;">
					
					<b>Steam Integration</b> <br/><br/>
					
					Enter your 64-bit Steam Id to connect your account to Steam. <br/>
					Your games will be synchronized on a daily basis to Tracktacular. <br/>
					You can find your 64-bit Steam Id from the <a href="http://steamidconverter.com/" target="_blank">Steam Id Converter</a>.<br/><br/>				
					
					{#. object="preferences.trackers.gameTrackerPreferences.steamId"}{>inputText/}{/.}
					
				</article> 
				
			</section>
			
		{/trackerPreferencesBody}	
		
		{>trackerPreferences/}	
		
		<div class="box-shadow preferences">		
			
			<h2>Import Data</h2>
			
			<div class="center" style="margin: 25px 0;">
				<a href="{importUrl}" class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all">Import Games From File</a>
				
				{?trackers.gameTrackerPreferences.steamId}
					
					<form style="margin: 25px" class="dialog-form" action="{formAction}" method="post">
						<input type="hidden" name="_sourcePage" value="{sourcePage}" />
						<input type="hidden" name="_eventName" value="synchronizeSteamGames" />
						<input type="hidden" name="synchronizeSteamGamesAction.formToken" value="{formToken}" />
						<input type="hidden" name="synchronizeSteamGamesAction.sessionToken" value="{sessionToken}" />
						<input 
							style="width: auto;"
							class="preferences-save dialog-form-button ui-state-default ui-priority-primary ui-corner-all" 
							name="synchronizeSteamGames" 
							value="Synchronize Games With Steam"
							type="submit"/>
					</form>
					
				{/trackers.gameTrackerPreferences.steamId}
			</div>
			
		</div>
		
	{/contentBody}