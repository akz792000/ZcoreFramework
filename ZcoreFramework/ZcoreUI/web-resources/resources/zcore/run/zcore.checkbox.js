/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'checkbox',
		
		inherit : 'postable',
		
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
						var 
							T = this.__,
							temp = T.temp;
						for (var i in temp) {
							temp[i].prop(this.name, v);
						}
						T.o.toggleClass("ui-state-" + this.name, v);
					}
				},				
				
				value : {
					type : 'boolean',
					get : function() {
						return this.__.focused.hasClass('ui-icon-check');						
					},
					set : function(v) {
						this.__.focused.toggleClass('ui-icon-check', v);
					}					
				},
			
				/*
				 * new
				 */
			
				label : {
					type : 'string',
					get : function() {
						return this.__.temp.label.html();						
					},
					set : function(v) {
						this.__.temp.label.html(v);
					}					
				},
				
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
							span : $('<span>', { 'class' : 'zui-center-v ui-widget-content ui-corner-all ui-icon'} ).appendTo(T.o),
							label : $('<label>', {'class' : 'zui-center-v'}).appendTo(T.o),
						});	
					    //--> focused & set events
						T.focused = T.temp.span
							.bind('click.checkbox.span', function() {	
								if (!$(this).prop('disabled')) { 
							        $(this).toggleClass('ui-icon-check', !$(this).hasClass('ui-icon-check'));
								}
						    })
						    .bind('keypress.checkbox', function(e) {
								switch (e.which) {
								//--> space
								case $.ui.keyCode.SPACE:
									e.stopPropagation();
									if (!$(this).prop('disabled')) { 
										T.click();
									}								
									break;									
								}
							});
					}			
				},				
				
				/*
				 * new
				 */
				
				click : {
					event : function(v) {
						var T = this.__;
						if ($.isFunction(v)) {	
							T.temp.span.bind('click.checkbox', function(e) {
								if (!$(this).prop('disabled')) 
									v.apply(T, arguments);
							});					
						}
					},
					method : function(v) {
						this.__.temp.span.trigger('click.checkbox');
					}
				}				
				
			});		
			
		}
	
	});
	
})(jQuery, jQuery.ZOF);