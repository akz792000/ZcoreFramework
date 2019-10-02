/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
			
	_.clazz({
		
		name : 'file',
		
		inherit : 'postable',
		
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
						temp.btn.button("option", "disabled", v);						
					}
				},
				
				tabindex : {
					refresh : 'finalize',
					get : function() {
						return this.__.temp.dsc.attr(this.name);
					},
					set : function(v) {
						this.__.temp.dsc.attr(this.name, v);
					}
				},			
				
				value : {
					get : function() {
						return this.__.temp.val.get(0).files[0];
					},
					set : function(v) {
						//--> NOP
					}					
				},
				
				/*
				 * new
				 */
				
				size : _.defaultProperty('int', 1),
				
				accept : _.defaultProperty('string', '.png,.tiff,.pdf,.jpeg,.jpg')
				
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
							val : $('<input>', {
								type : 'file',
								style : 'display: none',
								accept : T.params.accept
							}).appendTo(T.o),
							//--> dsc
							dsc : $('<input>', {
								type : 'text',
								'class' : "ui-widget-content ui-state-default zui-text-content zui-text-sizing ui-corner-all",	
								readonly : 'readonly'
							}).appendTo(T.o),
							//--> btn
							btn : $('<button>', {
								tabindex : -1,
								'class' : 'zui-center-v'
							}).appendTo(T.o)
						});
						//--> set focused
						T.focused = T.temp.dsc;
					}					
				},
				
				render : {
					method : function() {
						var 
							T = this.__,							
							temp = T.temp;
						temp.val.change(function() {
							//--> check size of file
							var size = this.files[0].size / 1024 / 1024;
							if (size > T.size()) {
								$.ZAP.box.show($.ZAP.message.msg('error'), $.ZAP.message.msg('Bad file size', [T.size()]));
								return;
							}
							//--> check size of file
							var 
								ext = $(this).val().split('.').pop().toLowerCase(),
								accept = T.accept() + ",";
							if (accept.indexOf(ext + ",") == -1) {
								$.ZAP.box.show($.ZAP.message.msg('error'), $.ZAP.message.msg('Bad file types', [T.accept()]));
								return;
							}							
							//--> set
							T.validateClear();
							temp.dsc.val($(this).val()).trigger('change');
						});
						temp.btn 
								.button({
									icons: {
										primary: "ui-icon-folder-open",
										text : undefined
									}
								})
								.click(function() {
									temp.val.trigger('click');
								})
								.find('.ui-button-text')
									.remove();
					}
				},					
															
				validateObject : {
					method : function() {
						return this.__.temp.dsc;                  	
					}
				},
				
				clean : { 			
					method : function() {
						var temp = this.__.temp;
						temp.val.val('').attr('title', '');
						temp.dsc.val('').attr('title', '');					
					}
				}				
				
			});	
			
		}
						
	});
	
})(jQuery, jQuery.ZOF);