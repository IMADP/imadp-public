
	{#. menuTitle="Meal Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Meal" icon="s-app-edit" template="restaurant_mealDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{?mealsSortable}		
			{#. itemTitle="Sort Meals" icon="s-sort" template="restaurant_mealDialogSort" contextId="{restaurantId}"}
				{>menuItemDialogForm/}
			{/.}
		{/mealsSortable}
		
		{#. itemTitle="Delete Meal" objectName="meal" successIds="content-messages, content-body"}
			{>menuItemToDelete/}
		{/.}
							
	{/menuBody}