
	{#contentBody}
	
		<h2>Completed Budgets</h2>				
			
		<h4 style="padding-top: 5px; padding-left:60px;">
			{completedBudgetsCount} Completed {@when test="{completedBudgetsCount} == 1"} Budget {:else} Budgets {/when}
		</h4>
		
		{#completedBudgets}
			{>budget_budget/}
		{/completedBudgets}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
			
	{/contentBody}