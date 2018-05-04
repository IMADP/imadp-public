
	{#. dialogTitle="Save Movie" event="saveMovie" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="movie" value="{id}" />
					
		<div class="sh1">Add a movie to track.</div> 
		
		<label for="movie.title" class="primary required">Title</label>
		{#. object="movie.title" max="256"}{>inputText/}{/.}
		
		<label for="movie.tag" class="primary">Tags (comma separated list)</label>
		<label for="movie.tag" class="secondary">Add tags as a comma separated list (ie: crime, drama, action)</label>  
		{#. object="movie.tag" max="256"}{>inputText/}{/.}
		
		<div class="cf">
			<div class="half">
				<label for="movie.rating" class="primary">Movie Rating</label> 
				<label for="movie.rating" class="secondary">Rate this movie after it has been seen</label>  
				{#. object="movie.rating" options=ratings optionName="name" optionValue="value"}{>inputSelect/}{/.}
			</div>
			<div class="half">
				<label for="movie.targetDate" class="primary">Target Date</label>
				<label for="movie.targetDate" class="secondary">An optional target date to watch this movie</label>  
				{#. object="movie.targetDate"}{>inputDate/}{/.}
			</div>
		</div>
		
		<label for="movie.notes" class="primary">Notes</label>
		{#. object="movie.notes"}{>inputNotes/}{/.}
		
	{/dialogFormBody}
