		
	<div class="ui-navbar ui-mini">
		<ul class="ui-grid-b">
			<li class="ui-block-a">
				<a href="{receivedPageUrl}" class="ui-btn ui-btn-inline ui-btn-hover-c ui-btn-up-c {?receivedPage} ui-disabled {:else} ui-btn-active  {/receivedPage}">
					<span class="ui-btn-inner">
						<span class="ui-btn-text">
							Received
						</span>
					</span>
				</a>
			</li>
			{#. url="#" block="b" label="Home" class="to-home"} {>pageNavigatorLink/} {/.}
			<li class="ui-block-c">
				<a href="{givenPageUrl}" class="ui-btn ui-btn-inline ui-btn-up-c {^receivedPage} ui-disabled {:else} ui-btn-active  {/receivedPage}">
					<span class="ui-btn-inner">
						<span class="ui-btn-text">
							Given
						</span>
					</span>
				</a>
			</li>			
		</ul>
	</div>
		