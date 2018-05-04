
	{#. menuTitle="Task Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Task" icon="s-app-edit" template="task_taskDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{#. itemTitle="Sort Tasks" icon="s-sort" template="task_taskDialogSort" contextId="{category}"}
			{>menuItemDialogForm/}
		{/.}
									
		{#. itemTitle="Complete Task" objectName="task" successIds="content-messages, category-{category}, navigation-chart-{rootCategoryId}, navigation-chart-all"}
			{>menuItemToStateArchived/}
		{/.}
		
		{#. itemTitle="Delete Task" objectName="task" successIds="content-messages, category-{category}, navigation-chart-{rootCategoryId}, navigation-chart-all"}
			{>menuItemToStateDeleted/}
		{/.}
							
	{/menuBody}