
	{#. menuTitle="Entry Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Entry" icon="s-app-edit" template="journal_entryDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{#. itemTitle="Direct Link" itemLink=entryLink}
			{>menuItemLink/}
		{/.}
		
		{#. itemTitle="Delete Entry" objectName="entry" successIds="content-messages, content-body"}
			{>menuItemToDelete/}
			
			{<menuItemSubmitFormParams}
				<input type="hidden" name="journal" value="{journal.id}" />							
			{/menuItemSubmitFormParams}		
		{/.}
							
	{/menuBody}