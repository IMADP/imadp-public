		
	<article class="item">
			
		<header>	
			{>menuButton/}
			{>notesButton/}
		</header>
		
		<h3 class="center">		
			{#. objectName="movie" paramName="title" successIds="movie-{id}"}
				{>editable/}
			{/.}   
		</h3>
		
		{>notes/}
		
		{>persistableStateDetails/}
		
		{>movie_deletedMovieMenu/}
		
	</article>