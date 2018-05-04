
	{#. dialogTitle="Save Recipe" event="saveRecipe" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="recipe" value="{id}" />
		
		{?persistableState}
			<input type="hidden" name="recipe.persistableState" value="{persistableState}" />
		{/persistableState}
		
		Add a recipe to track.
		
		<label for="recipe.chapter" class="primary required">Chapter</label> 
		{#. object="recipe.chapter" options=chapters optionName="title" optionValue="id"}{>inputSelect/}{/.}
			    
	    <label for="recipe.name" class="primary required">Recipe</label>  
	    {#. object="recipe.name" max="256"}{>inputText/}{/.}
			
		<label for="recipe.description" class="primary">Description</label>
		{#. object="recipe.description" max="256"}{>inputText/}{/.}
		
		<label for="recipe.ingredients" class="primary required">Ingredients</label>  
		<label for="recipe.ingredients" class="secondary">Add each ingredient on a new line. To separate into sections, end a line with a colon (ie: Gravy:)</label>  
		{#. object="recipe.ingredients"}{>inputTextarea/}{/.} 
		
		<label for="recipe.directions" class="primary required">Directions</label>  
		<label for="recipe.directions"  class="secondary">Add each step on a new line. To separate into sections, end a line with a colon (ie: Gravy:)</label>  
		{#. object="recipe.directions"}{>inputTextarea/}{/.} 
		
		<label for="recipe.notes" class="primary">Notes</label>  
		{#. object="recipe.notes"}{>inputNotes/}{/.}
		
		<label for="recipe.recipeTagsAsString" class="primary">Tags (comma separated list)</label>  
		<label for="recipe.recipeTagsAsString"  class="secondary">Add recipe tags as a comma separated list (ie: healthy, italian, high protein)</label>  
		{#. object="recipe.recipeTagsAsString" max="1024"}{>inputText/}{/.}
		
		<br/>
		<br/>
		
		{#. object="recipe.favorite"}{>inputCheckbox/}{/.}
		<label for="recipe.favorite" style="display:inline">Mark as a <strong>Favorite Recipe</strong></label>
				
	{/dialogFormBody}