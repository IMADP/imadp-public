
	{#contentBody}
			
		<nav>
			
			{^publicMode}
				{#. addButtonTemplate="restaurant_restaurantDialog" addButtonTitle="Add Restaurant"}
					{>addButton/}		
				{/.}
			{/publicMode}		
			
			{>restaurant_restaurantChart:restaurantsByName/}
			
			{>restaurant_restaurantChart:restaurantsByCity/}
			
			{>restaurant_restaurantChart:restaurantsByState/}
			
			{>restaurant_restaurantChart:restaurantsByTag/}
			
			{>restaurant_restaurantChart:restaurantsByRating/}
				
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
						{#restaurantCategories}
							<option value="restaurant-category-{nameSlug}">{name}</option>
						{/restaurantCategories}
					</select>
					!}
				</div>			
			</header>
			
			<h3 class="center">
				Restaurant Library
			</h3>  
			
			<h4 class="center">
				{restaurantCount} {@when test="{restaurantCount} == 1"} Restaurant {:else} Restaurants {/when}
			</h4>  
			        		
			{#restaurantCategories}
	            {>restaurant_restaurantCategory/}
	        {/restaurantCategories}
			
		</article>
        		
        {>restaurant_tutorial/}
        
	{/contentBody}