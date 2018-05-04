	<li class="ui-li ui-li-static li-item">
		<div class="item-wrapper">
				
		    <div class="item-left">
		    	{#. menuButtonTemplate="book_bookMenu"}
					{>menuButton/}		
				{/.}
			</div>
			
		    <div class="item-center">
	    		<div class="title">{title}</div>
	    	 	<div class="subtitle">{author}</div>
	    	 	<div class="subscript">{tag}</div>
		    	{?completed} {>rating:rating/} {/completed}
    	 	</div>
		    
		    <div class="item-right">
		    	{>notesButton/}
		    	
		    	{?chapters}
			    	{#. targetId="chapters-{id}" collapsed="true"}
		    			{>toggleCollapse/}
		    		{/.}
	    		{/chapters}
	        </div>
		        
		</div>		
		
	 	{>notes/}
	 	
	</li>
	
	<li id="chapters-{id}" class="none">
		<ul class="container">
	
			{#chapters}
				{>book_chapter/}
			{/chapters}
	    
	    </ul>
    
    </li>
	
	