		
	<article class="item">
			
		<header>	
		
			{>menuButton/}
			
			{?url}
				{~s}<a href="{url}"><span class="icon-s s-link"></span></a>{~s}
			{/url}		
			
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
		
		{>wish_inactiveItemMenu/}
		
	</article>