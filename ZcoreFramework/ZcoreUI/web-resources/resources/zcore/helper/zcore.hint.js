/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
		
	_.clazz({
		
		name : 'hint',
		
		body : function() {	
		
			$.extend(this, {
				
				_init : function() {
					this._o = 
						$('<div>', {
							"class" : "zui-global zui-hint ui-state-highlight ui-priority-primary ui-corner-all",
							html : '<div></div>'
						}).appendTo('body');	
				},
				
				_position : function(o) {					
					this._o.position({
						my : "right bottom",
						at : "right top",
						collision : "flipfit flip",
						of : o
					});
				},
		
				show : function(o, msg, container) {
					var t = this;
					o
						.bind('mouseenter.ZcoreHint', function() {
							 t._o.find('div').html(msg);
							 t._o
								.css('direction', o.css('direction'))
								.show();						 
							 t._position(o);
							 container.bind('scroll.ZcoreHint', function(event) {
								 t._position(o);
							});
						})
						.bind('mouseleave.ZcoreHint', function() {
							container.unbind('scroll.ZcoreHint');
							t._o.hide(); 
						});						
				},
			
				hide : function(o) {					
					o 
						.unbind('mouseenter.ZcoreHint')
						.unbind('mouseleave.ZcoreHint');					
					this._o.hide();
				},
				
				destroy : function() {
					this._o.remove();
				}			
				
			});
			
			this._init();
			
		}
		
	});
	
})(jQuery, jQuery.ZOF);