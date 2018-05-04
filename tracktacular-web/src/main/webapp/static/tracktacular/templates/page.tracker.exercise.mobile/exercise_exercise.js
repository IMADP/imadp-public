	
	<li class="ui-li ui-li-static li-item-header-1">
		<div class="item-wrapper">
		    <div class="item-left">
		    	{#. menuButtonTemplate="exercise_exerciseMenu"}
					{>menuButton/}		
				{/.}
		    </div>
	    	<div class="item-center">
	    	 	<div class="title">{name}</div>
	    	 	{>notes/}
	 		</div>
	 		<div class="item-right">
 				{>notesButton/}
			</div>
		</div>	
	</li>
	
	{#entries}
        {>exercise_entry/}
    {/entries}		