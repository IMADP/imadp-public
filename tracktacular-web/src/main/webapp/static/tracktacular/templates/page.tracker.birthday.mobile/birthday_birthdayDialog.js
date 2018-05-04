
	{#. dialogTitle="Save Birthday" event="saveBirthday" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="birthday" value="{id}" />
					
		<div class="sh1">Add a birthday to track.</div> 
		
		<label for="birthday.firstName" class="primary required">First Name</label>
		{#. object="birthday.firstName" max="35"}{>inputText/}{/.}
		
		<label for="birthday.middleName" class="primary">Middle Name</label>
		{#. object="birthday.middleName" max="35"}{>inputText/}{/.}
		
		<label for="birthday.lastName" class="primary required">Last Name</label>
		{#. object="birthday.lastName" max="35"}{>inputText/}{/.}
		
		<label for="birthday.date" class="primary required">Birthday</label>
		{#. object="birthday.date"}{>inputDate/}{/.}
		
		<label for="birthday.notes" class="primary">Notes</label>
		{#. object="birthday.notes"}{>inputNotes/}{/.}

	{/dialogFormBody}
