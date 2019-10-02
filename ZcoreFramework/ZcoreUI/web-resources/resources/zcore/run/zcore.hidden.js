/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'hidden',
		
		inherit : 'input',
		
		body : function(o) {
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 properties
			 * ---------------------------------------------------------------------- 
			 */		
							
			this.properties({
				
				/*
				 * override
				 */
				
				value : {
					type : 'object',
					get : function() {
						return this.value;						
					},
					set : function(v) {
						this.value = v;
					}					
				}				
			
			});
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 methods
			 * ---------------------------------------------------------------------- 
			 */	
				
			this.methods({

				/*
				 * override
				 */
				
				clean : {
					method : function() {
						this.__.value(null);
					}
				}				
				
			});
			
		}
		
	});	
	
})(jQuery, jQuery.ZOF);