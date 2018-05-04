/**
 * Visits a url that was selected in a drop down.
 * 
 */
function toSelectUrl($this) {
	this.window.location = $this.val();
}

/**
 * Converts a url to a short url.
 * 
 */
function toShortUrl($this) {
	var url = $this.attr('href');
	
    $.getJSON("http://api.bitly.com/v3/shorten?callback=?", 
        { 
            "format": "json",
            "apiKey": "R_0cc1f316b5befcfc5986fed0114e009e",
            "login": "tracktacular",
            "longUrl": url
        },
        function(response) {
        	$this.attr('href', response.data.url);
        }
    );
}

/**
 * Submits a payment to the Stripe api.
 * 
 */
function toSubmitPayment($this) {

	// Disable the submit button to prevent repeated clicks
	var $busyIndicator = $this.find('#dialog-form-busy');
	var $submitButton = $this.find('#payment-form-submit');
	var $labelNumber = $this.find("label[for='payment-number']");
	var $labelExp = $this.find("label[for='payment-exp']");
	var $labelCvc = $this.find("label[for='payment-cvc']");
	
	$busyIndicator.show();
    $submitButton.button('disable');
    $labelNumber.removeClass('error');
    $labelExp.removeClass('error');
    $labelCvc.removeClass('error');
    
    Stripe.createToken($this, function(status, response) {
		
		if (response.error)
		{
			if(response.error.param == 'number')
				$labelNumber.addClass('error');
		    
		    if(response.error.param == 'exp_month' || response.error.param == 'exp_year')
				$labelExp.addClass('error');
		   
			if(response.error.param == 'cvc')
				$labelCvc.addClass('error');
		
		    // Show the errors on the form
		    $this.find('.payment-errors').text(response.error.message);
		    
		    $busyIndicator.hide();
    		$submitButton.button('enable');
		}
		else
		{
		    // token contains id, last4, and card type
		    var token = response.id;
		   
		    // Insert the token into the form so it gets submitted to the server
		    $this.append($('<input type="hidden" name="customerId" />').val(token));
		    $this.get(0).submit();
		}
	});

    // Prevent the form from submitting with the default action
    return false;
}
	
/**
 * Scrolls to a section that was selected in a drop down.
 * 
 */
function toScroll($this) {
	$('html, body').stop().animate({
        scrollTop: $($('#' + $this.val())).offset().top
    }, 750, 'easeInOutExpo');
}

/**
 * Prepares a form that was not rendered with a stripes form tag.
 * Allows proper handling of checkboxes.
 * 
 */
function toSubmitForm($this) {
	$('input[type=checkbox]', $this).each(function() { 
		if(!$(this).is(':checked'))
			$('<input>').attr('type','hidden').attr('name', $(this).attr('name')).attr('value','false').appendTo($this);
	});
}

/**
 * Executes an ajax request to retrieve the tracktacular report data.
 * 
 * The following data attributes are used:
 *  [data-tracktacular-report-data-url] - The url to retrieve the data
 * 
 * @param $this
 */
function toTracktacularReport($this) {
	var tracktacularReportData = $this.data('tracktacularReportData');
			
	// draw navigation chart
	drawTracktacularReportNavigationChart(tracktacularReportData.trackerReports);
				
	// invoke page update for the associated trackers
	$(tracktacularReportData.trackerReports).each(function() {
		var tracker = this.trackerReport.trackerName;
		var $trackerReport = $('#' + tracker, $this);
		
		if(tracker && tracktacular.trackers[tracker].js)
			window[tracktacular.trackers[tracker].js.onPageUpdate($trackerReport)];
	});
	
	return false;
}

/**
 * Draws the tracktacular report pie chart doubling as the report shortcut.
 * 
 * @param trackerReports
 */
function drawTracktacularReportNavigationChart(trackerReports) {
	var chartData = new Array(trackerReports.length);
	var alertData = new Array(trackerReports.length);

	for(var i=0,len=trackerReports.length; i<len; i++)
	{
		var value = trackerReports[i].trackerReport.trackerName;	
		var alerts = trackerReports[i].trackerReport.alerts;	
		
		var cData = {
				alerts: alerts,
				color: tracktacular.trackers[value].color,
				name: tracktacular.trackers[value].name,
				y: 1,
				id: value
		};
		
		var aData = {
				alerts: alerts,
				color: alerts ? '#FF0000' : '#F0FAF2',
				name: tracktacular.trackers[value].name,
				y: 1,
				id: value
		};
		
		chartData[i] = cData;
		alertData[i] = aData;
	}

	// render the tracktacular report navigation chart
	new Highcharts.Chart({
		chart: {
			renderTo: $('#tracktacular-report-navigation-chart').get(0),
			height: 230
		},
		credits: {
			enabled:false
		}, 
		legend: {
			enabled:false
		}, 
		title: {
			text: null
		},
		tooltip: {
			enabled: true,
			formatter: function() {
				return '<b>'+ this.point.name +'</b>';
			}
		},
		plotOptions: {
			pie: {
				allowPointSelect: false,
				cursor: 'pointer',
				shadow: true,
				borderWidth: 0,
				borderColor: '#000000', 
				backgroundColor: null,
				plotBackgroundColor: null,
				showInLegend: true,
				dataLabels: {
					enabled: true,
					formatter: function() {
						return '<b>'+ this.point.name +'</b>';
					}
				},
				point: {
					events: {
						legendItemClick: function() {
							$('html, body').stop().animate({
					            scrollTop: $($('#' + this.id)).offset().top
					        }, 750,'easeInOutExpo');
							window.location.hash = this.id;
							return false;
						}
					}
				}
			}
		},
		series: [{
			type: 'pie',
			events: {
				click: function(event) {
					$('html, body').stop().animate({
			            scrollTop: $($('#' + event.point.id)).offset().top
			        }, 750,'easeInOutExpo');
					window.location.hash = event.point.id;
				}
			},
			dataLabels: {
			    formatter: function () {
			        if(this.point.alerts) 
			            return '<span style="fill: red;font-weight:bold;">' + this.point.name + '</span>';
			        
			    	return '<b>' + this.point.name + '</b>';
			    }
			},
			data: chartData,
			size: '79%'
		}, {
			type: 'pie',
			events: {
				click: function(event) {
					$('html, body').stop().animate({
			            scrollTop: $($('#' + event.point.id)).offset().top
			        }, 750,'easeInOutExpo');
					window.location.hash = event.point.id;
				}
			},
			data: alertData,
			innerSize: '80%',
			dataLabels: {
				formatter: function() {
					return null;
				}
			}
		}]
	});	
}


/**
 * Draws a tag cloud for the given element.
 * 
 * @param $tagCloud
 */
function toTagCloud($tagCloud) {
	$tagCloud.attr('id', 'tag-cloud' + new Date().getTime());
	
	$tagCloud.tagcanvas({
		textFont: 'Impact,Arial Black,sans-serif',
		textColour: '#00f',
		textHeight: 25,
		outlineColour: '#f96',
		outlineThickness: 0,
		maxSpeed: 0.04,
		minBrightness: 0.1,
		depth: 0.92,
		initial: [0.1,-0.1],
		decel: 0.98,
		wheelZoom: false,
		reverse: true,
		shadow: '#ccf',
		shadowBlur: 3,
		weight: true,
		weightMode: 'both'
	});
	
}

/**
 * Displays the selected object, initiated by the following the data fields:
 *  [data-view-external-link="true"]   				- a flag that determines whether to treat this as a normal link
 *  [data-view-selected-all="true"]   				- a flag that determines whether to show all
 *  [data-view-selected-container-class="{class}"]  - the class of the elements to hide when an element is selected 
 *  [data-view-selected-target-id="{id}"] 			- the id of the element to show when selected
 *
 * @param $toViewSelected
 */
function toViewSelected($toViewSelected) {

	if($toViewSelected.hasClass('active'))
		return false;
		
	if($toViewSelected.data("viewExternalLink"))
	{
		this.window.location = $toViewSelected.attr("href");
		return true;
	}
	
	var all = $toViewSelected.data("viewSelectedAll");
	var containerClass = $toViewSelected.data("viewSelectedContainerClass");
	var targetId = $toViewSelected.data("viewSelectedTargetId");
	
	if(all)
	{
		$('.' + containerClass).slideDown();
	}
	else
	{
		// hide all containers except for the target id
		$('.' + containerClass).slideUp();
		$('#' + targetId).slideDown();
	}

	$('.to-view-selected').each(function() {
		$(this).removeClass('active');
	});

	$toViewSelected.addClass('active');
	
	// update the browsers url state
	var url = $toViewSelected.attr("href");
	history.replaceState({}, document.title, url);
		
	return false;
}

/**
 * Creates a slider out of the selected element, initiated by the following the data fields:
 *  [data-slider-input-target-id="{id}"]   - the id of the input to bind the value
 *  [data-slider-display-target-id="{id}"] - the id of the element to display the value
 *  [data-slider-disabled="true"]          - specifies whether the initial state of the slider is disabled
 *  [data-slider-min="{min}"]              - specifies the minimum value of the slider
 *  [data-slider-max="{max}"]              - specifies the maximum value of the slider
 *  [data-slider-step="{step}"]            - specifies the amount to step through the slider
 * 
 * @param $this
 */
function toSlider($this) {
	var $input = $('#' + $this.data('sliderInputTargetId'));
	var $display = $('#' + $this.data('sliderDisplayTargetId'));
	var disabled = $this.data('sliderDisabled') == true;
	var min = $this.data('sliderMin');
	var max = $this.data('sliderMax');
	var step = $this.data('sliderStep');
	
	$display.text($input.val());
	
	$this.slider({
		value:$input.val(),
		min: min,
		max: max,
		step: step,
		disabled: disabled,
		slide: function( event, ui ) {
			$input.val(ui.value );
			$display.text(ui.value);
		}
	});
	
	return false;
}

/**
 * Opens a birthdate picker for the selected element.
 * 
 * @param $this
 */
function toBirthdatePicker($this) {
	$this.removeClass("hasDatepicker")
	$this.datepicker("destroy");
	$this.datepicker({
		changeMonth: true,
		dateFormat: 'yy-mm-dd',
		changeYear: true,
		yearRange: "1900:" + new Date().getFullYear()
	});
	return false;
}

/**
 * Opens a date picker for the selected element.
 * 
 * @param $this
 */
function toDatePicker($this) {
	$this.removeClass("hasDatepicker")
	$this.datepicker("destroy");
	$this.datepicker({
		 dateFormat: 'yy-mm-dd' 
	});
	return false;
}

/**
 * Opens a time picker for the selected element.
 * 
 * @param $this
 */
function toTimePicker($this) {
	$this.removeClass("hasDatepicker")
	$this.timepicker("destroy");
	$this.timepicker({
	    stepMinute: 5,
	    separator: 'T',
	    pickerTimeFormat: 'hh:mm'
	});
	
	return false;
}

/**
 * Opens a date time picker for the selected element.
 * 
 * @param $this
 */
function toDateTimePicker($this) {
	$this.removeClass("hasDatepicker")
	$this.datetimepicker("destroy");
	$this.datetimepicker({
		dateFormat: 'yy-mm-dd',
		separator: 'T',
	    pickerTimeFormat: 'hh:mm'
	});
	return false;
}

/**
 * Provides sorting functionality for sortable lists configured with the folowing data fields: 
 *  [data-sortable-input-target-id={id}]    - the id of the input which receives the comma separated results
 *  [data-sortable-connected-class={class}] - the (optional) class to connect the sortable list to
 * 
 * @param $this
 */
function toSortable($this) {
	$this.sortable('destroy');
	
	var sortableConnectedClass = $this.data('sortableConnectedClass');
	
	// initialize any nested sortables
	var sortFunction = function(event, ui) { 
		var result = $this.sortable('toArray');
		var target = $this.data("sortableInputTargetId");
		var $target = $('#' + target);
		$target.attr('value', result);
	};

	$this.sortable({
		connectWith: sortableConnectedClass ? '.' + sortableConnectedClass : '',
		dropOnEmpty: true,
		receive: sortFunction,
		stop: sortFunction
	});

}

/**
 * Handles a toggle event.
 * 
 * The target to toggle is determined by the first matching target data field:
 *  [data-toggle-target-id="{id}"] 
 *  [data-toggle-target-class="{class}"] 
 *  [data-toggle-target-parent="true"]
 *  [data-toggle-target-next="true"] 
 *  
 * After the animation, the css class of the element may be toggled if the following data field is provided:
 *  [data-toggle-css="{css}"]  
 * 
 * A linked target id can toggled as associated element if the following data field is provided:
 *  [data-linked-target-id="{id}"]  
 * 
 * Finally, a form may be submitted to persist changes if one was found inside the link body.
 *  [data-toggle-prevent-form="true"] 
 *   
 * @param $this
 */
function toToggle($this) {
	var toggleTargetId = $this.data("toggleTargetId");
	var toggleTargetClass = $this.data("toggleTargetClass");
	var toggleTargetParent = $this.data("toggleTargetParent");
	var toggleTargetNext = $this.data("toggleTargetNext");
	var toggleLinkedTargetId  = $this.data("toggleLinkedTargetId");
	var successIds = $this.data('toggleFormSuccessIds');
	var preventForm = $this.data('togglePreventForm');
	var $form = $this.find("form");
	var $target;
	
	// determine the target
	if(toggleTargetId)
		$target = $('#' + toggleTargetId);
	else if(toggleTargetClass)
		$target = $('.' + toggleTargetClass);	
	else if(toggleTargetParent)
		$target = $this.parent();	
	else if(toggleTargetNext)
		$target = $this.next();
		
	// toggle the css class if one was provided
	if($this.data("toggleCss"))
	{
		var css = $this.attr('class');
		var cssToggle = $this.attr('data-toggle-css');
		$this.attr('data-toggle-css', css);
		$this.attr('class', cssToggle);
	}			
	
	// toggle the target
	if($target)
		$target.slideToggle('normal', function() {			
			if(toggleLinkedTargetId)
				$('#' + toggleLinkedTargetId).slideToggle('normal');						
		});
		
	// submit the json form if one was supplied
	var options = {
		successIds : successIds,
		onPageUpdate: onPageUpdate
	};
	
	if(!preventForm && $form.length > 0)
		ajaxSubmitForm($form, options);	
	
	return false;
}

/**
 * Handles a highlight event.
 * 
 * The target to highlight is determined by the first matching target data field:
 *  [data-highlight-target-id="{id}"] 
 *  
 * @param $this
 */
function toHighlight($this) {
	var toggleTargetId = $this.data("highlightTargetId");
	var $target = $('#' + toggleTargetId);
	
	if($target.css('background-color') == 'rgb(255, 255, 153)')
		$target.css('background-color', '#FFFFFF');
	else
		$target.css('background-color', '#ffff99');
		
	return false;
}

function htmlDecode(input){
  var e = document.createElement('div');
  e.innerHTML = input;
  return e.childNodes.length === 0 ? "" : e.childNodes[0].nodeValue;
}

/**
 * Allows the selected element to be modified in place via the following data fields:
 *  [data-editable-param-name]  - The name of the parameter to update
 * 
 * @param $this
 */
function toEditable($this) {
	
	var id = $this.data('editableId');
	var property = $this.data('editableProperty');
	var successIds = $this.data('editableSuccessIds');
	var selectOptions = $this.data('editableSelectOptions');
	var selectValue = $this.data('editableSelectValue');
	var index = property.indexOf('.');
	var object = property.substring(0, index);
	var param = property.substring(index + 1);
	var objectCapitalized = object.charAt(0).toUpperCase() + object.slice(1);
	
	// handle the submission
	var onSubmit = function(content) {
		var current = htmlDecode(content.current);
		
		if(!selectOptions && current == content.previous)
			return;
		
		var params = [
			{name: object, value: id}, 
			{name: property, value: current}, 
			{name: '_sourcePage', value: getTemplateData().sourcePage}, 
			{name: '_eventName', value: 'save' + objectCapitalized}];
			
	    var url = getTemplateData().formAction;
	    var data = $.param(params);   
	    
	    var $ajaxIndicator = $('<img/>', {
		     'class': 'busy-indicator-small',
		     'src': '/static/tracktacular/img/page/busy-indicator-small.gif',
		     'alt': 'Loading'
		});
	    
	   var options = {
			successIds: successIds,
			onPageUpdate: onPageUpdate,
			onBeforeAjax: function(data) {
		        $ajaxIndicator.appendTo($this);
		    },
			onAfterAjax: function(data) {
		       	$ajaxIndicator.remove();
		    },
			onFormInvalid: function() {
				$this.addClass('editable-error');
			},
			onFormSuccess: function() {
				$this.removeClass('editable-error');
			}
		};
	    
	    // submit the request
	    ajaxSubmit(url, data, options);
	}
	
	$this.editable({
		onSubmit: onSubmit,
		type: selectOptions ? 'select' : 'text',
		options: selectOptions,
		selectValue: selectValue
	});
	
}

/**
 * Displays a tooltip menu consisting of the element target with the data parameter:
 *  [data-menu-target-id]
 * 
 * @param $this
 */
function toMenu($this) {
	var targetId = $this.data('menuTargetId');
	var $target = $('#' + targetId);
	
	if(!$this.qtip('api'))
	{
		$this.qtip({
			id: targetId,
			overwrite: false, 
			content: {
				text: $target.html(), 
				title: {
					text: $target.data("menuTitle"),
					button: true
				}
			},
			position: {
				my: 'bottom center',
				at: 'top center'
			},
			show: {
				event: 'click',
				solo: true
			},
			hide: 'unfocus',
			style: {
				classes: 'ui-tooltip-shadow',
				width: 180,
				widget: true
			}
		});	

		$this.qtip('api').show();
	}
	
	return false;
}

/**
 * Handles the functionality of a form submission through a menu option.
 * 
 * @param $this
 */
function toMenuItemSubmitForm($this) {
	if($this.data('disabled') == true)
		return false;
	     
	var $tooltip = $this.closest('.ui-tooltip');
	var $ajaxIndicator = $(".menu-busy-indicator", $tooltip);
	var $form = $this.find('form');
	var successIds = $this.data('menuItemSubmitFormSuccessIds');
	var scrollToId = $this.data('menuItemSubmitFormScrollToId');
	
	var onBeforeAjax = function(data) {
		$this.data('disabled', true);
        $ajaxIndicator.show();
    };
    
    var onAfterAjax = function(data) {
        $this.data('disabled', false);
        $ajaxIndicator.hide();
    };
	
	var options = {
			onBeforeAjax: onBeforeAjax,	
			onAfterAjax: onAfterAjax,	
			successIds: successIds,
			onPageUpdate: onPageUpdate,
			onFormSuccess: function(data) {
				var tooltipApi = $tooltip.qtip('api');
				
				if(tooltipApi)
					tooltipApi.hide();
					
				if(scrollToId)
				{
					$('html, body').stop().animate({
			            scrollTop: $($('#' + scrollToId)).offset().top
			        }, 750, 'easeInOutExpo');
				}
						
			}
	};

	ajaxSubmitForm($form, options);		
	return false;
}

/**
 * Opens up a form dialog from a tooltip menu, hiding the menu after the dialog is opened.
 * 
 * @param $this
 * @param options
 */
function toMenuItemDialogForm($this) {
	$this.closest('.ui-tooltip').qtip('api').hide();	
	return toDialogForm($this);
}

/**
 * Opens up a form dialog with the data found in the following data fields:
 *  [data-form-dialog-target-id="{id}"] - the target element to display in the dialog
 * 
 * @param $this
 * @param options
 */
function toDialogForm($this) {	
	var template =  $this.data("dialogFormTemplate");
	var templateContextId = $this.data("dialogFormTemplateContextId");
		
	var options = {
		templateContextId: templateContextId,
		onPageUpdate: onPageUpdate
	};

	openAjaxDialogForm(template, options);
	return false;
}

/**
 * OpenAjaxDialogForm
 * 
 * Opens a dialog form with the given dialogFormContainerId, busyIndicatorId, and options.
 * 
 * The lifecycle of the dialog is tricky. When opening a dialog, jquery pulls the content out of the dom
 * and appends it to the body. Additionally, we are pulling the busy indicator out of the dom and appending it
 * to the dialog button pane. This method will return both elements to their previous state on close. 
 * 
 * Dialogs also have built in template functionality to avoid repeated identical forms with the following data fields:
 *  [data-dialog-template-id]    - the id of the form to use as a template
 *  [data-dialog-template-json]  - a json representation of the form data to be injected into the template
 *  [data-dialog-title]          - the default title to use if one was not supplied
 * 
 * @param dialogFormContainerId - The id of the dialog form container
 * @param options               - An options object with several features and callbacks:
 *     onPrepareDialog          - callback to prepare a dialog before opening
 *     onFormInvalid            - callback after a form is submitted with validation errors
 *     onFormSuccess            - callback after a form is submitted with validation errors
 */
function openAjaxDialogForm(template, options) {
	if(!options)
		options = {};
	
	// prepare the context for rendering
	var context = getTemplateData();
	
	if(options.templateContextId)
		context.contentBody.dialogFormData = context.references[options.templateContextId];
	else
		context.contentBody.dialogFormData = {};
		
	// rend the content of the form through the template and content
	dust.render(template, context, function(err, out) {
		if(err)
			console.log(err);
		
		// fade properties
		var fadeDuration = 200;
		
		// dialog form elements
		var $dialogForm = $(out);		
		var $dialogFormClose = $('a.dialog-form-close', $dialogForm);
		var $dialogFormSubmit = $('button.dialog-form-button', $dialogForm);
		var $dialogFormBusy = $('#dialog-form-busy', $dialogForm);
		
		// content elements
		var $main = $('#main');
		var $content = $('#content');
		var $contentEdit = $('#content-edit');
		var $contentView = $('#content-view');
		
		// form properties
		var successIds = $dialogForm.data('dialogFormSuccessIds');
		
		// append the form and register any junit handlers
		$contentEdit.empty();
		$contentEdit.append($dialogForm);
		onPageUpdate($dialogForm);
				
		// close form callback
		var onCloseForm = function() {
			$contentEdit.fadeOut(fadeDuration, function() {
				$main.css('background', 'url(/static/tracktacular/img/page/bg-main.gif) repeat-y -1px 0');
				$content.css('background-color', 'white');
				$contentEdit.empty();
				$contentView.fadeIn(fadeDuration);
			});
		};		
		
		// open form callback
		var onOpenForm = function() {
			$main.css('background', 'url(/static/tracktacular/img/page/bg-edit.png) repeat-y -1px 0');
			$content.css('background-color', 'transparent');
				$contentEdit.fadeIn(fadeDuration);
		};
				
		// bind the close event to restore the state of the page
		$dialogFormClose.bind('click', function() {
			onCloseForm();
			return false;
		});
		
		// bind the escape key to close the dialog
		$(document).bind("keyup", function(event){
		   if (event.keyCode == 27) {
		      onCloseForm();
		      $(document).unbind("keyup");
		   }
		});
		
		// bind the submit button to submit the form
		$dialogFormSubmit.bind('click', function() {
			$dialogForm.submit();
		});
						
		// before ajax callback
		var onBeforeAjax = function() {
			$dialogFormSubmit.button("disable");
			$dialogFormBusy.show();
		};

		// after ajax callback
		var onAfterAjax = function() {
			$dialogFormSubmit.button("enable");
			$dialogFormBusy.hide();
		};
			
		// invalid form submission callback
		var onFormInvalid = function(data) {
			$('#validation-errors').slideDown();
			
			var validationResultData = data;
			
			$.each(validationResultData.validationResult.ajaxValidationErrors, function(index, error) { 
				$.each(error.objectNames, function(index, objectName) { 
					
					// get the object name and escape periods or jquery cannot select them
					var objectNameId = objectName.replace(/\./g,'\\.');
					$("label[for='"+objectNameId+"']", $dialogForm).addClass('error');
					
					// get the error id
					var objectNameErrorId = objectNameId + '-error';
					$('#'+objectNameErrorId, $dialogForm).html(error.validationMessage).slideDown();
					
			    });
		    });
			     
		};  

		// valid for submission callback
		var onFormSuccess = function(data) { 
			onCloseForm();  
			
			if(options.onFormSuccess)
				options.onFormSuccess($dialogForm);
		};
				
		// register the submit handler of the form
		$dialogForm.submit(function() {
			
			// if a submission is in progress, return to prevent double submissions
			if($dialogFormSubmit.hasClass('ui-state-disabled'))
				return false;
			
			// clear previous validation errors
			$('#validation-errors', $dialogForm).slideUp('fast');
			
			$('label.error', $dialogForm).each(function() { 
				$(this).removeClass('error');
			});
			
			$('div.error', $dialogForm).each(function() { 
				$(this).slideUp('fast');
			});
			
			// submit the form                       
			ajaxSubmitForm($dialogForm, {
				successIds    : successIds,
				onPageUpdate  : onPageUpdate,
				onBeforeAjax  : onBeforeAjax,
				onAfterAjax   : onAfterAjax,
				onFormInvalid : onFormInvalid,                  
				onFormSuccess : onFormSuccess
			});
			
			return false;
		});
		
		// display the dialog form
		$contentView.fadeOut(fadeDuration, onOpenForm);
		
	});
	
}

/**
 * Submits a form via ajax with the given event name.
 * 
 * @param $form - The jquery form object to submit
 * @param options - An options object including several features and callbacks:
 *      $ajaxIndicator   - a jquery object to act as an indicator that will display during the ajax call
 *      onBeforeAjax     - callback before the ajax request starts
 *      onAfterAjax      - callback after the ajax request completes
 *      onFormInvalid    - callback after a form is submitted with validation errors
 *      onFormSuccess    - callback after a form is submitted successfully without validation errors
 *      onAjaxError      - callback after an error occurs during ajax processing
 *      onSessionExpired - callback after the server received a session expired notification
 */
function ajaxSubmitForm($form, options) {    
	toSubmitForm($form);
   
    var url = $form.attr("action");
    var data = $form.serialize();   
        
    // submit the request
    ajaxSubmit(url, data, options);
}

/**
 * Submits an ajax request.
 * 
 * @param url - The url to submit the request (REQUIRED)
 * @param data - The query string holding parameters to submit along with the request
 * @param ajaxInvalidIds - An array containing a list of html ids to be replaced for an invalid submissions
 * @param ajaxSuccessIds - An array containing a list of html ids to be replaced for successful submissions
 * @param options - An options object including several features and callbacks:
 *      $ajaxIndicator          - a jquery object to act as an indicator that will display during the ajax call
 *      onBeforeAjax            - callback before the ajax request starts
 *      onAfterAjax             - callback after the ajax request completes
 *      onPageUpdate(context)   - callback whenever a new element has been added to the dom via the ajax response
 *      onFormInvalid           - callback after a form is submitted with validation errors
 *      onFormSuccess           - callback after a form is submitted successfully without validation errors
 *      onAjaxError             - callback after an error occurs during ajax processing
 *      onSessionExpired        - callback after the server received a session expired notification
 */
function ajaxSubmit(url, data, options) {   
    if(!options)
        options = {};
    
    // ajax callbacks
    var onBeforeAjax = options.onBeforeAjax  || function(data) {
        if(options.$ajaxIndicator)
            options.$ajaxIndicator.show();
    };
    
    var onAfterAjax = options.onAfterAjax   || function(data) {
        if(options.$ajaxIndicator)
            options.$ajaxIndicator.hide();
    };
    
    var onAjaxError = options.onAjaxError || function() {
        alert("An error has occurred processing your request.");        
    };
    
    var onSessionExpired = options.onSessionExpired || function(data) {
        alert("Your session has expired! Click ok to reload the page.");
        window.location.reload(true);
    };
    
    // ajax call
    var xhr = $.ajax({
        type: 'POST',
        url: url,
        cache: false,
        data: data,
        dataType: "json",
  		context: this,
        beforeSend: onBeforeAjax,
        complete: onAfterAjax,
        error: onAjaxError,
        success: function (data) {  
        	
            // handle session expiration
            if (xhr.getResponseHeader('IMADP-NewSession')) {
                onSessionExpired(data);
            }       

            // handle form validation errors
            else if (xhr.getResponseHeader('IMADP-ValidationError')) {
            	
               	// form invalid callback
            	if(options.onFormInvalid)
                    options.onFormInvalid(data);
            }       

            // handle successful response
            else {
            	
            	var templateData = data;
            	
            	if(templateData)
            		setTemplateData(templateData);
            	
            	if(options.successIds)
            	{
            		var replaceContent = function($context) {
            			
	            		// replace the success ids on the page with those from the context in memory
	            		var ids = $.trim(options.successIds).split(',');
	            		
	             		$.each(ids, function(index, value) { 
	             			if(value && $.trim(value).length > 0)
	             			{
		            			var selector = '#' + $.trim(value);
		            			
		            			$(selector).replaceWith($(selector, $context));
		
		                        if(options.onPageUpdate)
		                            options.onPageUpdate($(selector));
                            }
	            		});
	            	};	   
	            	            		
	            	var $context = $('<div></div>');
	            	var contentMessagesTemplate = 'contentMessages';
					var contentBodyTemplate = getTemplateData().contentBodyTemplate;
					
					dust.render(contentMessagesTemplate, getTemplateData(), function(err, out) {
						if(err)
							console.log(err);
						
						var $tempContext = $('<div id="content-messages"></div>');
	        			$tempContext.append(out);
	        			$context.append($tempContext);
	        			
	        			if(contentBodyTemplate)
	        			{
	        				dust.render(contentBodyTemplate, getTemplateData(), function(err, out) {
								if(err)
									console.log(err);
		
								var $tempContext = $('<div id="content-body"></div>');
			        			$tempContext.append(out);
			        			$context.append($tempContext);
			        			
			        			replaceContent($context);
							});
	        			}
	        			else
	        			{
	        				replaceContent($context);
	        			}
					});
				            	
            	}           
            	
            	// form success callback
            	if(options.onFormSuccess)
                    options.onFormSuccess();
            }

        }
    });
}