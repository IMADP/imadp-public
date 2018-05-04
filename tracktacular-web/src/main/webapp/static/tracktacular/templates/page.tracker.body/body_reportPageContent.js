
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no body entries.     
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
							
		<header class="report-info">	
			Your Next Recommended Measurement Date is {recommendedTestDate}		
		</header>
	
		{#alerts}
	         <header class="report-alert">	
	         	<span class="icon-s s-alert"></span> Your recommended measurement date is overdue!
			</header> 
        {/alerts}
        
        {#body publicMode="true"}
			<div style="margin: -5px 0 15px 0">
				{>body_bodyChart/}
			</div>
		{/body}
       
	{/trackerReportEnabled}