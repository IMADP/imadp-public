
	{#contentBody}
			
		<nav>
			
			{^publicMode}
				{#. addButtonTemplate="tv_showDialog" addButtonTitle="Add Show"}
					{>addButton/}		
				{/.}
			{/publicMode}		
			
			{>tv_showChart:showsByTitle/}
			
			{>tv_showChart:showsByTag/}
			
			{>tv_showChart:showsByRating/}
				
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
						{#showCategories}
							<option value="show-category-{nameSlug}">{name}</option>
						{/showCategories}
					</select>
					!}
				</div>			
			</header>
			
			<h3 class="center">
				Tv Show Library
			</h3>  
			
			<h4 class="center">
				{showCount} {@when test="{showCount} == 1"} Show {:else} Shows {/when}
			</h4>  
			        		
			{#showCategories}
	            {>tv_showCategory/}
	        {/showCategories}
			
		</article>
        		
        {>tv_tutorial/}
        
	{/contentBody}