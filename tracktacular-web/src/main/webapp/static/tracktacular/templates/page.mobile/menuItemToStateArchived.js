	
	{^archivedState}		
		{#. icon="s-check"}
			{>menuItemSubmitForm/}
		{/.}
		
		{<menuItemSubmitFormBody}
			<form action="{formAction}" method="post">
				<input type="hidden" name="{objectName}" value="{id}" />
				<input type="hidden" name="{objectName}.persistableState" value="ARCHIVED" />
				<input type="hidden" name="_sourcePage" value="{sourcePage}" />
				<input type="hidden" name="_eventName" value="save{objectName|capitalize}" />
				{+menuItemSubmitFormParams/}
			</form>
		{/menuItemSubmitFormBody}	
		
	{/archivedState}