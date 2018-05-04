
	{#. dialogTitle="Sort Recipes" event="sortRecipes" successIds="content-messages, recipe-chapter-{id}"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input id="sorted-recipes" type="hidden" name="sortedRecipes" />
					
		<p>Drag and drop to sort your recipes.</p> 
				
		<ul class="sortable" data-sortable-input-target-id="sorted-recipes">
			{#recipes}
               <li id="{id}" class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>{name}</li>
            {/recipes}	
		</ul>
		
	{/dialogFormBody}