
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
		
		{?chaptersSortable}		
			{#. itemTitle="Sort Chapters" icon="s-sort" template="recipe_recipeChapterDialogSort"}
				{>menuItemDialogForm/}
			{/.}
		{/chaptersSortable}
		
		{?recipesSortable}		
			{#. itemTitle="Sort Recipes" icon="s-sort" template="recipe_recipeDialogSort" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
		{/recipesSortable}
		
		{?deletable}	
			{#. itemTitle="Delete Chapter" objectName="recipeChapter" successIds="content-messages, content-body"}
				{>menuItemToDelete/}
			{/.}			
		{/deletable}
		
	{/menuBody}