	
	<li class="ui-li ui-li-static li-item-header-1" {@when test="{currentDay}"}style="background: #87BD63;"{/when}>
		<div class="item-wrapper">
		    <div class="item-left">
		    	{#. menuButtonTemplate="calendar_calendarDayMenu"}
					{>menuButton/}
				{/.}
		    </div>
	    	<div class="item-center">
	    		<div class="title">{dayOfWeek}</div>
	    		<div class="subtitle">{monthAndYear}</div>
	    	</div>
	 		<div class="item-right">	
	 			{?items}
		 			{#. targetId="day-{id}" collapsed=dayCollapsed}
						{>toggleCollapse/}  {~s}
					{/.}
				{/items}
			</div>
	    </div> 
	</li>
	
	{?items}
		<li id="day-{id}" class="{?dayCollapsed} none {/dayCollapsed}">
			<ul class="container">
		
				{#items}
			       {>calendar_calendarDaySummaryItem/}
			    {/items}
		    
		    </ul>
	    </li>
    {/items}