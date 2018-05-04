
	<li class="ui-li ui-li-static li-item-header-1">
		<div class="item-wrapper">
		    <div class="item-left">
		    	
		    </div>
	    	<div class="item-center">
	    	 	<div class="title">{name}</div>
	    	 	<div class="subtitle">{bookCount} {@when test="{bookCount} == 1"} Book {:else} Books {/when}</div>
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
	
			{#books}
		    	{>book_book/}
		    {/books}
	    
	    </ul>
    
    </li>	
	
   	