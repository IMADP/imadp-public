
	{#contentBody}
	
		<h2>Completed Items</h2>				
		
		<h4 style="padding-top: 5px; padding-left:60px;">
			{completedItemsCount} Completed {@when test="{completedItemsCount} == 1"} Item {:else} Items {/when}
		</h4>
			
		{#completedItems}
			{>wish_inactiveItem/}
		{/completedItems}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
			
	{/contentBody}