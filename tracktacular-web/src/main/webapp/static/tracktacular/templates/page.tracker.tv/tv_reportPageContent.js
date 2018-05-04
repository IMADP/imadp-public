
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no shows in your library.     
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
							
		<header class="report-info">	
			{showCount} {@when test="{showCount} == 1"} Show {:else} Shows {/when} in your Library	
		</header>
		
		{#targetDateShows}
	         <header class="report-alert">	
	         	<span class="icon-s s-alert"></span> The target date for show <i>{title}</i> is today!
			</header>
        {/targetDateShows}
		
		{#targetDateEpisodes}
	         <header class="report-alert">	
	         	<span class="icon-s s-alert"></span> The target date for episode/season <i>{title}</i> is today!
			</header>
        {/targetDateEpisodes}
		
		<div style="margin: 15px 0 15px 0; text-align:center;">
			{>tv_showChart:showsByTitle/}
			
			{>tv_showChart:showsByTag/}
			
			{>tv_showChart:showsByRating/}
       	</div>
       	
		{?unratedShows}
	    	 <div class="title center" style="margin-top: -10px;">
       			<b>{unratedShowCount} Unrated {@when test="'{unratedShowCount}' == 1"} Show {:else} Shows {/when}</b>
       		 </div>
       	
       		 <div style="margin: 10px 50px 35px 50px;">
		       	{#unratedShows}					
				   <div class="cf" style="padding: 15px 45px 0 45px">			
						<div class="left icons-3 align-right" style="padding-top: 3px;"></div>
						<div class="left">
							<div class="item-leader cf" style="width: 470px;">
								<div class="item-leader-left title" style="max-width: 340px;">
									{title}	
								</div>
								<div class="item-leader-right center">
									<span class="icon-s s-question"></span> 
								</div>
							</div>
							<div class="subtitle" style="margin-left: 10px; max-width: 470px;">				
								{tag}
							</div>
						</div>
					</div>										
				{/unratedShows}
       		</div>
       		
       	{/unratedShows}
       	
	{/trackerReportEnabled}