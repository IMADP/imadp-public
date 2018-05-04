
	{#contentBody}
			
		<header class="content-body-header cf">
		
			<div class="left">
				<h2>{name}</h2>				
		
				<h4>
					{workoutCount} {@when test="{workoutCount} == 1"} Workout {:else} Workouts {/when}
				</h4>
			</div>
			 
			<div class="right">
				{#. addButtonTemplateContextId="newWorkout" addButtonTemplate="exercise_workoutDialog" addButtonTitle="Add Workout"}
					{>addButton/}		
				{/.}
			</div>	
			
	    </header>    
			 			
		{#workouts}
            {>exercise_workout/}
        {/workouts}
        
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
				
		{>exercise_tutorial/}
		
	{/contentBody}