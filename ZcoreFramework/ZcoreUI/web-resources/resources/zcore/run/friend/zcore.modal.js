/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
			
	_.clazz({
		
		name : 'modal',
		
		body : function(o) {		
						
			/*
			 * ----------------------------------------------------------------------
			 * 								 methods
			 * ---------------------------------------------------------------------- 
			 */	
			
			this.methods({				
						
				/*
				 * new
				 */
				
				dialog : {
					method : function(v) {		
						var
							T = this.__,
							title = T.title(),
							handler = T.temp.handler,
							tabindex = 1000,
							v = v || {},
							opt = $.extend({
								autoOpen : false,
								dialogClass : 'zui-global' + (handler.dialogClass == undefined ? '' : ' ' + handler.dialogClass),
								title : title,
								autoOpen : false, 
								modal : true, 
								resizable : false, 
								draggable : true,	
								closeOnEscape : true,
								minHeight : 250,
								minWidth : 300,
								height : 'auto',
								width : 'auto',
								close : function() {
									T.temp.modal = undefined;
									$(this).dialog('destroy').remove();
								}
							}, v),
							dlg = $('<div>', { id : "ZcoreLov_" + T.id() })
								.appendTo('body')
								.dialog(opt);
						//--> set modal	
						T.temp.modal = dlg;	
						//--> widget
						var titlebar = dlg.dialog('widget').find('.ui-dialog-titlebar');
						titlebar.find('.ui-dialog-titlebar-close').remove();
						if (title == '') { 
							titlebar.hide();
						}
						//--> widget
						handler.widget = $('<div>', {
							'class' : 'ui-widget ui-widget-content ui-corner-all',
							tabindex : tabindex++ 
						}).appendTo(dlg.empty());
						//--> show
						handler.render();
						//--> btn
						var btns = {
								ok : function() {
									T.value(handler.value());
									dlg.dialog('close');
									T.focused.focus().change();
								},
								clear : function() {
									T.clear();
									dlg.dialog('close');
									T.focused.focus();
								},
								cancel : function() {
									dlg.dialog('close');
									T.focused.focus();
								}
							},
							buttons = [];
						for (var caption in btns) {
							buttons.push({
								text : $.ZAP.message.msg(caption),
								click : btns[caption],
								tabindex : tabindex++
							});
						}
						//--> show
						dlg
							.dialog('option', {
								buttons : buttons,
								position : v.position == undefined ? { my: "center", at: "center", of: window } : v.position 
							})
							.dialog('open')
							.bind('keypress.dialog', function(e) {
								switch (e.keyCode) {
								//--> 13
								case $.ui.keyCode.ENTER:	
									e.stopPropagation();
									btns.ok();
									break;
								}
							});
						//--> set button focus and blur
						$([handler.widget, dlg.dialog('widget').find('.ui-dialog-buttonpane').find('button')])
							.each(function() {
								$(this)	
									.blur(function() {
										$(this).removeClass('zui-state-hover');
									})
									.focus(function() {
										$(this).addClass('zui-state-hover');
									});
							});						
						//--> focus	
						handler.widget.focus();
					}
				},
				
				remove : {
					advice : 'before',
					method : function() {
						var T = this.__;
						if (T.mode != 'create') {
							if (T.temp.modal) {
								T.temp.modal.dialog('close');
							}
						}
					}
				}				
			
			});	
			
		}
						
	});
	
})(jQuery, jQuery.ZOF);