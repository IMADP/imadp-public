
	{! Task !}
	<article id="task-{id}" style="padding:4px 0;">
		
		<div class="table">
		
			<div class="cell icons-3 align-right">
				{>notesButton/}
				{>menuButton/}
				{>task_taskIcon/} 
			</div>
			
			<div class="cell" style="padding-left: 5px;">
			
				<div class="title">
					{#. objectName="task" paramName="name"}
						{>editable/}
					{/.} 
				</div>
				
				{?targetDateString}
					<div class="subtitle">
						{targetDate}
						
						{?late}
							{~s}<span class="icon-s s-x" style="margin-top: 3px;"></span>
						{/late}						
					</div>
				{/targetDateString}
				
			</div>
			
		</div>
		
		{>notes/}
		
		{>task_taskMenu/}
		
	</article>