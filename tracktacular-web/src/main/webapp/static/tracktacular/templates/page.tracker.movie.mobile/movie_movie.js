	<li class="ui-li ui-li-static li-item">
		<div class="item-wrapper">
				
		    <div class="item-left">
		    	{#. menuButtonTemplate="movie_movieMenu"}
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
	        </div>
		        
		</div>		
		
	 	{>notes/}
	 	
	</li>