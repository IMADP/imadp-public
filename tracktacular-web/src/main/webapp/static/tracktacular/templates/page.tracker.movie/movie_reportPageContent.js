
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no movies in your library.     
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
							
		<header class="report-info">	
			{movieCount} {@when test="{movieCount} == 1"} Movie {:else} Movies {/when} in your Library	
		</header>
		
		{#targetDateMovies}
	         <header class="report-alert">	
	         	<span class="icon-s s-alert"></span> The target date for movie <i>{title}</i> is today!
			</header>
        {/targetDateMovies}
		
		<div style="margin: 15px 0 15px 0; text-align:center;">
			{>movie_movieChart:moviesByTitle/}
			
			{>movie_movieChart:moviesByTag/}
			
			{>movie_movieChart:moviesByRating/}
       	</div>
       	
		{?unratedMovies}
	    	 <div class="title center" style="margin-top: -10px;">
       			<b>{unratedMovieCount} Unrated {@when test="'{unratedMovieCount}' == 1"} Movie {:else} Movies {/when}</b>
       		 </div>
       	
       		 <div style="margin: 10px 50px 35px 50px;">
		       	{#unratedMovies}					
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
				{/unratedMovies}
       		</div>
       		
       	{/unratedMovies}
       	
	{/trackerReportEnabled}