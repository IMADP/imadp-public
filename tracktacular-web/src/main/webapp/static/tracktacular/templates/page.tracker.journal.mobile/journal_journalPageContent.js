
	{#contentBody}
			
		{#. addButtonTemplate="journal_entryDialog" addButtonTemplateContextId="newEntry" addButtonTitle="Add Entry"}
			{>addButton/}		
		{/.}
			
		{#entries}
            {>journal_entry/}
        {/entries}
        
	    {>pageNavigator/}
        
	{/contentBody}