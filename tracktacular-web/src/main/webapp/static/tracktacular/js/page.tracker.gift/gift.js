tracktacular.trackers.gift.js = {

	onPageLoad: function() {

	},

	onPageUpdate: function($context) {

		// chart icons for gifts
		$('.gift-chart-icon', $context).each(function() {
			gift_drawGiftChart($(this));		
		});

		// chart icons for reports
		$('.gift-report-chart', $context).each(function() {
			gift_drawGiftReportChart($(this));		
		});

	}

}

/**
 * Draws a chart icon for the given element.
 * 
 * @param $chartIcon
 */
function gift_drawGiftChart($chartIcon) {
	var giftType =  $chartIcon.data('chartGiftType');
	var giftColor = $chartIcon.data('chartGiftColor');
	
	chartData = [{
		color: giftColor,
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
 * Initializes the chart.
 * 
 * @param $chartData
 */
function gift_drawGiftReportChart($chartData) { 
	var chartData = $chartData.data('chartData');
	var data = new Array();
	var categories = new Array();
	
	$.each(chartData.categories, function(index, value) {
		var name = value.name;
		var amount = value.giftCount;
			
		var drilldown = {
			categories: [],
			data: []
		};
		
		$.each(value.childCategories, function(childIndex, childValue) {
			var childName = childValue.name;
			var childAmount = childValue.giftCount;
			
			drilldown.categories.push(childName);
			drilldown.data.push({
				y: childAmount
			});
			
		});
		
		categories.push(name);

		data.push({
			y: amount,
			drilldown: drilldown
		});
	});
	
	// Build the data arrays
	var rootCategoryData = [];
	var allCategoryData = [];
	var colors = Highcharts.getOptions().colors;
	 
	for (var i = 0; i < data.length; i++) {

		// add root data
		rootCategoryData.push({
			name: categories[i],
			y: data[i].y,
			color: colors[i]
		});

		// add version data
		for (var j = 0; j < data[i].drilldown.data.length; j++) {
			var brightness = 0.2 - (j / data[i].drilldown.data.length) / 5 ;
			
			allCategoryData.push({
				name: data[i].drilldown.categories[j],
				y: data[i].drilldown.data[j].y,
				color: Highcharts.Color(rootCategoryData[i].color).brighten(brightness).get()
			});
		}
	}

	// Create the chart
	chart = new Highcharts.Chart({
		chart: {
			renderTo: $chartData.get(0),
			margin: [20,0,20,0],
			type: 'pie',
			height: 260,
			width: 660
		},
		title: {
			text: null
		},
		credits: {
			enabled: false
		},
		plotOptions: {
			pie: {
				shadow: false
			}
		},
		tooltip: {
			formatter: function() {
				return '<b>'+ this.point.name +':</b> '+ this.y;
			}
		},
		series: [{
			name: 'Root Categories',
			data: allCategoryData,
			size: '45%',
			dataLabels: {
				formatter: function() {
					return null;
				}
			}
		}, {
			name: 'Category Breakdown',
			data: rootCategoryData,
			innerSize: '45%',
			dataLabels: {
            	formatter: function() {
            		return '<b>'+ this.point.name +':</b> '+ this.y;
                }
            }
		}]
	});
}