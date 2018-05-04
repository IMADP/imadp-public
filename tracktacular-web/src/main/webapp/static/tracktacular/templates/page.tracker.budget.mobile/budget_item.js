
	<li class="ui-li ui-li-static li-item">
		<div class="item-wrapper">
				
		    <div class="item-left">
		    	{#. menuButtonTemplate="budget_itemMenu"}
					{>menuButton/}		
				{/.}
			</div>
			
		    <div class="item-center">
	    	 	<div class="title">
	    	 		{name}
	    	 	</div>
	    	 	<div class="subtitle bold {?income} income {:else} expense {/income}">{totalItemAmount}</div>
	    	 	{?quantity}<div class="subscript">{quantity}x {amount}</div>{/quantity}
		    </div>
		    
		    <div class="item-right">
		    	{>notesButton/}
	        </div>
		        
		</div>		
		
	 	{>notes/}
	 	
	</li>