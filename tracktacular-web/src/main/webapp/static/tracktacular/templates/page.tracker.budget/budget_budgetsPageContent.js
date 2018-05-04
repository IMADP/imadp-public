
	{#contentBody}
	
		<nav>
		
			{#. addButtonTemplate="budget_budgetDialog" addButtonTitle="Add Budget"}
				{>addButton/}		
			{/.}
					
			{#activeBudgets}
				<div class="option" id="navigation-chart-{id}">
		
					<span class="option-icon navigation-chart" 
						data-chart-data="{chartData|json}"
					    data-link-id="navigation-chart-link-{id}">
					</span>
						
					<a id="navigation-chart-link-{id}" href="{viewBudgetUrl}"> {name} </a>
							
				</div>				 
	        {/activeBudgets}
		    
		</nav>
		
		{#activeBudgets}
	        {>budget_budget/}
	    {/activeBudgets}
        
        {>budget_tutorial/}
	
	{/contentBody}