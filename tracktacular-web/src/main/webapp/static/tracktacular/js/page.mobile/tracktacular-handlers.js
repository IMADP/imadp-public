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

/**
 * Handles a link click by rendering the body through ajax.
 *   
 * @param $this
 */
function toLink($this) {
	var url = $this.attr('href');
	
	var external = false;
	
	$this.parents().each(function() { 
		if($(this).data('external'))
			external = true;
	});
	
	if(url != '#' && !($this.data('external') || external))
	{
	
		// if the link destination requires a script, load it now as an optimization
		var trackerTitle = $this.data('trackerTitle');
		var trackerScriptUrl = $this.data('trackerScriptUrl');
		
		if(trackerTitle)
			$('#title').html(trackerTitle);
		
		if(trackerScriptUrl)
			$.ajax({
			    dataType: "script",
			    cache: true,
			    url: trackerScriptUrl
			  });
			  
		// append mobile parameter
		if(url.indexOf("mobile=true") == -1)
			if(url.indexOf("?") == -1)
				url = url + '?mobile=true';
			else
				url = url + '&mobile=true';
		 
		var $contentEdit = $('#content-edit');
		var $contentView = $('#content-view');
		var $contentLoad = $('#content-load');
		var $content = $contentEdit.css('display') !== 'none' ? $contentEdit : $contentView;
		
		// fade out the current content and display the loading content
		$content.fadeOut(100, function() {
			$contentLoad.fadeIn(100);
		});
		
		// execute the ajax link
		$.getJSON(url, function(data) {
		
			// update the template data
			setTemplateData(data);
			
			// render content messages
			dust.render('contentMessages', getTemplateData(), function(err, out) {
				if(err)
					console.log(err);
		
				$('#content-messages').html(out);
			});
		
			// render content body
			dust.render(getTemplateData().contentBodyTemplate, getTemplateData(), function(err, out) {
				if(err)
					console.log(err);
					
				$('#content-body').html(out);
				
				$contentLoad.fadeOut(100, function() {
					$contentView.fadeIn();
				});
				
			});
		});
		
		window.location.hash = "tracker";
		return false;
	}
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
		var css = $('span', $this).attr('class');
		var cssToggle = $this.attr('data-toggle-css');
		$this.attr('data-toggle-css', css);
		$('span', $this).attr('class', cssToggle);
	}			
	
	// toggle the target
	if($target)
		$target.toggle(0, function() {			
			if(toggleLinkedTargetId)
				$('#' + toggleLinkedTargetId).toggle(0);						
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
 * Displays the home menu.
 * 
 * @param $this
 */
function toHome($this) {

	// set the title
	$('#title').html("Tracktacular"); 
	
	// content elements
	var $contentEdit = $('#content-edit');
	var $contentView = $('#content-view');
	var $contentMessages = $('#content-messages');
	var $contentBody = $('#content-body');
	
	if($contentEdit.css('display') !== 'none')
	{	
		$contentMessages.empty();
		$contentBody.html($indexPageContent);					
		$contentEdit.fadeOut(100, function() {
			$contentEdit.empty();
			$contentView.fadeIn(100); 
		});
	}
	else
	{
		if($contentView.css('display') == 'none')
		{
			$contentMessages.empty();
			$contentBody.html($indexPageContent);	
			$contentView.fadeIn(100);
		}
		else
			$contentView.fadeOut(100, function() {
				$contentMessages.empty();
				$contentBody.html($indexPageContent);	
				$contentView.fadeIn(100);
			});
	}
	
	return false;
}
		
/**
 * Displays a menu consisting of the element target with the data parameter:
 *  [data-menu-form-template]
 *  [data-menu-form-template-context-id]
 * 
 * @param $this
 */
function toMenu($this) {

	var template =  $this.data("menuFormTemplate");
	var templateContextId = $this.data("menuFormContextId");
	
	// prepare the context for rendering
	var context = getTemplateData();
	
	if(templateContextId)
		context.contentBody.menuFormData = context.references[templateContextId];
	else
		context.contentBody.menuFormData = {};
	
	// rend the content of the menu through the template and context
	dust.render(template, context, function(err, out) {
		if(err)
			console.log(err);
		
		// menu form elements
		var $menuForm = $(out);		
		var $menuFormClose = $('a.menu-form-close', $menuForm);
		
		// content elements
		var $contentEdit = $('#content-edit');
		var $contentView = $('#content-view');
				
		// append the menu and register any junit handlers
		$contentEdit.empty();
		$contentEdit.append($menuForm);
				
		// close form callback
		var onCloseForm = function() {
			$contentEdit.fadeOut(100, function() {
				$contentEdit.empty();
				$contentView.fadeIn(100);
			});
		};		
		
		// bind the close event to restore the state of the page
		$menuFormClose.bind('click', function() {
			onCloseForm();
			return false;
		});
		
		// bind the escape key to close the menu
		$(document).bind("keyup", function(event){
		   if (event.keyCode == 27) {
		      onCloseForm();
		      $(document).unbind("keyup");
		   }
		});			
				
		// display the menu form
		if($contentView.css('display') != 'none')
			$contentView.fadeOut(100, function() {
				$contentEdit.fadeIn(100);
			});
		else
			$contentEdit.fadeIn(100);
	});
	
	return false;
}

/**
 * Handles the functionality of a form submission through a menu option.
 *  [data-menu-item-submit-form-confirm]
 * 
 * @param $this
 */
function toMenuItemSubmitForm($this) {
	
	if($this.data('disabled') == true)
		return false;
	
	if($this.data("menuItemSubmitFormConfirm") && !confirm("Are you sure?"))
		return false;
	
    // content elements
	var $contentEdit = $('#content-edit');
	var $contentView = $('#content-view');
		 
	var $ajaxIndicator = $this.find('.menu-busy-indicator');
	var $form = $this.find('form');
	var successIds = $this.data('menuItemSubmitFormSuccessIds');
	var scrollToId = $this.data('menuItemSubmitFormScrollToId');
	
	var onBeforeAjax = function(data) {
		$this.addClass('ui-disabled');
		$this.data('disabled', true);
        $ajaxIndicator.show(0);
    };
    
    var onAfterAjax = function(data) {
        
    };
	
	var options = {
			onBeforeAjax: onBeforeAjax,	
			onAfterAjax: onAfterAjax,	
			successIds: successIds,
			onPageUpdate: onPageUpdate,
			onFormSuccess: function(data) {	
				$contentEdit.fadeOut(100, function() {
					$contentEdit.empty();
					$contentView.fadeIn(100);
				});
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
			$contentEdit.fadeOut(100, function() {
				$contentEdit.empty();
				$contentView.fadeIn(100);
			});
		};		
		
		// open form callback
		var onOpenForm = function() {
			$contentEdit.fadeIn(100);
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
			$dialogFormSubmit.attr('disabled', 'disabled').addClass('ui-disabled');
			$dialogFormBusy.show(0);
		};

		// after ajax callback
		var onAfterAjax = function() {
			$dialogFormSubmit.removeAttr('disabled').removeClass('ui-disabled');
			$dialogFormBusy.hide(0);
		};
			
		// invalid form submission callback
		var onFormInvalid = function(data) {
			$('#validation-errors').show(0);
			
			var validationResultData = data;
			
			$.each(validationResultData.validationResult.ajaxValidationErrors, function(index, error) { 
				$.each(error.objectNames, function(index, objectName) { 
					
					// get the object name and escape periods or jquery cannot select them
					var objectNameId = objectName.replace(/\./g,'\\.');
					$("label[for='"+objectNameId+"']", $dialogForm).addClass('error');
					
					// get the error id
					var objectNameErrorId = objectNameId + '-error';
					$('#'+objectNameErrorId, $dialogForm).html(error.validationMessage).show(0);
					
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
			if($dialogFormSubmit.hasClass('ui-disabled'))
				return false;
			
			// clear previous validation errors
			$('#validation-errors', $dialogForm).hide(0);
			
			$('label.error', $dialogForm).each(function() { 
				$(this).removeClass('error');
			});
			
			$('div.error', $dialogForm).each(function() { 
				$(this).hide(0);
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
		if($contentView.css('display') == 'none')
			onOpenForm();
		else
			$contentView.fadeOut(100, onOpenForm);
		
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
            options.$ajaxIndicator.show(0);
    };
    
    var onAfterAjax = options.onAfterAjax   || function(data) {
        if(options.$ajaxIndicator)
            options.$ajaxIndicator.hide(0);
    };
    
    var onAjaxError = options.onAjaxError || function() {
        alert("An error has occurred processing your request.");        
    };
    
    var onSessionExpired = options.onSessionExpired || function(data) {
        alert("Your session has expired! Click ok to reload the page.");
        window.location.reload(true);
    };
    
    // append mobile parameter
	if(url.indexOf("mobile=true") == -1)
		if(url.indexOf("?") == -1)
			url = url + '?mobile=true';
		else
			url = url + '&mobile=true';
	 
    // ajax call
    var xhr = $.ajax({
        type: 'POST',
        url: url,
        data: data,
        dataType: "json",
  		cache: false,
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