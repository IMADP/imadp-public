
	{#contentBody}
			
		<nav>
			
			{^publicMode}
				{#. addButtonTemplate="music_albumDialog" addButtonTitle="Add Album"}
					{>addButton/}		
				{/.}
			{/publicMode}	
			
			{>music_albumChart:albumsByArtist/}			
			{>music_albumChart:albumsByTitle/}			
			{>music_albumChart:albumsByTag/}			
			{>music_albumChart:albumsByRating/}
				
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
						{#albumCategories}
							<option value="album-category-{nameSlug}">{name}</option>
						{/albumCategories}
					</select>
					!}
				</div>			
			</header>
			
			<h3 class="center">
				Music Library
			</h3>  
			
			<h4 class="center">
				{albumCount} {@when test="{albumCount} == 1"} Album {:else} Albums {/when}
			</h4>  
			        		
			{#albumCategories}
	            {>music_albumCategory/}
	        {/albumCategories}
			
		</article>
        		
        {>music_albumTutorial/}
        
	{/contentBody}