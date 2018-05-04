
	{#contentBody}
			
		<header class="content-body-header cf">
		
			<div class="left">
				<h2>Wish List</h2>				
		
				<h4>
					{itemCount} {@when test="{itemCount} == 1"} Item {:else} Items {/when}
				</h4>
			</div>
			 
			<div class="right">
				{#. addButtonTemplate="wish_itemDialog" addButtonTitle="Add Item"}
					{>addButton/}		
				{/.}
			</div>	
			
	    </header>
	    
		<article class="item" style="padding-bottom: 50px">
		
			<header>						
				<span class="icon-s-blank"></span>				
			</header>
			
			<h3 class="center">
				Wish List
			</h3>  
			
			<h4 class="center" style="padding-bottom: 15px;">
				{itemCount} {@when test="{itemCount} == 1"} Item {:else} Items {/when} 
			</h4>  
			        		
			{#items}
	            {>wish_item/}
	        {/items}
	        
        	 {?showChart}	
	 	        <div id="wish-pie-chart">
	        		<div class="wish-pie-chart" style="padding-top: 50px;"
	        			data-chart-data="{chartData|json}" ></div>
	        	</div>	
        	 {/showChart}	
 	        
		</article>
        		
        {>wish_tutorial/}
					
	{/contentBody}