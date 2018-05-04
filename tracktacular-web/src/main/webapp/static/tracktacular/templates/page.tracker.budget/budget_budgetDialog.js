
	{#. dialogTitle="Save Budget" event="saveBudget" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="budget" value="{id}" />
					
		Add a new budget to track your finances. 
		
		<label for="budget.name" class="primary required">Name</label>
		<label for="budget.name" class="secondary">Enter a short nickname for this budget (ie: Personal, Work)</label>
		{#. object="budget.name" max="35"}{>inputText/}{/.}
		
		<label for="budget.description" class="primary">Description</label>  
		{#. object="budget.description" max="256"}{>inputText/}{/.}
		
		<label for="budget.notes" class="primary">Notes</label>
		{#. object="budget.notes"}{>inputNotes/}{/.}
				
	{/dialogFormBody}
