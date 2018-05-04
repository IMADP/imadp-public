	
	<div id="restaurant-category-{nameSlug}" style="padding-bottom: 5px;">
		
		<div class="cf" style="margin: 40px 60px 0 60px;">
           
            <div class="left" style="margin: -5px 0 0 5px;">	
				<h3>
					{name}
				</h3>
				<h4 style="padding-left: 2px;">
					{restaurantCount} {@when test="{restaurantCount} == 1"} Restaurant {:else} Restaurants {/when}
				</h4>
            </div>
             
        </div>	
        
    	{#restaurants}
        	{>restaurant_restaurant/}
        {/restaurants}
	
	</div>
