
	{#. dialogTitle="Save Body" event="saveBody" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="body" value="{id}" />							
		
		<p>Add a new body entry (At least one measurement is required)</p> 		
		
		<label for="body.date" class="primary required">Date</label>
		{#. object="body.date"}{>inputDate/}{/.}
		
		<label for="body.height" class="primary">Height</label>
		{#. object="body.height" min="0"}{>inputNumber/}{/.}
		
		<label for="body.neck" class="primary">Neck</label>
		{#. object="body.neck" min="0"}{>inputNumber/}{/.}
		
		<label for="body.chest" class="primary">Chest</label>
		{#. object="body.chest" min="0"}{>inputNumber/}{/.}
		
		<label for="body.biceps" class="primary">Biceps</label>
		{#. object="body.biceps" min="0"}{>inputNumber/}{/.}
		
		<label for="body.forearms" class="primary">Forearms</label>
		{#. object="body.forearms" min="0"}{>inputNumber/}{/.}
		
		<label for="body.wrists" class="primary">Wrists</label>
		{#. object="body.wrists" min="0"}{>inputNumber/}{/.}
		
		<label for="body.waist" class="primary">Waist</label>
		{#. object="body.waist" min="0"}{>inputNumber/}{/.}
		
		<label for="body.hips" class="primary">Hips</label>
		{#. object="body.hips" min="0"}{>inputNumber/}{/.}
		
		<label for="body.thighs" class="primary">Thighs</label>
		{#. object="body.thighs" min="0"}{>inputNumber/}{/.}
		
		<label for="body.calves" class="primary">Calves</label>
		{#. object="body.calves" min="0"}{>inputNumber/}{/.}
		
		<label for="body.ankles" class="primary">Ankles</label>
		{#. object="body.ankles" min="0"}{>inputNumber/}{/.}
		
		<label for="body.feet" class="primary">Feet</label>
		{#. object="body.feet" min="0"}{>inputNumber/}{/.}
		
		<label for="body.weight" class="primary">Weight</label>
		{#. object="body.weight" min="0"}{>inputNumber/}{/.}
		
		<label for="body.bodyFat" class="primary">Body Fat %</label>
		{#. object="body.bodyFat" min="0" max="100"}{>inputNumber/}{/.}
		
		<label for="body.notes" class="primary">Notes</label>
		{#. object="body.notes"}{>inputNotes/}{/.}

	{/dialogFormBody}