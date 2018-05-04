	
	{#contentBody}
	
		{#. addButtonTemplate="goal_goalDialog" addButtonTitle="Add Goal" addButtonTemplateContextId="newGoal"}
			{>addButton/}		
		{/.}
		
		{#activeGoals}
            {>goal_goal/}
        {/activeGoals}
	   	
	    {>pageNavigator/}
        
	{/contentBody}