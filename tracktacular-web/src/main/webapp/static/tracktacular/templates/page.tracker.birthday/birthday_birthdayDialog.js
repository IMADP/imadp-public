
	{#. dialogTitle="Save Birthday" event="saveBirthday" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="birthday" value="{id}" />
					
		<div class="sh1">Add a birthday to track.</div> 
		
		<div class="cf">
			<div class="third">
				<label for="birthday.firstName" class="primary required">First Name</label>
				{#. object="birthday.firstName" max="35"}{>inputText/}{/.}
			</div>
			<div class="third">
				<label for="birthday.middleName" class="primary">Middle Name</label>
				{#. object="birthday.middleName" max="35"}{>inputText/}{/.}
			</div>
			<div class="third">
				<label for="birthday.lastName" class="primary required">Last Name</label>
				{#. object="birthday.lastName" max="35"}{>inputText/}{/.}
			</div>
		</div>
		
		<label for="birthday.date" class="primary required">Birthday</label>
		{#. object="birthday.date"}{>inputDateMonthYear/}{/.}
		
		<div style="margin-top: 25px">
			<label for="birthday.alertRecurrence.length" class="primary inline">Remind me about this birthday {~s}</label>
			{#. object="birthday.alertRecurrence.length" min="0" style=" width: 60px;"}{>inputNumber/}{/.}
			{#. object="birthday.alertRecurrence.type" options=recurrenceTypes optionName="name" optionValue="value"}{>inputSelect/}{/.}
			<label for="birthday.alertRecurrence.length" class="primary inline">{~s} before it arrives. </label>
		</div>
		
		<label for="birthday.notes" class="primary">Notes</label>
		{#. object="birthday.notes"}{>inputNotes/}{/.}

	{/dialogFormBody}
