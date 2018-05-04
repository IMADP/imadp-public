
	{#. dialogTitle="Save Book" event="saveBook" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="book" value="{id}" />
					
		<div class="sh1">Add a book or short story to track.</div> 
		
		<label for="book.title" class="primary required">Title</label>
		{#. object="book.title" max="256"}{>inputText/}{/.}
		
		<label for="book.author" class="primary required">Author</label>
		{#. object="book.author" max="256"}{>inputText/}{/.}
		
		<label for="book.tag" class="primary">Tags (comma separated list)</label>
		<label for="book.tag" class="secondary">Add tags as a comma separated list (ie: fiction, satire, romance)</label>  
		{#. object="book.tag" max="256"}{>inputText/}{/.}
		
		<div class="cf">
			<div class="half">
				<label for="book.rating" class="primary">Book Rating</label> 
				<label for="book.rating" class="secondary">Rate this book after it has been completed</label>  
				{#. object="book.rating" options=ratings optionName="name" optionValue="value"}{>inputSelect/}{/.}
			</div>
			<div class="half">
				<label for="book.targetDate" class="primary">Target Date</label>
				<label for="book.targetDate" class="secondary">An optional target date to read this book</label>  
				{#. object="book.targetDate"}{>inputDate/}{/.}
			</div>
		</div>
		
		<label for="book.notes" class="primary">Notes</label>
		{#. object="book.notes"}{>inputNotes/}{/.}
		
	{/dialogFormBody}
