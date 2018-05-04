
	<div class="box-shadow preferences">		
		
		<form action="{formAction}" method="post">			
			
			{^publicMode}
				<input 
					type="hidden" 
					name="preferences" 
					value="preferences"/>
			{/publicMode}
			
			{+preferencesBody/}
			
			{^publicMode}
				<input 
					type="hidden" 
					name="_sourcePage" 
					value="{sourcePage}" />
			{/publicMode}
			
			{^publicMode}
				<input 
					class="preferences-save dialog-form-button ui-state-default ui-priority-primary ui-corner-all" 
					name="savePreferences" 
					value="Save Preferences"
					type="submit">
			{/publicMode}
			
		</form>
		
	</div>
	