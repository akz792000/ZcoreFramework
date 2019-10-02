/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'accordion',
		
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
				
				__null__ : ['tabindex', 'value'],						
				
				disabled : {
					refresh : 'finalize',
					after : function(v) {
						var T = this.__;
						_.disabledChild(T, v);	
						T.canister.accordion({ disabled: v });		
					}
				},				
				
				direction : {		
					after : function(v) {
						var 
							T = this.__,
							orientation = T.orientation(v);
						T.canister
							.accordion('option', "icons", {
								header : "ui-icon-circle-arrow-" + orientation[2].substr(0, 1),
								activeHeader : "ui-icon-circle-arrow-s"
							});
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
				
				initialize : {
					advice : 'after',
					method : function() {
						var T = this.__;
						T.canister = $('<div>').appendTo(T.o);
					}		
				},
				
				refresh : { 				
					method : function() {
						this.__.canister.accordion('refresh');
					}			
				},				
			
				render : {
					advice : 'before',
					method : function() {
						this.__.canister.accordion({							
							heightStyle : "fill",
							collapsible : true,
							/*
							 * if you want to draw it's children component the 
							 * speed if animate must be zero 
							 */
							animate: 0 
						});
					}			
				},
				
				embed : {
					method : function() {				
						return this.__.canister;
					}
				},
				
				active : {
					method : function(v) {
						this.__.canister.accordion("option", 'active', v);
					}
				}
			
			});
			
		}
		
	});
	
})(jQuery, jQuery.ZOF);