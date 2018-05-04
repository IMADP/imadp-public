
	{#. dialogTitle="Save Album" event="saveAlbum" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="album" value="{id}" />									
			
		Add a new album
		
		<label for="album.title" class="primary required">Title</label>
		{#. object="album.title" max="256"}{>inputText/}{/.}
		
		<label for="album.artist" class="primary required">Artist</label>
		{#. object="album.artist" max="256"}{>inputText/}{/.}
		
		<label for="album.tag" class="primary">Tags (comma separated list)</label>
		<label for="album.tag" class="secondary">Add tags as a comma separated list (ie: alternative, pop, running music)</label>  
		{#. object="album.tag" max="256"}{>inputText/}{/.}
		
		<label for="album.rating" class="primary">Rating</label> 
		<label for="album.rating" class="secondary">Rate this album after listening</label>  
		{#. object="album.rating" options=ratings optionName="name" optionValue="value"}{>inputSelect/}{/.}
		
		<label for="album.targetDate" class="primary">Target Date</label>
		<label for="album.targetDate" class="secondary">An optional target date to listen or learn</label>  
		{#. object="album.targetDate"}{>inputDate/}{/.}
		
		<label for="album.notes" class="primary">Notes</label>
		{#. object="album.notes"}{>inputNotes/}{/.}
		
	{/dialogFormBody}
