
	
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
		
		{#. itemTitle="Add Multiple Tasks" icon="s-app-add-multiple" template="task_taskDialogMultiple" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
		
		{?categorySortable}		
			{#. itemTitle="Sort Categories" icon="s-sort" template="task_taskCategoryDialogSort" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/categorySortable}
		
		{?tasksSortable}		
			{#. itemTitle="Sort Tasks" icon="s-sort" template="task_taskDialogSort" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/tasksSortable}
		
		{?deletable}	
			{#. itemTitle="Delete Category" objectName="taskCategory" successIds="content-messages, content-body"}
				{>menuItemToDelete/}
			{/.}			
		{/deletable}
		
	{/menuBody}