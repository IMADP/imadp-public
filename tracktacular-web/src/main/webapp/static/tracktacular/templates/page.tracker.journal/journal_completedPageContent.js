
	{#contentBody}
	
		<h2>Completed Journals</h2>				
	
		<h4 style="padding-top: 5px; padding-left:60px;">
			{completedJournalsCount} Completed {@when test="{completedJournalsCount} == 1"} Journal {:else} Journals {/when}
		</h4>				
					 
		{#completedJournals}
			{>journal_journal/}
		{/completedJournals}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
			
	{/contentBody}