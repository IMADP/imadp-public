
	<!--[if !IE]> -->
		<canvas class="to-tag-cloud" height="350" width="720" style="margin: 25px 0 0 35px;">
		 	<ul>
				{#tagCloudItems}
			    	<li><a href="{selectUrl}" style="font-size: {weight}ex">{nameSlug}</a></li>
		    	{/tagCloudItems}
		    </ul>
		 </canvas>
	 <!-- <![endif]-->
	 
	 
	 <!--[if IE]>
	 	<div style="margin: 40px 60px;">
			{#tagCloudItems}
		    	<a href="{selectUrl}" style="margin: 10px; line-height: {weightInPx}px; font-size: {weightInPx}px">{nameSlug}</a> {~s}
	    	{/tagCloudItems}
	    </div>
	<![endif]-->
	