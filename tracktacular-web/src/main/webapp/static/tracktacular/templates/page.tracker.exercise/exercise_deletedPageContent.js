
	{#contentBody}
		
	 	<h2>Deleted Routines</h2>				
	
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedRoutinesCount} Deleted {@when test="{deletedRoutinesCount} == 1"} Routine {:else} Routines {/when}
		</h4>				
			
		{#deletedRoutines}
			{>exercise_routine/}
		{/deletedRoutines}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
				
	{/contentBody}