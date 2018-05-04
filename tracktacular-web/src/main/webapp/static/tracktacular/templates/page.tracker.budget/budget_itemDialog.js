
	{#. dialogTitle="Save Item" event="saveItem" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="budget" value="{budgetId}" />
		<input type="hidden" name="item" value="{id}" />
					
		Add a new budget item. 
		
		<label for="item.category" class="primary required">Category</label> 
		{#. object="item.category" options=allCategories optionName="path" optionValue="id"}{>inputSelect/}{/.}
		
		<label for="item.name" class="primary required">Name</label>
		{#. object="item.name" max="256"}{>inputText/}{/.}
		
		<label for="item.amount" class="primary required">Amount</label>
		<label for="item.amount" class="secondary">Enter negative numbers to indicate expenses (ie: -100)</label>
		{#. object="item.amount"}{>inputNumber/}{/.}
		
		<label for="item.quantity" class="primary">Quantity</label>
		<label for="item.quantity" class="secondary">For items with multiple entries, enter a quantity and the amount will be multiplied</label>
		{#. object="item.quantity" min="0"}{>inputNumber/}{/.} 
		
		<label for="item.notes" class="primary">Notes</label>
		{#. object="item.notes"}{>inputNotes/}{/.}
		
	{/dialogFormBody}