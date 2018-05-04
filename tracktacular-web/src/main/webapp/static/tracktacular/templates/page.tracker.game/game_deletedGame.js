		
	<article class="item">
			
		<header>	
			{>menuButton/}
			{>notesButton/}
		</header>
		
		<h3 class="center">		
			{#. objectName="game" paramName="title" successIds="game-{id}"}
				{>editable/}
			{/.}   
		</h3>
		
		<h4 class="center">
			{#. objectName="game" paramName="platform" successIds="book-{id}"}
				{>editable/}
			{/.}
		</h4>
		
		{>notes/}
		
		{>persistableStateDetails/}
		
		{>game_deletedGameMenu/}
		
	</article>