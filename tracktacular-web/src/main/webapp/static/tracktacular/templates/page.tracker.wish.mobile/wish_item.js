
	<li class="ui-li ui-li-static li-item">
		<div class="item-wrapper">
				
		    <div class="item-left">
		    	{#. menuButtonTemplate="wish_itemMenu"}
					{>menuButton/}		
				{/.}
			</div>
			
		    <div class="item-center">
	    	 	<div class="title">{name}</div>
	    	 	<div class="subtitle">{description}</div>
	    	 	{?formattedPrice}<div class="subscript" style="color: green;">{formattedPrice}</div>{/formattedPrice}
		    </div>
		    
		    <div class="item-right">
		    	{>notesButton/}
	        </div>
		        
		</div>		
		
	 	{>notes/}
	 	
	</li>