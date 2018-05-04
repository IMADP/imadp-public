	
	{#. menuTitle="Objective Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{#. itemTitle="Edit Objective" icon="s-app-edit" template="goal_objectiveDialog" contextId="{id}"}
			{>menuItemDialogForm/}
		{/.}
					
		{#. itemTitle="Clone Objective" icon="s-app-add" template="goal_objectiveDialog" contextId="{id}-clone"}
			{>menuItemDialogForm/}
		{/.}
					
		{^completed}	
			{#. itemTitle="Complete Objective" objectName="objective" successIds="goal-{goalId}, navigation-chart-{goalId}, navigation-chart-all"}
				{>menuItemToStateArchived/}
			{/.}
		{:else}
			{#. itemTitle="Uncomplete Objective" objectName="objective" successIds="goal-{goalId}, navigation-chart-{goalId}, navigation-chart-all"}
				{>menuItemToStateActive/}
			{/.}
		{/completed}
		
		{#. itemTitle="Delete Objective" objectName="objective" successIds="goal-{goalId}, navigation-chart-{goalId}, navigation-chart-all"}
			{>menuItemToDelete/}
		{/.}
										
	{/menuBody}