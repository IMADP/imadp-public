	
	<div class="cf" style="padding: 15px 45px 0 65px" id="song-{id}">
			
		<div class="left icons-3 align-right" style="padding-top: 3px;">

			{#. notesId="notes-{nameSlug}-{id}"}
				{>notesButton/}
			{/.}

			{>menuButton/}

			<span class="icon-s s-song" title="Song"></span>

		</div>
		
		<div class="left">
		
			<div class="item-leader cf" style="width: 470px;">	
						
				<div class="item-leader-left title" style="max-width: 340px;">
					{#. objectName="song" paramName="title"}
						{>editable/}
					{/.}	
					<div class="subtitle" style="max-width: 470px;">
						{artist}
					</div>
					<div class="subscript" style="max-width: 470px;">
						{album}
					</div>
				</div>
				
				{#. objectName="song"}
					{>ratingControl/}
					
					{<ratingControlContent}
				    	{?instrument}
							<div class="subscript center">
								<b style="color:green;">{progress}%</b> on {instrument}
							</div>
						{/instrument}
				    {/ratingControlContent}
				{/.}
				
			</div>
			
			{#. notesId="notes-{nameSlug}-{id}"}
				{>notes/}
			{/.}
			
		</div>
		
		{>music_songMenu/}
		
	</div>