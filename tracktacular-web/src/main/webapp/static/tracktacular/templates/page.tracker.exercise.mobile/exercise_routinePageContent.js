
	{#contentBody}
			
		{#. addButtonTemplateContextId="newWorkout" addButtonTemplate="exercise_workoutDialog" addButtonTitle="Add Workout"}
			{>addButton/}		
		{/.}
			
		{#workouts}
            {>exercise_workout/}
        {/workouts}
        
	    {>pageNavigator/}
        
	{/contentBody}