
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no restaurants in your library.     
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
							
		<header class="report-info">	
			{restaurantCount} {@when test="{restaurantCount} == 1"} Restaurant {:else} Restaurants {/when} in your Library	
		</header>
		
		{#targetDateRestaurants}
	         <header class="report-alert">	
	         	<span class="icon-s s-alert"></span> The target date for restaurant <i>{name}</i> is today!
			</header>
        {/targetDateRestaurants}
		
		{#targetDateMeals}
	         <header class="report-alert">	
	         	<span class="icon-s s-alert"></span> The target date for meal <i>{name}</i> is today!
			</header>
        {/targetDateMeals}
		
		<div style="margin: 15px 0 15px 0; text-align:center;">
			{>restaurant_restaurantChart:restaurantsByName/}
			
			{>restaurant_restaurantChart:restaurantsByCity/}
			
			{>restaurant_restaurantChart:restaurantsByState/}
			
			{>restaurant_restaurantChart:restaurantsByTag/}
			
			{>restaurant_restaurantChart:restaurantsByRating/}
       	</div>
       	
		{?unratedRestaurants}
	    	 <div class="title center" style="margin-top: -10px;">
       			<b>{unratedRestaurantCount} Unrated {@when test="'{unratedRestaurantCount}' == 1"} Restaurant {:else} Restaurants {/when}</b>
       		 </div>
       	
       		 <div style="margin: 10px 50px 35px 50px;">
		       	{#unratedRestaurants}					
				    <div class="cf" style="padding: 15px 45px 0 45px">			
						<div class="left icons-3 align-right" style="padding-top: 3px;"></div>
						<div class="left">
							<div class="item-leader cf" style="width: 470px;">
								<div class="item-leader-left title" style="max-width: 340px;">
									{name}	
								</div>
								<div class="item-leader-right center">
									<span class="icon-s s-question"></span> 
								</div>
							</div>
							<div class="subtitle" style="margin-left: 10px; max-width: 470px;">				
								{city}, {state}
							</div>
							<div class="subscript" style="margin-left: 10px; max-width: 470px;">				
								{tag}
							</div>
						</div>
					</div>								
				{/unratedRestaurants}
       		</div>
       		
       	{/unratedRestaurants}
       	
	{/trackerReportEnabled}