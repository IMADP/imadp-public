	
	{^deletedState}		
		{#. confirm="true" icon="s-x"}
			{>menuItemSubmitForm/}
		{/.}
		
		{<menuItemSubmitFormBody}
			<form action="{formAction}" method="post">
				<input type="hidden" name="{objectName}" value="{id}" />
				<input type="hidden" name="{objectName}.persistableState" value="DELETED" />
				<input type="hidden" name="_sourcePage" value="{sourcePage}" />
				<input type="hidden" name="_eventName" value="save{objectName|capitalize}" />
				{+menuItemSubmitFormParams/}
			</form>
		{/menuItemSubmitFormBody}	
		
	{/deletedState}