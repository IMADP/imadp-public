
	{#contentBody}
			
		{#. addButtonTemplate="game_gameDialog" addButtonTitle="Add Game"}
			{>addButton/}		
		{/.}
		
		<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">
			<li class="ui-li ui-li-static li-item-header li-item-header-no-icon">
				<div class="item-wrapper">
				    <div class="item-center">
			    	 	<div class="title">{gameCount} {@when test="{gameCount} == 1"} Game {:else} Games {/when}</div>
	    	 		</div>
				</div>	
			</li>
			
			{#gameCategories}
	            {>game_gameCategory/}
	        {/gameCategories}
	        	
        </ul>	
        
	    {>pageNavigator/}
        
	{/contentBody}