	
	{#. menuTitle="Options"}
		{>menu/}		
	{/.}
		
	{<menuBody}	
		
		<li class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-c">
			<div class="ui-btn-inner ui-li">
				<div class="ui-btn-text">
					<a href="#" class="to-home ui-link-inherit">
						Home
					</a>
				</div>
				<span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span>
			</div>
		</li>
		
		{#. menuItemLinkTitle="Switch to Full Site" menuItemLinkUrl=toNonMobileLinkUrl menuItemLinkExternal="true"}
			{>menuItemLink/}		
		{/.}
		
		{#. menuItemLinkTitle="Logout" menuItemLinkUrl=logoutLinkUrl menuItemLinkExternal="true"}
			{>menuItemLink/}		
		{/.}
		
	{/menuBody}		