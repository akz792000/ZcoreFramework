/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'text',
		
		inherit : 'input',
		
		friend : 'mask',
		
		body : function(o) {	
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 methods
			 * ---------------------------------------------------------------------- 
			 */
			
			this.methods({
				
				/*
				 * override
				 */			
			
				initialize : { 	
					advice : 'after',
					method : function() {
						var T = this.__;
						T.temp.mask.o = T.o;
					}			
				}				
				
			});		
			
		}
	
	});
	
})(jQuery, jQuery.ZOF);