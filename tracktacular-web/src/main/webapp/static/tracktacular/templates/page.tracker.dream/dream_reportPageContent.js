
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no recorded dreams.       
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
	
		<header class="report-info">	
			{dreamCount} Recorded {@when test="'{dreamCount}' == 1"} Dream {:else} Dreams {/when}	
		</header>
		
		<section class="report-chart" style="margin-bottom:20px;">
			<div class="dream-report-timeline" data-chart-data="{chartData|json}"></div>	
		</section>
       
	{/trackerReportEnabled}