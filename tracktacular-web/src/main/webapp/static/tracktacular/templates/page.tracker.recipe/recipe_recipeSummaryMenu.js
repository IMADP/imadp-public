
	{#. menuTitle="Recipe Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Recipe" icon="s-app-edit" template="recipe_recipeDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
				
		{?recipesSortable}		
			{#. itemTitle="Sort Recipes" icon="s-sort" template="recipe_recipeDialogSort" contextId="{chapter}"}
				{>menuItemDialogForm/}
			{/.}
		{/recipesSortable}		
									
		{#. itemTitle="Delete Recipe" objectName="recipe" successIds="content-messages, recipe-chapter-{chapter}, navigation-chart-{chapter}, navigation-chart-all"}
			{>menuItemToStateDeleted/}
		{/.}
							
	{/menuBody}