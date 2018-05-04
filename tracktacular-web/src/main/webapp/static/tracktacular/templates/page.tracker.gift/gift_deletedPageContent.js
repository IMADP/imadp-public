
	{#contentBody}
		
		<h2>Deleted Gifts</h2>		
				
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedGiftsCount} Deleted {@when test="{deletedGiftsCount} == 1"} Gift {:else} Gifts {/when}
		</h4>				
	
		{#deletedGifts}
			{>gift_deletedGift/}
		{/deletedGifts}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
			
	{/contentBody}