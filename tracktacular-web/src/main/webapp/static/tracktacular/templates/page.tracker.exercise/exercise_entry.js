
	<tr id="entry-{id}" class="to-highlight" data-highlight-target-id="entry-{id}">
	
		{^publicMode}
			<td>
				{>menuButton/}
			</td>
		{/publicMode}
		
		<td>{time}</td>
		
		{?trackRepetitions}
			<td>
				{#. objectName="entry" paramName="repetitions" successIds="entry-{id}"}
					{>editable/}
				{/.} 
			</td>
		{/trackRepetitions}
			
		{?trackWeight}
			<td>
				{#. objectName="entry" paramName="weight" successIds="entry-{id}"}
					{>editable/}
				{/.} 
			</td>
		{/trackWeight}
			
		{?trackDistance}
			<td>
				{#. objectName="entry" paramName="distance" successIds="entry-{id}"}
					{>editable/}
				{/.} 
			</td>
		{/trackDistance}
			
		{?trackDuration}
			<td>
				{?hours}
					{#. objectName="entry" paramName="hours" successIds="entry-{id}"}
						{>editable/}h {~s}
					{/.} 
				{/hours}
					
				{?minutes}
					{#. objectName="entry" paramName="minutes" successIds="entry-{id}"}
						{>editable/}m {~s}
					{/.} 
				{/minutes} 
					
				{?seconds}
					{#. objectName="entry" paramName="seconds" successIds="entry-{id}"}
						{>editable/}s {~s}
					{/.} 
				{/seconds} 
			</td>
		{/trackDuration}
			
		{?trackCalories}
			<td>
				{#. objectName="entry" paramName="calories" successIds="entry-{id}"}
					{>editable/}
				{/.} 
			</td>
		{/trackCalories}	
			
		<td class="none">
			{>exercise_entryMenu/}
		</td>
		
	</tr>