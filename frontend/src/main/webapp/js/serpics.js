/**
 * @main  js for serpics template
 * @use   var 'rest' object from global scope for call rest service server side
 * @example rest.functionName(params) not instantiate new rest object such var rest = new RestClient()
 */

jQuery(document).ready(function() {


	/** call rest on startup **/
	rest.executeGetCategory('categoryService/top','/',buildAccordionPanelCategory,error)
	rest.executeGetBrand('brandService','/',buildBrandMenu,error)
	
	
	/** init * */
	jQuery(window).load(function() {
		jQuery(".loading-container").fadeOut(3000);
		jQuery(".loading,loading-text").delay(100).fadeOut("slow");
		handlerLocation();
	})
	
	/** by delegate events **/
	jQuery(document).on("click", "[data-child]",function() {		
		  patherId = $(this).data("child");	
		  /** make category sub menu from rest **/
		  if(!document.getElementById(patherId)){
			  rest.executeGetChildCategory('categoryService/',"getChild/" + patherId,buildSubMenuCategory,error,patherId)			
		}
	});
	
	/** by delegate events ry pageset category in sesion storage to retrive in it's catego**/
	jQuery(document).on("click", "[data-category]",function() {		
		  categoryName = $(this).data("category");
		if(rest.supportsHTML5Storage())
		  rest.setSessionStorageProperty("currentCategory",categoryName)
		  else
			  rest.setPropertyInToCookie("currentCategory", categoryName, 30)
	});	
	
	createBreadCrumbsCategoryPage()
})
 