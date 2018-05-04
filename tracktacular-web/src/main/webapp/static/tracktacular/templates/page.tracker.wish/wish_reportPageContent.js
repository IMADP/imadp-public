
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no items on your wish list.
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
							
		<header class="report-info">	
			{itemCount} {@when test="{itemCount} == 1"} Item {:else} Items {/when} on your Wish List
		</header>
		
		<div id="wish-pie-chart" style="margin-top: -25px; margin-bottom: 35px;">
    		<div class="wish-pie-chart" style="padding-top: 50px;"
    			data-chart-data="{chartData|json}" ></div>
    	</div>
       	
	{/trackerReportEnabled}