
	{#contentBody}
		
 		<h2>Deleted Journals</h2>				
	
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedJournalsCount} Deleted {@when test="{deletedJournalsCount} == 1"} Journal {:else} Journals {/when}
		</h4>				
			
		{#deletedJournals}
			{>journal_journal/}
		{/deletedJournals}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
				
	{/contentBody}