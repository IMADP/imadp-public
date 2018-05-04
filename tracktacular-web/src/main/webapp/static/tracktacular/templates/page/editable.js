
	{^publicMode}
		<span class="{cssClass} editable" 
			data-editable-id="{id}" 
			data-editable-property="{objectName}.{paramName}"
			{?successIds}data-editable-success-ids="{successIds}"{/successIds}>
			{@out key=paramName/}
		</span>
	{:else}
		<span class="{cssClass}">
			{@out key=paramName/}
		</span>
	{/publicMode}