
	{#contentBody}
		
		<h2>Deleted Goals</h2>				
	
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedGoalsCount} Deleted {@when test="{deletedGoalsCount} == 1"} Goal {:else} Goals {/when}
		</h4>				
			
		{#deletedGoals}
			{>goal_goal/}
		{/deletedGoals}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
			
	{/contentBody}                                                                                                                                                                                                                                                                                                                                                