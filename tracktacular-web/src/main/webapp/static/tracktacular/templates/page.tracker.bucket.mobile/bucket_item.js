
	<li class="ui-li ui-li-static li-item">
		<div class="item-wrapper">
				
		    <div class="item-left">
		    	{#. menuButtonTemplate="bucket_itemMenu"}
					{>menuButton/}		
				{/.}
			</div>
			
		    <div class="item-center">
	    	 	<div class="title {?completed}strike{/completed}">{name}</div>
	    	 	<div class="subtitle">{description}</div>
	    	 	{?completed}<div class="subscript">Completed on {completedDate}</div>{/completed}
		    </div>
		    
		    <div class="item-right">
		    	{>notesButton/}
	        </div>
		        
		</div>		
		
	 	{>notes/}
	 	
	</li>