
	{#contentBody}
		
		<h2>Completed Goals</h2>				
	
		<h4 style="padding-top: 5px; padding-left:60px;">
			{completedGoalsCount} Completed {@when test="{completedGoalsCount} == 1"} Goal {:else} Goals {/when}
		</h4>				
		
		{#completedGoals}
			{>goal_goal/}
		{/completedGoals}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
				
	{/contentBody}                                                                                                                                                                                                                                                                                                                                                            