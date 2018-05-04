		
	<div id="item-{id}" style="padding-bottom: 5px">
		
		<div class="cf">
			
			<div class="left icons-3 align-right" style="padding-top: 3px;">
				{>notesButton/}
				{>menuButton/}	
							
				{?income}
					<span class="icon-s s-med"></span>
				{:else}
					<span class="icon-s s-red"></span>
				{/income}
				
			</div>
			
			<div class="left">
			
				<div class="item-leader item-leader-{depth} cf">	
							
					<div class="item-leader-left item-leader-left-{depth} title">
					
						{#. cssClass="budget-item-name" objectName="item" paramName="name"}
							{>editable/}
						{/.}
						
					</div>
						
					<div class="item-leader-right title center">
						{totalItemAmount} 
				
						{?quantity}
						 	<div class="subtitle">
						 		({quantity}x {amount})
						 	</div>
					 	{/quantity}
					</div>
					
				</div>
			
			</div>
			
		</div>
		
		{>notes/}		
				
		{>budget_itemMenu/}
								
	</div>