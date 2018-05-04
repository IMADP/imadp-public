
	{#contentBody}
	
		<ul id="index" class="ui-listview ui-listview-inset ui-corner-all ui-shadow">
		
			{?personalLinks}
				<li class="ui-li ui-li-divider ui-bar-b">Personal</li>
			{/personalLinks}
			
			{#personalLinks}
				{>indexPageLink/}
			{/personalLinks}
			
			{?peopleLinks}
				<li class="ui-li ui-li-divider ui-bar-b">People</li>
			{/peopleLinks}
			
			{#peopleLinks}
				{>indexPageLink/}
			{/peopleLinks}
			
			{?healthLinks}
				<li class="ui-li ui-li-divider ui-bar-b">Health</li>
			{/healthLinks}
			
			{#healthLinks}
				{>indexPageLink/}
			{/healthLinks}
			
			{?entertainmentLinks}
				<li class="ui-li ui-li-divider ui-bar-b">Entertainment</li>
			{/entertainmentLinks}
			
			{#entertainmentLinks}
				{>indexPageLink/}
			{/entertainmentLinks}
			
		</ul>	
		
		<div style="margin-top: 25px;" class="ui-btn-text">
			<a data-external="true" href="{blogUrl}" class="ui-btn ui-shadow ui-btn-corner-all ui-btn-hover-b ui-btn-up-b">
				<span class="ui-btn-inner">
					<span class="ui-btn-text">
						Blog
					</span>
				</span>
			</a>
		</div>
		
	{/contentBody}
		