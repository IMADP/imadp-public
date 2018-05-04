
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no active budgets.     
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
	
		<header class="report-info">	
			{activeBudgetsCount} Active {@when test="'{activeBudgetsCount}' == 1"} Budget {:else} Budgets {/when}
		</header>
		
		<div style="margin-top: 15px; margin-bottom: 30px; text-align:center;">
			{#activeBudgets}
				<div class="option" id="navigation-chart-{id}">
		
					<span class="option-icon navigation-chart" 
						data-chart-data="{chartData|json}"
					    data-link-id="navigation-chart-link-{id}">
					</span>
						
					<a id="navigation-chart-link-{id}" href="{viewBudgetUrl}"> {name} </a>
							
				</div>				 
	        {/activeBudgets}
        </div>
        
	{/trackerReportEnabled} 