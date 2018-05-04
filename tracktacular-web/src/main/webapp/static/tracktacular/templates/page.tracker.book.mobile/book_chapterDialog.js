
	{#. dialogTitle="Save Chapter" event="saveChapter" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="chapter" value="{id}" />							
		<input type="hidden" name="chapter.book" value="{bookId}" />							
			
		Add a new chapter for {bookTitle}
		
		<label for="chapter.title" class="primary required">Title</label>
		{#. object="chapter.title" max="256"}{>inputText/}{/.}
		
		<label for="chapter.rating" class="primary">Chapter Rating</label> 
		<label for="chapter.rating" class="secondary">Rate this chapter after it has been completed</label>  
		{#. object="chapter.rating" options=ratings optionName="name" optionValue="value"}{>inputSelect/}{/.}
		
		<label for="chapter.targetDate" class="primary">Target Date</label>
		<label for="chapter.targetDate" class="secondary">An optional target date to read this chapter</label>  
		{#. object="chapter.targetDate"}{>inputDate/}{/.}
			
		<label for="chapter.notes" class="primary">Notes</label>
		{#. object="chapter.notes"}{>inputNotes/}{/.}
		
	{/dialogFormBody}
