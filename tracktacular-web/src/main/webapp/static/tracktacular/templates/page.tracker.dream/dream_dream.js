		
	<article class="item" id="dream-{id}" >
			
		<header>
		
			{>menuButton/}
			
			{#. targetId="dream-content-{id}" collapsed=dreamCollapsed preventForm="true"}
    			{>toggleCollapse/}
    		{/.}
    		
    		<span class="right">
    			{?publicMode}
    				{?favorite} <span class="icon-s s-star-on"></span> {:else} <span class="icon-s s-star-off"></span> {/favorite}
    			{:else}
    				{#. collapsed="{favorite}" collapsedIcon="s-star-on" uncollapsedIcon="s-star-off" successIds="dream-chart-all, dream-chart-favorite"}
	    				{>toggleCollapse/}  {~s}
		    		{/.}
		    		
		    		{<toggleCollapseForm}
		    			<form action="{formAction}" method="post">
							<input type="hidden" name="dream" value="{id}" />
							<input type="hidden" name="_eventName" value="toggleDreamFavorite" />
							<input type="hidden" name="_sourcePage" value="{sourcePage}" />
						</form>
					{/toggleCollapseForm}	
    			{/publicMode}
    		</span>
    		
		</header>
				
		<h3 class="center">
	
			{#. objectName="dream" paramName="title"}
				{>editable/}
			{/.}   
			
		</h3>  
		
		<h4 class="center">
			{date}
		</h4>  
		
		<div id="dream-content-{id}" {@when test="{dreamCollapsed}"} class="none" {/when} > 
			<pre class="text-content">{content}</pre>
			
			{?analysis}
				<pre class="info-text">
					<header>Analysis</header>
					{analysis}
				</pre>
			{/analysis} 
		</div>
		
		{?showTags}
			<div class="tags">
				{#dreamsigns}
					<span class="tag">{name}</span>
				{/dreamsigns}
				
				{?lucid}
					<span class="red-tag">lucid</span>
				{/lucid}
			</div>
		{/showTags}
			
		{^activeState}
			{>persistableStateDetails/}
		{/activeState}
		
		{>dream_dreamMenu/}
				
	</article>