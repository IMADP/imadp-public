
	{#contentBody}
	
		<h2>Deleted Items</h2>				
		
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedItemsCount} Deleted {@when test="{deletedItemsCount} == 1"} Item {:else} Items {/when}
		</h4>
			
		{#deletedItems}
			{>wish_inactiveItem/}
		{/deletedItems}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
			
	{/contentBody}