	
	<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">
		<li class="ui-li ui-li-static li-item-header">
			<div class="item-wrapper">
			    <div class="item-left">
			    	{#. menuButtonTemplate="budget_budgetMenu"}
						{>menuButton/}		
					{/.}
			    </div>
		    	<div class="item-center">
		    	 	<div class="title">{name}</div>
		    	 	<div class="subtitle">{description}</div>
		    	 	{>notes/}
    	 		</div>
    	 		<div class="item-right">
    	 			{>notesButton/}		
			    </div>
			</div>	
		</li>
		
		{?showItems}	
			
			{#categories}
	            {>budget_itemCategory/}
	        {/categories}
	        
			<li class="ui-li ui-li-static li-item">
				
			 	<div class="title center">
					<b>Total Balance: <span class="{?income} income {:else} expense {/income}">{netAmount}</span></b>
				</div>
			 	
			 	{>persistableStateDetails/}
			 	
			</li>
			
	     {:else}

			<li class="center ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-c">
				<div class="center ui-btn-inner ui-li">
					<div class="ui-btn-text">
						<a href="{viewBudgetUrl}" class="center ui-link-inherit">
							<b>Total Balance: <span class="{?income} income {:else} expense {/income}">{netAmount}</span></b>
							{>persistableStateDetails/}
						</a>
					</div>
					<span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span>
					
				</div>
			</li>
			
		{/showItems}
        	
    </ul>	