
	{#contentBody}
		
		<h2>Deleted Albums</h2>		
				
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedAlbumsCount} Deleted {@when test="{deletedAlbumsCount} == 1"} Album {:else} Albums {/when}
		</h4>				
	
		{#deletedAlbums}
			{>music_deletedAlbum/}
		{/deletedAlbums}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
			
	{/contentBody}