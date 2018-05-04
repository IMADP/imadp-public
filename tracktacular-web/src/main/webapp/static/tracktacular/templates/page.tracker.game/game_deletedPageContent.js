
	{#contentBody}
		
		<h2>Deleted Games</h2>		
				
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedGamesCount} Deleted {@when test="{deletedGamesCount} == 1"} Game {:else} Games {/when}
		</h4>				
	
		{#deletedGames}
			{>game_deletedGame/}
		{/deletedGames}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
			
	{/contentBody}