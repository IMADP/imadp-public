/**
 * Capitalizes a value.
 *
 * @param value
 */
dust.filters.capitalize = function(value){
	return value.replace(/(?:^|\s)\S/g, function(a) { return a.toUpperCase(); });
};

/**
 * Converts a value to json.
 *
 * @param value
 */
dust.filters.json = function(value) { 
	return dust.escapeHtml(JSON.stringify(value)); 
};