
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no exercise routines.     
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
							
		<header class="report-info">	
			{routineCount} Exercise {@when test="{routineCount} == 1"} Routine {:else} Routines {/when}
		</header>
		
		{#lateRoutines}
	         <header class="report-alert">	
	         	<span class="icon-s s-alert"></span> Routine <i>{name}</i> is being neglected!
			</header>
        {/lateRoutines}
		
		<div style="margin-top: 15px; margin-bottom: 30px; text-align:center;">
			{#activeRoutines}
				<div class="option" id="navigation-chart-{id}">
		
					<span class="option-icon navigation-chart" 
						data-chart-data="{chartData|json}"
					    data-link-id="navigation-chart-link-{id}">
					</span>
						
					<a id="navigation-chart-link-{id}" href="{viewRoutineUrl}"> {name} </a>
							
				</div>				 
	        {/activeRoutines}
       	</div>
       	
	{/trackerReportEnabled}