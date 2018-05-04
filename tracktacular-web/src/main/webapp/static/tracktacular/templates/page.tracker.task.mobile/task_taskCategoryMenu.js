
	{#. menuTitle="Category Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}
	
		{#. itemTitle="Edit Category" icon="s-app-edit" template="task_taskCategoryDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
			
		{?subCategorizable}	
			{#. itemTitle="Add Sub Category" icon="s-add" template="task_taskCategoryDialog" contextId="{newSubCategory.id}"}
				{>menuItemDialogForm/}
			{/.}
		{/subCategorizable}	
			
		{#. itemTitle="Add New Task" icon="s-app-add" template="task_taskDialog" contextId="{newTask.id}"}
			{>menuItemDialogForm/}
		{/.}
		
		{?deletable}	
			{#. itemTitle="Delete Category" objectName="taskCategory" successIds="content-messages, content-body"}
				{>menuItemToDelete/}
			{/.}			
		{/deletable}
		
	{/menuBody}