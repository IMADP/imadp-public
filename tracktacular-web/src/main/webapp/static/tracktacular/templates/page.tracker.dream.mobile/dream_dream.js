	
	<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">
		
		<li class="ui-li ui-li-static li-item-header">
			<div class="item-wrapper">
			    <div class="item-left">
			    	{#. menuButtonTemplate="dream_dreamMenu"}
						{>menuButton/}		
					{/.}
			    </div>
		    	<div class="item-center">
		    	 	<div class="title">{title}</div>
		 		 	<div class="subtitle">{date}</div>
		 		 	<div class="subscript">
						{#dreamsigns}
							{name}{~s}
						{/dreamsigns}
						{?favorite}<b>favorite{~s}</b>{/favorite}
						{?lucid}<b>lucid{~s}</b>{/lucid}
					</div>
		 		</div>
		 		<div class="item-right">
		 			{#. targetId="dream-content-{id}" collapsed="true"}
		    			{>toggleCollapse/}
		    		{/.}
				</div>
			</div>	
		</li>
			
		<li class="ui-li ui-li-static li-item" id="dream-content-{id}" style="display:none;">
			<pre>{content}</pre>
    	 	
    	 	{?analysis}
				<pre class="analysis">
					<header>Analysis</header>
					{analysis}
				</pre>
			{/analysis} 
			
		</li>
	</ul>
