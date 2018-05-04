
	{#contentBody}
			
 		<h2>Completed Tasks</h2>				
 		
		<h4 style="padding-top: 5px; padding-left:60px;">
			{completedTasksCount} Completed {@when test="{completedTasksCount} == 1"} Task {:else} Tasks {/when}
		</h4>
		
		{?completedTasks}
		   <article class="item" id="completed-tasks-chart" style="margin-top: 20px; padding-left: 25px; padding-right:25px;">
		    	<header>			
					<span class="icon-s-blank"></span>
				</header>				
				<h3 class="center" style="margin-bottom: 20px;">Completed Tasks Timeline</h3> 
				<div class="task-report-completed-tasks" data-chart-data="{chartData|json}"></div>
			</article>
	    {/completedTasks}
		
		{#completedTasks}
			{>task_inactiveTask/}
		{/completedTasks}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
				
	{/contentBody}