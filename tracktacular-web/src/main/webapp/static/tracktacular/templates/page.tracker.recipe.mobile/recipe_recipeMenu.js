	
	{#. menuTitle="Recipe Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Recipe" icon="s-app-edit" template="recipe_recipeDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
		
		{#. itemTitle="Delete Recipe" objectName="recipe" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
							
	{/menuBody}	