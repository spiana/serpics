/**
 * function for manipulating DOM on fly
 */

function buildNavSingleItem(code){		
var item = $("<div class='panel panel-default'><div class='panel-heading'>" +
			 "<h4 class='panel-title'><a class='navgen'  href='#'>" + code + "</a>" +
			 "</h4></div></div>")
return item
}