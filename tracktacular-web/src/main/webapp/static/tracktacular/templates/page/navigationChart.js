	
	{#navigationChart}
		<div class="option" id="navigation-chart-{chartId}">
		
			<span class="option-icon navigation-chart" 
				data-chart-data="{chartData|json}"
			    data-link-id="navigation-chart-link-{chartId}">
			</span>
				
			<a id="navigation-chart-link-{chartId}" 
				class="to-view-selected {?selected}active{/selected}" 
				href="{selectUrl}" 
			 	data-view-external-link="{externalLink}"
				data-view-selected-all="{selectedAll}"
				data-view-selected-container-class="{selectedContainerClass}"
				data-view-selected-target-id="{selectedTargetId}">
				{chartLabel}
			</a>
					
		</div>
	{/navigationChart}