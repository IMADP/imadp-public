tracktacular.trackers.calendar.js = {
		
	onPageLoad: function() {

	},
	
	onPageUpdate: function($context) {
	
		// calendar month charts
		$('.calendar-nav-chart', $context).each(function() {
			calendar_drawCalendarNavChart($(this))
		});
			
	}
	
}


/**
 * Draws a chart icon for the given element.
 * 
 * @param $chartData
 */
function calendar_drawCalendarNavChart($chartData) {
	var id = $chartData.data('id');
	var backgroundColor = '#7092BE';
	var activeColor = '#FF5360';
	var activeMonthColor = '#A9CF46';
	var data = new Array();
	var currentMonth = new Date().getMonth() + 1;
	
	data.push({
			color: id == 'all' || id == 1 ? (currentMonth == 1 ? activeMonthColor : activeColor) : backgroundColor,
			y: 1
		},{
			color: id == 'all' || id == 2 ? (currentMonth == 2 ? activeMonthColor : activeColor) : backgroundColor,
			y: 1
		},{
			color: id == 'all' || id == 3 ? (currentMonth == 3 ? activeMonthColor : activeColor) : backgroundColor,
			y: 1
		},{
			color: id == 'all' || id == 4 ? (currentMonth == 4 ? activeMonthColor : activeColor) : backgroundColor,
			y: 1
		},{
			color: id == 'all' || id == 5 ? (currentMonth == 5 ? activeMonthColor : activeColor) : backgroundColor,
			y: 1
		},{
			color: id == 'all' || id == 6 ? (currentMonth == 6 ? activeMonthColor : activeColor) : backgroundColor,
			y: 1
		},{
			color: id == 'all' || id == 7 ? (currentMonth == 7 ? activeMonthColor : activeColor) : backgroundColor,
			y: 1
		},{
			color: id == 'all' || id == 8 ? (currentMonth == 8 ? activeMonthColor : activeColor) : backgroundColor,
			y: 1
		},{
			color: id == 'all' || id == 9 ? (currentMonth == 9 ? activeMonthColor : activeColor) : backgroundColor,
			y: 1
		},{
			color: id == 'all' || id == 10 ? (currentMonth == 10 ? activeMonthColor : activeColor) : backgroundColor,
			y: 1
		},{
			color: id == 'all' || id == 11 ? (currentMonth == 11 ? activeMonthColor : activeColor) : backgroundColor,
			y: 1
		},{
			color: id == 'all' || id == 12 ? (currentMonth == 12 ? activeMonthColor : activeColor) : backgroundColor,
			y: 1
		});
			
	new Highcharts.Chart({
		chart: {
			margin: [-3,0,0,-3],
			spacingTop: 0,
			spacingRight: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			backgroundColor: null,
			plotBackgroundColor: null,
			renderTo: $chartData.get(0),
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
					window.location = $chartData.data("url");
				}
			},
			data: data
		}]
	});
}
