	
	<li class="ui-li ui-li-static li-item">
		<div class="item-wrapper">
		    <div class="item-left">
		    	{?editable}
					{#. menuButtonTemplate="calendar_calendarDaySummaryItemMenu"}
						{>menuButton/}
					{/.}
				{:else}
					<span style="margin-left: 12px;" class="icon-s s-{tracker}"></span>
				{/editable}
		    </div>
	    	<div class="item-center">
	    	 	<div class="title">{name}</div>
	    	 	<div class="subtitle">{description}</div>
	    	 	{?startTimeString}<div class="subscript">{?startTimeString} {startTime} {/startTimeString} {?endTimeString} {~s} - {endTime} {/endTimeString}</div>{/startTimeString}
	 		</div>
	 		<div class="item-right">
	 			{#. notesId="notes-{dayId}-{id}"}
					{>notesButton/}		
				{/.}
		    </div>
		</div>	
		{#. notesId="notes-{dayId}-{id}"}
			{>notes/}
		{/.}
	</li>