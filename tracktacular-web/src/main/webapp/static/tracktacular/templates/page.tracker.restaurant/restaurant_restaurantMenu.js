
	{#. menuTitle="Restaurant Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Restaurant" icon="s-app-edit" template="restaurant_restaurantDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
		
		{#. itemTitle="Add Meal" icon="s-add" template="restaurant_mealDialog" contextId="{newMeal.id}"}
			{>menuItemDialogForm/}
		{/.}
		
		{?mealsSortable}		
			{#. itemTitle="Sort Meals" icon="s-sort" template="restaurant_mealDialogSort" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/mealsSortable}
				
		{#. itemTitle="Delete Restaurant" objectName="restaurant" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
							
	{/menuBody}