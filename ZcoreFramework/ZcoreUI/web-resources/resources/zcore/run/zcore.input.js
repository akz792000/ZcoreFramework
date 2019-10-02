/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'input',
		
		inherit : 'postable',
		
		body : function(o) {
		
			/*
			 * ----------------------------------------------------------------------
			 * 								 initialize
			 * ---------------------------------------------------------------------- 
			 */
			
			this.tag = '<input>',		
			
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
					get : function() {
						return this.__.focused.val();						
					},
					set : function(v) {
						this.__.focused.val(v);
					}					
				},
				
				/*
				 * new
				 */		
			
				maxlength : {
					type : 'int',
					defVal : -1,
					get : function() {	
						var T = this.__;
						return T.focused.attr(this.name) !== undefined ? T.focused.attr(this.name) : this.defVal; 
					},
					set : function(v) {	
						var T = this.__;
						if (v === this.defVal) {
							T.focused.removeAttr(this.name);
						} else {
							v = (v < T.value().length) ? T.value().length : v;
							T.focused.attr(this.name, v); 
						}
					}					
				}				
			
			});	
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 methods
			 * ---------------------------------------------------------------------- 
			 */
			
			this.methods({
				
				finalize : {
					advice : 'after',
					method : function() {
						//--> (zui-text-sizing) for proper width & height it should be set in finalize method
						this.__.focused.addClass('ui-widget-content zui-text-content ui-corner-all zui-text-sizing');
					}					
				}				
				
			});		
			
		}
	
	});
	
})(jQuery, jQuery.ZOF);