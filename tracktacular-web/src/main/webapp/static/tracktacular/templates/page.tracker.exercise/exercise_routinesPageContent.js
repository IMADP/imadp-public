
	{#contentBody}
		
		<nav>
			
			{#. addButtonTemplateContextId="newRoutine" addButtonTemplate="exercise_routineDialog" addButtonTitle="Add Routine"}
				{>addButton/}		
			{/.}
					
			{#activeRoutines}
				<div class="option" id="navigation-chart-{id}">
		
					<span class="option-icon navigation-chart" 
						data-chart-data="{chartData|json}"
					    data-link-id="navigation-chart-link-{id}">
					</span>
						
					<a id="navigation-chart-link-{id}" href="{viewRoutineUrl}"> {name} </a>
							
				</div>				 
	        {/activeRoutines}
	        
	    </nav>	    
		
		{#activeRoutines}
            {>exercise_routine/}
        {/activeRoutines}		
				
		{>exercise_tutorial/}
		
	{/contentBody}