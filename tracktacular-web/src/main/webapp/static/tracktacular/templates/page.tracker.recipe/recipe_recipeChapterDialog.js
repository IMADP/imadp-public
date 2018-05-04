
	{#. dialogTitle="Save Chapter" event="saveRecipeChapter" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="recipeChapter" value="{id}" />
					
		Add a new chapter to your recipe book.
		
		<label for="recipeChapter.title" class="primary required">Title</label>
		{#. object="recipeChapter.title" max="35"}{>inputText/}{/.}
		
		<label for="recipeChapter.description" class="primary">Description</label>
		{#. object="recipeChapter.description" max="256"}{>inputText/}{/.}
		
	{/dialogFormBody}