/**
 * function for manipulating DOM on fly
 */

/**
 * @param code name of category
 */
function buildNavSingleItem(code){
	var item = $("<div class='panel panel-default'><div class='panel-heading'>"
			+ "<h4 class='panel-title'><a class='navgen'  href='#'>"
			+ code + "</a>" + "</h4></div></div>")
	return item
}

/**
 * 
 * @param data response of rest call
 */
function retrieveAllCategory(data) {

	var category = []

	if (data.responseObject.content.length > 0) {
		categories = data.responseObject.content
		categories.forEach(function(entry) {
			buildNavSingleItem(entry.code).appendTo($('div.category-products'))
		})
	}
	
}

/**
 * 
 * @param data response of rest call
 */
function buildMenuCategoryLevelOne(data) {

	var category = []

	if (data.responseObject.length > 0) {
		categories = data.responseObject
		categories.forEach(function(entry) {
			buildNavSingleItem(entry.code).appendTo($('div.category-products'))
		})
	}
	
}

/**
 * 
 * @param data response of rest call
 */
function buildSubMenuCategory(data){
	
	if (data.responseObject.length != 0) {
	 var node = document.createElement("li");                 
	 var textnode = document.createTextNode("Water");         
	 node.appendChild(textnode); 	 
	 var node2 = document.createElement("a");;	 
	 var anchor = document.createElement("a");
	 anchor.setAttribute("href", "#");	 
	 $(anchor).wrap(node)	 
	 document.getElementById("sub-category").appendChild(node);	 
	}
}


/**
 * 
 * @param data response of rest call
 */
function error(){
	
}

/**
 * 
 * @param data response of rest call
 */
function complete(){
	
}



