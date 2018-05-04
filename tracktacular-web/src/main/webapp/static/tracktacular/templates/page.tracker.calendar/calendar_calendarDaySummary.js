		
	<div id="day-{id}" class="calendar-day-panel {?hidden}none{/hidden}" style="padding-bottom: 5px;">
		
		{! Items !}	
		<div id="day-content-{id}">
			{#items}
               {>calendar_calendarDaySummaryItem/}
            {/items}
		</div>
        		
	</div>