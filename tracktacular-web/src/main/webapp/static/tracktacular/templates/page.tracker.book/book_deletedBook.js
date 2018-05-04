		
	<article class="item">
			
		<header>
			{>menuButton/}
			{>notesButton/}
		</header>
		
		<h3 class="center">		
			{#. objectName="book" paramName="title" successIds="book-{id}"}
				{>editable/}
			{/.}   
		</h3>
		
		<h4 class="center">
			{#. objectName="book" paramName="author" successIds="book-{id}"}
				{>editable/}
			{/.}
		</h4>
		
		{>notes/}
		
		{>persistableStateDetails/}
		
		{>book_deletedBookMenu/}
		
	</article>