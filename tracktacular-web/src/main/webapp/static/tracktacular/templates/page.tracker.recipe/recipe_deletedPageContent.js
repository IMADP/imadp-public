
	{#contentBody}
		
		<h2>Deleted Recipes</h2>				
	
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedRecipesCount} Deleted {@when test="{deletedRecipesCount} == 1"} Recipe {:else} Recipes {/when}
		</h4>				
			 
		{#deletedRecipes recipeCollapsed="true"}
			{>recipe_recipe/}
		{/deletedRecipes}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
				
	{/contentBody}