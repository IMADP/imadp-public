
	{#. menuTitle="Category Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}
	
		{#. itemTitle="Edit Category" icon="s-app-edit" template="budget_itemCategoryDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
			
		{?subCategorizable}	
			{#. itemTitle="Add Sub Category" icon="s-add" template="budget_itemCategoryDialog" contextId="{newSubCategory.id}"}
				{>menuItemDialogForm/}
			{/.}
		{/subCategorizable}	
		
		{#. itemTitle="Add New Item" icon="s-app-add" template="budget_itemDialog" contextId="{newItem.id}"}
			{>menuItemDialogForm/}
		{/.}
		
		{?categorySortable}		
			{#. itemTitle="Sort Categories" icon="s-sort" template="budget_itemCategoryDialogSort" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/categorySortable}
		
		{?itemsSortable}		
			{#. itemTitle="Sort Items" icon="s-sort" template="budget_itemDialogSort" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/itemsSortable}
		
		{?deletable}	
			{#. itemTitle="Delete Category" objectName="itemCategory" successIds="content-messages, content-body"}
				{>menuItemToDelete/}
				
				{<menuItemSubmitFormParams}
					<input type="hidden" name="budget" value="{budgetId}" />							
				{/menuItemSubmitFormParams}	
			{/.}			
		{/deletable}
		
	{/menuBody}