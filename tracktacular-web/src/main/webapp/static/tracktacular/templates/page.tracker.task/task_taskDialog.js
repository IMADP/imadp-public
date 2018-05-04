
	{#. dialogTitle="Save Task" event="saveTask" successIds="content-messages, content-body"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input type="hidden" name="task" value="{id}" />
		
		{?persistableState}
			<input type="hidden" name="task.persistableState" value="{persistableState}" />
		{/persistableState}
		
		<label for="task.category" class="primary required">Category</label> 
		{#. object="task.category" options=allCategories optionName="categoryPath" optionValue="categoryId"}{>inputSelect/}{/.}
			
		<div class="cf">
			<div class="half">
				<label for="task.priority" class="primary required">Priority</label>  
			    <label for="task.priority" class="secondary">Tasks are sorted according to priority</label>  
			    <div id="task.priority-error" class="none error"></div> 
			    
			    <div class="cf" style="padding-top: 5px;">
					<div class="left center" style="padding-right: 15px">
						{#. object="task.priority" value="HIGH"}{>inputRadio/}{/.}
						<label for="task.priority.HIGH" class="secondary inline"><b style="color:#F58634">High</b></label>				
					</div>
					<div class="left center" style="padding-right: 15px">
						{#. object="task.priority" value="MEDIUM"}{>inputRadio/}{/.}
						<label for="task.priority.MEDIUM" class="secondary inline"><b style="color:#A9CF46">Medium</b></label>		
					</div>
					<div class="left center" style="padding-right: 15px">
						{#. object="task.priority" value="LOW"}{>inputRadio/}{/.}
						<label for="task.priority.LOW" class="secondary inline"><b style="color:#5CC7D1">Low</b></label>
					</div>
				</div>
			</div>
			<div class="half">
				<label for="task.targetDate" class="primary">Target Date</label>  
				<label for="task.targetDate" class="secondary">Optionally specify a target date to complete</label> 
				{#. object="task.targetDate"}{>inputDate/}{/.}
			</div>
		</div>		
		
		<label for="task.name" class="primary required">Task</label>  
		{#. object="task.name" max="256"}{>inputText/}{/.}
			
		<label for="task.notes" class="primary">Notes</label>
		{#. object="task.notes"}{>inputNotes/}{/.}
		
	{/dialogFormBody}