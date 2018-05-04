
	{#. menuTitle="Meal Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Meal" icon="s-app-edit" template="restaurant_mealDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{#. itemTitle="Delete Meal" objectName="meal" successIds="content-messages, content-body"}
			{>menuItemToDelete/}
		{/.}
							
	{/menuBody}