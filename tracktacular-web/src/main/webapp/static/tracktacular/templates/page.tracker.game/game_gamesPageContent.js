
	{#contentBody}
			
		<nav>
			
			{^publicMode}
				{#. addButtonTemplate="game_gameDialog" addButtonTitle="Add Game"}
					{>addButton/}		
				{/.}
			{/publicMode}		
			
			{>game_gameChart:gamesByTitle/}
			
			{>game_gameChart:gamesByPlatform/}
			
			{>game_gameChart:gamesByTag/}
			
			{>game_gameChart:gamesByRating/}
				
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
						{#gameCategories}
							<option value="game-category-{nameSlug}">{name}</option>
						{/gameCategories}
					</select>
					!}
				</div>			
			</header>
			
			<h3 class="center">
				Game Library
			</h3>  
			
			<h4 class="center">
				{gameCount} {@when test="{gameCount} == 1"} Game {:else} Games {/when}
			</h4>  
			        		
			{#gameCategories}
	            {>game_gameCategory/}
	        {/gameCategories}
			
		</article>
        
        {>game_tutorial/}
        
	{/contentBody}