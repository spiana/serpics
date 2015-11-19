/**
 * @main  js for build every menu and other from response object pass it by rest service
 * this are all callback necessary to create the layout "on fly" related the response of rest service
 */

/**
 * 
 * @param divParent
 * @param textContent
 * @return accordion element contain pather category
 */
function builCategoryAccordionItem(textContent, patherId) {

	/**
	 * make category with accordion (this is the cause of much javascript for
	 * this function, for brand is more easy
	 */
	var panelDefault 		= document.createElement("div")
	var panelheading 		= document.createElement("div")
	var panelTitle 			= document.createElement("h4")
	var accordionItem 		= document.createElement("a")
	var span 				= document.createElement("span")
	var awesome 			= document.createElement("i")
	var strong 				= document.createElement("strong")

	panelDefault.setAttribute("class", "panel panel-default")
	panelheading.setAttribute("data-child", patherId)
	panelheading.setAttribute("class", "panel-heading")
	panelTitle.setAttribute("class", "panel-title")
	accordionItem.setAttribute("data-toggle", "collapse")
	accordionItem.setAttribute("data-category", textContent)
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
 * @param data  response of rest call
 */
function buildAccordionPanelCategory(data) {

	var category = []

	if (data.responseObject.length > 0) {

		/** category from rest * */
		categories = data.responseObject
		categories.forEach(function(entry) {
			$('.category-products').append(
					builCategoryAccordionItem(entry.code, entry.id))
		})
	}
}

/** 
 * @param data  response of rest call
 */
function buildSubMenuCategory(data, patherId) {

	if (data.responseObject.length != 0) {
		var panelCollapse 	= document.createElement("div")
		var panelBody 		= document.createElement("div")
		var list = document.createElement("ul")
		panelCollapse.setAttribute("class", "panel-collapse collapse")
		panelCollapse.setAttribute("data-role", "child-category")
		panelBody.setAttribute("class", "panel-body")

		/** category child from rest * */
		var subcategory = data.responseObject

		subcategory.forEach(function(entry) {
			panelCollapse.setAttribute("id", patherId)
			var anchor = document.createElement("a")
			var listEl = document.createElement("li")
			anchor.setAttribute("href", "#")
			anchor.setAttribute("data-product-id", entry.id)
			anchor.innerHTML = entry.code
			$(listEl).html(anchor)
			$(listEl).appendTo(list)
			$(panelBody).html(list)
			$(panelCollapse).html(panelBody)
			$(panelCollapse).insertAfter('[data-child="' + patherId + '"]')
		})

		panelCollapse.setAttribute("aria-expanded", "true")
		panelCollapse.setAttribute("class", "panel-collapse collapse in")
	}
}
/**
 * 
 */
function createBreadCrumbsCategoryPage(){
	
	var category = sessionStorage.getItem("currentCategory")
	/** make lowercase a category word and capitalize the catregory**/
	category = category.toLowerCase().replace(/\b[a-z]/g, function(letter) {
    return letter.toUpperCase();
		});
	if($('li.categoryBreadcrumbs').length)
		$('li.categoryBreadcrumbs').text(category)
}

/**
 * 
 * @param data
 */
function buildBrandMenu(data) {

	if (data.responseObject.length != 0) {

		/** brand child from rest * */
		var brand = data.responseObject.content

		brand.forEach(function(entry) {
			var el = document.createElement("li")
			var anchor = document.createElement("a")
			anchor.setAttribute("href", "#")
			$(anchor).html(	'<span class="pull-right">' + '(' + 50 + ')' + '</span>'+ entry.name)
			$(el).html(anchor)
			$(el).appendTo('[data-role="brand"]')
		})
	}
}

/**
 * 
 * @param data
 */
function buildProductMenu(data) {
	
	if (data.responseObject.length != 0) {

		/** product menu from rest * */
		var product = data.responseObject.content

		product.forEach(function(entry) {
			
			var el 		= document.createElement("li")
			var anchor 	= document.createElement("a")		
			anchor.setAttribute("href", "#m")	
			anchor.setAttribute("data-product", entry.id)	
			$(anchor).html('<span class="pull-right"><i class="fa fa-external-link" data-product-id="'+entry.id+'"></i></span>'
							+ entry.code)
			$(el).html(anchor)
			$(el).appendTo('[data-role="pruduct"]')				
		})
	}
}

function makeProductDetailOnModal(data){
	
	
	 var product = data.responseObject

	var buyable 		= document.getElementById("buyableOnModal")
	var description 	= document.getElementById("productDescriptionOnModal")
	var price 			= document.getElementById("productPriceOnModal")
	var category	 	= document.getElementById("productBrandOnModal")
	var brand		 	= document.getElementById("productCategoryOnModal")
	var id 				= document.getElementById("productIdOnModal")
	
	var modalTitle 		= $('.modal-title')
	var modalbody 		= $('.modal-body')	
	
	$(id).text('ID: ' + product.id)
	$(price).html('€' + product.price.currentPrice)
	$(category).html('<strong>Category: </strong>' + product.categories[0].code)
	$(brand).html('<strong>Brand: </strong>' + product.brand.name)
	$(description).text(product.description)
	
	modalTitle.html(product.code)
	
	if (product.buyable) {
		$(buyable).html('<strong>Availability:</strong> In Stock')
	} else
		$(buyable).html('<strong>Availability:</strong> Out Stock')			
			
	
	$('#myModal').modal()
}


/**
 * 
 * @param data
 */
function buildProductDetail(data) {
	
	var product = data.responseObject

	var buyable 		= document.getElementById("buyable")
	var description 	= document.getElementById("productDescription")
	var price 			= document.getElementById("productPrice")
	var category	 	= document.getElementById("productBrand")
	var brand		 	= document.getElementById("productCategory")
	var id 				= document.getElementById("productId")
	
	var modalTitle 		= $('.modal-title')
	var modalbody 		= $('.modal-body')	
	
	$(id).text('ID: ' + product.id)
	$(price).html('€' + product.price.currentPrice)
	$(category).html('<strong>Category: </strong>' + product.categories[0].code)
	$(brand).html('<strong>Brand: </strong>' + product.brand.name)
	$(description).text(product.description)
	
	modalTitle.html(product.code)
	
	if (product.buyable) {
		$(buyable).html('<strong>Availability:</strong> In Stock')
	} else
		$(buyable).html('<strong>Availability:</strong> Out Stock')					
			
	
}

/**
 * 
 * @param data  response of rest call
 */
function error() {

}

function handlerImageSlideOnModal(){
	
	$('[data-role=image] img').bind('click',function(event){
		event.preventDefault()
		var selectedItem = $(this).attr('src')
	    var currentItem  = $('body').find('[data-current-image]')
	    /** switch image **/
	    currentItem.attr({'src':selectedItem})
	    currentItem.attr({'data-current-image':selectedItem})
	    console.log('image change.....new image: ' + selectedItem)
	})
}


/**
 *  
 * @param data  response of rest call
 */
function handlerLocation() {

	jQuery('a[href$=".html"]').bind("click", function(event) {
		event.preventDefault()
		var href = $(this).attr('href')
		if (href.indexOf("index") != -1) {
			window.location.href = '/frontend'
		} else if (href.indexOf(".html") != -1)
			window.location.href = href
			else
				console.log('')
	})
}
