	
	<ul class="item ui-listview ui-listview-inset ui-corner-all ui-shadow" id="body-{id}" >
		
		<li class="ui-li ui-li-static li-item-header">
			<div class="item-wrapper">
			    <div class="item-left">
			    	{#. menuButtonTemplate="cholesterol_cholesterolMenu"}
						{>menuButton/}		
					{/.}
			    </div>
		    	<div class="item-center">
		    	 	<div class="title">Cholesterol Entry</div>
		 		 	<div class="subtitle">{date}</div>
		 		</div>
		 		<div class="item-right">
		 			{>notesButton/}
				</div>
			</div>	
		</li>
			
		<li class="ui-li ui-li-static li-item center">
			
			{?ldlCholesterol}<div><b>Low-Density Lipoprotein (LDL): </b> {ldlCholesterol} </div>{/ldlCholesterol}
			{?hdlCholesterol}<div><b>High-Density Lipoprotein (HDL): </b> {hdlCholesterol} </div>{/hdlCholesterol}
			{?triglycerides}<div><b>Triglycerides: </b> {triglycerides} </div>{/triglycerides}
			{?totalCholesterol}<div><b>Total Cholesterol: </b> {totalCholesterol} </div>{/totalCholesterol}
			
    	 	{>notes/}
			
			{>persistableStateDetails/}
			
		</li>
	</ul>