	
	<article class="item" id="calendar-entry-{id}">
			
		<header>
		 	{>menuButton/}
			{>notesButton/}
		</header>
				
		<h3 class="center">	
			{#. objectName="calendarEntry" paramName="name" successIds="journal-chart-{id}"}
				{>editable/}
			{/.} 			
		</h3>  
		
		<h4 class="center">
			{?description}	
				{#. objectName="calendarEntry" paramName="description"}
					{>editable/}
				{/.}    
			{/description}	
		</h4> 	
		
		{>notes/}
		
		<div class="title center" style="padding-top: 20px;">
			<b> {startDate} {?endDateString} - {endDate} {/endDateString} </b>
		</div>
		
		{?startTimeString}
			<div class="subtitle center">
				{startTime} {?endTimeString} - {endTime} {/endTimeString}
			</div>
		{/startTimeString}
		 		
		{?recurring}
			
			<div class="title center" style="padding-top: 20px;">
				This event will repeat every {>recurrencePeriod:recurrence/}
			</div>
		
		{/recurring}
			
		{>persistableStateDetails/}
		
		{>calendar_calendarEntryMenu/}
				
	</article>
	