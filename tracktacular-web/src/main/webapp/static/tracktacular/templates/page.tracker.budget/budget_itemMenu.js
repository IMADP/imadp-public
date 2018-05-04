	
	{#. menuTitle="Item Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}
	
		{#. itemTitle="Edit Item" icon="s-app-edit" template="budget_itemDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
			
		{?itemsSortable}		
			{#. itemTitle="Sort Items" icon="s-sort" template="budget_itemDialogSort" contextId="{category}"}
				{>menuItemDialogForm/}
			{/.}
		{/itemsSortable}
		
		{#. itemTitle="Delete Item" objectName="item" successIds="content-messages, content-body"}
			{>menuItemToDelete/}
						
			{<menuItemSubmitFormParams}
				<input type="hidden" name="budget" value="{budgetId}" />							
			{/menuItemSubmitFormParams}	
		{/.}
		
	{/menuBody}