	
	<div id="show-category-{nameSlug}" style="padding-bottom: 5px;">
		
		<div class="cf" style="margin: 40px 60px 0 60px;">
           
            <div class="left" style="margin: -5px 0 0 5px;">	
				<h3>
					{name}
				</h3>
				<h4 style="padding-left: 2px;">
					{showCount} {@when test="{showCount} == 1"} Show {:else} Shows {/when}
				</h4>
            </div>
             
        </div>	
        
    	{#shows}
        	{>tv_show/}
        {/shows}
	
	</div>
