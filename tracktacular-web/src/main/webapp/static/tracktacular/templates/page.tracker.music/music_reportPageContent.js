
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no albums in your library.     
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
							
		<header class="report-info">	
			{albumCount} {@when test="{albumCount} == 1"} Album {:else} Albums {/when} in your Library	
		</header>
		
		{#targetDateAlbums}
	         <header class="report-alert">	
	         	<span class="icon-s s-alert"></span> The target date for album <i>{title}</i> is today!
			</header>
        {/targetDateAlbums}
		
		{#targetDateSongs}
	         <header class="report-alert">	
	         	<span class="icon-s s-alert"></span> The target date for song <i>{title}</i> is today!
			</header>
        {/targetDateSongs}
		
		<div style="margin: 15px 0 15px 0; text-align:center;">
			{>music_albumChart:albumsByArtist/}			
			{>music_albumChart:albumsByTitle/}			
			{>music_albumChart:albumsByTag/}				
			{>music_albumChart:albumsByRating/}
       	</div>
       	
		{?unratedAlbums}
	    	 <div class="title center" style="margin-top: -10px;">
       			<b>{unratedAlbumCount} Unrated {@when test="'{unratedAlbumCount}' == 1"} Album {:else} Albums {/when}</b>
       		 </div>
       	
       		 <div style="margin: 10px 50px 35px 50px;">
		       	{#unratedAlbums}					
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
								{artist} {?album} <i>{album}</i> {/album} 
							</div>
						</div>
					</div>								
				{/unratedAlbums}
       		</div>
       		
       	{/unratedAlbums}
       	
	{/trackerReportEnabled}