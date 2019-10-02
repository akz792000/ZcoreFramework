/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
			
	_.clazz({
		
		name : 'textbtn',
		
		inherit : 'text',
		
		body : function(o) {	
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 initialize
			 * ---------------------------------------------------------------------- 
			 */
			
			this.tag = '<div>',					
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 properties
			 * ---------------------------------------------------------------------- 
			 */		
			
			this.properties({
				
				/*
				 * override
				 */									
				
				disabled : {
					after : function(v) {
						var temp = this.__.temp;						
						$.each(['val', 'btn'], function(i, j) {
							if (v) {
								temp[j].attr('disabled', 'disabled').addClass('ui-state-disabled');
							} else {
								temp[j].removeAttr('disabled').removeClass('ui-state-disabled');	
							}
						});
						temp.btn.button("option", "disabled", v);						
					}
				},	
				
				/*
				 * new
				 */				
				
				icon : {
					get : function() {
						return this.__.temp.btn.button('option', 'icons')['primary']; 
					},
					set : function(v) {
						var 
							T = this.__,
							options = T.temp.btn.button('option');
						options.icons['primary'] = v;
						T.temp.btn.button('option', options);
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
						$.extend(T.temp, {
							//--> val
							val : $('<input>').appendTo(T.o),
							//--> btn
							btn : $('<button>', {
								tabindex : -1,
								'class' : 'zui-center-v'
							}).appendTo(T.o)							
						});
						//--> set mask
						T.temp.mask.o = T.temp.val;
						//--> set focused
						T.focused = T.temp.val;
					}					
				},
				
				render : {
					method : function() {
						var T = this.__;						
						//--> btn 
						T.temp.btn 
								.button()
								.find('.ui-button-text')
									.remove();
					}
				},	
				
				/*
				 * new
				 */
				
				click : {
					event : function(v) {
						var T = this.__;
						if ($.isFunction(v)) {	
							T.temp.btn.bind('click.zcore', function(e) {
								if (!$(this).is(':disabled')) {
									v.apply(T, arguments);
								}
							});					
						}
					},
					method : function(v) {
						this.__.temp.btn.trigger('click.zcore');
					}
				}					
				
			});	
			
		}
						
	});
	
})(jQuery, jQuery.ZOF);