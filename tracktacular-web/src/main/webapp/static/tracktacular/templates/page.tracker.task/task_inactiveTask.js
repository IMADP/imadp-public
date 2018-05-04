	
	<article class="item">
			
		<header>	
			{>menuButton/}
			{>task_taskIcon/} 
			{>notesButton/}
		</header>
		
		<h3 class="center">	
			{#. objectName="task" paramName="name"}
				{>editable/}
			{/.} 			
		</h3>  
		
		<h4 class="center">
			<b>Category:</b> {categoryPath}	
		</h4> 	
		
		{>notes/}
		
		{>persistableStateDetails/}
		
		{>task_inactiveTaskMenu/}
		
	</article>