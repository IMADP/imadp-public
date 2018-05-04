
	{#. menuTitle="Exercise Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Exercise" icon="s-app-edit" template="exercise_exerciseDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{?exercisesSortable}		
			{#. itemTitle="Sort Exercises" icon="s-sort" template="exercise_exerciseDialogSort" contextId="{workoutId}"}
				{>menuItemDialogForm/}
			{/.}
		{/exercisesSortable}
		
		{#. itemTitle="Add Entry" icon="s-app-add" template="exercise_entryDialog" contextId="{newEntry.id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{?entriesSortable}		
			{#. itemTitle="Sort Entries" icon="s-sort" template="exercise_entryDialogSort" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/entriesSortable}
		
		{#. itemTitle="Delete Exercise" objectName="exercise" successIds="content-messages, content-body"}
			{>menuItemToDelete/}
			
			{<menuItemSubmitFormParams}
				<input type="hidden" name="routine" value="{workout.routine.id}" />							
			{/menuItemSubmitFormParams}		
		{/.}
							
	{/menuBody}	