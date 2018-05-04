
	{#. dialogTitle="Save Game" event="saveGame" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="game" value="{id}" />
					
		<div class="sh1">Add a game to track.</div> 
		
		<label for="game.title" class="primary required">Title</label>
		{#. object="game.title" max="256"}{>inputText/}{/.}
		
		<label for="game.platform" class="primary required">Platform</label>
		<label for="game.platform" class="secondary">Game platform (ie: Board Games, Nes, Snes, Xbox)</label>  
		{#. object="game.platform" max="256"}{>inputText/}{/.}
		
		<label for="game.tag" class="primary">Tags (comma separated list)</label>
		<label for="game.tag" class="secondary">Add tags as a comma separated list (ie: Fps, Rpg, Co-op)</label>  
		{#. object="game.tag" max="256"}{>inputText/}{/.}
		
		<label for="game.rating" class="primary">Game Rating</label> 
		<label for="game.rating" class="secondary">Rate this game after it has been played</label>  
		{#. object="game.rating" options=ratings optionName="name" optionValue="value"}{>inputSelect/}{/.}
		
		<label for="game.targetDate" class="primary">Target Date</label>
		<label for="game.targetDate" class="secondary">An optional target date to play this game</label>  
		{#. object="game.targetDate"}{>inputDate/}{/.}
		
		<label for="game.notes" class="primary">Notes</label>
		{#. object="game.notes"}{>inputNotes/}{/.}
		
	{/dialogFormBody}
