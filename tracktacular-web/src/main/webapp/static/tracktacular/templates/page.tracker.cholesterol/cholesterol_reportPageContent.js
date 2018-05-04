
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no cholesterol entries.     
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
	
		<header class="report-info">	
			Your Next Recommended Test Date is {recommendedTestDate}		
		</header>
		
		{#alerts}
	         <header class="report-alert">	
	         	<span class="icon-s s-alert"></span> Your recommended test date is overdue!
			</header>
        {/alerts}
        
        <div style="padding: 10px 0 25px 0;">
			{#cholesterol}
				{>cholesterol_cholesterolChart/}
			{/cholesterol}
       </div>
       
	{/trackerReportEnabled}