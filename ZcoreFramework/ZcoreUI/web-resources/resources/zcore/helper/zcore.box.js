/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
		
	_.clazz({
		
		name : 'box',
		
		body : function() {		
		
			$.extend(this, {
							
				_init : function() {
					this._o = $('<div>', {
							html : "<p><b></b></p>"
						})
						.appendTo('body')
						.dialog({
							dialogClass : 'zui-global',
							autoOpen : false, 
							modal : true, 
							resizable : false, 
							draggable : true,	
							closeOnEscape : false,							
							width : 'auto',
							height : 'auto',
							maxWidth : 900,
							maxHeight : 700
						});
					this._o.css({
						'max-width' : '900px',
						'max-height' : '700px'						
					});						
					//--> widget
					this._w = this._o.dialog('widget');
					this._w.find('.ui-dialog-titlebar').find('.ui-dialog-titlebar-close').remove();	
				},
				
				_box : function(title, msg, btns, focus, direction) {						
					var	buttons = {};				
					for (var i = 0; i < btns.length; i++) {
						buttons[btns[i].label] = btns[i].click;
					}
					//--> show
					this._o.find('b').html($.ZAP.message.translate(msg)).css({
						margin: '4px'
					});
					this._o
						.dialog('option', 'title', $.ZAP.message.translate(title))
						.dialog('option', 'buttons', buttons)
						.dialog('open');
					//--> centeralize title and buttons	
					this._w.find('b')
						.position({
							"my" : "center center",
							"at" : "center center",
							"of" : this._o,
							using : function(pos, obj) {
								$(this).css({
									top : pos.top < 0 ? 0 : pos.top
								});
							}							
						});			
					//--> set button focus and blur
					var btnset = this._o.next(); 
					btnset.find('button').each(function() {
						$(this)	
							.blur(function() {
								$(this).removeClass('zui-state-hover');
							})
							.focus(function() {
								$(this).addClass('zui-state-hover');
							});
					});
					//--> focus it
					(focus !== undefined 
							? btnset.find("span:contains('" + this._message(focus) + "')").parent().focus() 
							: btnset.find('button:first')).focus(); 	
					//--> set direction
					if (direction !== undefined) {
						this._o.css('direction', direction);
					}
				},
				
				_message : function(v) {
					return $.ZAP.message.msg(v.toLowerCase());
				},
				
				show: function(title, msg, type, func, focus, direction) {
					var btns = undefined;
					switch (type) {
					case 'MB_YESNO':
						btns = [
								{
									label : this._message('YES'), 
									click : function() {
										$(this).dialog('close');
										if (func !== undefined) {
											if ($.isFunction(func)) 
												func();
											else if ($.isFunction(func['YES']))
												func['YES']();
										}
									}
								},
								{
									label : this._message('NO'),
									click : function() {
										$(this).dialog('close');
										if (func !== undefined) {
											if ($.isFunction(func['NO']))
												func['NO']();
										}
									}
								}
							];			
							break;
					case 'MB_YES':
						btns = [
								{
									label : this._message('YES'),
									click : function() {
										$(this).dialog('close');
										if (func !== undefined) {
											if ($.isFunction(func)) 
												func();
											else if ($.isFunction(func['YES']))
												func['YES']();
										}
									}
								}
							];			
							break;						
					case 'MB_OKCANCEL':
						btns = [
								{
									label : this._message('OK'), 
									click : function() {
										$(this).dialog('close');
										if (func !== undefined) {
											if ($.isFunction(func)) 
												func();
											else if ($.isFunction(func['OK']))
												func['OK']();
										}
									}
								},
								{
									label : this._message('CANCEL'),
									click : function() {
										$(this).dialog('close');
										if (func !== undefined) {
											if ($.isFunction(func['CANCEL']))
												func['CANCEL']();
										}
									}
								}
							];			
							break;
					case 'MB_OK':
						btns = [
								{
									label : this._message('OK'), 
									click : function() {
										$(this).dialog('close');
										if (func !== undefined) {
											if ($.isFunction(func)) 
												func();
											else if ($.isFunction(func['OK']))
												func['OK']();
										}
									}
								}
							];			
							break;						
					default:
						btns = [
								{
									label : this._message('OK'), 
									click : function() {
										$(this).dialog('close');
									}
								}
							];						
					}
					this._box(title, msg, btns, focus, direction);
				},
				
				destroy : function() {
					this._o.dialog('destroy').remove();
				}						
							
			});
			
			this._init();
			
		}
		
	});
	
})(jQuery, jQuery.ZOF);