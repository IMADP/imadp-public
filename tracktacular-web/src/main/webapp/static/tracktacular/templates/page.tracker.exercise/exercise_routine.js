
	<article class="item routine-panel {?hidden}none{/hidden}" id="routine-{id}">
			
		<header>
			{>menuButton/}
			{>notesButton/} 
		</header>
				
		<h3 class="center">	
			{#. objectName="routine" paramName="name" successIds="navigation-chart-{id}"}
				{>editable/}
			{/.} 			
		</h3>  
		
		<h4 class="center">
			{?description}	
				{#. objectName="routine" paramName="description"}
					{>editable/}
				{/.}    
			{/description}	
		</h4> 	
		
		{>notes/} 
				
		<div class="title center" style="padding-top: 25px">
			<b>{workoutCount} {@when test="{workoutCount} == 1"} Workout {:else} Workouts {/when}</b>
		</div>
		
		{?showStatisticsChart}
			<section class="report-chart" style="padding-top: 15px;">
				<div style="height: 400px;" class="exercise-report-active" data-chart-data="{statisticsChartData|json}"></div>
			</section>
		{/showStatisticsChart}
		
		<div class="center" style="padding-top: 15px;">
			<a href="{viewRoutineUrl}" class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all">View Routine</a>
		</div>		
			
		{>persistableStateDetails/}
		
		{>exercise_routineMenu/}
				
	</article>
	