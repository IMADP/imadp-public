
	{! Game !}
	<div class="cf" style="padding: 15px 45px 0 65px" id="game-{id}">
			
		<div class="left icons-3 align-right" style="padding-top: 3px;">			
			
			{#. notesId="notes-{nameSlug}-{id}"}
				{>notesButton/}
			{/.}
			
			{>menuButton/}
						
		</div>
		
		<div class="left">
		
			<div class="item-leader cf" style="width: 470px;">	
						
				<div class="item-leader-left title" style="max-width: 340px;">
					{#. objectName="game" paramName="title"}
						{>editable/}
					{/.}	
				</div>
					
				{#. objectName="game"}
					{>ratingControl/}
				{/.}
				
			</div>
			
			<div class="subtitle" style="margin-left: 10px; max-width: 470px;">
				{#. objectName="game" paramName="platform"}
					{>editable/}
				{/.}
			</div>
			
			<div class="subscript" style="margin-left: 10px; max-width: 470px;">				
				{#. objectName="game" paramName="tag"}
					{>editable/}
				{/.}
			</div>
			
			{#. notesId="notes-{nameSlug}-{id}"}
				{>notes/}
			{/.}
			
		</div>
		
		{>game_gameMenu/}
		
	</div>