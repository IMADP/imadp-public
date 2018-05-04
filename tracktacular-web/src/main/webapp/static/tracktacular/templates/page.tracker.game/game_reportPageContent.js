
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no games in your library.     
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
							
		<header class="report-info">	
			{gameCount} {@when test="{gameCount} == 1"} Game {:else} Games {/when} in your Library	
		</header>
		
		{#targetDateGames}
	         <header class="report-alert">	
	         	<span class="icon-s s-alert"></span> The target date for game <i>{title}</i> is today!
			</header>
        {/targetDateGames}
		
		<div style="margin: 15px 0 15px 0; text-align:center;">
			{>game_gameChart:gamesByTitle/}
			
			{>game_gameChart:gamesByPlatform/}
			
			{>game_gameChart:gamesByTag/}
			
			{>game_gameChart:gamesByRating/}
       	</div>
       	
		{?unratedGames}
	    	 <div class="title center" style="margin-top: -10px;">
       			<b>{unratedGameCount} Unrated {@when test="'{unratedGameCount}' == 1"} Game {:else} Games {/when}</b>
       		 </div>
       	
       		 <div style="margin: 10px 50px 35px 50px;">
		       	{#unratedGames}					
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
								{platform}
							</div>
							<div class="subscript" style="margin-left: 10px; max-width: 470px;">				
								{tag}
							</div>
						</div>
					</div>								
				{/unratedGames}
       		</div>
       		
       	{/unratedGames}
       	
	{/trackerReportEnabled}