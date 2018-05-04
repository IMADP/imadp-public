
	{! Movie !}
	<div class="cf" style="padding: 15px 45px 0 65px" id="movie-{id}">
			
		<div class="left icons-3 align-right" style="padding-top: 3px;">			
			{#. notesId="notes-{nameSlug}-{id}"}
				{>notesButton/}
			{/.}
			{>menuButton/}
		</div>
		
		<div class="left">
		
			<div class="item-leader cf" style="width: 470px;">	
						
				<div class="item-leader-left title" style="max-width: 340px;">
					{#. objectName="movie" paramName="title"}
						{>editable/}
					{/.}	
				</div>
					
				{#. objectName="movie"}
					{>ratingControl/}
				{/.}
				
			</div>
			
			<div class="subtitle" style="margin-left: 10px; max-width: 470px;">
				{#. objectName="movie" paramName="tag"}
					{>editable/}
				{/.}
			</div>
			
			{#. notesId="notes-{nameSlug}-{id}"}
				{>notes/}
			{/.}
			
		</div>
		
		{>movie_movieMenu/}
		
	</div>