/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
			
	_.clazz({
		
		name : 'captcha',
		
		inherit : 'postable',
		
		friend : 'ajax',
		
		body : function(o) {		
			
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
						if (v) {
							for (var i in temp) {
								temp[i].attr(this.name, this.name).addClass('ui-state-' + this.name);
							}
						} else {
							for (var i in temp) {
								temp[i].removeAttr(this.name).removeClass('ui-state-' + this.name);
							}
						}
						this.__.temp.btn.button("option", "disabled", v);						
					}
				},
				
				tabindex : {
					refresh : 'finalize',
					get : function() {
						return this.__.temp.val.attr(this.name);

					},
					set : function(v) {
						this.__.temp.val.attr(this.name, v);
					}
				},
				
				value : {
					get : function() {
						return this.__.temp.val.val(); 
					},
					set : function(v) {
						this.__.temp.val.val(v); 
					}			
				},
				
				data : {
					refresh : 'finalize',
					type : 'object',
					get : function() {
						return this.__.temp.img.attr("src");
					},
					set : function(v) {
						this.__.temp.img.attr("src", "data:image/jpeg;base64," + v);
					}	
				},
				
				/*
				 * new
				 */		
			
				maxlength : {
					type : 'int',
					defVal : -1,
					get : function() {	
						var T = this.__;
						return T.temp.val.attr(this.name) !== undefined ? T.temp.val.attr(this.name) : this.defVal; 
					},
					set : function(v) {	
						var T = this.__;
						if (v === this.defVal) {
							T.temp.val.removeAttr(this.name);
						} else {
							v = (v < T.value().length) ? T.value().length : v;
							T.temp.val.attr(this.name, v); 
						}
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
						T.o.append('<ul><li/><li/></ul>');
						T.temp = {
							img : $('<img>', {
								tabindex : -1,
								'class' : "ui-corner-all"
							}).appendTo(T.o.find('li:first')),							
							val : $('<input>', {
								type : 'text',
								'class' : "ui-widget-content zui-text-content zui-text-sizing ui-corner-all"
							}).appendTo(T.o.find('li:last')),							
							btn : $('<button>', {
								tabindex : -1,
								'class' : 'zui-center-v'
							}).appendTo(T.o.find('li:last'))							
						};
						//--> set focused
						T.focused = T.temp.val;						
					}					
				},
				
				render : {
					method : function() {
						var T = this.__;
						T.temp.btn 
								.button({
									icons: {
										primary: "ui-icon-refresh",
										text : undefined
									}
								})
								.click(function() {
									T.fetch();
								})
								.find('.ui-button-text')
									.remove();
					}
				},
								
				validateObject : {
					method : function() {
						return this.__.temp.val;                  	
					}
				},
				
				/*
				 * new
				 */
				
				fetch : {
					method : function() {
						var T = this.__;
						if (!T.disabled()) { 
							$.ZAP.ajax({
						        type : T.methodType(),
						        url : T.service(),   
						        dataType : "html",
						        barHide : false,
						        success: function(data) {				
						        	T.data(data);
						        }	
							}); 
						}
					}
				}				
			
			});	
			
		}
						
	});
	
})(jQuery, jQuery.ZOF);