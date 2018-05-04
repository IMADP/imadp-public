
	{#. menuTitle="Goal Options"}
		{>menu/}		
	{/.}
	
	{<menuBody}	
	
		{?activeState}
			{#. itemTitle="Edit Goal" icon="s-app-edit" template="goal_goalDialog" contextId="{id}"}
				{>menuItemDialogForm/}
			{/.}
					
			{#. itemTitle="Add Objective" icon="s-add" template="goal_objectiveDialog" contextId="{newObjective.id}"}
				{>menuItemDialogForm/}
			{/.}
		{/activeState}
									
		{#. itemTitle="Activate Goal" objectName="goal" successIds="content-messages, content-body"}
			{>menuItemToStateActive/}
		{/.}
		
		{#. itemTitle="Complete Goal" objectName="goal" successIds="content-messages, content-body"}
			{>menuItemToStateArchived/}
		{/.}
		
		{#. itemTitle="Delete Goal" objectName="goal" successIds="content-messages, content-body"}
			{>menuItemToStateDeleted/}
		{/.}
		
		{?deletedState}
			{#. itemTitle="Perma-Delete Goal" objectName="goal" successIds="content-messages, content-body"}
				{>menuItemToDelete/}
			{/.}
		{/deletedState}
							
	{/menuBody}	