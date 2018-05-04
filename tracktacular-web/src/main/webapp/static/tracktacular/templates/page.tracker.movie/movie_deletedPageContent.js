
	{#contentBody}
		
		<h2>Deleted Movies</h2>		
				
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedMoviesCount} Deleted {@when test="{deletedMoviesCount} == 1"} Movie {:else} Movies {/when}
		</h4>				
	
		{#deletedMovies}
			{>movie_deletedMovie/}
		{/deletedMovies}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
			
	{/contentBody}