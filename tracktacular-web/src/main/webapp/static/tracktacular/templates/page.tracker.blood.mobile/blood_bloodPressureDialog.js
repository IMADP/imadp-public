
	{#. dialogTitle="Save Blood Pressure" event="saveBloodPressure" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="bloodPressure" value="{id}" />							
		
		<p>Add a new blood pressure entry</p> 		
		
		<label for="bloodPressure.date" class="primary required">Date</label>
		<label for="bloodPressure.date" class="secondary">The date that the test was taken</label>
		{#. object="bloodPressure.date"}{>inputDate/}{/.}
		
		<label for="bloodPressure.systolic" class="primary required">Systolic Pressure</label>
		<label for="bloodPressure.systolic" class="secondary">Top number</label>
		{#. object="bloodPressure.systolic" min="0" max="999"}{>inputNumber/}{/.}
			
		<label for="bloodPressure.diastolic" class="primary required">Diastolic Pressure</label>
		<label for="bloodPressure.diastolic" class="secondary">Bottom number</label>
		{#. object="bloodPressure.diastolic" min="0" max="999"}{>inputNumber/}{/.}
			
		<label for="bloodPressure.notes" class="primary">Notes</label>
		{#. object="bloodPressure.notes"}{>inputNotes/}{/.}
		
	{/dialogFormBody}