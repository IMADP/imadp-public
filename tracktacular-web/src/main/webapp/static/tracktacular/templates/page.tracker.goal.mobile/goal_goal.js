		
	<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow" id="goal-{id}">
		<li class="ui-li ui-li-static li-item-header">
			<div class="item-wrapper">
			    <div class="item-left">
			    	{#. menuButtonTemplate="goal_goalMenu"}
						{>menuButton/}		
					{/.}
			    </div>
		    	<div class="item-center">
		    	 	<div class="title">{name}</div>
		    	 	<div class="subtitle">{description}</div>
		    	 	<div class="subscript">{targetDate} - <span class="income bold">{progress}% </span></div>
		    	 	{>notes/}
    	 		</div>
    	 		<div class="item-right">
    	 			{>notesButton/}		
			    </div>
			</div>
			
			{>notes/}
				
		</li>
		
		{?objectives}		
			<li class="ui-li ui-li-static li-item-header-1">
				<div class="item-wrapper">
				    <div class="item-left"></div>
			    	<div class="item-center">
			    	 	<div class="title">{completedObjectivesCount}/{objectivesCount} Objectives Completed</div>
			 		</div>
			 		<div class="item-right">	
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
					</div>
				</div>	
			</li>
			
			<li id="objectives-{id}"  class="{?goalCollapsed} none {/goalCollapsed}">
				<ul class="container">
			
					{#objectives}		
						{>goal_objective/}
					{/objectives}
			    
			    </ul>
		    
		    </li>
		{/objectives}
		
	</ul>