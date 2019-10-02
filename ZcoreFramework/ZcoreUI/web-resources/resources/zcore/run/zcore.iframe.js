/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'iframe',
		
		inherit : 'component',
		
		body : function(o) {	
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 initialize
			 * ---------------------------------------------------------------------- 
			 */	
			
			this.tag = '<iframe>';			
			
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
						T.o.attr({
							frameborder : "0",
							marginwidth : "0",
							marginheight : "0"
						});					
						T.o.addClass('ui-corner-all');
					}			
				}
				
			});			
			
		}
						
	});	
	
})(jQuery, jQuery.ZOF);