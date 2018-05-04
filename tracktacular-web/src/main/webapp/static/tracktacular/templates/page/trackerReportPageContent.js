	{#contentBody}
	
		{?trackerReportSimpleView}
		
			{#trackerReport}
				{>trackerReport/}
			{/trackerReport}
			
		{:else}
			
			{#trackerReport}
				<article class="item" style="padding: 0;">
					<div class="report" id="{trackerName}">
						{>trackerReport/}
					</div>								
				</article>
			{/trackerReport}
				
		{/trackerReportSimpleView}
					
	{/contentBody}