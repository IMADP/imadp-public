	
	<tr>
		{#days}
			{>calendar_calendarDay/}
		{/days}
	</tr>
	<tr>
		<td id="calendar-month-row-{id}" class="calendar-month-row" colspan="7">
			<div id="calendar-month-row-content-{id}" class="calendar-month-row-content">
				<br/>
				
				{?noItems}
					<div class="center">You have no calendar entries for this week.</div>
					<br/>
				{:else}
					{#days}
						{>calendar_calendarDaySummary/}
					{/days}

					<div style="padding-bottom: 40px"></div>
				{/noItems}
			</div>
		</td>
	</tr>