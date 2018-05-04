
	{#contentBody}
	
		<h2>Recipe Tags</h2>				
	
		<h4 style="padding-top: 5px; padding-left:60px;">
			{recipeTagCloud.tagCloudItemCount} {@when test="{recipeTagCloud.tagCloudItemCount} == 1"} Tag {:else} Tags {/when}
		</h4>				
		
		{>tagCloud:recipeTagCloud/}
	    
	    {#recipeTagSelected}
	    	
	    	<h2>Tag: {recipeTag}</h2>				
	
			<h4 style="padding-top: 5px; padding-left:60px;">
				{recipeTagCount} {@when test="{recipeTagCount} == 1"} Recipe {:else} Recipes {/when}
			</h4>
			
	    	{#recipeTags}
				{>recipe_recipe/}
			{/recipeTags}
		
			{#pageNavigator}
				{>pageNavigator/}
			{/pageNavigator}
			
		{/recipeTagSelected}
			  
	{/contentBody}