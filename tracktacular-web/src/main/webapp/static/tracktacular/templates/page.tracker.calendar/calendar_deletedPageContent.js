
	{#contentBody}
	
		<h2>Deleted Calendar Entries</h2>				
	
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedCalendarEntryCount} Deleted Calendar {@when test="{deletedCalendarEntryCount} == 1"} Entry {:else} Entries {/when}
		</h4>				
			
		{#deletedCalendarEntries}
			{>calendar_calendarEntry/}
		{/deletedCalendarEntries}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
		
	{/contentBody}