
	{#. dialogTitle="Sort Tasks" event="sortTasks" successIds="content-messages, category-{id},navigation-chart-{rootCategoryId},navigation-chart-all"}
		{>dialogForm/}
	{/.}
	
	{<dialogFormBody}
	
		<input id="sorted-high" type="hidden" name="sortedHighPriorityTasks" />
		<input id="sorted-medium" type="hidden" name="sortedMediumPriorityTasks" />
		<input id="sorted-low" type="hidden" name="sortedLowPriorityTasks" />
					
		<p>Drag and drop to sort your tasks.</p> 
				
		<span class="icon-s s-high"></span> 
		High Priority Tasks
		
		<ul style="border: 1px solid #F58634;margin: 15px 0;"
			class="sortable connected-sortable task-sort" 
			data-sortable-input-target-id="sorted-high"
			data-sortable-connected-class="connected-sortable">
			{#tasks}
				{@when test="'{priority}' == 'HIGH'"}
					<li id="{id}" class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>{name}</li>
				{/when}
			{/tasks}
		</ul>
		
		<span class="icon-s s-med"></span> 
		Medium Priority Tasks
		<ul style="border: 1px solid #A9CF46;margin: 15px 0;"
			class="sortable connected-sortable task-sort"
			data-sortable-input-target-id="sorted-medium"
			data-sortable-connected-class="connected-sortable">
			{#tasks}
				{@when test="'{priority}' == 'MEDIUM'"}
					<li id="{id}" class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>{name}</li>
				{/when}
			{/tasks}
		</ul>
		
		<span class="icon-s s-low"></span> 
		Low Priority Tasks
		<ul style="border: 1px solid #5CC7D1;margin: 15px 0;"
			class="sortable connected-sortable task-sort" 
			data-sortable-input-target-id="sorted-low"
			data-sortable-connected-class="connected-sortable">
			{#tasks}
				{@when test="'{priority}' == 'LOW'"}
					<li id="{id}" class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>{name}</li>
				{/when}
			{/tasks}	
		</ul>
		
	{/dialogFormBody}