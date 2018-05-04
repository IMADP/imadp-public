
	{#contentBody}
			
		{#. addButtonTemplate="music_albumDialog" addButtonTitle="Add Album"}
			{>addButton/}		
		{/.}

		<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">
			<li class="ui-li ui-li-static li-item-header li-item-header-no-icon">
				<div class="item-wrapper">
				    <div class="item-center">
			    	 	<div class="title">{albumCount} {@when test="{albumCount} == 1"} Album {:else} Albums {/when}</div>
	    	 		</div>
				</div>	
			</li>
			
			{#albumCategories}
	            {>music_albumCategory/}
	        {/albumCategories}
	        	
        </ul>	
        
	    {>music_pageNavigator/}
        
	{/contentBody}