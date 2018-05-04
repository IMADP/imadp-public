/**
 * Conditionally executes a template block.
 *
 */
dust.helpers.when = function(chunk, context, bodies, params){
	var cond = (params.test);

	if( params && params.test ){
		// resolve dust references in the expression
		if( typeof cond === "function" ){
			cond = '';
			chunk.tap( function( data ){
				cond += data;
				return '';
			} ).render( params.test, context ).untap();
			if( cond === '' ){
				cond = false;
			}
		}
		// eval expressions with no dust references
		if( eval( cond ) ){
			return chunk.render( bodies.block, context );
		}
		if( bodies['else'] ){
			return chunk.render( bodies['else'], context );
		}
	}
	// no condition
	else {
		if( window.console ){
			window.console.log( "No expression given!" );
		}
	}
	return chunk;
};

/**
 * Writes out a context value by a dynamic key.
 *
 */
dust.helpers.out = function(chunk, context, bodies, params){
	var key = params.key;
	return chunk.write(context.get(key));
};

/**
 * Writes out a property value for a given object.
 *
 */
dust.helpers.getProperty = function(chunk, context, bodies, params){
	var value = getPropertyValue(context.stack.head, params.object, params.suffix);
    return chunk.write(value === undefined ? '' : value);
};
 
/**
 * Writes out the selected attribute for options.
 *
 */
dust.helpers.selected = function(chunk, context, bodies, params){
	var objectValue = getPropertyValue(context.stack.tail.head, params.object);
	var optionValue = context.stack.head[params.optionKey];
	return chunk.write(objectValue == optionValue ? 'selected' : '');
};

/**
 * Writes out the checked attribute for radio/checkboxes.
 *
 */
dust.helpers.checked = function(chunk, context, bodies, params){
	return chunk.write(getPropertyValue(context.stack.head, params.object) + '' == params.value + '' ? 'checked="checked"' : ''); 
};

/**
 * Retrieves a property value from the given object by matching it exactly.
 *
 */
function getPropertyValue(object, property, suffix) {
	var keys = property.substring(property.indexOf('.') + 1).split('.');
	var value;
	
	for(i = 0; i < keys.length; i++)
	{
		if(!object)
			return;
		
		var key = keys[i];
		
		if(suffix && i == keys.length -1)
			key = key + suffix;
	
		value = object[key]
		object = value;
	}
	
	return value;
}