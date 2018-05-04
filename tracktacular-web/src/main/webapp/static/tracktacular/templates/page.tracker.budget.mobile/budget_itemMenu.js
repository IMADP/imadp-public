
	{#. menuTitle="Item Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}
	
		{#. itemTitle="Edit Item" icon="s-app-edit" template="budget_itemDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
			
		{#. itemTitle="Delete Item" objectName="item" successIds="content-messages, content-body"}
			{>menuItemToDelete/}
						
			{<menuItemSubmitFormParams}
				<input type="hidden" name="budget" value="{budgetId}" />							
			{/menuItemSubmitFormParams}	
		{/.}
		
	{/menuBody}