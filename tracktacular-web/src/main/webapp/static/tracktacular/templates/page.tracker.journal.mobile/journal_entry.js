
	<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow" id="entry-{id}" >
		
		<li class="ui-li ui-li-static li-item-header">
			<div class="item-wrapper">
			    <div class="item-left">
			    	{#. menuButtonTemplate="journal_entryMenu"}
						{>menuButton/}		
					{/.}
			    </div>
		    	<div class="item-center">
		    	 	<div class="title">{title}</div>
		 		 	<div class="subtitle">{date}</div>
		 		</div>
		 		<div class="item-right">
		 			{#. targetId="entry-content-{id}" collapsed="true"}
		    			{>toggleCollapse/}
		    		{/.}
				</div>
			</div>	
		</li>
			
		<li class="ui-li ui-li-static li-item" id="entry-content-{id}" style="display:none;">
			<pre>{content}</pre>
    	 	
    	 	{#. display="true"}
	    		{>notes/}	
			{/.}
			
		</li>
	</ul>