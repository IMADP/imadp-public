
	{#. dialogTitle="Save Gift" event="saveGift" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="gift" value="{id}" />
					
		<div class="sh1">Add a gift you've given to someone else.</div> 
		
		<label for="gift.name" class="primary required">Gift Name</label>
		{#. object="gift.name" max="256"}{>inputText/}{/.}
		
		<label for="gift.receiver" class="primary required">To</label>
		<label for="gift.receiver" class="secondary">Who did you give this gift to?</label>
		{#. object="gift.receiver" max="256"}{>inputText/}{/.}
		
		<label for="gift.occasion" class="primary required">Occasion</label>
		<label for="gift.occasion" class="secondary">Occasion for the gift (ie: Anniversary, Birthday)</label>
		{#. object="gift.occasion" max="256"}{>inputText/}{/.}
		
		<label for="gift.date" class="primary required">Date</label>
		<label for="gift.date" class="secondary">Date the gift was given</label>
		{#. object="gift.date"}{>inputDate/}{/.}
		
		<label for="gift.price" class="primary">Price</label>
		<label for="gift.price" class="secondary">Optional estimated price of the gift</label>
		{#. object="gift.price" min="0"}{>inputNumber/}{/.}
		
		<label for="gift.notes" class="primary">Notes</label>
		{#. object="gift.notes"}{>inputNotes/}{/.}
		
		<div style="padding-top: 15px">
			{#. object="gift.favorite"}{>inputCheckbox/}{/.}
			<label for="gift.favorite" style="display:inline">Mark as a <strong>Favorite Gift</strong></label> 
		</div>
		
	{/dialogFormBody}
