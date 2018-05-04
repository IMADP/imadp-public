
	<li class="ui-li ui-li-static li-item">
		<div class="item-wrapper">
				
		    <div class="item-left">
		    	{#. menuButtonTemplate="gift_giftMenu"}
					{>menuButton/}		
				{/.}
			</div>
			
		    <div class="item-center">
	    	 	<div class="title">{name}</div>
	    	 	<div class="subtitle">
					{?received}
						Gift from {sender} on {date}		
					{:else}
						Gift to {receiver} on {date}
					{/received}		
				</div>
	    	 	{?formattedPrice}<div class="subscript" style="color: green;">{formattedPrice}</div>{/formattedPrice}
		    </div>
		    
		    <div class="item-right">
		    	{>notesButton/}
	        </div>
		        
		</div>		
		
	 	{>notes/}
	 	
	</li>