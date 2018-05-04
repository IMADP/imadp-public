
	{#contentBody}
		
		<h2>Deleted Cholesterol</h2>				
	
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedCholesterolsCount} Deleted Cholesterol {@when test="{deletedCholesterolsCount} == 1"} Entry {:else} Entries {/when}
		</h4>				
			
		{#deletedCholesterols deleted="true"}
			{>cholesterol_cholesterol/}
		{/deletedCholesterols}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
		
	{/contentBody}