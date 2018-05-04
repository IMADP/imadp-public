	<li class="ui-li ui-li-static li-item">
		<div class="item-wrapper">
				
		    <div class="item-left">
		    	{#. menuButtonTemplate="game_gameMenu"}
					{>menuButton/}		
				{/.}
			</div>
			
		    <div class="item-center">
	    	 	<div class="title">{title}</div>
	    	 	<div class="subtitle">{platform}</div>
	    	 	<div class="subscript">{tag}</div>
		    	{?completed} {>rating:rating/} {/completed}
		    </div>
		    
		    <div class="item-right">
		    	{>notesButton/}
	        </div>
		        
		</div>		
		
	 	{>notes/}
	 	
	</li>