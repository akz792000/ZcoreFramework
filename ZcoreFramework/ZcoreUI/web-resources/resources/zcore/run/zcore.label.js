/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'label',
		
		inherit : 'component',
		
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
						return this.__.temp.label.text(); 
					},
					set : function(v) {
						this.__.temp.label.text(v);
					}					
				},
			
				/*
				 * new
				 */
			
				mandatory : $.extend(_.defaultProperty('boolean'), {
					after : function() {
						var T = this.__;
						T.mandatory() ? T.temp.icon.html('*') : T.temp.icon.html('');
					}
				})
				
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
						$.extend(T.temp, {
							icon : $('<div>', {
									'class' : 'ui-state-error',
									html : ''
								}).appendTo(T.o),
							label : $('<label>').appendTo(T.o)
						});						
					}		
				}
			
			});			
			
		}
						
	});	
	
})(jQuery, jQuery.ZOF);