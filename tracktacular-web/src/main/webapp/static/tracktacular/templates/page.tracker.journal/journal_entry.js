	<article class="item" id="entry-{id}" >
			
		<header>
		
			{>menuButton/}
			
			{#. targetId="entry-content-{id}" collapsed=entryCollapsed}
    			{>toggleCollapse/}
    		{/.} 
    		    		
		</header>
				
		<h3 class="center">	
			{#. objectName="entry" paramName="title"}
				{>editable/}
			{/.}   
		</h3>  
		
		<h4 class="center">
			{date}
		</h4>  
		
		<div id="entry-content-{id}" {@when test="{entryCollapsed}"} class="none" {/when}> 
			<pre class="text-content">{content}</pre>
			
			{#. display="true"}
				{>notes/}
			{/.}
		</div>
				
		{^activeState}
			{>persistableStateDetails/}
		{/activeState}
		
		{>journal_entryMenu/}
				
	</article>