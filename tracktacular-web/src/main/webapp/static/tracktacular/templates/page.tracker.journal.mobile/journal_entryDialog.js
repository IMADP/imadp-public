
	{#. dialogTitle="Save Entry" event="saveEntry" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="entry" value="{id}" />							
		<input type="hidden" name="entry.journal" value="{journal.id}" />							
		<input type="hidden" name="journal" value="{journal.id}" />							
			
		Add a new entry 
		
		<label for="entry.date" class="primary required">Date</label>  
		{#. object="entry.date"}{>inputDateTime/}{/.}
		
		<label for="entry.title" class="primary required">Title</label>
		{#. object="entry.title" max="256"}{>inputText/}{/.}
		
		<label for="entry.content" class="primary required">Content</label>  
		{#. object="entry.content"}{>inputTextarea/}{/.} 
		
		<label for="entry.notes" class="primary">Notes</label>
		{#. object="entry.notes"}{>inputNotes/}{/.}
		
	{/dialogFormBody}
