
	{#contentBody}
				
		{#. addButtonTemplate="task_taskCategoryDialog" addButtonTitle="Add Category"}
			{>addButton/}		
		{/.}
		
		{#categories}
            {>task_taskCategory/}
        {/categories}			
					
	    {>pageNavigator/}
        
	{/contentBody}