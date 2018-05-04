
	{#. dialogTitle="Save Item" event="saveItem" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="item" value="{id}" />							
		
		Add a new item
		
		<label for="item.name" class="primary required">Name</label>
		{#. object="item.name" max="256"}{>inputText/}{/.}
		
		<label for="item.description" class="primary">Description</label>
		<label for="item.description" class="secondary">Optionally enter additional information to display below this bucket list item</label>
		{#. object="item.description" max="256"}{>inputText/}{/.}
		
		<label for="item.completedDate" class="primary">Completed Date</label>
		<label for="item.completedDate" class="secondary">Only set the completed date after this item has been accomplished</label>
		{#. object="item.completedDate"}{>inputDate/}{/.} 
		
		<label for="item.notes" class="primary">Notes</label>
		{#. object="item.notes"}{>inputNotes/}{/.}
				
	{/dialogFormBody}