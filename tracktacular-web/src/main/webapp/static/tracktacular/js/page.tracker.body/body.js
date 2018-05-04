tracktacular.trackers.body.js = {
		
	onPageLoad: function() {

	},
	
	onPageUpdate: function($context) {
		
		// report for history
		$('.body-report-history', $context).each(function() {
			body_bodyReportHistory($(this));		
		});
		
	}
	
}

/**
 * Initializes the report section.
 * 
 * @param $report
 */
function body_bodyReportHistory($report) {
	var chartData = $report.data('chartData');
	var renderTo = $report.get(0);
	
	var heightData = new Array();
	var neckData = new Array();
	var chestData = new Array();
	var bicepsData = new Array();
	var forearmsData = new Array();
	var wristsData = new Array();
	var waistData = new Array();
	var hipsData = new Array();
	var thighsData = new Array();
	var calvesData = new Array();
	var anklesData = new Array();
	var feetData = new Array();
	var weightData = new Array();
	var bodyFatData = new Array();

	$.each(chartData, function(index, value) {
		if(value.height)
			heightData.push([value.dateMillis, value.height]);
			
		if(value.neck)
			neckData.push([value.dateMillis, value.neck]);
			
		if(value.chest)
			chestData.push([value.dateMillis, value.chest]);
			
		if(value.biceps)
			bicepsData.push([value.dateMillis, value.biceps]);
			
		if(value.forearms)
			forearmsData.push([value.dateMillis, value.forearms]);
			
		if(value.wrists)
			wristsData.push([value.dateMillis, value.wrists]);
			
		if(value.waist)
			waistData.push([value.dateMillis, value.waist]);
			
		if(value.hips)
			hipsData.push([value.dateMillis, value.hips]);
			
		if(value.thighs)
			thighsData.push([value.dateMillis, value.thighs]);
			
		if(value.calves)
			calvesData.push([value.dateMillis, value.calves]);
			
		if(value.ankles)
			anklesData.push([value.dateMillis, value.ankles]);
			
		if(value.feet)
			feetData.push([value.dateMillis, value.feet]);
			
		if(value.weight)
			weightData.push([value.dateMillis, value.weight]);
	
		if(value.bodyFat)
			bodyFatData.push([value.dateMillis, value.bodyFat]);
	});
	
	heightData.reverse();
	neckData.reverse();
	chestData .reverse();
	bicepsData.reverse();	
	forearmsData.reverse();
	wristsData.reverse();
	waistData .reverse();
	hipsData.reverse();	
	thighsData.reverse();
	calvesData.reverse();
	anklesData .reverse();
	feetData.reverse();	
	weightData.reverse();	
	bodyFatData.reverse();	
		
	seriesData = new Array();
	
	if(heightData.length > 0)
		seriesData.push({
			name: 'Height',
			data: heightData
		});
	
	if(neckData.length > 0)
		seriesData.push({
			name: 'Neck',
			data: neckData
		});
	
	if(chestData.length > 0)
		seriesData.push({
			name: 'Chest',
			data: chestData
		});
	
	if(bicepsData.length > 0)
		seriesData.push({
			name: 'Biceps',
			data: bicepsData
		});
	
	if(forearmsData.length > 0)
		seriesData.push({
			name: 'Forearms',
			data: forearmsData
		});
	
	if(wristsData.length > 0)
		seriesData.push({
			name: 'Wrists',
			data: wristsData
		});
	
	if(waistData.length > 0)
		seriesData.push({
			name: 'Waist',
			data: waistData
		});
	
	if(hipsData.length > 0)
		seriesData.push({
			name: 'Hips',
			data: hipsData
		});
	
	if(thighsData.length > 0)
		seriesData.push({
			name: 'Thighs',
			data: thighsData
		});
	
	if(calvesData.length > 0)
		seriesData.push({
			name: 'Calves',
			data: calvesData
		});
	
	if(anklesData.length > 0)
		seriesData.push({
			name: 'Ankles',
			data: anklesData
		});
	
	if(feetData.length > 0)
		seriesData.push({
			name: 'Feet',
			data: feetData
		});
	
	if(weightData.length > 0)
		seriesData.push({
			name: 'Weight',
			data: weightData
		});
	
	if(bodyFatData.length > 0)
		seriesData.push({
			name: 'Body Fat %',
			data: bodyFatData
		});
	
	body_bodyReportHistoryChart(seriesData, renderTo);
}

/**
 * Initializes the Total Body detail chart.
 * 
 * @param $report
 */
function body_bodyReportHistoryChart(seriesData, renderTo) {
	chart = new Highcharts.Chart({
        chart: {
            renderTo: renderTo,
            type: 'line',
            spacingLeft: 50
			
        },
        title: {
			text: null
		},
		subtitle: {
			text: null
		},
		credits: {
            enabled: false
        },
        legend: {
            enabled: true
        },
         xAxis: {
            type: 'datetime'
        },
        yAxis: {
            title: {
                text: 'Body Measurements'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }],
            min: 0           
        },
        tooltip: {
            formatter: function() {
                return '<b>'+this.series.name+':</b> '+ this.y +'<br/>'+
                    Highcharts.dateFormat('%B %e, %Y', this.x);
            }
        },
        plotOptions: {
			series: {
				cursor: 'pointer',
				marker: {
					lineWidth: 1
				}
			}
		},
		series: seriesData
    });
}