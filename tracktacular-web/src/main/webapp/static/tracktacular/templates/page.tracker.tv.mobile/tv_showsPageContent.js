
	{#contentBody}
			
		{#. addButtonTemplate="tv_showDialog" addButtonTitle="Add Show"}
			{>addButton/}		
		{/.}
		
		<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">
			<li class="ui-li ui-li-static li-item-header li-item-header-no-icon">
				<div class="item-wrapper">
				    <div class="item-center">
			    	 	<div class="title">{showCount} {@when test="{showCount} == 1"} Show {:else} Shows {/when}</div>
	    	 		</div>
				</div>	
			</li>
			
			{#showCategories}
	            {>tv_showCategory/}
	        {/showCategories}
	        	
        </ul>	
        
	    {>pageNavigator/}
        
	{/contentBody}