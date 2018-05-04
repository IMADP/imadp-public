	<select id="{object}" name="{object}" {?class}class="{class}"{/class} {?disabled}disabled="disabled"{/disabled}>
		{#options}
			<option {@selected object=object optionKey=optionValue}{/selected} value="{@out key=optionValue}{/out}">{@out key=optionName}{/out}</option>
		{/options}
	</select>
	<div id="{object}-error" class="none error"></div> 