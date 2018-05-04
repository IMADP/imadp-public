	<li class="ui-li ui-li-static li-item">
		<div class="item-wrapper">
				
		    <div class="item-left">
		    	{#. menuButtonTemplate="tv_showMenu"}
					{>menuButton/}		
				{/.}
			</div>
			
		    <div class="item-center">
	    	 	<div class="title">{title}</div>
	    	 	<div class="subtitle">{tag}</div>
		    	{?completed} {>rating:rating/} {/completed}
		    </div>
		    
		    <div class="item-right">
		    	{>notesButton/}
		    	
		    	{?episodes}
			    	{#. targetId="episodes-{id}" collapsed="true"}
		    			{>toggleCollapse/}
		    		{/.}
	    		{/episodes}
	        </div>
		        
		</div>		
		
	 	{>notes/}
	 	
	</li>
	
	<li id="episodes-{id}" class="none">
		<ul class="container">
	
			{#episodes}
				{>tv_episode/}
			{/episodes}
			    
	    </ul>
    
    </li>
	
	