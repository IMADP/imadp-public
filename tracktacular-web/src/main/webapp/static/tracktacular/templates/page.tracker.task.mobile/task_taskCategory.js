	{?root}<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">{/root}
		<li class="ui-li ui-li-static li-item-header-{depth}">
			<div class="item-wrapper">
			    <div class="item-left">
			    	{#. menuButtonTemplate="task_taskCategoryMenu"}
						{>menuButton/}		
					{/.}
			    </div>
		    	<div class="item-center">
		    	 	<div class="title">{name}</div>
		    	 	<div class="subtitle">{totalTasks} {@when test="'{totalTasks}' == 1"} Task {:else} Tasks {/when}</div>
		 		</div>
		 		<div class="item-right">	
		 			{?categoryCollapsible}
			 			{#. targetId="category-content-{id}" collapsed=categoryCollapsed}
							{>toggleCollapse/}  {~s}
						{/.}
						
						{<toggleCollapseForm}
							<form action="{formAction}" method="post">
								<input type="hidden" name="taskCategory" value="{id}" />
								<input type="hidden" name="_eventName" value="toggleTaskCategoryCollapse" />
								<input type="hidden" name="_sourcePage" value="{sourcePage}" />
							</form>
						{/toggleCollapseForm}
					{/categoryCollapsible}
				</div>
			</div>	
		</li>
		
		<li id="category-content-{id}"  class="{?categoryCollapsed} none {/categoryCollapsed}">
			<ul class="container">
		
				{#tasks}
		            {>task_task/}
		        {/tasks}
				
				{#childCategories}
		            {>task_taskCategory/}
		        {/childCategories}
		    
		    </ul>
	    
	    </li>
	{?root}</ul>{/root}