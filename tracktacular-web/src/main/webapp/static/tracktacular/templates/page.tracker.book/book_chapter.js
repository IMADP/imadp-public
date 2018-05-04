
	{! Book Chapter !}
	<div class="cf" style="padding: 10px 0 0 0" id="book-chapter-{id}">
			
		<div class="left icons-2 align-right" style="padding-top: 3px;">	
			{>notesButton/}
			{>menuButton/}
		</div>
		
		<div class="left">
		
			<div class="item-leader cf" style="width: 432px;">	
						
				<div class="item-leader-left title" style="max-width: 305px;">
					<h4>
						{#. objectName="chapter" paramName="title"}
							{>editable/}
						{/.}
					</h4>	
				</div>
					
				{#. objectName="chapter"}
					{>ratingControl/}
				{/.}
				
			</div>
			
			{>notes/}
			
		</div>
		
		{>book_chapterMenu/}
		
	</div>