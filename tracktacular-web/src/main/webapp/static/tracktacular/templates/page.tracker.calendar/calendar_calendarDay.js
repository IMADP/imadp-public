	
	<td>		
		<div class="calendar-day-header cf
			{@when test="{currentDay}"} 
				current-day 
			{:else} 
				{@when test="{activeMonth}"}
					active-month
				{:else}
					inactive-month 
				{/when} 
			{/when}
		">
			{@when test="{sunday}"} 
				<span class="left" style="margin-left: -30px;">
					{#. targetId="calendar-month-row-content-{weekId}" collapsed="true"}
						{>toggleCollapse/}  {~s}
					{/.}
				</span>
			{/when}
			
			<span class="left">
				{^disableMenus}
					{#. menuTargetId="menu-{id}"}
						{>menuButton/}
					{/.}
				{/disableMenus}
			</span>
			<span class="right">
				{dayNumber}
			</span>
		</div>
			
		{>calendar_calendarDayMenu/}
			
		{#items}
			
			<div class="calendar-item 
				{@idx}
					{@when test="{.}%2 == 0"}
						calendar-item-even
					{:else}
						calendar-item-odd
					{/when}
				{/idx} ">
				{name}
			</div>
		{/items}
		
	</td>