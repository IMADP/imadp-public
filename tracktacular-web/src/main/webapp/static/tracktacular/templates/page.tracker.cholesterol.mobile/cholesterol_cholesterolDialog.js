
	{#. dialogTitle="Save Cholesterol" event="saveCholesterol" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="cholesterol" value="{id}" />							
		
		<label for="cholesterol.date" class="primary required">Date</label>
		<label for="cholesterol.date" class="secondary">The date that the test was taken</label>
		{#. object="cholesterol.date"}{>inputDate/}{/.}
		
		<p>Add a new cholesterol entry (At least one cholesterol type is required)</p> 		
		
		<label for="cholesterol.ldlCholesterol" class="primary">LDL Cholesterol</label>
		{#. object="cholesterol.ldlCholesterol" min="0"}{>inputNumber/}{/.}
		
		<label for="cholesterol.hdlCholesterol" class="primary">HDL Cholesterol</label>
		{#. object="cholesterol.hdlCholesterol" min="0"}{>inputNumber/}{/.}
		
		<label for="cholesterol.triglycerides" class="primary">Total Triglycerides</label>
		{#. object="cholesterol.triglycerides" min="0"}{>inputNumber/}{/.}
		
		<label for="cholesterol.totalCholesterol" class="primary">Total Cholesterol</label>
		{#. object="cholesterol.totalCholesterol" min="0"}{>inputNumber/}{/.}
		
		<label for="cholesterol.notes" class="primary">Notes</label>
		{#. object="cholesterol.notes"}{>inputNotes/}{/.}

	{/dialogFormBody}