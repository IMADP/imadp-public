
	{#contentBody}
			
		<nav>
			
			{^publicMode}
				{#. addButtonTemplate="music_songDialog" addButtonTitle="Add Song"}
					{>addButton/}		
				{/.}
			{/publicMode}		
			
			{>music_songChart:songsByArtist/}			
			{>music_songChart:songsByTitle/}
			{>music_songChart:songsByAlbum/}			
			{>music_songChart:songsByTag/}			
			{>music_songChart:songsByInstrument/}			
			{>music_songChart:songsByRating/}
				
	    </nav>
	    
	    <article class="item" style="padding-bottom: 50px">
		
			<header class="cf">						
				<div class="left">
					<span class="icon-s-blank"></span>
				</div>	
				<div class="right">
					{! 
					<select class="to-scroll">
						<option selected="selected">Jump to section</option>
						{#songCategories}
							<option value="song-category-{nameSlug}">{name}</option>
						{/songCategories}
					</select>
					!}
				</div>			
			</header>
			
			<h3 class="center">
				Music Library
			</h3>  
			
			<h4 class="center">
				{songCount} {@when test="{songCount} == 1"} Song {:else} Songs {/when}
			</h4>  
			        		
			{#songCategories}
	            {>music_songCategory/}
	        {/songCategories}
			
		</article>
        		
        {>music_songTutorial/}
        
	{/contentBody}