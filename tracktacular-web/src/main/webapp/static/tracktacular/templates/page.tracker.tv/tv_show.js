
	{! Show !}
	<div class="cf" style="padding: 15px 45px 0 65px" id="show-{id}">
			
		<div class="left icons-3 align-right" style="padding-top: 3px;">			
			
			{#. notesId="notes-{nameSlug}-{id}"}
				{>notesButton/}
			{/.}
			
			{?episodes}
				{#. targetId="show-episodes-{nameSlug}-{id}" collapsed=showCollapsed}
	    			{>toggleCollapse/}
	    		{/.} {~s}
			{/episodes}
			
			{>menuButton/}
						
		</div>
		
		<div class="left">
		
			<div class="item-leader cf" style="width: 470px;">	
						
				<div class="item-leader-left title" style="max-width: 340px;">
					{#. objectName="show" paramName="title"}
						{>editable/}
					{/.}	
				</div>
					
				{#. objectName="show"}
					{>ratingControl/}
				{/.}
				
			</div>
			
			<div class="subtitle" style="margin-left: 10px; max-width: 470px;">
				{#. objectName="show" paramName="tag"}
					{>editable/}
				{/.}
			</div>
			
			{#. notesId="notes-{nameSlug}-{id}"}
				{>notes/}
			{/.}
			
			<div id="show-episodes-{nameSlug}-{id}" class="none" >
				{#episodes}
					{>tv_episode/}
				{/episodes}
			</div>
			
		</div>
		
		{>tv_showMenu/}
		
	</div>