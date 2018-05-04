
	{#. dialogName="Save Meal" event="saveMeal" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="meal" value="{id}" />							
		<input type="hidden" name="meal.restaurant" value="{restaurantId}" />							
			
		Add a new meal for {restaurantName}
		
		<label for="meal.name" class="primary required">Name</label>
		{#. object="meal.name" max="256"}{>inputText/}{/.}
		
		<label for="meal.tag" class="primary">Tags (comma separated list)</label>
		<label for="meal.tag" class="secondary">Add tags as a comma separated list (ie: american, italian, cheap)</label>  
		{#. object="meal.tag" max="256"}{>inputText/}{/.}
		
		<div class="cf">
			<div class="half">
				<label for="meal.rating" class="primary">Meal Rating</label> 
				<label for="meal.rating" class="secondary">Rate this meal</label>  
				{#. object="meal.rating" options=ratings optionName="name" optionValue="value"}{>inputSelect/}{/.}
			</div>
			<div class="half">
				<label for="meal.targetDate" class="primary">Target Date</label>
				<label for="meal.targetDate" class="secondary">An optional target date to try this meal</label>  
				{#. object="meal.targetDate"}{>inputDate/}{/.}
			</div>
		</div>
		
		<label for="meal.notes" class="primary">Notes</label>
		{#. object="meal.notes"}{>inputNotes/}{/.}
		
	{/dialogFormBody}
