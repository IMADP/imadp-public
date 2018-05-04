
    {#. menuTitle="Chapter Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}
	
		{#. itemTitle="Edit Chapter" icon="s-app-edit" template="recipe_recipeChapterDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
			
		{#. itemTitle="Add New Recipe" icon="s-app-add" template="recipe_recipeDialog" contextId="{newRecipe.id}"}
			{>menuItemDialogForm/}
		{/.}
		
		{?deletable}	
			{#. itemTitle="Delete Chapter" objectName="recipeChapter" successIds="content-messages, content-body"}
				{>menuItemToDelete/}
			{/.}			
		{/deletable}
		
	{/menuBody}