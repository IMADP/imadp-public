
	{#contentBody}
			
		<nav>
		
			{#. addButtonTemplate="recipe_recipeChapterDialog" addButtonTitle="Add Chapter"}
				{>addButton/}		
			{/.}
					
			{>navigationChart/}
			
			{#chapters}
				 {>navigationChart/}
			{/chapters}
		    
		</nav>
		
		<article class="item" style="padding-bottom: 50px">
		
			<header>						
				<span class="icon-s-blank"></span>				
			</header>
			
			<h3 class="center">
				Recipe Book
			</h3>  
			
			<h4 class="center">
				{recipeCount} {@when test="{recipeCount} == 1"} Recipe {:else} Recipes {/when}
			</h4>  
			     		
			{#chapters}
	            {>recipe_recipeChapter/}
	        {/chapters}
						
		</article>
        		
        {>recipe_tutorial/}
					
	{/contentBody}