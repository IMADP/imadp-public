		
	<article class="item">
			
		<header>
			{>menuButton/}
			{>notesButton/}
		</header>
		
		<h3 class="center">		
			{#. objectName="album" paramName="title" successIds="album-{id}"}
				{>editable/}
			{/.}   
		</h3>
		
		<h4 class="center">
			{artist} 
		</h4> 	
		
		<h4 class="center">
			<i>{album}</i>
		</h4> 	
		
		{>notes/}
		
		{>persistableStateDetails/}
		
		{>music_deletedAlbumMenu/}
		
	</article>