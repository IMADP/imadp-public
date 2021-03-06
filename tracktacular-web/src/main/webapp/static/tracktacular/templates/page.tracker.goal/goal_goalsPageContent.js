
	{#contentBody}
	
		<nav>
			
			{#. addButtonTemplate="goal_goalDialog" addButtonTitle="Add Goal" addButtonTemplateContextId="newGoal"}
				{>addButton/}		
			{/.}
					
			{>navigationChart/}
	
			{#activeGoals}
				 {>navigationChart/}
	        {/activeGoals}
	        
	    </nav>
	
		{?activeGoals}
		   <article class="item" id="goal-timeline-chart" style="margin-top: 20px; padding-left: 25px; padding-right:25px;">
		    	<header>			
					<span class="icon-s-blank"></span>
				</header>				
				<h3 class="center" >Goal Timeline</h3> 
				<h4 class="center" style="padding-bottom: 20px;">
					{goalCount} Active {@when test="'{goalCount}' == 1"} Goal {:else} Goals {/when}	
				</h4>
				<div class="goal-report-active-timeline" data-chart-data="{chartData|json}"></div>
			</article>
	    {/activeGoals}
	     
		{#activeGoals}
            {>goal_goal/}
        {/activeGoals}
        		
        {>goal_tutorial/}
        		
	{/contentBody}  