
	{#contentBody}
		<nav>
			
			{?publicMode}
				<div class="option"></div>
			{:else}
				{#. addButtonTemplate="calendar_calendarEntryDialog" addButtonTitle="Add Entry"}
					{>addButton/}		
				{/.}
			{/publicMode}		
				
			<div class="option"></div>
			
			{#months}
				{@idx}
					{@when test="{.} == 6"}
						<div class="option"></div>{~s}
						<div class="option"></div>
					{/when}
				{/idx}
				
	            <div class="option" id="calendar-chart-{id}">
	
					<span class="option-icon calendar-nav-chart" 
						data-id="{id}"
					    data-url="{selectMonthUrl}">
					</span>
						
					<a id="calendar-chart-link-{id}" 
							class="{?selected}active{/selected}" 
							href="{selectMonthUrl}">{monthName}</a>
				</div>
	            
	        {/months}
	    </nav>
	    
		<h3 class="center" style="margin-top: 25px">{calendarTitle}</h3>
	
		{>calendar_calendar/}
		
		{>calendar_tutorial/}
		
	{/contentBody}