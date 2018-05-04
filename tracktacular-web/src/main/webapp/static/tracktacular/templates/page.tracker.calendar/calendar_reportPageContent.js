
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no calendar entries this week.     
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
	
		<header class="report-info">	
			{itemCount} Calendar {@when test="'{itemCount}' == 1"} Entry {:else} Entries {/when} for the Week		
		</header>
		
		{#calendarEntryAlerts}
	         <header class="report-alert">	
	         	<span class="icon-s s-alert"></span> Event <i>{name}</i> is today!
			</header>
        {/calendarEntryAlerts}
		
		<div style="margin-top: 35px; margin-bottom: 35px;">
			{#days}
				{#items}
				
					<div style="margin: 20px 50px;">
						<div class="title" style="margin-left: 10px;">
							<span class="icon-s s-{tracker}"></span> {name}
						</div>
						
						<div class="subtitle" style="margin-left: 35px; max-width: 470px;">				
							{description}
						</div>
						
						<div class="subscript" style="margin-left: 35px; max-width: 470px;">				
							{date} {?startTimeString} {startTime} {/startTimeString} {?endTimeString} {~s} - {endTime} {/endTimeString}
						</div>
					</div>	
					
	            {/items}
			{/days}
		</div>
	
	{/trackerReportEnabled}