/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'textarea',
		
		inherit : 'input',
		
		body : function(o) {	
		
			/*
			 * ----------------------------------------------------------------------
			 * 								 initialize
			 * ---------------------------------------------------------------------- 
			 */
			
			this.tag = '<textarea>';
		
		}
		
	});
	
})(jQuery, jQuery.ZOF);