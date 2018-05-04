
	{>trackerReportPageContent/}
	
	{<trackerReportDisabled}		
		You have no recipes.       
	{/trackerReportDisabled}
	
	{<trackerReportEnabled}
	
		<header class="report-info">	
			{recipeCount} {@when test="'{recipeCount}' == 1"} Recipe {:else} Recipes {/when} in {chapterCount} {@when test="'{chapterCount}' == 1"} Chapter {:else} Chapters {/when}	
		</header>
		
		<div style="margin-top: 15px; margin-bottom: 20px; text-align:center;">
			{>navigationChart/}
				
			{#chapters}
				 {>navigationChart/}
			{/chapters}
       	</div>
       	
	{/trackerReportEnabled}