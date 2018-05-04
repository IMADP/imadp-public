
	{#contentBody}
		
		<h2>Deleted Books</h2>		
				
		<h4 style="padding-top: 5px; padding-left:60px;">
			{deletedBooksCount} Deleted {@when test="{deletedBooksCount} == 1"} Book {:else} Books {/when}
		</h4>				
	
		{#deletedBooks}
			{>book_deletedBook/}
		{/deletedBooks}
	
		{#pageNavigator}
			{>pageNavigator/}
		{/pageNavigator}
			
	{/contentBody}