/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'toggle',
		
		inherit : 'postable',
		
		body : function(o) {		
		
			/*
			 * ----------------------------------------------------------------------
			 * 								 initialize
			 * ---------------------------------------------------------------------- 
			 */
			
			this.tag = '<div>';
			
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
						var	temp = this.__.temp;
						$.each(['input', 'label'], function(i, j) {
							if (v) {
								temp[j].attr('disabled', 'disabled').addClass('ui-state-disabled');
							} else {
								temp[j].removeAttr('disabled').removeClass('ui-state-disabled');	
							}
						});
						this.__.focused.button("option", "disabled", v);
					}
				},				
										
				value : {
					refresh : 'finalize',
					type : 'boolean',
					get : function() {
						return this.__.temp.input.is(":checked"); 
					},
					set : function(v) {
						this.__.temp.input.prop("checked", v);						
					},
					after : function() {
						var T = this.__;
						T.temp.input.button('refresh');
						T.refresh();
					}
				},
				
				
				/*
				 * new
				 */
				
				primary : _.defaultProperty('string', 'ui-icon-cancel'),
				
				primaryOn : _.defaultProperty('string', 'ui-icon-check'),
				
				caption : _.defaultProperty('string', $.ZAP.message.msg('no')),
				
				captionOn : _.defaultProperty('string', $.ZAP.message.msg('yes')),
				
				secondary : _.defaultProperty(),
				
				secondaryOn : _.defaultProperty(),
				
				key : _.defaultProperty('boolean', true) 
				
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
						var 
							T = this.__,
							id = T.id() + new Date().getTime();
						T.temp = {
							input : $('<input>', {
								type : 'checkbox',
								id : id
							}).appendTo(T.o),							
							label : $('<label>', {
								'for' : id,
								html : T.params.value
							}).appendTo(T.o),							
						};
						T.o.addClass("ui-state-default ui-corner-all");
						//--> set focused
						T.focused = T.temp.input;	 
					}					
				},
				
				render : { 		
					method : function() {
						var T = this.__;
						T.temp.input.button();	
						//--> set event
						T.temp.input.bind('click.toggle.input', function() {
							if (!T.disabled()) {
								T.refresh();
							}
						});							
					}
				},			
				
				finalize : {
					method : function() {
						var 
							T = this.__;
						T.focused
							.bind('focus', function() {
								if (T.enabled()) {
									$(this).parent().addClass('zui-state-hover');
								}								
							})
							.bind('blur', function() {
								$(this).parent().removeClass('zui-state-hover');
							});	
					}
				},
				
				refresh : {
					method : function() {
						var 
							T = this.__,
							options = T.temp.input.button('option'),
							label = T.caption(),
							primary = T.primary(),
							secondary = T.secondary();
						if (T.key() && T.value()) {
							label = T.captionOn(),
							primary = T.primaryOn(),
							secondary = T.secondaryOn();
						}
						//--> set options
						options.label = label;
						options.icons.primary = primary;
						options.icons.secondary = secondary;
						T.temp.input.button('option', options);
					}
				},
				
				/*
				 * new
				 */
				
				click : {
					event : function(v) {
						var T = this.__;
						if ($.isFunction(v)) {	
							T.temp.input.bind('click.toggle', function(e) {
								if (!$(this).is(':disabled')) {
									v.apply(T, arguments);
								}
							});					
						}
					},
					method : function(v) {
						this.__.temp.input.trigger('click.toggle');
					}
				}								
						
			});		
			
		}
									
	});
	
})(jQuery, jQuery.ZOF);