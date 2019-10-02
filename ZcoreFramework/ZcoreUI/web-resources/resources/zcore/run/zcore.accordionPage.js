/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'accordionPage',
		
		inherit : 'component',
		
		friend : 'table',
			
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
				
				__null__ : ['tabindex'],		
				
				value : {
					get : function() {
						return this.__.temp.header.text();
					},
					set : function(v) {
						this.__.temp.header.text(v);
					}	
				},		
				
				disabled : {
					refresh : 'finalize',
					after : function(v) {
						_.disabledChild(this.__, v);
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
				
				beforeModelize : {
					method : function() {						
						this.__.active();
					}
				},					
								
				initialize : {
					advice : 'after',
					method : function() {
						var T = this.__;
						T.container = T.parent.canister;						
					}		
				},
				
				render : {
					advice : 'after',
					method : function() {
						var T = this.__;	
						T.temp.header = $('<h3>', {
							number : T.container.find('h3').length
						}).insertBefore(T.o);
					}			
				},			
				
				finalize : {	
					advice : 'before',
					method : function() {
						this.__.parent.refresh();						
					}
				},
				
				/*
				 * new
				 */
				
				active : {
					method : function() {
						var T = this.__;
						T.container.accordion("option", 'active', parseInt(T.temp.header.attr('number')));
					}
				}
			
			});
			
		}
		
	});
	
})(jQuery, jQuery.ZOF);