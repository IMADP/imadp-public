
	{#contentBody}
			
		{#. addButtonTemplate="book_bookDialog" addButtonTitle="Add Book"}
			{>addButton/}		
		{/.}
		
		<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">
			<li class="ui-li ui-li-static li-item-header li-item-header-no-icon">
				<div class="item-wrapper">
				    <div class="item-center">
			    	 	<div class="title">{bookCount} {@when test="{bookCount} == 1"} Book {:else} Books {/when}</div>
	    	 		</div>
				</div>	
			</li>
			
			{#bookCategories}
	            {>book_bookCategory/}
	        {/bookCategories}
	        	
        </ul>	
        
	    {>pageNavigator/}
        
	{/contentBody}