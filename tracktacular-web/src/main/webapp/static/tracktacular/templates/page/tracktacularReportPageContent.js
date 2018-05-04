				
	{#contentBody}
	
		<article class="item" style="padding: 0;" id="tracktacular-report" data-tracktacular-report-data="{.|json}">
		
			<header>						
				<span class="icon-s-blank"></span>			
			</header>
			
			<h3 class="center">
				Tracktacular Report
			</h3>  
			
			<h4 class="center">
				{date}
			</h4>  
			
			{^trackersFound}					
				<section style="margin: 40px;">		
		       		<p class="center">
		       			You have no data in any of your trackers.
		   			</p>
				</section>
			{/trackersFound}
				
			{?trackersFound}					
				
				<div class="report">		
					<div class="report-segment" >
						<div id="tracktacular-report-navigation-chart" style="margin-top: 40px;"></div>
					</div>		
				</div>
				
				{?alerts}
					<div class="center" style="color: #F00; font-weight: bold;margin-bottom: 15px; margin-top: -15px;">
						{#. targetClass="no-alerts-found" style="margin-bottom: -3px" collapsed=true}
		    				{>toggleCollapse/}  {~s}
			    		{/.}
			    		You have {trackersWithAlerts} {@when test="'{trackersWithAlerts}' == 1"} tracker {:else} trackers {/when} with alerts.	
					</div>
				{:else}
					<div class="center" style="margin-bottom: 15px; margin-top: -15px;">
						You have no alerts.	
					</div>	
				{/alerts}
				
				{#trackerReports}
					{#trackerReport}
						<div class="report" id="{trackerName}">
							{>"{trackerName}_reportPageContent"/}
						</div>
					{/trackerReport}
				{/trackerReports}
					
			{/trackersFound}
			
		</article>
		
	{/contentBody}	