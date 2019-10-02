/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'image',
		
		inherit : 'component',
		
		body : function(o) {	
		
			/*
			 * ----------------------------------------------------------------------
			 * 								 initialize
			 * ---------------------------------------------------------------------- 
			 */	
			
			this.tag = '<img>';
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 properties
			 * ---------------------------------------------------------------------- 
			 */
			
			this.properties({
				
				/*
				 * override
				 */
				
				__null__ : ['direction', 'tabindex', 'value'],
											
				/*
				 * new
				 */
				
				src : _.defaultProperty()			
				
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
			
				initialize : { 	
					advice : 'after',
					method : function() {
						var T = this.__;
						T.o.addClass('ui-corner-all');
					}			
				}
				
			});
			
		}
					
	});
	
})(jQuery, jQuery.ZOF);