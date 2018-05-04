	
	<div id="song-category-{nameSlug}" style="padding-bottom: 5px;">
		
		<div class="cf" style="margin: 40px 60px 0 60px;">
           
            <div class="left" style="margin: -5px 0 0 5px;">	
				<h3>
					{name}
				</h3>
				<h4 style="padding-left: 2px;">
					{songCount} {@when test="{songCount} == 1"} Song {:else} Songs {/when}
				</h4>
            </div>
             
        </div>	
        
    	{#songs}
    		{>music_song/}
        {/songs}
	
	</div>
