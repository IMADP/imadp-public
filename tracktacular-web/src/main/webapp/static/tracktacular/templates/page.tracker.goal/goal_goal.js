		
	<article class="item goal-panel {?hidden}none{/hidden}" id="goal-{id}" >
			
		<header>	
				
			{>menuButton/}
			
			{@when test="{objectivesCount} > 0"}
				
	    		{#. targetId="objectives-{id}" collapsed=goalCollapsed}
	    			{>toggleCollapse/}  {~s}
	    		{/.}
					
	    		{<toggleCollapseForm}
	    			<form action="{formAction}" method="post">
						<input type="hidden" name="goal" value="{id}" />
						<input type="hidden" name="_eventName" value="toggleGoalCollapse" />
						<input type="hidden" name="_sourcePage" value="{sourcePage}" />
					</form>
				{/toggleCollapseForm}	
	    			    		
	    	{/when}
	    	
	    	{>notesButton/}
			
		</header>
		
		<h3 class="center">
	
			{#. objectName="goal" paramName="name" successIds="goal-chart-{id}, navigation-chart-link-{id}, goal-timeline-chart"}
				{>editable/}
			{/.} 
			
		</h3>  
		
		<h4 class="center">
			{startDate} - {targetDate}
		</h4>  
		
		<div class="center title" style="padding-top: 25px">
			<b>
				{#. objectName="goal" paramName="description"}
					{>editable/}
				{/.}
			</b> 
		</div>
		
		{@when test="{objectivesCount} > 0"}
			<div class="center subtitle">
				{completedObjectivesCount}/{objectivesCount} Objectives Completed
			</div>
		{/when}
		
		{>notes/}
				
		{@when test="{objectivesCount} > 0"}
			<div id="objectives-{id}" class="{?goalCollapsed}none{/goalCollapsed}" style="padding-top: 10px;">		
				{#objectives}		
					{>goal_objective/}
				{/objectives}
			</div>
		{/when}
		
		<div class="goal-chart" style="padding-top: 15px;"
			data-progress="{progress}" 
			data-start-date="{startDate}" 
			data-target-date="{targetDate}" ></div>
		
		{^activeState}
			{>persistableStateDetails/}
		{/activeState}
		
		{>goal_goalMenu/}	
		
	</article>
