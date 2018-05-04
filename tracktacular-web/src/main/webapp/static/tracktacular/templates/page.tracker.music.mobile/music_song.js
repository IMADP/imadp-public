	<li class="ui-li ui-li-static li-item">
		<div class="item-wrapper">
				
		    <div class="item-left">
	    		{#. menuButtonTemplate="music_songMenu"}
					{>menuButton/}		
				{/.}
			</div>
			
		    <div class="item-center">
	    	 	<div class="title">{title}</div>
	    	 	<div class="subtitle">{artist}</div>
	    	 	<div class="subtitle"><i>{album}</i></div>
	    	 	
	    	 	{?instrument}
					<div class="subscript">
						<b style="color:green;">{progress}%</b> on {instrument}
					</div>
				{/instrument}
	    	 	
		    	{?completed} {>rating:rating/} {/completed}
		    </div>
		    
		    <div class="item-right">
		    	{>notesButton/}
	        </div>
		        
		</div>		
		
	 	{>notes/}
	 	
	</li>