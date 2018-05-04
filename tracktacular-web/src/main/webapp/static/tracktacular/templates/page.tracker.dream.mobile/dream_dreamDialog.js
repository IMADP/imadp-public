
	{#. dialogTitle="Save Dream" event="saveDream" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="dream" value="{id}" />							
		<input type="hidden" name="dreamsign" value="{dreamsign}" />							
			
		Add a new dream. If you had multiple dreams in the same night that weren't related, add them as separate entries.
		
		<label for="dream.date" class="primary required">Date</label>  
		{#. object="dream.date"}{>inputDateTime/}{/.}
		
		<label for="dream.title" class="primary required">Title</label>
		<label for="dream.title" class="secondary">Try to come up with a clever title for your dream</label>  
		{#. object="dream.title" max="256"}{>inputText/}{/.}
		
		<label for="dream.content" class="primary required">Content</label>  
		{#. object="dream.content"}{>inputTextarea/}{/.} 
		
		<label for="dream.analysis" class="primary">Analysis</label>  
		{#. object="dream.analysis"}{>inputTextarea/}{/.}
		
		<label for="dream.dreamsignsAsString" class="primary">Dreamsigns</label>  
		<label for="dream.dreamsignsAsString" class="secondary">Add recurring themes as a comma separated list (ie: ocean, water, waves)</label>  
		{#. object="dream.dreamsignsAsString" max="1024"}{>inputText/}{/.}
		
		<div style="padding-top: 15px">
			{#. object="dream.lucid"}{>inputCheckbox/}{/.}
			<label for="dream.lucid" style="display:inline">Mark as a <strong>Lucid Dream</strong></label>
		</div>
		
		<div style="padding-top: 15px">
			{#. object="dream.favorite"}{>inputCheckbox/}{/.}
			<label for="dream.favorite" style="display:inline">Mark as a <strong>Favorite Dream</strong></label>
		</div>
		            
	{/dialogFormBody}
