
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no goals.       
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
	
		<header class="report-info">	
			{goalCount} {@when test="'{goalCount}' == 1"} Goal {:else} Goals {/when}	
		</header>
		
		{#lateGoals}
	         <header class="report-alert">	
	         	<span class="icon-s s-alert"></span> Goal <i>{name}</i>  hasn't been completed!
			</header>
        {/lateGoals}
        
		{#lateObjectives}
	         <header class="report-alert">	
	         	<span class="icon-s s-alert"></span> Objective <i>{name}</i>  hasn't been completed!
			</header>
        {/lateObjectives}
		
		<div style="margin-top: 15px; margin-bottom: 20px; text-align:center;">
			{>navigationChart/}
	
			{#activeGoals}
				 {>navigationChart/}
	        {/activeGoals}
       	</div>
       	
       {?lateGoals}
			
	       	<div class="title center" style="margin-top: -10px;">
	   			<b>{lateGoalsCount} Late {@when test="'{lateGoalsCount}' == 1"} Goal {:else} Goals {/when}</b>
	       	</div>
	       	
	        <div style="margin: 20px 50px 30px 50px;">
	       		{#lateGoals}
		
				     <div class="item-leader cf" style="margin-left: 35px; margin-top: 15px;">	
								
						<div class="item-leader-left title" style="max-width: 360px;" >
							{name}
						</div>
							
						<div class="item-leader-right center align-right" style="width: 105px;">
							<span class="subtitle">{targetDate}</span> <span class="icon-s s-x"></span>
						</div>
						
					</div>
					
					<div class="subtitle" style="margin-left: 50px; margin-bottom: 10px;">
						{description}
					</div>
					
				{/lateGoals}
	       	</div>
       	 
       	 {/lateGoals}    
       	 
       	 {?lateObjectives}
			
	       	<div class="title center" style="margin-top: -10px;">
	   			<b>{lateObjectivesCount} Late {@when test="'{lateObjectivesCount}' == 1"} Objective {:else} Objectives {/when}</b>
	       	</div>
	       	
	        <div style="margin: 20px 50px 30px 50px;">
	       		{#lateObjectives}
		
				    <div class="item-leader cf" style="margin-left: 35px; margin-top: 15px;">	
								
						<div class="item-leader-left title" style="max-width: 360px;" >
							{name}
						</div>
							
						<div class="item-leader-right center align-right" style="width: 105px;">
							<span class="subtitle">{targetDate}</span> <span class="icon-s s-x"></span>
						</div>
						
					</div>
					
					<div class="subtitle" style="margin-left: 50px; margin-bottom: 10px;">
						{goalName}
					</div>
					
				{/lateObjectives}
	       	</div>
       	 
       	 {/lateObjectives}     
       	
	{/trackerReportEnabled}