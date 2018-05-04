
	<li class="ui-li ui-li-static li-item">
		<div class="item-wrapper">
				
		    <div class="item-left">
		    	{#. menuButtonTemplate="task_taskMenu"}
					{>menuButton/}		
				{/.}
			</div>
			
		    <div class="item-center">
	    	 	<div class="title" style="color:{>task_taskColor/}">{name}</div>
	    	 	
	    	 	{?targetDateString}<div class="subtitle">{targetDate}</div>{/targetDateString}
		    </div>
		    
		    <div class="item-right">
		    	{>notesButton/}
	        </div>
		        
		</div>		
		
	 	{>notes/}
	 	
	</li>