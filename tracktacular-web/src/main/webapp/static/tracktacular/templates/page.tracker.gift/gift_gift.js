
	{! Gift !}
	<div class="cf" style="padding: 15px 45px 0 65px" id="gift-{id}">
			
		<div class="left icons-3 align-right" style="padding-top: 3px;">			
			
			{>notesButton/}
			
			{?publicMode}
				{?favorite} <span class="icon-s s-star-on"></span> {:else} <span class="icon-s s-star-off"></span> {/favorite}
			{:else}
				{#. collapsed="{favorite}" collapsedIcon="s-star-on" uncollapsedIcon="s-star-off"}
    				{>toggleCollapse/}  {~s}
	    		{/.}
	    		
	    		{<toggleCollapseForm}
	    			<form action="{formAction}" method="post">
						<input type="hidden" name="gift" value="{id}" />
						<input type="hidden" name="_eventName" value="toggleGiftFavorite" />
						<input type="hidden" name="_sourcePage" value="{sourcePage}" />
					</form>
				{/toggleCollapseForm}	
			{/publicMode}
				
			{>menuButton/}
						
		</div>
		
		<div class="left">
		
			<div class="item-leader cf" style="width: 470px;">	
						
				<div class="item-leader-left title" style="max-width: 340px;">
					{#. objectName="gift" paramName="name"}
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
			
			<div class="subtitle" style="margin-left: 10px; max-width: 470px;">
				{?received}
					{#. objectName="gift" paramName="occasion" successIds="gift-{id}" successIds="gift-report-chart"}
						{>editable/}
					{/.} {~s}
					
					gift from {~s}
				
					{#. objectName="gift" paramName="sender" successIds="gift-{id}" successIds="gift-report-chart"}
						{>editable/}
					{/.} on {~s}
				
					{date} {~s}			
				{:else}
					{#. objectName="gift" paramName="occasion" successIds="gift-{id}" successIds="gift-report-chart"}
						{>editable/}
					{/.} {~s}
					
					gift to {~s}
				
					{#. objectName="gift" paramName="receiver" successIds="gift-{id}" successIds="gift-report-chart"}
						{>editable/}
					{/.} on {~s}
				
					{date} {~s}			
				{/received}					
			</div>
			
			{>notes/}
			
		</div>
		
		{>gift_giftMenu/}
		
	</div>