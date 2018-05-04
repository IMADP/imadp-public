	
	<div class="cf" style="padding: 20px 45px 0 45px;" id="item-panel-{id}">
			
		<div class="left icons-2 align-right" style="padding-top: 3px;">
			{>notesButton/}
			{>menuButton/}
		</div>
		
		<div class="left">
		
			<div class="item-leader cf">	
						
				<div class="item-leader-left title {?completed}strike{/completed}" style="max-width: 375px;" >
				
					{?completed}
						{#. cssClass="strike" objectName="item" paramName="name"}
							{>editable/}
						{/.} 
					{:else}
						{#. objectName="item" paramName="name"}
							{>editable/}
						{/.} 
					{/completed}
					
				</div>
				  
				<div class="item-leader-right center">
					{?completed}
						<span class="subtitle">{completedDate}</span>
					{:else}
						<span class="icon-s s-question" title="Unfinished"></span> 
					{/completed}
				</div>
					
			</div>
			
			<div class="subtitle" style="margin-left: 10px; max-width: 375px;">
				{#. objectName="item" paramName="description"}
					{>editable/}
				{/.}
			</div>
			  
			{>notes/}
		
		</div>
		
		{>bucket_itemMenu/}
		
	</div>
	