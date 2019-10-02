/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'panel',
		
		inherit : 'component',
		
		friend : 'table',
		
		body : function(o) {	
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 initialize
			 * ---------------------------------------------------------------------- 
			 */
			
			this.tag = '<fieldset>';
		
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
						T.focused.removeClass('zui-state-focused-' + this.name);
						_.disabledChild(T, v);						
					}
				},
				
				/*
				 * new
				 */
				
				caption : {
					get : function() {
						return this.__.temp.head.find('label').text();
					},
					set : function(v) {
						var T = this.__;
						if (v) {
							T.temp.head.show().find('label').text(v);
							T.o.addClass('zui-panel-legend');
						} else {
							T.temp.head.hide().find('label').text('');
							T.o.removeClass('zui-panel-legend');
						}
					}
				},
				
				closed : $.extend(_.defaultProperty('boolean'), {
					after : function(v) {
						var 
							T = this.__,
							span = T.temp.head.children('span');
						if (v) {
							span.removeClass("ui-icon-minus").addClass("ui-icon-plus");
							T.o.addClass("zui-panel-collapsed").removeClass("ui-corner-all");							
							T.temp.body.hide();
						} else {
							span.removeClass("ui-icon-plus zui-panel-collapsed").addClass("ui-icon-minus");
							T.o.removeClass("zui-panel-collapsed").addClass("ui-corner-all");
							T.temp.body.show();
						}
					}
				})
											
			});
			
			/*
			 * ----------------------------------------------------------------------
			 * 								methods
			 * ---------------------------------------------------------------------- 
			 */	
			
			this.methods({
				
				/*
				 * override
				 */
			
				initialize : {
					advice : 'after',
					method : function() {
						/*
						 * for prevent interference with dialog we don't use
						 * ui-dialog and ui-dialog-titlebar
						 */
						var T = this.__;
						T.o.addClass("ui-widget ui-widget-content ui-corner-all");
						$.extend(T.temp, {
							head : $('<legend>', {
								'class' : 'zui-panel-header ui-helper-clearfix',	
							}).appendTo(T.o),
							body : $('<div>', {
								'class' : 'zui-panel-content'
							}).appendTo(T.o),
						});	
						//--> add legend header	
						$('<span>', {
							"class" : "ui-icon ui-icon-minus"
						}).appendTo(T.temp.head).click(function() {
							if (!T.o.is(':disabled')) {
								if ($(this).hasClass("ui-icon-minus")) {
									T.closed(true);
								} else {
									T.closed(false);
								}
							}
						});
						$('<label>').appendTo(T.temp.head);
						//--> set canister						
						T.canister = T.temp.body;
					}
				}	
				
			});
			
		}
		
	});
	
})(jQuery, jQuery.ZOF);