
	{#. dialogTitle="Save Category" event="saveItemCategory" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="budget" value="{budgetId}" />
		<input type="hidden" name="itemCategory" value="{id}" />
		<input type="hidden" name="itemCategory.budget" value="{budgetId}" />
		<input type="hidden" name="itemCategory.parentCategory" value="{parentCategoryId}" />
					
		Add a new category to organize your items. 
		
		<label for="itemCategory.name" class="primary required">Name</label>
		{#. object="itemCategory.name" max="35"}{>inputText/}{/.}
		
	{/dialogFormBody}