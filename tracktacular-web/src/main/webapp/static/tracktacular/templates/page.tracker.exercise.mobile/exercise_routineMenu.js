
	{#. menuTitle="Routine Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Routine" icon="s-app-edit" template="exercise_routineDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
	
		{#. itemTitle="Complete Routine" objectName="routine" successIds="content-messages, content-body"}
			{>menuItemToStateArchived/}
		{/.}
		
		{#. itemTitle="Delete Routine" objectName="routine" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
							
	{/menuBody}	