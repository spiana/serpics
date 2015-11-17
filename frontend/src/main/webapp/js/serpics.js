/**
 * function from rest service
 */

jQuery(document).ready(function() {

	/** restClient instance **/
	var rest = new RestClient()
	
	/** init * */
	jQuery(window).load(function() {
		jQuery(".loading-container").fadeOut(1000);
		jQuery(".loading,loading-text").delay(100).fadeOut("slow");
		handlerLocation();
	})
	
	/** by delegate events **/
	jQuery(document).on("click", "[data-child]",function() {
		
		  patherId = $(this).data("child");	
		  /** make category sub menu fromm rest **/
		  if(!document.getElementById(patherId)){
			rest.executeGetChildCategory('categoryService/',"getChild/" + patherId,buildSubMenuCategory,error,patherId)			
		}
	});
	
})
 