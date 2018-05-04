			
	
	{#. menuTitle="Exercise Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Exercise" icon="s-app-edit" template="exercise_exerciseDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{#. itemTitle="Add Entry" icon="s-app-add" template="exercise_entryDialog" contextId="{newEntry.id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{#. itemTitle="Delete Exercise" objectName="exercise" successIds="content-messages, content-body"}
			{>menuItemToDelete/}
			
			{<menuItemSubmitFormParams}
				<input type="hidden" name="routine" value="{workout.routine.id}" />							
			{/menuItemSubmitFormParams}		
		{/.}
							
	{/menuBody}	