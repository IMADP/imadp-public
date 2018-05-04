tracktacular.trackers.book.js = {

	onPageLoad: function() {

	},

	onPageUpdate: function($context) {

		// chart icons for books
		$('.book-chart-icon', $context).each(function() {
			book_drawBookChart($(this));		
		});

	}

}

/**
 * Draws a chart icon for the given element.
 * 
 * @param $chartIcon
 */
function book_drawBookChart($chartIcon) {
	var bookType =  $chartIcon.data('chartBookType');
	var bookColor = $chartIcon.data('chartBookColor');
	
	chartData = [{
		color: bookColor,
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