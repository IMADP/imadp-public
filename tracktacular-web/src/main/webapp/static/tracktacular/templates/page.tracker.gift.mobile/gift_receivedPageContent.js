
	{#contentBody}
			
		{#. addButtonTemplate="gift_giftGivenDialog" addButtonTitle="Add Gift"}
			{>addButton/}		
		{/.}

	    <ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">
			<li class="ui-li ui-li-static li-item-header li-item-header-no-icon">
				<div class="item-wrapper">
				    <div class="item-center">
			    	 	<div class="title">{giftCount} {@when test="{giftCount} == 1"} Gift {:else} Gifts {/when} Received</div>
	    	 		</div>
				</div>	
			</li>
			
			{#giftCategories}
	            {>gift_giftCategory/}
	        {/giftCategories}
	        	
        </ul>
        
        {>gift_pageNavigator/}
        
	{/contentBody}