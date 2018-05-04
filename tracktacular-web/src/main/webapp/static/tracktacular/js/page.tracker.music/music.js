tracktacular.trackers.music.js = {

	onPageLoad: function() {

	},

	onPageUpdate: function($context) {

		// chart icons for songs
		$('.song-chart-icon', $context).each(function() {
			music_drawSongChart($(this));		
		});
		
		// chart icons for albums
		$('.album-chart-icon', $context).each(function() {
			music_drawAlbumChart($(this));		
		});

	}

}

/**
 * Draws a chart icon for the given element.
 * 
 * @param $chartIcon
 */
function music_drawSongChart($chartIcon) {
	var songType =  $chartIcon.data('chartSongType');
	var songColor = $chartIcon.data('chartSongColor');
	
	chartData = [{
		color: songColor,
		y: 1
	}];
	
	new Highcharts.Chart({
		chart: {
			margin: [-3,0,0,-3],
			spacingTop: 0,
			spacingRight: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			backgroundColor: null,
			plotBackgroundColor: null,
			renderTo: $chartIcon.get(0),
			width: 36,
			height: 36
		},
		credits: {
			enabled: false
		},
		title: {
			text: null
		},
		tooltip: {
			enabled: false
		},
		plotOptions: {
			series: {
				states: {
					hover: {
						enabled: false
					}
				}
			},
			pie: {
				allowPointSelect: false,
				cursor: 'pointer',
				shadow: false,
				dataLabels: {
					enabled: false
				}
			}
		},
		series: [{
			type: 'pie',
			events: {
				click: function(event) {
					window.location = $('#'+$chartIcon.data("linkId")).attr('href');
				}
			},
			data: chartData
		}]
	});
}


/**
 * Draws a chart icon for the given element.
 * 
 * @param $chartIcon
 */
function music_drawAlbumChart($chartIcon) {
	var albumType =  $chartIcon.data('chartAlbumType');
	var albumColor = $chartIcon.data('chartAlbumColor');
	
	chartData = [{
		color: albumColor,
		y: 1
	}];
	
	new Highcharts.Chart({
		chart: {
			margin: [-3,0,0,-3],
			spacingTop: 0,
			spacingRight: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			backgroundColor: null,
			plotBackgroundColor: null,
			renderTo: $chartIcon.get(0),
			width: 36,
			height: 36
		},
		credits: {
			enabled: false
		},
		title: {
			text: null
		},
		tooltip: {
			enabled: false
		},
		plotOptions: {
			series: {
				states: {
					hover: {
						enabled: false
					}
				}
			},
			pie: {
				allowPointSelect: false,
				cursor: 'pointer',
				shadow: false,
				dataLabels: {
					enabled: false
				}
			}
		},
		series: [{
			type: 'pie',
			events: {
				click: function(event) {
					window.location = $('#'+$chartIcon.data("linkId")).attr('href');
				}
			},
			data: chartData
		}]
	});
}