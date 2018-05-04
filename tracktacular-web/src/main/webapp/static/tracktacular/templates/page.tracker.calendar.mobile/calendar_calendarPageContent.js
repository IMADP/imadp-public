
	{#contentBody}
	
		{#. addButtonTemplate="calendar_calendarEntryDialog" addButtonTitle="Add Entry"}
			{>addButton/}		
		{/.}
		
		<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">
			
			<li class="ui-li ui-li-static li-item-header li-item-header-no-icon">
				<div class="item-wrapper">
				    <div class="item-center">
			    		{calendarTitle}
			    	</div>
			    </div>
			</li>
			
			{#weeks}
				{#days}
					{>calendar_calendarDay/}
				{/days}
			{/weeks}
	        	
	    </ul>
	    
	    <div class="ui-navbar ui-mini">
			<ul class="ui-grid-b">
				<li class="ui-block-a">
					<a href="{previousMonthUrl}" class="ui-btn ui-btn-inline ui-btn-hover-c ui-btn-up-c ui-btn-active">
						<span class="ui-btn-inner">
							<span class="ui-btn-text">
								Last Month
							</span>
						</span>
					</a>
				</li>
				{#. url="#" block="b" label="Home" class="to-home"} {>pageNavigatorLink/} {/.}
				<li class="ui-block-c">
					<a href="{nextMonthUrl}" class="ui-btn ui-btn-inline ui-btn-up-c ui-btn-active">
						<span class="ui-btn-inner">
							<span class="ui-btn-text">
								Next Month
							</span>
						</span>
					</a>
				</li>			
			</ul>
		</div>	
		
	{/contentBody}