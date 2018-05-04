	
	<div id="category-{id}" class="{?root} task-category-root-panel {?hidden}none{/hidden} {/root}" style="margin: 40px 0 0 60px;">
		
		<div class="table">
		
			<div class="cell icons-2 align-right">
				
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
				
				{#. menuTargetId="menu-{id}"}
					{>menuButton/}
				{/.} 
				
			</div>
			
			<div class="cell" style="padding-left: 5px;">
			
				<h3>
					{?root} 
						{#. objectName="taskCategory" paramName="name" successIds="navigation-chart-{rootCategoryId}"}
							{>editable/}
						{/.}
				    {:else}
						{#. objectName="taskCategory" paramName="name"}
							{>editable/}
						{/.}
				    {/root}
				</h3>
				
				<h4 style="margin-left:3px;">
					{totalTasks} {@when test="'{totalTasks}' == 1"} Task {:else} Tasks {/when}
				</h4>
				
			</div>
			
		</div>
		
		<div id="category-content-{id}" class="{?categoryCollapsed} none {/categoryCollapsed}">
		
			 <ul style="margin-bottom: 0; padding-left: 20px;">
				{#tasks}
	                <li>{>task_task/}</li>
	            {/tasks}
			</ul>
			
			{#childCategories}
	            {>task_taskCategory/}
	        {/childCategories}
		    
		</div>
		
		{>task_taskCategoryMenu/}
		
	</div>
	