
	{#contentBody}
		{#menuFormData}
	
			<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow">
	
				<li class="ui-li ui-li-static li-item-header">
					<div class="item-wrapper">
						<div class="item-left"></div>
						<div class="item-center">
							<div class="title">{menuTitle}</div>
						</div>
						<div class="item-right">	
							<a href="#" class="menu-form-close icon-button ui-btn ui-shadow ui-btn-corner-all ui-btn-inline ui-btn-up-c" data-menu-form-template="" data-menu-form-context-id="">
								<span class="icon-s s-x"></span>
							</a>
						</div>
					</div>
				</li>
				
				{+menuBody/}	
				
			</ul>
	
		{/menuFormData}
	{/contentBody}