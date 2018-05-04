
	{#contentBody}
			
		<nav>
			
			{^publicMode}
				{#. addButtonTemplate="gift_giftGivenDialog" addButtonTitle="Add Gift"}
					{>addButton/}		
				{/.}
			{/publicMode}		
			
			{>gift_giftChart:giftsByOccasion/}
			
			{>gift_giftChart:giftsBySender/}
				
	    </nav>
	    
	    <article class="item" style="padding-bottom: 50px">
		
			<header class="cf">						
				<div class="left">
					<span class="icon-s-blank"></span>
				</div>	
				<div class="right">
					{! 
					<select class="to-scroll">
						<option selected="selected">Jump to section</option>
						{#giftCategories}
							<option value="gift-category-{nameSlug}">{name}</option>
						{/giftCategories}
					</select>
					!}
				</div>			
			</header>
			
			<h3 class="center">
				Gifts Received
			</h3>  
			
			<h4 class="center">
				{giftCount} {@when test="{giftCount} == 1"} Gift {:else} Gifts {/when}
			</h4>  
			        		
			{#giftCategories}
	            {>gift_giftCategory/}
	        {/giftCategories}
	        
	        {?showChart}	
	        	<div id="gift-report-chart">
    				<div style="padding-top: 25px" class="gift-report-chart" data-chart-data="{chartData|json}"></div>
    			</div>
			{/showChart}	 
			
		</article>
        
		{>gift_tutorial/}	
        
	{/contentBody}