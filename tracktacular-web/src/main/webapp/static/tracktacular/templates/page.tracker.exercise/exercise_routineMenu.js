
	{#. menuTitle="Routine Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{?activeState}
			{#. itemTitle="Edit Routine" icon="s-app-edit" template="exercise_routineDialog" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/activeState}
		
		{#. itemTitle="Activate Routine" objectName="routine" successIds="content-messages, content-body"}
			{>menuItemToStateActive/}
		{/.}
		
		{#. itemTitle="Complete Routine" objectName="routine" successIds="content-messages, content-body"}
			{>menuItemToStateArchived/}
		{/.}
		
		{#. itemTitle="Delete Routine" objectName="routine" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
		
		{?deletedState}
			{#. itemTitle="Perma-Delete Routine" objectName="routine" successIds="content-messages, content-body"}
				{>menuItemToDelete/}
			{/.}
		{/deletedState}
							
	{/menuBody}	