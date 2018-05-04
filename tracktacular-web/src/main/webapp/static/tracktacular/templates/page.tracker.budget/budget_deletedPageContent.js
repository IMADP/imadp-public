
	{#contentBody}
	
		<h2>Deleted Budgets</h2>				
		
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedBudgetsCount} Deleted {@when test="{deletedBudgetsCount} == 1"} Budget {:else} Budgets {/when}
		</h4>
			
		{#deletedBudgets}
			{>budget_budget/}
		{/deletedBudgets}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
			
	{/contentBody}