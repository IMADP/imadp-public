
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no items on your Bucket List.
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
	
		<header class="report-info">	
			{finishedItemCount} out of {itemCount} Completed Items on your Bucket List		
		</header>
		
		<div class="bucket-progress-chart" style="padding-top: 15px; padding-bottom: 20px" data-progress="{progress}"></div>
       
	{/trackerReportEnabled}