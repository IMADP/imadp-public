
	<div>
		{?ldlCholesterol}
			<div class="title center" style="margin: 25px 0 0 0;">
				Low-Density Lipoprotein (LDL): <strong>{ldlCholesterol}</strong>
			</div>
			
			<div class="cholesterol-chart-ldl" data-ldl="{ldlCholesterol}" style="margin-left: 80px"></div>
		{/ldlCholesterol}
		
		{?hdlCholesterol}
			<div class="title center" style="margin: 25px 0 0 0">
				High-Density Lipoprotein (HDL): <strong>{hdlCholesterol}</strong>
			</div>
			
			<div class="cholesterol-chart-hdl" data-hdl="{hdlCholesterol}" style="margin-left: 80px"></div>				
		{/hdlCholesterol}
		
		{?triglycerides}
			<div class="title center" style="margin: 25px 0 0 0">
				Triglycerides: <strong>{triglycerides}</strong>
			</div>
			
			<div class="cholesterol-chart-triglycerides" data-triglycerides="{triglycerides}" style="margin-left: 80px"></div>				
		{/triglycerides}
		
		{?totalCholesterol}
			<div class="title center" style="margin: 25px 0 0 0">
				Total Cholesterol: <strong>{totalCholesterol}</strong>			
			</div>
			
			<div class="cholesterol-chart-total" data-total="{totalCholesterol}" style="margin-left: 80px"></div>			
		{/totalCholesterol}
	</div>
		
		