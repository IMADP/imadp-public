// addThis config
addthis_config = {
	ui_click : true   
}

// disable ajax cache
$.ajaxSetup ({
    cache: false
});

/**
 * JQuery's ready function.
 * 
 * Adds a heartbeat to keep session alive, prepares the page for client side templating, and registers all
 * jquery bindings as well as the associated tracker for the page.
 * 
 */
$(function(){
	var startTime = new Date().getTime();
	
	// global variable holding the associated tracker
	tracktacular.tracker = $('html').data('tracker');
	
	// set the templateData
	setTemplateData($('#templateData').data('templateData'));
		
	// retrieve a content body template to determine if we are rending the body
	var contentBodyTemplate = getTemplateData().contentBodyTemplate;
		
	if(contentBodyTemplate)
	{
		// make an ajax call to retrieve the full template data
		$.getJSON(getTemplateData().formAction, function(data) {
		
			// merge the contentBody section with the template data
			var templateData = data;
			var references = {};
		    buildReferences(references, templateData);
		    getTemplateData().references = references;
			getTemplateData().contentBody = templateData.contentBody;
					
			dust.render(contentBodyTemplate, getTemplateData(), function(err, out) {
				if(err)
					console.log(err);
	
				// render content messages
				dust.render('contentMessages', getTemplateData(), function(err, out) {
					if(err)
						console.log(err);
			
					$('#content-messages').html(out);
				});
			
				$('#content-body').html(out);
				
				onPageLoad(); 
				onPageUpdate();
			});
		});
		
	}
	else
	{		
		// render templates
		var contentMessagesTemplate = 'contentMessages';
		
		dust.render(contentMessagesTemplate, getTemplateData(), function(err, out) {
			if(err)
				console.log(err);
	
			$('#content-messages').html(out);
		});
		
		onPageLoad(); 
		onPageUpdate();
	}	

});


/**
 * Gets the current template data.
 *
 */
function getTemplateData() {
	return tracktacular.templateData;
}

/**
 * Sets the current template data.
 *
 * @param templateData
 */
function setTemplateData(templateData) {
	var references = {};
    buildReferences(references, templateData);
    templateData.references = references;
    tracktacular.templateData = templateData;
}

/**
 * Builds a reference map out of any objects with an id. 
 * This can be used to lookup contexts for dialogs and other templates who need a specific context. 
 * 
 * @param references
 * @param data
 */
function buildReferences(references, data) {

	// if an id exists add it to the references object
	if(data.hasOwnProperty("id"))
		references[data.id] = data;
	
	// recurse through any values the object has
	for (var key in data)
	{
        if (data.hasOwnProperty(key))
        {
        	var value = data[key]; 
        	
        	// if an array is found, loop through each object and build the references for that object
        	if($.isArray(value))
                $.each(value, function(index, item) { 
                    if(typeof item == "object")
                        buildReferences(references, item);
                });
           
        	// if an object is found, build the references for that object
        	else if(typeof value == "object")
       			buildReferences(references, value);
        }
	}
	
}

/**
 * Function executed one time on page load after the dom is ready.
 * 
 */
var onPageLoad = function() {

	// global event handlers
	$("body").delegate("form", "submit", function() {
		return toSubmitForm($(this));
	}); 
	
	$("body").delegate("#payment-form", "submit", function() {
		return toSubmitPayment($(this));
	}); 

	$("body").delegate(".to-toggle", "click", function() {
		return toToggle($(this));
	}); 

	$("body").delegate(".to-view-selected", "click", function() {
		return toViewSelected($(this));
	}); 

	$("body").delegate(".to-highlight", "click", function() {
		return toHighlight($(this));
	}); 

	$("body").delegate("a.to-menu", "click", function() {			
		return toMenu($(this));
	});		

	$("body").delegate("a.to-menu-item-submit-form", "click", function() {
		return toMenuItemSubmitForm($(this));
	}); 	
	
	$("body").delegate("a.to-menu-item-dialog-form", "click", function() {
		return toMenuItemDialogForm($(this));
	}); 
	
	$('body').delegate('select.to-select-url', 'change', function() {
	    return toSelectUrl($(this));
  	});
	
	$('body').delegate('select.to-scroll', 'change', function() {
	    return toScroll($(this));
  	});
	
	$("body").delegate("a.to-dialog-form", "click", function() {
		return toDialogForm($(this));
	});
	
	// invoke page load for the associated tracker
	if(tracktacular.tracker && tracktacular.trackers[tracktacular.tracker].js.onPageLoad)
		window[tracktacular.trackers[tracktacular.tracker].js.onPageLoad()];	
}
 
/**
 * Function executed every time the page is updated, including the initial page load.
 * 
 * @param $context - The context, which may be null, containing the updated page elements.
 */
var onPageUpdate = function($context) {

	$('.dialog-form-button', $context).each(function() { 
		$(this).button();
  	});

	// load handlers
	$('.editable', $context).each(function() { 
		toEditable($(this));
	}); 
	
	$('a.to-short-url', $context).each(function() { 
		toShortUrl($(this));
	}); 
	
	$('input.date-time-picker', $context).each(function() { 
		toDateTimePicker($(this));
	}); 
	
	$('input.date-picker', $context).each(function() { 
		toDatePicker($(this));
	}); 
	
	$('input.time-picker', $context).each(function() { 
		toTimePicker($(this));
	}); 
	
	$('input.birthdate-picker', $context).each(function() { 
		toBirthdatePicker($(this));
	}); 
	
	$('ul.sortable', $context).each(function() { 
		toSortable($(this));
	});	
	
	$('div.slider', $context).each(function() { 
		toSlider($(this));
	}); 
	
	$('.to-tag-cloud', $context).each(function() {
		toTagCloud($(this));		
	});
	
	$('#tracktacular-report', $context).each(function() {
		toTracktacularReport($(this));	
	});
	
	// invoke page update for the associated tracker
	if(tracktacular.tracker && tracktacular.trackers[tracktacular.tracker].js.onPageUpdate)
		window[tracktacular.trackers[tracktacular.tracker].js.onPageUpdate($context)];	
}