
	{#. dialogTitle="Save Item" event="saveItem" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="item" value="{id}" />							
		
		Add a new item
		
		<label for="item.name" class="primary required">Name</label>
		<label for="item.name" class="secondary">Enter the name of your wish list item</label>
		{#. object="item.name" max="256"}{>inputText/}{/.}
		
		<label for="item.description" class="primary">Description</label>
		<label for="item.description" class="secondary">Optionally enter additional information to display below this wish list item</label>
		{#. object="item.description" max="256"}{>inputText/}{/.}
		
		<label for="item.url" class="primary">Product Url</label>
		<label for="item.url" class="secondary">Optional link to a product website or online store to purchase </label>
		{#. object="item.url" max="256"}{>inputText/}{/.}
		
		<label for="item.price" class="primary">Price</label>
		<label for="item.price" class="secondary">Optional estimated price of the item</label>
		{#. object="item.price" min="0"}{>inputNumber/}{/.}
		
		<label for="item.notes" class="primary">Notes</label>
		{#. object="item.notes"}{>inputNotes/}{/.}
				
	{/dialogFormBody}