
	{#contentBody}
		
		<header class="content-body-header cf">
		
			<div class="left">
				<h2>Calendar Entries</h2>				
		
				<h4>
					{calendarEntryCount} Calendar {@when test="{calendarEntryCount} == 1"} Entry {:else} Entries {/when}
				</h4>
			</div>
			 
			<div class="right">
				{#. addButtonTemplateContextId="newEntry" addButtonTemplate="calendar_calendarEntryDialog" addButtonTitle="Add Entry"}
					{>addButton/}		
				{/.}
			</div>	
			
	    </header>		
			
		{#calendarEntries}
			{>calendar_calendarEntry/}
		{/calendarEntries}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
		
		{>calendar_tutorial/}
			
	{/contentBody}