	<li class="ui-li ui-li-static li-item">
		<div class="item-wrapper">
				
		    <div class="item-left">
		    	{#. menuButtonTemplate="restaurant_restaurantMenu"}
					{>menuButton/}		
				{/.}
			</div>
			
		    <div class="item-center">
	    	 	<div class="title">{name}</div>
	    	 	<div class="subtitle">{city}, {state}</div>
	    	 	<div class="subscript">{tag}</div>
		    	{?completed} {>rating:rating/} {/completed}
		    </div>
		    
		    <div class="item-right">
		    	{>notesButton/}
		    	
		    	{?meals}
			    	{#. targetId="songs-{id}" collapsed="true"}
		    			{>toggleCollapse/}
		    		{/.}
	    		{/meals}
	        </div>
		        
		</div>		
		
	 	{>notes/}
	 	
	</li>
	
	<li id="songs-{id}" class="none">
		<ul class="container">
	
			{#meals}
				{>restaurant_meal/}
			{/meals}
	    
	    </ul>
    
    </li>
	
	