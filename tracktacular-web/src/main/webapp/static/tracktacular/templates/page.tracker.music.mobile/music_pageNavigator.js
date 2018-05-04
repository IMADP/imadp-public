		
	<div class="ui-navbar ui-mini">
		<ul class="ui-grid-b">
			<li class="ui-block-a">
				<a href="{albumsPageUrl}" class="ui-btn ui-btn-inline ui-btn-hover-c ui-btn-up-c {?albumsPage} ui-disabled {:else} ui-btn-active  {/albumsPage}">
					<span class="ui-btn-inner">
						<span class="ui-btn-text">
							Albums
						</span>
					</span>
				</a>
			</li>
			{#. url="#" block="b" label="Home" class="to-home"} {>pageNavigatorLink/} {/.}
			<li class="ui-block-c">
				<a href="{songsPageUrl}" class="ui-btn ui-btn-inline ui-btn-up-c {^albumsPage} ui-disabled {:else} ui-btn-active  {/albumsPage}">
					<span class="ui-btn-inner">
						<span class="ui-btn-text">
							Songs
						</span>
					</span>
				</a>
			</li>			
		</ul>
	</div>
		