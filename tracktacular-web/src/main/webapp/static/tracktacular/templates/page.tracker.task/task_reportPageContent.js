
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no tasks.       
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
	
		<header class="report-info">	
			{taskCount} {@when test="'{taskCount}' == 1"} Task {:else} Tasks {/when} in {rootCategoriesCount} {@when test="'{rootCategoriesCount}' == 1"} Category {:else} Categories {/when}	
		</header>
		
		{#lateTasks}
	         <header class="report-alert">	
	         	<span class="icon-s s-alert"></span> Task <i>{name}</i>  hasn't been completed!
			</header>
        {/lateTasks}
		
		<div style="margin-top: 15px; margin-bottom: 20px; text-align:center;">
			{>navigationChart/}
		
			{#rootCategoriesList}
				 {>navigationChart/}
		    {/rootCategoriesList}
       	</div>
       	
	    {?highPriorityTasks}
			
	       	<div class="title center" style="margin-top: -10px;">
	   			<b>{highPriorityTasksCount} High Priority {@when test="'{highPriorityTasksCount}' == 1"} Task {:else} Tasks {/when}</b>
	       	</div>
	       	
	        <div style="margin: 20px 50px 30px 50px;">
	       		{#highPriorityTasks}
		
				     <div class="title">
						{>task_taskIcon/} {name}
					</div>
					
					<div class="subtitle" style="margin-left: 22px;">
						{path}
					</div>
					
					<div class="subscript" style="margin-left: 22px; margin-bottom: 10px;">
						{targetDate}
					</div>
					
				{/highPriorityTasks}
	       	</div>
       	 
       	 {/highPriorityTasks}
       	 
       	 {?lateTasks}
			
	       	<div class="title center" style="margin-top: -10px;">
	   			<b>{lateTasksCount} Late {@when test="'{lateTasksCount}' == 1"} Task {:else} Tasks {/when}</b>
	       	</div>
	       	
	        <div style="margin: 20px 50px 30px 50px;">
	       		{#lateTasks}
		
				     <div class="title">
						{>task_taskIcon/} {name}
					</div>
					
					<div class="subtitle" style="margin-left: 22px;">
						{path}
					</div>
					
					<div class="subscript" style="margin-left: 22px; margin-bottom: 10px;">
						{targetDate}
					</div>
					
				{/lateTasks}
	       	</div>
       	 
       	 {/lateTasks}
       	 
	{/trackerReportEnabled}