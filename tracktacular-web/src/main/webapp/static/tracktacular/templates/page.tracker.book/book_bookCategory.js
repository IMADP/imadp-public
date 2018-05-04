	
	<div id="book-category-{nameSlug}" style="padding-bottom: 5px;">
		
		<div class="cf" style="margin: 40px 60px 0 60px;">
           
            <div class="left" style="margin: -5px 0 0 5px;">	
				<h3>
					{name}
				</h3>
				<h4 style="padding-left: 2px;">
					{bookCount} {@when test="{bookCount} == 1"} Book {:else} Books {/when}
				</h4>
            </div>
             
        </div>	
        
    	{#books}
        	{>book_book/}
        {/books}
	
	</div>
