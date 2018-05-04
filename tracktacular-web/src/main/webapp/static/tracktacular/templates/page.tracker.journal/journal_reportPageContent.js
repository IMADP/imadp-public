
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no journals.     
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
							
		<header class="report-info">	
			{journalCount} {@when test="{journalCount} == 1"} Journal {:else} Journals {/when}
		</header>
		
		{#lateJournals}
	         <header class="report-alert">	
	         	<span class="icon-s s-alert"></span> Journal <i>{name}</i> is being neglected!
			</header>
        {/lateJournals}
		
		<div style="margin-top: 15px; margin-bottom: 30px; text-align:center;">
			{#activeJournals}
				<div class="option" id="navigation-chart-{id}">
		
					<span class="option-icon navigation-chart" 
						data-chart-data="{chartData|json}"
					    data-link-id="navigation-chart-link-{id}">
					</span>
						
					<a id="navigation-chart-link-{id}" href="{viewJournalUrl}"> {name} </a>
							
				</div>				 
	        {/activeJournals}
       	</div>
       	
	{/trackerReportEnabled}