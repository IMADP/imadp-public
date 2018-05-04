
	{#contentBody}
	
		{#. addButtonTemplate="bucket_itemDialog" addButtonTitle="Add Item"}
			{>addButton/}		
		{/.}
		
		<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">
			<li class="ui-li ui-li-static li-item-header li-item-header-no-icon">
				<div class="item-wrapper">
				    <div class="item-center">
			    	 	<div class="title">
			    	 		{finishedItemCount}/{itemCount} Completed {@when test="{itemCount} == 1"} Item {:else} Items {/when}
			    	 	</div>
	    	 		</div>
				</div>	
			</li>
			
			{#items}
	    		{>bucket_item/}
	        {/items}
	        	
        </ul>	
        		
	    {>pageNavigator/}
        
	{/contentBody}