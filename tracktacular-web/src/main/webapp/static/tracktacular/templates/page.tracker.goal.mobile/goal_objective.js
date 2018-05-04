	
	<li class="ui-li ui-li-static li-item" id="objective-panel-{id}">
		<div class="item-wrapper">
		    <div class="item-left">
		    	{#. menuButtonTemplate="goal_objectiveMenu"}
					{>menuButton/}		
				{/.}
		    </div>
	    	
	    	<div class="item-center">
	    	 	<div class="title {?completed}strike{/completed}">{.name}</div>
	    	 	<div class="subtitle">{.description}</div>
	    	 	<div class="subscript">{.targetDate}</div>
		    </div>
	 		
	 		<div class="item-right">
	 			{>notesButton/}		
		    </div>
		</div>
		
		{>notes/}
			
	</li>