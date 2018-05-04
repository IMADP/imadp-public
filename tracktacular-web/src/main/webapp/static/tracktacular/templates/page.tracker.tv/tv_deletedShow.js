		
	<article class="item">
			
		<header>	
			{>menuButton/}
			{>notesButton/}
		</header>
		
		<h3 class="center">		
			{#. objectName="show" paramName="title" successIds="show-{id}"}
				{>editable/}
			{/.}   
		</h3>
		
		{>notes/}
		
		{>persistableStateDetails/}
		
		{>tv_deletedShowMenu/}
		
	</article>