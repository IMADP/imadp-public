	
	<div id="album-category-{nameSlug}" style="padding-bottom: 5px;">
		
		<div class="cf" style="margin: 40px 60px 0 60px;">
           
            <div class="left" style="margin: -5px 0 0 5px;">	
				<h3>
					{name}
				</h3>
				<h4 style="padding-left: 2px;">
					{albumCount} {@when test="{albumCount} == 1"} Album {:else} Albums {/when}
				</h4>
            </div>
             
        </div>	
        
    	{#albums}
    		{>music_album/}
        {/albums}
	
	</div>
