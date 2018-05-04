
	{#. dialogTitle="Save Journal" event="saveJournal" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="journal" value="{id}" />							
			
		Add a new journal
		
		<label for="journal.name" class="primary required">Name</label>
		<label for="journal.name" class="secondary">Enter a short nickname for this journal (ie: Personal, Work)</label>
		{#. object="journal.name" max="35"}{>inputText/}{/.}
		
		<label for="journal.description" class="primary">Description</label>
		{#. object="journal.description" max="256"}{>inputText/}{/.}
		
		<div style="margin-top: 25px">
			<label for="journal.alertRecurrence.length" class="primary inline">Create an alert if an entry hasn't been entered in {~s}</label>
			{#. object="journal.alertRecurrence.length" min="0" style=" width: 60px;"}{>inputNumber/}{/.}
			{#. object="journal.alertRecurrence.type" options=recurrenceTypes optionName="name" optionValue="value"}{>inputSelect/}{/.}
		</div>
		
		<label for="journal.notes" class="primary">Notes</label>
		{#. object="journal.notes"}{>inputNotes/}{/.}
		
	{/dialogFormBody}