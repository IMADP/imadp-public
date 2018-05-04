
	{#contentBody}
			
		<nav>
			
			{^publicMode}
				{#. addButtonTemplate="book_bookDialog" addButtonTitle="Add Book"}
					{>addButton/}		
				{/.}
			{/publicMode}		
			
			{>book_bookChart:booksByTitle/}
			
			{>book_bookChart:booksByAuthor/}
			
			{>book_bookChart:booksByTag/}
			
			{>book_bookChart:booksByRating/}
				
	    </nav>
	    
	    <article class="item" style="padding-bottom: 50px">
		
			<header class="cf">						
				<div class="left">
					<span class="icon-s-blank"></span>
				</div>	
				<div class="right">
					{! 
					<select class="to-scroll">
						<option selected="selected">Jump to section</option>
						{#bookCategories}
							<option value="book-category-{nameSlug}">{name}</option>
						{/bookCategories}
					</select>
					!}
				</div>			
			</header>
			
			<h3 class="center">
				Book Library
			</h3>  
			
			<h4 class="center">
				{bookCount} {@when test="{bookCount} == 1"} Book {:else} Books {/when}
			</h4>  
			        		
			{#bookCategories}
	            {>book_bookCategory/}
	        {/bookCategories}
			
		</article>
        
        {>book_tutorial/}	
	
	{/contentBody}