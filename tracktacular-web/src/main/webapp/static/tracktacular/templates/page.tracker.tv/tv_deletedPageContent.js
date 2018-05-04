
	{#contentBody}
		
		<h2>Deleted Shows</h2>		
				
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedShowsCount} Deleted {@when test="{deletedShowsCount} == 1"} Show {:else} Shows {/when}
		</h4>				
	
		{#deletedShows}
			{>tv_deletedShow/}
		{/deletedShows}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
			
	{/contentBody}