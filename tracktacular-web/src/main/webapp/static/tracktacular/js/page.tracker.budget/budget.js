tracktacular.trackers.budget.js = {	
		
	onPageLoad: function() {

	},
	
	onPageUpdate: function($context) {
		
		// budget charts
		$('.navigation-chart', $context).each(function() {
			budget_drawNavigationChart($(this))
		});
		
		// budget charts
		$('.budget-chart', $context).each(function() {
			budget_drawBudgetChart($(this))
		});
		
	}
	
}

/**
 * Draws a chart icon for the given element.
 * 
 * @param $navigationChart
 */
function budget_drawNavigationChart($navigationChart) {
	var chartData = $navigationChart.data('chartData');	
	var data = new Array();
	
	if(chartData.rootCategories.length == 0)
	{
		data.push({
			color: '#D9D9D9',
			y: 1
		});
	}
	else
	{	
		$.each(chartData.rootCategories, function(index, value) {
			
			if(value.netItemAmount == 0)
			{
				data.push({
					color: '#D9D9D9',
					y: 1
				});
			}
			else
			{
				var color = value.income ? '#A9CF46' : '#FF5360';
				var y = value.netItemAmount < 0 ? value.netItemAmount * -1 : value.netItemAmount;
				
				data.push({
					color: color,
					y: y
				});
			}
			
		})
	}
	
	new Highcharts.Chart({
		chart: {
			margin: [-3,0,0,-3],
			spacingTop: 0,
			spacingRight: 0,
			spacingBottom: 0,
			spacingLeft: 0,
			backgroundColor: null,
			plotBackgroundColor: null,
			renderTo: $navigationChart.get(0),
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
					window.location = $('#'+$navigationChart.data("linkId")).attr("href");
				}
			},
			data: data
		}]
	});
}

/**
 * Initializes the chart.
 * 
 * @param $chartData
 */
function budget_drawBudgetChart($chartData) { 
	var noColor = '#B9B9B9';
	var incomeColor = '#A9CF46';
	var expenseColor = '#FF5360';
	var chartData = $chartData.data('chartData');
	var data = new Array();
	var categories = new Array();
	
	var noData = true;
	$.each(chartData.rootCategories, function(index, value) {
		if(value.netItemAmount != 0)
			noData = false;
	});
		
	// if no categories are present
	if(chartData.rootCategories.length == 0 || noData)
	{
		categories.push("No Data");
		
		data.push({
			color: noColor,
			y: 1,
			drilldown: {
				color: noColor,
				categories: ["No Data"],
				data: [{
					color: noColor,
					y: 1
				}]
			}
		});
	}
	
	else
	{
		$.each(chartData.rootCategories, function(index, value) {
			var name = value.name;
			var negative = value.netItemAmount < 0;
			var amount = Math.abs(value.netItemAmount);
			var color = value.netItemAmount == 0 ? noColor : (value.netItemAmount < 0 ? expenseColor : incomeColor); 
				
			var drilldown = {
				categories: [],
				data: []
			};

			// if no children are present
			if(value.childCategories.length == 0)
			{
				drilldown.categories.push(name);
				drilldown.data.push({
					color: color,
					negative: negative,
					y: amount
				});
			}
			else
			{
				var childTotal = 0;
				
				$.each(value.childCategories, function(childIndex, childValue) {
					var childName = childValue.name;
					var childNegative = childValue.netItemAmount < 0;
					var childAmount = Math.abs(childValue.netItemAmount);childValue.netItemAmount < 0
					var childColor = childValue.netItemAmount == 0 ? noColor : (childValue.netItemAmount < 0 ? expenseColor : incomeColor); 
					childTotal = childTotal + childAmount;
					
					drilldown.categories.push(childName);
					drilldown.data.push({
						color: childColor,
						negative: childNegative,
						y: childAmount
					});
					
				});
				
				if(childTotal < amount)
				{
					drilldown.categories.push(name);
					drilldown.data.push({
						color: color,
						negative: negative,
						y: amount - childTotal
					});
				}
				
			}
			
			categories.push(name);

			data.push({
				color: color,
				y: amount,
				negative: negative,
				drilldown: drilldown
			});
		});
	}
	
	// Build the data arrays
	var rootCategoryData = [];
	var allCategoryData = [];
	for (var i = 0; i < data.length; i++) {

		// add root data
		rootCategoryData.push({
			name: categories[i],
			y: data[i].y,
			color: data[i].color
		});

		// add version data
		for (var j = 0; j < data[i].drilldown.data.length; j++) {
			var brightness = 0.2 - (j / data[i].drilldown.data.length) / 5 ;
			
			allCategoryData.push({
				name: data[i].drilldown.categories[j],
				y: data[i].drilldown.data[j].y,
				negative: data[i].drilldown.data[j].negative,
				color: Highcharts.Color(data[i].drilldown.data[j].color).brighten(brightness).get()
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
				return '<b>'+ this.point.name +'</b>: '+ this.y + ' <b>('+Math.floor(this.percentage) + '%</b>)';
			}
		},
		series: [{
			name: 'Root Categories',
			data: rootCategoryData,
			size: '45%',
			dataLabels: {
				formatter: function() {
					return null;
				}
			}
		}, {
			name: 'Category Breakdown',
			data: allCategoryData,
			innerSize: '45%',
			dataLabels: {
            	formatter: function() {
            		return this.y > 1 ? '<b>'+ this.point.name +':</b> '+ (this.point.negative ? '-' : '+') + this.y  : null;
                }
            }
		}]
	});
}