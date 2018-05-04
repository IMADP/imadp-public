
	{#. dialogTitle="Save Show" event="saveShow" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="show" value="{id}" />
					
		<div class="sh1">Add a show to track.</div> 
		
		<label for="show.title" class="primary required">Title</label>
		{#. object="show.title" max="256"}{>inputText/}{/.}
		
		<label for="show.tag" class="primary">Tags (comma separated list)</label>
		<label for="show.tag" class="secondary">Add tags as a comma separated list (ie: crime, drama, documentary)</label>  
		{#. object="show.tag" max="256"}{>inputText/}{/.}
		
		<div class="cf">
			<div class="half">
				<label for="show.rating" class="primary">Show Rating</label> 
				<label for="show.rating" class="secondary">Rate this show after it has been seen</label>  
				{#. object="show.rating" options=ratings optionName="name" optionValue="value"}{>inputSelect/}{/.}
			</div>
			<div class="half">
				<label for="show.targetDate" class="primary">Target Date</label>
				<label for="show.targetDate" class="secondary">An optional target date to watch this show</label>  
				{#. object="show.targetDate"}{>inputDate/}{/.}
			</div>
		</div>
		
		<label for="show.notes" class="primary">Notes</label>
		{#. object="show.notes"}{>inputNotes/}{/.}
		
	{/dialogFormBody}
