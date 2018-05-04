
	<li class="ui-li ui-li-static li-item-header-1">
		<div class="item-wrapper">
		    <div class="item-left">
		    	
		    </div>
	    	<div class="item-center">
	    	 	<div class="title">{name}</div>
	    	 	<div class="subtitle">{albumCount} {@when test="{albumCount} == 1"} Album {:else} Albums {/when}</div>
	    	</div>
	 		<div class="item-right">
	 			{#. targetId="category-{nameSlug}" collapsed="true"}
	    			{>toggleCollapse/}
	    		{/.}
		    </div>
		</div>
			
	</li>	
	
	<li id="category-{nameSlug}" class="none">
		<ul class="container">
	
			{#albums}
		    	{>music_album/}
		    {/albums}	
	    
	    </ul>
    
    </li>		
	
   	