	
	<div class="cf" style="padding: 20px 45px 0 45px;" id="item-panel-{id}">
			
		<div class="left icons-3 align-right" style="padding-top: 3px;">
			{>notesButton/}	
			
			{?url}
				{~s}<a href="{url}"><span class="icon-s s-link"></span></a>{~s}
			{/url}		
			
			{>menuButton/}
		</div>
		
		<div class="left">
		
			<div class="item-leader cf">	
						
				<div class="item-leader-left title" style="max-width: 375px;" >
				
					{index}.{~s}
					
					{#. objectName="item" paramName="name" successIds="wish-pie-chart"}
						{>editable/}
					{/.} 
				</div>
				  
				<div class="item-leader-right center">
					{?formattedPrice}
						<span class="title" style="color: green;">{formattedPrice}</span>
					{:else}
						<span class="icon-s s-question" title="Unknown Price"></span> 
					{/formattedPrice}
				</div>
					
			</div>
			
			<div class="subtitle" style="margin-left: 10px; max-width: 375px;">
				{#. objectName="item" paramName="description"}
					{>editable/}
				{/.}
			</div>
			  
			{>notes/}	
		
		</div>
		
		{>wish_itemMenu/}	
		
	</div>
	