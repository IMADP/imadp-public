
	{#contentBody}
		
		<h2>Deleted Body</h2>				
	
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedBodiesCount} Deleted Body {@when test="{deletedBodiesCount} == 1"} Entry {:else} Entries {/when}
		</h4>				
			
		{#deletedBodies deleted="true"}
			{>body_body/}
		{/deletedBodies}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
		
	{/contentBody}