		
	<article class="item">
			
		<header>	
			{>menuButton/}
			{>notesButton/}
		</header>
		
		<h3 class="center">		
			{#. objectName="restaurant" paramName="name" successIds="restaurant-{id}"}
				{>editable/}
			{/.}   
		</h3>
		
		<h4 class="center">
			{#. objectName="restaurant" paramName="tag" successIds="restaurant-{id}"}
				{>editable/}
			{/.}
		</h4>
		
		{>notes/}
		
		{>persistableStateDetails/}
		
		{>restaurant_deletedRestaurantMenu/}
		
	</article>