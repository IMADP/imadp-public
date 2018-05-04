
	{#contentBody}
			
		{#. addButtonTemplate="music_songDialog" addButtonTitle="Add Song"}
			{>addButton/}		
		{/.}

		<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">
			<li class="ui-li ui-li-static li-item-header li-item-header-no-icon">
				<div class="item-wrapper">
				    <div class="item-center">
			    	 	<div class="title">{songCount} {@when test="{songCount} == 1"} Song {:else} Songs {/when}</div>
	    	 		</div>
				</div>	
			</li>
			
			{#songCategories}
	            {>music_songCategory/}
	        {/songCategories}
	        	
        </ul>	
        
	    {>music_pageNavigator/}
        
	{/contentBody}