
	{#contentBody}
		
		<h2>Deleted Tasks</h2>		
			
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedTasksCount} Deleted {@when test="{deletedTasksCount} == 1"} Task {:else} Tasks {/when}
		</h4>
		
		{#deletedTasks}
			{>task_inactiveTask/}
		{/deletedTasks}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
				
	{/contentBody}