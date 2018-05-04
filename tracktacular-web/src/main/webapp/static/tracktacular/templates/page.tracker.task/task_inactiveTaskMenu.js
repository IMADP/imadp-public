	
	{#. menuTitle="Task Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}
	
		{#. itemTitle="Activate Task" icon="s-app-add" template="task_taskDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
		
		{?archivedState}
			{#. itemTitle="Delete Task" objectName="task" successIds="content-messages, content-body"}
				{>menuItemToStateDeleted/}
			{/.}
		{/archivedState}
		
		{?deletedState}
			{#. itemTitle="Perma-Delete Task" objectName="task" successIds="content-messages, content-body"}
				{>menuItemToDelete/}
			{/.}
		{/deletedState}
		
	{/menuBody}