
/**
 * 
 * @param divParent
 * @param textContent
 * @return accordion element contain pather category
 */
function builCategoryAccordionItem(textContent , patherId){
	
	/** make category with accordion (this is the cause of much javascript for this function, for brand is more easy**/
	var panelDefault 	= document.createElement("div")
	var panelheading 	= document.createElement("div")
	var panelTitle 		= document.createElement("h4")
    var accordionItem 	= document.createElement("a")
    var span 			= document.createElement("span")
    var awesome 		= document.createElement("i")
    var strong 			= document.createElement("strong")
    
    panelDefault.setAttribute("class", "panel panel-default")
   	panelheading.setAttribute("data-child", patherId)
    panelheading.setAttribute("class", "panel-heading")   
    panelTitle.setAttribute("class", "panel-title")    
	accordionItem.setAttribute("data-toggle", "collapse")
	accordionItem.setAttribute("data-parent", "#accordian")
	accordionItem.setAttribute("href", "#" + patherId)	
	span.setAttribute("class", "badge pull-right")
	awesome.setAttribute("class", "fa fa-plus")		
	 
	$(span).html(awesome)
	$(accordionItem).html(span)
	$(strong).html(textContent)
	$(strong).appendTo(accordionItem)
	$(panelTitle).html(accordionItem)
	$(panelheading).html(panelTitle)
	$(panelDefault).html(panelheading)
	return panelDefault
}


/**
 * 
 * @param data response of rest call
 */
function buildAccordionPanelCategory(data) {

	var category = []

	if (data.responseObject.length > 0) {
		
		/** category from rest **/
		categories = data.responseObject
		categories.forEach(function(entry) {
			$('.category-products').append(builCategoryAccordionItem(entry.code,entry.id))	
		})
	}		
}

/**
 * 
 * @param data response of rest call
 */
function buildSubMenuCategory(data,patherId){	
	
	if (data.responseObject.length != 0) {	
	var panelCollapse 	= document.createElement("div")
	var panelBody 		= document.createElement("div")
	var list 			= document.createElement("ul")
	panelCollapse.setAttribute("class", "panel-collapse collapse")
	panelCollapse.setAttribute("data-role", "child-category")
	panelBody.setAttribute("class", "panel-body")
	
	/** category child from rest **/
	var subcategory = data.responseObject
	
	subcategory.forEach(function(entry) 
	{	
		panelCollapse.setAttribute("id", patherId)
		var anchor = document.createElement("a")
		var listEl = document.createElement( "li" )
		anchor.setAttribute("href", "#")	 
		anchor.innerHTML = entry.code	
		$(listEl).html(anchor)	 
		$(listEl).appendTo(list)
		$(panelBody).html(list)
		$(panelCollapse).html(panelBody)
		$(panelCollapse).insertAfter('[data-child="'+ patherId +'"]')		
	})	 	
	
		panelCollapse.setAttribute("aria-expanded", "true")
		panelCollapse.setAttribute("class", "panel-collapse collapse in")
	}	
}

function buildBrandMenu(data){		
	
	if (data.responseObject.length != 0) {	
		
	/** brand child from rest **/
	var brand = data.responseObject.content
	
	brand.forEach(function(entry) 
	{	
		var el  			= document.createElement("li")
		var anchor  		= document.createElement("a")
		var span  			= document.createElement("span")	   
		anchor.setAttribute("href", "#")		
		$(anchor).html('<span class="pull-right">' + '(' + 50 + ')' + '</span>' + entry.name)	
		$(el).html(anchor)
		$(el).appendTo('[data-role="brand"]')		
	})
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
function handlerLocation(){
	
	jQuery('a').bind("click",function(event) {
		event.preventDefault()
		var href = $(this).attr('href')
		if(href.indexOf("index")!=-1){
			window.location.href = '/frontend'
		}
		else
		if(href.indexOf(".html")!=-1)
			window.location.href = href
	})
}



