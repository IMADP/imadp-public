
	{#contentBody}
	
		<nav>
			
			{#. addButtonTemplate="task_taskCategoryDialog" addButtonTitle="Add Category"}
				{>addButton/}		
			{/.}
					
			{>navigationChart/}
		
			{#categories}
				 {>navigationChart/}
		    {/categories}
		    
		</nav>
        
        <section style="margin: 0 60px 0 0;">		
			{#categories}
	            {>task_taskCategory/}
	        {/categories}			
		</section>
        		
        {>task_tutorial/}
				
	{/contentBody}