
	{#contentBody}
		
		<form enctype="multipart/form-data" class="dialog-form" action="{formAction}" method="post">
		
			<div class="box-shadow">
				
				<div class="dialog-form-heading ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
					<span class="left ui-dialog-title" id="ui-dialog-title-2">{+importTitle/}</span>
				</div>
				
				<div class="dialog-form-body">
				
					<div id="validation-errors" class="message-box message-error cf none">
						<div class="message-icon">
							<span class="icon-l l-error"></span>
						</div>
						<div class="message-content">
							<div>There were errors with your submission</div>
					    </div>
					</div>
					
					You can import data from an external source by first converting it to a <b>CSV</b> file, which is simply a text file {~s}
					where each line holds a comma separated list of values. The first line must contain the headers, which identifies what the values are. {~s}
					
					<br/>
					<br/>
					
					The easiest way is to first load your data in a spreadsheet like Microsoft Excel. {~s} <br/>
					The first row should have these columns (some are optional): {~s}
					
					<br/>
					<br/>
					
					<div class="center">
						<b> | </b>
						{#importProperties}
							<b>{name} | </b>
						{/importProperties}
					</div>
					
					<br/>
					
					The remaining rows should contain your data. Date values should be in the format: YYYY-MM-DD (example: 1983-12-01).{~s} 
					
					<br/>
					<br/>
					
					When you are finished, {~s}
					click <b>File > Save As</b> and select the <b>CSV</b> file type to export it. {~s}
					
					<br/>
					<br/>
					
					Upload your file here and click submit to preview your data. {~s}
					If any rows were invalid, move your mouse over the <span class="icon-s s-x"></span> icon to find out why.
					
					<br/>
					<br/>
					
					<div class="center">
						<input type="file" name="importedData" />
					</div>
					
					<input type="hidden" name="_sourcePage" value="{sourcePage}" />
					<input type="hidden" name="_eventName" value="importPreview" />
				
				</div>
				
				<hr>
				
				<div class="dialog-form-footer cf">
					<div class="dialog-form-footer-img">
						<img id="dialog-form-busy" class="none" alt="Loading"
							src="/static/tracktacular/img/page/busy-indicator-form.gif" >
					</div>
					<div class="dialog-form-footer-button">
						<button class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all">
							Preview
						</button>
					</div>
				</div>
				
			</div>
		</form>
			
		{?importedItems}
			<article class="item import" style="padding: 0;margin-bottom: 25px; margin-top:0;word-wrap:break-word;">
				<table class="striped-table" style="width: 100%;margin-left: 0;table-layout:fixed;">
					<tr>
						{#importProperties}
							<th>{name}</th>
						{/importProperties}
						<th style="width: 35px;">valid</th>
					</tr>
					
					{#importedItems}
						<tr>
							{#rows}
								<td style="border: 1px solid #EEEFF1;">{value}</td>
							{/rows}
							<td style="border: 1px solid #EEEFF1;">
								{?valid}
									<span class="icon-s s-check"></span>
								{:else}
									<span class="icon-s s-x" title="{#messages}{.}{@sep},{/sep}{/messages}"></span>
								{/valid}
							</td>
						</tr>
					{/importedItems}
					
				</table>
			</article>
			
			<div class="center">
			
				<form class="dialog-form" action="{formAction}" method="post">
					<input type="hidden" name="_sourcePage" value="{sourcePage}" />
					<input type="hidden" name="_eventName" value="importData" />
					<input type="hidden" name="importAction.formToken" value="{formToken}" />
					<input type="hidden" name="importAction.sessionToken" value="{sessionToken}" />
					<input 
						class="preferences-save dialog-form-button ui-state-default ui-priority-primary ui-corner-all" 
						name="importData" 
						value="Import All Items"
						type="submit"/>
				</form>
				
			</div>							
		{/importedItems}
		
	{/contentBody}