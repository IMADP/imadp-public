	
	<div class="cf" style="padding: 10px 45px 0 45px;" id="objective-panel-{id}">
			
		<div class="left icons-2 align-right" style="padding-top: 3px;">
			{>notesButton/}
			{>menuButton/}
		</div>
		
		<div class="left">
		
			<div class="item-leader cf">	
						
				<div class="item-leader-left title {?completed}strike{/completed}" style="max-width: 360px;" >
				
					{?completed}
						{#. cssClass="strike" objectName="objective" paramName="name"}
							{>editable/}
						{/.} 
					{:else}
						{#. objectName="objective" paramName="name"}
							{>editable/}
						{/.} 
					{/completed}
					
				</div>
					
				<div class="item-leader-right center align-right" style="width: 105px;">
					<span class="subtitle">{targetDate}</span> 
				
					{?completed}	
						<span class="icon-s s-check"></span>
					{/completed}
					
					{?late}	
						<span class="icon-s s-x"></span>
					{/late}
					
					{^completed}	
						{^late}	
							<span class="icon-s-blank"></span>
						{/late}
					{/completed}
				</div>
				
			</div>
			  
			{>notes/}	
		
		</div>
		
		{>goal_objectiveMenu/}	
		
	</div>
	