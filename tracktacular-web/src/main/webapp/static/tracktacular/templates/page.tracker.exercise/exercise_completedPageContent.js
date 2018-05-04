
	{#contentBody}
			
 		<h2>Completed Routines</h2>				
	
		<h4 style="padding-top: 5px; padding-left:60px;">
			{completedRoutinesCount} Completed {@when test="{completedRoutinesCount} == 1"} Routine {:else} Routines {/when}
		</h4>				
					 
		{#completedRoutines}
			{>exercise_routine/}
		{/completedRoutines}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
				
	{/contentBody}