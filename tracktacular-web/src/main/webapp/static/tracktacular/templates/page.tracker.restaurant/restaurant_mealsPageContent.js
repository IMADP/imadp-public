
	{#contentBody}
			
		<nav>
			
			{>restaurant_restaurantChart:mealsByName/}
			{>restaurant_restaurantChart:mealsByRestaurant/}
			{>restaurant_restaurantChart:mealsByTag/}
			{>restaurant_restaurantChart:mealsByRating/}
				
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
						{#mealCategories}
							<option value="meal-category-{nameSlug}">{name}</option>
						{/mealCategories}
					</select>
					!}
				</div>			
			</header>
			
			<h3 class="center">
				Restaurant Library
			</h3>  
			
			<h4 class="center">
				{mealCount} {@when test="{mealCount} == 1"} Meal {:else} Meals {/when}
			</h4>  
			        		
			{#mealCategories}
	            {>restaurant_mealCategory/}
	        {/mealCategories}
			
		</article>
        
	{/contentBody}