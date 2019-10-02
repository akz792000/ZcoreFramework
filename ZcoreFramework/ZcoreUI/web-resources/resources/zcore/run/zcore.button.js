/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'button',
		
		inherit : 'component',
		
		body : function(o) {		
		
			/*
			 * ----------------------------------------------------------------------
			 * 								 initialize
			 * ---------------------------------------------------------------------- 
			 */
			
			this.tag = '<button>';
						
			/*
			 * ----------------------------------------------------------------------
			 * 								 properties
			 * ---------------------------------------------------------------------- 
			 */
			
			this.properties({
				
				/*
				 * override
				 */
				
				__null__ : ['direction'],
								
				disabled : {
					after : function(v) {
						this.__.focused.button("option", "disabled", v);
					}
				},
						
				value : {
					get : function() {
						return this.__.focused.button('option', 'label'); 
					},
					set : function(v) {
						this.__.focused.button('option', {
							label : v,
							text : v !== ''
						});
					}	
				},					
				
				title : {
					/*
					 * bug : title not set when component has not value
					 * debug : change refresh ( render -> finalize )
					 */
					refresh : 'finalize',
				},
				
				/*
				 * new
				 */
				
				primary : {
					get : function() {
						return this.__.focused.button('option', 'icons')[this.name]; 
					},
					set : function(v) {
						var options = this.__.focused.button('option');
						options.icons[this.name] = v;
						this.__.focused.button('option', options);
					}					
				},
				
				secondary : {
					get : function() {
						return this.__.focused.button('option', 'icons')[this.name]; 
					},
					set : function(v) {
						var options = this.__.focused.button('option');
						options.icons[this.name] = v;
						this.__.focused.button('option', options);
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
				
				render : { 		
					method : function() {
						this.__.focused.button();	
					}
				},
				
				/*
				 * new
				 */
				
				click : {
					event : function(v) {
						var T = this.__;
						if ($.isFunction(v)) {	
							T.focused.bind('click.zcore', function(e) {
								if (!$(this).is(':disabled')) {
									v.apply(T, arguments);
								}
							});					
						}
					},
					method : function(v) {
						this.__.focused.trigger('click.zcore');
					}
				}
						
			});		
			
		}
									
	});
	
})(jQuery, jQuery.ZOF);