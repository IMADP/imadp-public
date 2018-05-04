
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
		
		{#. itemTitle="Delete Restaurant" objectName="restaurant" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
							
	{/menuBody}