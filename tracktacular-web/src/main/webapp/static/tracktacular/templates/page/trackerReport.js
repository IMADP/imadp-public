
	<div class="{?alerts} alerts-found {:else} no-alerts-found {?anyAlerts}none{/anyAlerts}{/alerts}">
		<section class="container">
			<header class="heading">
				<h3 style="padding: 11px; font-size:17px;">
					<div class="cf">
						<div class="left">
							<span class="icon-s s-{trackerName}" style="margin-right:7px;"></span>
							{trackerTitle} Report	
						</div>
						<div class="right">
							<select class="to-select-url tracker-report-select">
								<option selected="selected">Jump to page</option>
								{#trackerPages}
									<option value="{value}">{name}</option>
								{/trackerPages}
							</select>
						</div>
					</div>	
				</h3>
			</header>
		</section>
		
		<div id="{trackerName}-report-body">						
			{?enabled}						
				{+trackerReportEnabled/}							
			{:else}						
				<div class="center" style="padding: 25px 0;">
					{+trackerReportDisabled/}
				</div>								
			{/enabled} 								 
		</div>
	</div>