
	{#. dialogTitle="Save Entry" event="saveCalendarEntry" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="calendarEntry" value="{id}" />						
			
		Add a new calendar entry
		
		<label for="calendarEntry.name" class="primary required">Name</label>
		{#. object="calendarEntry.name" max="128"}{>inputText/}{/.}
		
		<label for="calendarEntry.description" class="primary">Description</label>
		{#. object="calendarEntry.description" max="256"}{>inputText/}{/.}
		
	   	<label for="calendarEntry.startDate" class="primary required">Start Date</label>  
		<label for="calendarEntry.startDate" class="secondary">Enter the date of the event</label>  
		{#. object="calendarEntry.startDate"}{>inputDate/}{/.} 
		
		<label for="calendarEntry.endDate" class="primary">End Date</label>  
		<label for="calendarEntry.endDate" class="secondary">End date if the event spans multiple days</label>  
		{#. object="calendarEntry.endDate"}{>inputDate/}{/.}
		
		<label for="calendarEntry.startTime" class="primary">Start Time</label>  
		<label for="calendarEntry.startTime" class="secondary">Optional time the event starts</label>  
		{#. object="calendarEntry.startTime"}{>inputTime/}{/.}
		
		<label for="calendarEntry.endTime" class="primary">End Time</label>  
		<label for="calendarEntry.endTime" class="secondary">Optional time the event ends</label>  
		{#. object="calendarEntry.endTime"}{>inputTime/}{/.}
		
		<label for="calendarEntry.recurrence.length" class="primary">Repeat this entry every {~s}</label>
		{#. object="calendarEntry.recurrence.length" min="0"}{>inputNumber/}{/.}
		{#. object="calendarEntry.recurrence.type" options=recurrenceTypes optionName="name" optionValue="value"}{>inputSelect/}{/.}
		
		<div style="padding-top: 15px">
			{#. object="calendarEntry.alert"}{>inputCheckbox/}{/.}
			<label for="calendarEntry.alert" style="display:inline">Create an alert for this event</label>
		</div>
		
		<label for="calendarEntry.notes" class="primary">Notes</label>  
		{#. object="calendarEntry.notes"}{>inputNotes/}{/.}
				
	{/dialogFormBody}
