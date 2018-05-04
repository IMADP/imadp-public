
	{#. menuTitle="Entry Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Entry" icon="s-app-edit" template="exercise_entryDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{?entriesSortable}		
			{#. itemTitle="Sort Entries" icon="s-sort" template="exercise_entryDialogSort" contextId="{exerciseId}"}
				{>menuItemDialogForm/}
			{/.}
		{/entriesSortable}
		
		{#. itemTitle="Delete Entry" objectName="entry" successIds="content-messages, content-body"}
			{>menuItemToDelete/}
			
			{<menuItemSubmitFormParams}
				<input type="hidden" name="routine" value="{routineId}" />							
			{/menuItemSubmitFormParams}		
		{/.}
							
	{/menuBody}	