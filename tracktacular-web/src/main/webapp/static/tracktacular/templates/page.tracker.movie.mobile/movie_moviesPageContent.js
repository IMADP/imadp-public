
	{#contentBody}
			
		{#. addButtonTemplate="movie_movieDialog" addButtonTitle="Add Movie"}
			{>addButton/}		
		{/.}
		
		<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">
			<li class="ui-li ui-li-static li-item-header li-item-header-no-icon">
				<div class="item-wrapper">
				    <div class="item-center">
			    	 	<div class="title">{movieCount} {@when test="{movieCount} == 1"} Movie {:else} Movies {/when}</div>
	    	 		</div>
				</div>	
			</li>
			
			{#movieCategories}
	            {>movie_movieCategory/}
	        {/movieCategories}
	        	
        </ul>	
        
	    {>pageNavigator/}
        
	{/contentBody}