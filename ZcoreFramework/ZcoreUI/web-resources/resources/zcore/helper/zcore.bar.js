/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
		
	_.clazz({
		
		name : 'bar',
		
		body : function() {	
		
			$.extend(this, {
				
				_init : function() {
					this._o = $('<div>' , {
						"class"  : "zui-global zui-bar ui-widget ui-widget-content ui-corner-all ui-priority-primary",
						html : '<div class="ui-widget-content ui-corner-all"></div><p></p>'
					}).appendTo('body');
					this._func = undefined;
				},
		
				show : function(type, msg, time) {
					var orientation = $.ZAP.orientation()[1];
					this.hide();
					var 
						p = this._o.find('p');					
					p.text($.ZAP.message.translate(msg));
					//--> set orientation
					p.css('padding-' + orientation, '8px');
					switch (type) {
					case 'success':
						this._o.removeClass('zui-bar-error').addClass('zui-bar-success');
						break;	
					case 'error':
						this._o.removeClass('zui-bar-success').addClass('zui-bar-error');
						break;
					}
					var _t_ = this;
					this._o
						.css('top', $('html').scrollTop())				
						.show('bounce', {}, 500, function() {   
							_t_._func = setTimeout(function() {
								_t_._hide();
							}, 
							(time === undefined) ? 50000 : time);
						})
						.click(function() {
							_t_.hide();
						});
				},
				
				_hide : function() {
					this._o.removeAttr('style').hide().fadeOut();							
				},
				
				hideEffect : function() {
					var _t_ = this;
					this._o.hide('highlight', {}, 500, function() {
						 _t_._o.removeAttr("style").hide().fadeOut();       	
					}, 50000);
				},
			
				hide : function() {
					if (this._func !== undefined) {
						clearTimeout(this._func);
						this._func = undefined;
					}
					this._hide();
				},
				
				destroy : function() {
					this._o.remove();
				}
				
			});
			
			this._init();
			
		}
		
	});
	
})(jQuery, jQuery.ZOF);