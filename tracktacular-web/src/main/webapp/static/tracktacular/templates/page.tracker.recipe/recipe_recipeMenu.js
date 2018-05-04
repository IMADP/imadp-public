
	{#. menuTitle="Recipe Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{?deletedState}
			{#. itemTitle="Activate Recipe" icon="s-app-add" template="recipe_recipeDialog" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/deletedState}
		
		{?activeState}
			{#. itemTitle="Edit Recipe" icon="s-app-edit" template="recipe_recipeDialog" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/activeState}
		
		{#. itemTitle="Delete Recipe" objectName="recipe" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
		
		{?deletedState}
			{#. itemTitle="Perma-Delete Recipe" objectName="recipe" successIds="content-messages, content-body"}
				{>menuItemToDelete/}
			{/.}
		{/deletedState}
							
	{/menuBody}	