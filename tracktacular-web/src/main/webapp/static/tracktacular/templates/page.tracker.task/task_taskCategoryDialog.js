
	{#. dialogTitle="Save Category" event="saveTaskCategory" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="taskCategory" value="{id}" />
		<input type="hidden" name="taskCategory.parentCategory" value="{parentCategoryId}" />
					
		Add a new category to organize your tasks.
		
		<label for="taskCategory.name" class="primary required">Name</label>
		{#. object="taskCategory.name" max="35"}{>inputText/}{/.}
		
	{/dialogFormBody}