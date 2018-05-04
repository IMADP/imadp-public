	
	{! Calendar Item !}
	<div class="cf" style="padding: 15px 45px 0 45px">
			
		<div class="left icons-2 align-right" style="padding-top: 3px;">	
			
			{#. notesId="notes-{dayId}-{id}"}
				{>notesButton/}
			{/.}
			
			{?editable}
				{?publicMode}
					<span class="icon-s s-{tracker}"></span>
				{:else}
					{^disableMenus}
						{>menuButton/}
					{/disableMenus}
				{/publicMode}
			{:else}
				<span class="icon-s s-{tracker}"></span>
			{/editable}
						
		</div>
		
		<div class="left" style="padding-left: 10px;">
		
			<div class="title">
				{name}
			</div>
			
			<div class="subtitle" style="max-width: 470px;">				
				{description}
			</div>
			
			<div class="subscript" style="max-width: 470px;">				
				{date} {?startTimeString} {startTime} {/startTimeString} {?endTimeString} {~s} - {endTime} {/endTimeString}
			</div>
			
			{#. notesId="notes-{dayId}-{id}"}
				{>notes/}
			{/.}
			
		</div>
		
		{?editable}
			{>calendar_calendarDaySummaryItemMenu/}
		{/editable}
		
	</div>