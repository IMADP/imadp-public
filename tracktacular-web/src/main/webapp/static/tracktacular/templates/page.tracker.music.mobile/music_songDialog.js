
	{#. dialogTitle="Save Song" event="saveSong" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="song" value="{id}" />								
		<input type="hidden" name="song.progress" id="songProgress" value="{progress}" />							
			
		Add a new song
		
		<label for="song.title" class="primary required">Title</label>
		{#. object="song.title" max="256"}{>inputText/}{/.}
		
		<label for="song.artist" class="primary required">Artist</label>
		{#. object="song.artist" max="256"}{>inputText/}{/.}
		
		<label for="song.album" class="primary">Album</label>
		{#. object="song.album" max="256"}{>inputText/}{/.}
		
		<label for="song.tag" class="primary">Tags (comma separated list)</label>
		<label for="song.tag" class="secondary">Add tags as a comma separated list (ie: alternative, pop, running music)</label>  
		{#. object="song.tag" max="256"}{>inputText/}{/.}
		
		<label for="song.rating" class="primary">Rating</label> 
		<label for="song.rating" class="secondary">Rate this song after listening</label>  
		{#. object="song.rating" options=ratings optionName="name" optionValue="value"}{>inputSelect/}{/.}
		
		<label for="song.targetDate" class="primary">Target Date</label>
		<label for="song.targetDate" class="secondary">An optional target date to listen or learn</label>  
		{#. object="song.targetDate"}{>inputDate/}{/.}
		
		<label for="song.instrument" class="primary">Instrument</label>
		<label for="song.instrument" class="secondary">To track your progress learning this song, enter an instrument (ie: Guitar) and use the progress input</label>
		{#. object="song.instrument" max="256"}{>inputText/}{/.}
		
		<label for="song.progress" class="primary">Progress</label>
		<label for="song.progress" class="secondary">Enter your progress percentage if you are learning to play on an instrument</label>
		{#. object="song.progress" min="0" max="100"}{>inputNumber/}{/.}
		
		<label for="song.notes" class="primary">Notes</label>
		{#. object="song.notes"}{>inputNotes/}{/.}
		
	{/dialogFormBody}
