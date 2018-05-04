
	{#. dialogTitle="Save Tasks" event="saveTasks" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="taskCategory" value="{id}" />
		
		Add up to 50 tasks to the same category. You can sort the tasks afterwards to prioritize them.
		
		<label for="multipleTasks" class="primary required">Tasks</label>  
		<label for="multipleTasks" class="secondary">Enter each unique task on a new line by pressing enter</label>  
		<textarea id="multipleTasks" name="multipleTasks" rows="10"></textarea>
		
	{/dialogFormBody}