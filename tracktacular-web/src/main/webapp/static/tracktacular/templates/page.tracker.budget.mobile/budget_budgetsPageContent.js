
	{#contentBody}
	
		{#. addButtonTemplate="budget_budgetDialog" addButtonTitle="Add Budget"}
			{>addButton/}		
		{/.}
		
		{#activeBudgets}
	        {>budget_budget/}
	    {/activeBudgets}
	   	
	    {>pageNavigator/}
        
	{/contentBody}