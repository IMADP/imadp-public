		
	{^backUrl}
		{^pageNavigator}
			<div class="ui-navbar ui-mini">
				<ul class="ui-grid-solo">
					{#. url="#" block="a" label="Home" class="to-home"} {>pageNavigatorLink/} {/.}
				</ul>
			</div>
		{/pageNavigator}
	{/backUrl}
	
	{?backUrl}
		{^pageNavigator}
			<div class="ui-navbar ui-mini">
				<ul class="ui-grid-a">
					{#. url=backUrl block="a" label="Back"} {>pageNavigatorLink/} {/.}
					{#. url="#" block="b" label="Home" class="to-home"} {>pageNavigatorLink/} {/.}
				</ul>
			</div>
		{/pageNavigator}
	{/backUrl}
	
	{^backUrl}
		{?pageNavigator}
			<div class="ui-navbar ui-mini">
				<ul class="ui-grid-b">
					{#pageNavigator.previousPageLink block="a" label="Prev"} {>pageNavigatorLink/} {/pageNavigator.previousPageLink}
					{#. url="#" block="b" label="Home" class="to-home"} {>pageNavigatorLink/} {/.}
					{#pageNavigator.nextPageLink block="c" label="Next"} {>pageNavigatorLink/} {/pageNavigator.nextPageLink}
				</ul>
			</div>
		{/pageNavigator}
	{/backUrl}
	
	{?backUrl}
		{?pageNavigator}
			<div class="ui-navbar ui-mini">
				<ul class="ui-grid-c">
					{#pageNavigator.previousPageLink block="a" label="Prev"} {>pageNavigatorLink/} {/pageNavigator.previousPageLink}
					{#. url=backUrl block="b" label="Back"} {>pageNavigatorLink/} {/.}
					{#. url="#" block="c" label="Home" class="to-home"} {>pageNavigatorLink/} {/.}
					{#pageNavigator.nextPageLink block="d" label="Next"} {>pageNavigatorLink/} {/pageNavigator.nextPageLink}
				</ul>
			</div>
		{/pageNavigator}
	{/backUrl}