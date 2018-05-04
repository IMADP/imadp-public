
	{#contentBody}
			
		{#. addButtonTemplate="recipe_recipeChapterDialog" addButtonTitle="Add Chapter"}
			{>addButton/}		
		{/.}
				
		{#chapters}
            {>recipe_recipeChapter/}
        {/chapters}
					
	    {>pageNavigator/}
        
	{/contentBody}