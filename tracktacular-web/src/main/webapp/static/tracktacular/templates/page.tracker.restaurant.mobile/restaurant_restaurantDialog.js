
	{#. dialogName="Save Restaurant" event="saveRestaurant" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="restaurant" value="{id}" />
					
		<div class="sh1">Add a restaurant to track.</div> 
		
		<label for="restaurant.name" class="primary required">Name</label>
		{#. object="restaurant.name" max="256"}{>inputText/}{/.}
		
		<label for="restaurant.city" class="primary required">City</label> 
		{#. object="restaurant.city" max="256"}{>inputText/}{/.}
		
		<label for="restaurant.state" class="primary required">State</label> 
		{#. object="restaurant.state" max="256"}{>inputText/}{/.}
		
		<label for="restaurant.tag" class="primary">Tags (comma separated list)</label>
		<label for="restaurant.tag" class="secondary">Add tags as a comma separated list (ie: american, italian, cheap)</label>  
		{#. object="restaurant.tag" max="256"}{>inputText/}{/.}
		
		<label for="restaurant.rating" class="primary">Restaurant Rating</label> 
		<label for="restaurant.rating" class="secondary">Rate this restaurant</label>  
		{#. object="restaurant.rating" options=ratings optionName="name" optionValue="value"}{>inputSelect/}{/.}
		
		<label for="restaurant.targetDate" class="primary">Target Date</label>
		<label for="restaurant.targetDate" class="secondary">An optional target date to eat here</label>  
		{#. object="restaurant.targetDate"}{>inputDate/}{/.}
		
		<label for="restaurant.notes" class="primary">Notes</label>
		{#. object="restaurant.notes"}{>inputNotes/}{/.}
		
	{/dialogFormBody}
