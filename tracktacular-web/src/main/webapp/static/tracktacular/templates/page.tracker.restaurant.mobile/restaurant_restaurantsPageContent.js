
	{#contentBody}
			
		{#. addButtonTemplate="restaurant_restaurantDialog" addButtonTitle="Add Restaurant"}
			{>addButton/}		
		{/.}
		
		<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">
			<li class="ui-li ui-li-static li-item-header li-item-header-no-icon">
				<div class="item-wrapper">
				    <div class="item-center">
			    	 	<div class="title">{restaurantCount} {@when test="{restaurantCount} == 1"} Restaurant {:else} Restaurants {/when}</div>
	    	 		</div>
				</div>	
			</li>
			
			{#restaurantCategories}
	            {>restaurant_restaurantCategory/}
	        {/restaurantCategories}
	        	
        </ul>	
        
	    {>pageNavigator/}
        
	{/contentBody}