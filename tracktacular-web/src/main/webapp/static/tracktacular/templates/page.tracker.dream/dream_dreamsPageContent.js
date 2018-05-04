
	{#contentBody}
		
		 <header class="content-body-header cf">
		
			<div class="left">
				<h2>Dreams</h2>				
		
				<h4>
					{itemCount} {?favoriteDreamType} Favorite {/favoriteDreamType} {?lucidDreamType} Lucid {/lucidDreamType} {@when test="{itemCount} == 1"} Dream {:else} Dreams {/when}
				</h4>
			</div>
			 
			<div class="right">
				{>dream_dreamChart:allDreams/}
			
				{>dream_dreamChart:favoriteDreams/}
				
				{>dream_dreamChart:lucidDreams/}
				
				<div class="option">
					<a href="{sortUrl}">
						<span class="option-icon">
							<span class="icon-l l-{?descending}asc{:else}desc{/descending}"></span>
						</span>
						{?descending}Oldest{:else}Newest{/descending} First
					</a>
				</div>
				
				{#. addButtonTemplate="dream_dreamDialog" addButtonTitle="Add Dream" addButtonTemplateContextId="newDream"}
					{>addButton/}		
				{/.}
						
			</div>	
			
	    </header>		
			
		{#dreams dreamCollapsed="true"}
			{>dream_dream/}
		{/dreams}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
        		
        {>dream_tutorial/}	
	
	{/contentBody}