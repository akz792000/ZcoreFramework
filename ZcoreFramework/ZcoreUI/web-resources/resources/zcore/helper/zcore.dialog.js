/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
		
	_.clazz({
		
		name : 'dialog',
		
		body : function() {
		
			$.extend(this, {
				
				_val : function(v, d) {
					return v != undefined ? v : d;
				},		
				
				provide : function(params) {
					if (typeof params === "string") {
						return {
							draggable : true,
							closeOnEscape : true,
							title : params
						}
					} else if (typeof params === "boolean") {
						return {
							draggable : true,
							closeOnEscape : true
						}
					} else {
						return params;
					}
				},
				
				show : function(name, params) {
					//--> initialize params
					params = this.provide(params);
					var o = $('<div>', { view : name }).appendTo('body').dialog({
							dialogClass : 'zui-global' + (params.dialogClass !== undefined ? ' ' + params.dialogClass : ''),
							autoOpen : true, 
							modal : this._val(params.modal, true), 
							resizable : false, 
							draggable : this._val(params.draggable, true),
							closeOnEscape : this._val(params.closeOnEscape, true),
							title : this._val($.ZAP.message.translate(params.title), ''),	
							/*
							 *  hide dialog to render all the components inside of itself
							 *  
							 *  we can't use hide or show method because when you use it
							 *  the focus function of for example one component in onFinalize
							 *  event of view, doesn't work
							 *  
							 *  in fact the dialog should first render and not be hide then all it's component render.
							 */								
							height : 0,
							width : 0,
							minWidth : params.minWidth || 0,
							minHeight : params.minHeight || 0,
							maxWidth : params.maxWidth || $(window).width(),
							maxHeight : params.maxHeight || $(window).height(),							
							close : function() {	
								//--> close on document
								if (params.closeOnDocument) {
									$(document).unbind('mousedown.dialog.' + name);
								}
								//--> close
								if (params.close) {
									params.close();
								}
								//--> remove all view components and it self when the close button press
								$.ZAP.removeView($(this).attr('view'));
								//--> destroy & remove
								$(this).dialog('destroy').remove();
							}
					});
					//--> set width and height zero
					o.dialog('widget').width(0).height(0);
					//--> titlebar
					var titlebar = o.dialog('widget').find('.ui-dialog-titlebar');
					var closeButton = titlebar.find('.ui-dialog-titlebar-close')
					closeButton.attr({
						'tabindex' : '-1',
						'title' : $.ZAP.message.msg('close')
					});
					if (!this._val(params.closeButton, true)) {
						closeButton.remove();
					}
					if (!this._val(params.titlebar, true)) {
						titlebar.remove();
					}
					if (params.closeOnDocument) {
						o.dialog('widget').bind('mousedown.dialog.' + name, function(e) {
							e.stopPropagation();
						});
						$(document).bind('mousedown.dialog.' + name, function(e) {
							if ($('body').children('.ui-widget-overlay').length == 0) {
								o.dialog('close');
							}
						});
					}
					return o;
				},				
				
				finalize : function(o, params) {
					//--> show the dialog
					o.css({
						'min-width' : (params.minWidth || 0) + 'px',
						'min-height' : (params.minHeight || 0) + 'px',
						'max-width' : (params.maxWidth || $(window).width()) + 'px',
						'max-height' : (params.maxHeight || $(window).height()) + 'px'						
					});	
					o.dialog('widget')
						.width("auto")
						.height("auto");
					o.dialog('option', {
						height : params.height || 'auto',
						width : params.width || 'auto',
						position : this._val(params.position, { my: "center", at: "center", of: window })
					});					
				}
														
			});			
		
		}
	
	});
	
})(jQuery, jQuery.ZOF);