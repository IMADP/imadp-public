
	{#contentBody}
		
		<h2>Deleted Dreams</h2>		
		
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedDreamsCount} Deleted {@when test="{deletedDreamsCount} == 1"} Dream {:else} Dreams {/when}
		</h4>				
	
		{#deletedDreams}
			{>dream_dream/}
		{/deletedDreams}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
				
	{/contentBody}