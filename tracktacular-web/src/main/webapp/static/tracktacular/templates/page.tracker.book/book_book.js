
	{! Book !}
	<div class="cf" style="padding: 15px 45px 0 65px" id="book-{id}">
			
		<div class="left icons-3 align-right" style="padding-top: 3px;">			
			
			{#. notesId="notes-{nameSlug}-{id}"}
				{>notesButton/}
			{/.}
			
			{?chapters}
				{#. targetId="book-chapters-{nameSlug}-{id}" collapsed=bookCollapsed}
	    			{>toggleCollapse/}
	    		{/.} {~s}
			{/chapters}
			
			{>menuButton/}
						
		</div>
		
		<div class="left">
		
			<div class="item-leader cf" style="width: 470px;">	
						
				<div class="item-leader-left title" style="max-width: 340px;">
					{#. objectName="book" paramName="title"}
						{>editable/}
					{/.}	
				</div>
					
				{#. objectName="book"}
					{>ratingControl/}
				{/.}
				
			</div>
			
			<div class="subtitle" style="margin-left: 10px; max-width: 470px;">				
				{#. objectName="book" paramName="author"}
					{>editable/}
				{/.}
			</div>
			
			<div class="subscript" style="margin-left: 10px; max-width: 470px;">				
				{#. objectName="book" paramName="tag"}
					{>editable/}
				{/.}
			</div>
			
			{#. notesId="notes-{nameSlug}-{id}"}
				{>notes/}
			{/.}
			
			<div id="book-chapters-{nameSlug}-{id}" class="none" >
				{#chapters}
					{>book_chapter/}
				{/chapters}
			</div>
			
		</div>
		
		{>book_bookMenu/}
		
	</div>