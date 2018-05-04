	
	{>trackerReportSegment:wishTrackerReportSummary/}

	{<heading}
		Wish List Summary
	{/heading}
	
	{<title}
		Wish List Summary
	{/title}
	
	{<subtitle}
		Wish List Report 
	{/subtitle}
	
	{<reportDisabled}
		You currently have no wish list items with any prices.
	{/reportDisabled}
	
	{<reportEnabled}
		
		<article class="item" style="padding-bottom: 40px">
		
			<header>	
				<span class="icon-s-blank"></span>	
			</header>
			
			<h3 class="center">
				Wish List
			</h3>  
			
			<h4 class="center">
				{itemCount} {@when test="{itemCount} == 1"} Item {:else} Items {/when}
			</h4> 
			
			{?showChart}	
	        	<div class="wish-pie-chart" style="padding-top: 40px;"
	 				data-chart-data="{chartData|json}" ></div>
			{/showChart}
 			
		</article>
	
	{/reportEnabled}
	