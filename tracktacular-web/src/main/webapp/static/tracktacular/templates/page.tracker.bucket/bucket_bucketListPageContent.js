
	{#contentBody}
			
		<header class="content-body-header cf">
		
			<div class="left">
				<h2>Bucket List</h2>
				<h4>{itemCount} {@when test="{itemCount} == 1"} Item {:else} Items {/when}</h4>
			</div>
			 
			<div class="right">
				{#. addButtonTemplate="bucket_itemDialog" addButtonTitle="Add Item"}
					{>addButton/}		
				{/.}
			</div>	
			
	    </header>
	    
		<article class="item" style="padding-bottom: 50px">
		
			<header>						
				<span class="icon-s-blank"></span>			
			</header>
			
			<h3 class="center">
				Bucket List
			</h3>  
			
			<h4 class="center" style="padding-bottom: 15px;">
				{finishedItemCount}/{itemCount} Completed {@when test="{itemCount} == 1"} Item {:else} Items {/when} 
			</h4>  
			        		
			{#items}
	            {>bucket_item/}
	        {/items}
	        
	        {?items}
		        <div class="bucket-progress-chart" style="padding-top: 25px;" data-progress="{progress}" ></div>
			 {/items}	
			
		</article>
        
        {>bucket_tutorial/}	
	
	{/contentBody}