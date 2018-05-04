
	{#. dialogTitle="Save Episode" event="saveEpisode" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="episode" value="{id}" />							
		<input type="hidden" name="episode.show" value="{showId}" />							
			
		Add a new episode or season for {showTitle}
		
		<label for="episode.title" class="primary required">Title</label>
		<label for="episode.title" class="secondary">Episode or Season name (ie: Pilot, 01x01, or Season 1)</label>  
		{#. object="episode.title" max="256"}{>inputText/}{/.}
		
		<label for="episode.rating" class="primary">Rating</label> 
		<label for="episode.rating" class="secondary">Rate this episode or season after watching</label>  
		{#. object="episode.rating" options=ratings optionName="name" optionValue="value"}{>inputSelect/}{/.}
		
		<label for="episode.targetDate" class="primary">Target Date</label>
		<label for="episode.targetDate" class="secondary">An optional target date to watch</label>  
		{#. object="episode.targetDate"}{>inputDate/}{/.}
		
		<label for="episode.notes" class="primary">Notes</label>
		{#. object="episode.notes"}{>inputNotes/}{/.}
		
	{/dialogFormBody}
