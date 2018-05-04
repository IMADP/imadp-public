	<li class="ui-li ui-li-static li-item">
		<div class="item-wrapper">
				
		    <div class="item-left">
		    	{#. menuButtonTemplate="restaurant_mealMenu"}
					{>menuButton/}		
				{/.}
			</div>
			
		    <div class="item-center">
	    	 	<div class="title">{restaurantName}</div>
		     	<div class="subtitle">{name}</div>
		     	{?completed} {>rating:rating/} {/completed}
		    </div>
		    
		    <div class="item-right">
		    	{>notesButton/}
	        </div>
		        
		</div>		
		
	 	{>notes/}
	 	
	</li>