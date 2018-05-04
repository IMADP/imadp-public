
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no books in your library.     
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
							
		<header class="report-info">	
			{bookCount} {@when test="{bookCount} == 1"} Book {:else} Books {/when} in your Library	
		</header>
		
		{#targetDateBooks}
	         <header class="report-alert">	
	         	<span class="icon-s s-alert"></span> The target date for book <i>{title}</i> is today!
			</header>
        {/targetDateBooks}
		
		{#targetDateChapters}
	         <header class="report-alert">	
	         	<span class="icon-s s-alert"></span> The target date for chapter <i>{title}</i> is today!
			</header>
        {/targetDateChapters}
		
		<div style="margin: 15px 0 15px 0; text-align:center;">
			{>book_bookChart:booksByTitle/}
				
			{>book_bookChart:booksByAuthor/}
			
			{>book_bookChart:booksByTag/}
			
			{>book_bookChart:booksByRating/}
       	</div>
       	
		{?unratedBooks}
	    	 <div class="title center" style="margin-top: -10px;">
       			<b>{unratedBookCount} Unrated {@when test="'{unratedBookCount}' == 1"} Book {:else} Books {/when}</b>
       		 </div>
       	
       		 <div style="margin: 10px 50px 35px 50px;">
		       	{#unratedBooks}
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
								{author}
							</div>
							<div class="subscript" style="margin-left: 10px; max-width: 470px;">				
								{tag}
							</div>
						</div>
					</div>					
				{/unratedBooks}
       		</div>
       		
       	{/unratedBooks}

	{/trackerReportEnabled}