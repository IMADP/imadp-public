
	{#contentBody}
		
		<h2>Deleted Restaurants</h2>		
				
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedRestaurantsCount} Deleted {@when test="{deletedRestaurantsCount} == 1"} Restaurant {:else} Restaurants {/when}
		</h4>				
	
		{#deletedRestaurants}
			{>restaurant_deletedRestaurant/}
		{/deletedRestaurants}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
			
	{/contentBody}