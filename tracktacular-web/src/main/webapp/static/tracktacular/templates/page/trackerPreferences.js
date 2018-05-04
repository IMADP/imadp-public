
	{>preferences/}
	
	{<preferencesBody}	

		<h2>Tracker Settings</h2>
				
		<section>
		
			<div class="center">
				<b>Enabled:</b> Displays the tracker in the navigation links to the left.<br/>
				<b>Tutorial:</b> Displays a tutorial box at the bottom of tracker pages.<br/>
				<b>Public:</b> Allows members and non-members to see your tracker data.<br/>
			</div>
			
			<article class="item" style="padding: 0;margin-bottom: 5px; margin-top:25px;">
			
				<table class="striped-table" style="width: 100%;margin-left: 0;">
					<tr>
						<th>Tracker</th>
						<th>Enabled</th>
						<th>Tutorial</th>
						<th>Public</th>
					</tr>
					
					<tr>
						<td class="left" style="padding: 5px 10px;">
							<span class="icon-s s-{trackerName}" style="margin: 0 5px;"></span> {trackerTitle}
						</td>
						<td class="center">
							<input id="{trackerEnabledName}" 
					   			name="{trackerEnabledName}"
					   			{?trackerEnabled}checked="checked"{/trackerEnabled}  
					   			value="true" type="checkbox" > {~s}
					   	</td>
					   	<td class="center">
							<input id="{trackerTutorialName}" 
					   			name="{trackerTutorialName}"
					   			{?trackerTutorial}checked="checked"{/trackerTutorial}  
					   			value="true" type="checkbox" > {~s}
					   	</td>
						<td class="center">
							<input id="{trackerPublicName}" 
					   			name="{trackerPublicName}"
					   			{?trackerPublic}checked="checked"{/trackerPublic}  
					   			value="true" type="checkbox" > {~s}
					   	</td>
					</tr>
					
				</table>
			</article>
			
			{+trackerPreferencesBody/}
			
		</section>
		
	{/preferencesBody}	
			