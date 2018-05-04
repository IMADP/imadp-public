		
	<article class="item">
			
		<header>
			{>menuButton/}
			{>notesButton/}
		</header>
		
		<h3 class="center">		
			{#. objectName="item" paramName="name" successIds="item-{id}"}
				{>editable/}
			{/.}   
		</h3>
		
		<h4 class="center">
			{#. objectName="item" paramName="description" successIds="book-{id}"}
				{>editable/}
			{/.}
		</h4>
		
		{>notes/}
		
		{>persistableStateDetails/}
		
		{>bucket_deletedItemMenu/}
		
	</article>