
	{! Restaurant !}
	<div class="cf" style="padding: 15px 45px 0 65px" id="restaurant-{id}">
			
		<div class="left icons-3 align-right" style="padding-top: 3px;">			
			
			{#. notesId="notes-{nameSlug}-{id}"}
				{>notesButton/}
			{/.}
			
			{?meals}
				{#. targetId="restaurant-meals-{nameSlug}-{id}" collapsed=restaurantCollapsed}
	    			{>toggleCollapse/}
	    		{/.} {~s}
			{/meals}
			
			{>menuButton/}
						
		</div>
		
		<div class="left">
		
			<div class="item-leader cf" style="width: 470px;">	
						
				<div class="item-leader-left title" style="max-width: 340px;">
					{#. objectName="restaurant" paramName="name"}
						{>editable/}
					{/.}	
				</div>
					
				{#. objectName="restaurant"}
					{>ratingControl/}
				{/.}
				
			</div>
			
			<div class="subtitle" style="margin-left: 10px; max-width: 470px;">
				{#. objectName="restaurant" paramName="city"}
					{>editable/}
				{/.}, {~s}
				
				{#. objectName="restaurant" paramName="state"}
					{>editable/}
				{/.}
			</div>
			
			<div class="subscript" style="margin-left: 10px; max-width: 470px;">				
				{#. objectName="restaurant" paramName="tag"}
					{>editable/}
				{/.}
			</div>
			
			{#. notesId="notes-{nameSlug}-{id}"}
				{>notes/}
			{/.}
			
			<div id="restaurant-meals-{nameSlug}-{id}" class="none" >
				{#meals}
					{>restaurant_meal/}
				{/meals}
			</div>
			
		</div>
		
		{>restaurant_restaurantMenu/}
		
	</div>