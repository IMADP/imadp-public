	
	<div class="cf" style="padding: 15px 45px 0 65px" id="album-{id}">
			
		<div class="left icons-3 align-right" style="padding-top: 3px;">
			
			{#. notesId="notes-{nameSlug}-{id}"}
				{>notesButton/}
			{/.}
			
			{>menuButton/}
			
			<span class="icon-s s-album" title="Album"></span>
			
		</div>
		
		<div class="left">
		
			<div class="item-leader cf" style="width: 470px;">	
						
				<div class="item-leader-left title" style="max-width: 340px;">
					{#. objectName="album" paramName="title"}
						{>editable/}
					{/.}	
					<div class="subtitle" style="max-width: 470px;">
						{artist}
					</div>
				</div>
					
				{#. objectName="album"}
					{>ratingControl/}
				{/.}
				
			</div>
			
			{#. notesId="notes-{nameSlug}-{id}"}
				{>notes/}
			{/.}
			
		</div>
		
		{>music_albumMenu/}
		
	</div>