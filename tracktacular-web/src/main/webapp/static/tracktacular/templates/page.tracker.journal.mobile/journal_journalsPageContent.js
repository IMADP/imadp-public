
	{#contentBody}
	
		{#. addButtonTemplate="journal_journalDialog" addButtonTitle="Add Journal"}
			{>addButton/}		
		{/.}
		
		{#activeJournals}
	        {>journal_journal/}
	    {/activeJournals}
	   	
	    {>pageNavigator/}
        
	{/contentBody}