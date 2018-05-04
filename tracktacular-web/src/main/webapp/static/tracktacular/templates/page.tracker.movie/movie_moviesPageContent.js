
	{#contentBody}
			
		<nav>
			
			{^publicMode}
				{#. addButtonTemplate="movie_movieDialog" addButtonTitle="Add Movie"}
					{>addButton/}		
				{/.}
			{/publicMode}		
			
			{>movie_movieChart:moviesByTitle/}
			
			{>movie_movieChart:moviesByTag/}
			
			{>movie_movieChart:moviesByRating/}
				
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
						{#movieCategories}
							<option value="movie-category-{nameSlug}">{name}</option>
						{/movieCategories}
					</select>
					!}
				</div>			
			</header>
			
			<h3 class="center">
				Movie Library
			</h3>  
			
			<h4 class="center">
				{movieCount} {@when test="{movieCount} == 1"} Movie {:else} Movies {/when}
			</h4>  
			        		
			{#movieCategories}
	            {>movie_movieCategory/}
	        {/movieCategories}
			
		</article>
        		
        {>movie_tutorial/}
        
	{/contentBody}