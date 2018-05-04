
	<li class="ui-li ui-li-static li-item-header-1">
		<div class="item-wrapper">
		    <div class="item-left">
		    	
		    </div>
	    	<div class="item-center">
	    	 	<div class="title">{name}</div>
	    	 	<div class="subtitle">{showCount} {@when test="{showCount} == 1"} Show {:else} Shows {/when}</div>
	    	</div>
	 		<div class="item-right">
	 			{#. targetId="category-{name}" collapsed="true"}
	    			{>toggleCollapse/}
	    		{/.}
		    </div>
		</div>
			
	</li>
	
	<li id="category-{name}" class="none">
		<ul class="container">
	
			{#shows}
		    	{>tv_show/}
		    {/shows}
	    
	    </ul>
    
    </li>		
	
   	