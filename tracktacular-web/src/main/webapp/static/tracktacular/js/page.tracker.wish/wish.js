tracktacular.trackers.wish.js = {	
		
	onPageLoad: function() {

	},
	
	onPageUpdate: function($context) {
		
		// progress chart
		$('.wish-pie-chart', $context).each(function() {
			wish_drawPieChart($(this))
		});
		
	}
	
}

/**
 * Initializes the report section.
 * 
 * @param $report
 */
function wish_drawPieChart($report) {
	var chartData = $report.data('chartData');
	var backgroundColor = '#7092BE';
	var activeColor = '#A9CF46';
	var data = new Array();
	
	$.each(chartData.items, function(index, value) {		
		data.push({
			y: value.amount ? value.amount : 0,
			title: value.name
		});		
	});
		
	new Highcharts.Chart({
		chart: {
			margin: [20,0,20,0],
			backgroundColor: null,
			plotBackgroundColor: null,
			renderTo: $report.get(0),
			height: 260,
			width: 660
		},
		credits: {
			enabled: false
		},
		title: {
			text:'',
			style: {
				fontSize: '14px'
			}
		},
		tooltip: {
			formatter: function() {
				return '<b>'+ this.point.title +':</b> ' + (this.point.y);
			}
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
	            shadow: true,
				dataLabels: {
	            	formatter: function() {
	            		return this.point.title;
	                }
	            }
			}
		},
		series: [{
			type: 'pie',
			data: data
		}]
	});
}