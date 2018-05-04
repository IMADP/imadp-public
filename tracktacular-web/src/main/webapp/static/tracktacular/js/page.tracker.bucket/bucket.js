tracktacular.trackers.bucket.js = {	
		
	onPageLoad: function() {

	},
	
	onPageUpdate: function($context) {
		
		// progress chart
		$('.bucket-progress-chart', $context).each(function() {
			bucket_drawProgressChart($(this))
		});
		
	}
	
}

/**
 * Initializes the report section.
 * 
 * @param $chartIcon
 */
function bucket_drawProgressChart($chartIcon) {
	var progress = $chartIcon.data('progress');

	new Highcharts.Chart({
		chart: {
			renderTo: $chartIcon.get(0),
			defaultSeriesType: 'bar',
			spacingLeft: 45,
			spacingRight: 45,
			spacingTop: 20,
			spacingBottom: 20,
			height:80
		},
		colors: 
			['#4572A7','#89A54E']
		,
		title: {
			text: null
		},
		subtitle: {
			text: null
		},
		credits: {
			enabled: false
		},
		yAxis: {
			min: 0,
			max: 100,
			gridLineWidth: 0,
			title: {
				text: null
			},
			labels: {
				formatter: function(){
					return null;
				}
			}
		},
		xAxis: [{
			gridLineWidth: 0,
			title: {
				text: 'Start'
			},
			labels: {
				formatter: function(){
					return null;
				}
			}
		},
		{
			gridLineWidth: 0,
			opposite: true,
			title: {
				text: 'End'
			},
			labels: {
				formatter: function(){
					return null;
				}
			}
		}

		],
		legend: {
			enabled: false
		},
		tooltip: {
			formatter: function() {
				return this.series.name;
			}
		},
		plotOptions: {
			bar: {
				pointStart: 0
			},
			series: {
				stacking: 'normal',
				dataLabels: {
					enabled: true,
					color: 'white',
					align: 'center',
	                y: 5,
	                style: {
						fontWeight:'bold',
						fontSize: '12',
						fontFamily: 'Verdana'
					},
					formatter: function(){
						return this.series.index == 1 && this.point.y > 6 ? this.point.y + '%': null;
					}	
				}
			}
		},
		series: [{
			pointWidth: 20,
			color: '#7092BE',
			name: (progress + '%'),
			data: [100 - progress]
		}, {
			pointWidth: 28,
			color: '#A9CF46',
			name: (progress + '%'),
			data: [progress]
		}]
	});

}