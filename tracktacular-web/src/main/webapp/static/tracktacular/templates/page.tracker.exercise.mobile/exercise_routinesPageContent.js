
	{#contentBody}
	
		{#. addButtonTemplateContextId="newRoutine" addButtonTemplate="exercise_routineDialog" addButtonTitle="Add Routine"}
			{>addButton/}		
		{/.}
		
		{#activeRoutines}
            {>exercise_routine/}
        {/activeRoutines}	
	   	
	    {>pageNavigator/}
        
	{/contentBody}