/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
			
	_.clazz({
		
		name : 'lov',
		
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
						var lov = this.__.temp.lov;						
						$.each(['val', 'dsc', 'btn'], function(i, j) {
							if (v) {
								lov[j].attr('disabled', 'disabled').addClass('ui-state-disabled');
							} else {
								lov[j].removeAttr('disabled').removeClass('ui-state-disabled');	
							}
						});
						lov.btn.button("option", "disabled", v);						
					}
				},
				
				tabindex : {
					refresh : 'finalize',
					get : function() {
						var 
							T = this.__,
							lov = T.temp.lov;
						return T.valWidth() ? lov.val.attr(this.name) : lov.dsc.attr(this.name);
					},
					set : function(v) {
						var
							T = this.__,
							lov = T.temp.lov;
						if (T.valWidth()) {
							lov.val.attr(this.name, v);
							lov.dsc.attr(this.name, "-1");
						} else {
							lov.val.attr(this.name, "-1");
							lov.dsc.attr(this.name, v);				
						}
					}
				},			
				
				value : {
					type : 'array',
					get : function() {
						return this.__.temp.lov.value;
					},
					set : function(v) {
						var 
							T = this.__,
							lov = T.temp.lov;						
						//--> set lov value
						lov.value = [];
						for (var i = 0; i < v.length; i++) {
							if ($.isArray(v[i]) && v[i][0] !== undefined) {
								lov.value.push(v[i]);	
							}
						}
						//--> set val and dsc
						(function(res) {
							var
								val = $.ZAP.toString(res.val),
								dsc = $.ZAP.toString(res.dsc);
							lov.val.val(val).attr('title', val).attr('ajaxval', val);
							lov.dsc.val(dsc).attr('title', dsc);
						})(T.valDsc());
					}					
				},		
				
				direction : {		
					temp : 'lov'					
				},				
								
				/*
				 * new
				 */
				
				lovHeight : _.defaultProperty('int', 200),
				
				lovWidth : _.defaultProperty('int', 300),
				
				valWidth : {
					type : 'string',
					get : function() {
						var 
							T = this.__,
							lov = T.temp.lov;
						return lov.val.css('display') === 'none' ? 0 : this.__.temp.lov.val.css('width');
					},
					set : function(v) {		
						var 
							T = this.__,
							lov = T.temp.lov;
						if (v.length) {
							lov.val.show().css('width', v);
							lov.dsc.css('width', 'calc(100% - ' + v + ')');
							//--> set focused
							T.focused = lov.val;
						} else {
							lov.val.hide();
							lov.dsc.css('width', '100%');	
							//--> set focused
							T.focused = lov.dsc;
						}
					}
				},
			
				maxlength : {
					type : 'int',
					defVal : -1,
					get : function() {	
						var 
							T = this.__,
							val = T.temp.lov.val;
						return val.attr(this.name) !== undefined ? val.attr(this.name) : this.defVal; 
					},
					set : function(v) {	
						var 
							T = this.__,
							val = T.temp.lov.val;
						if (v === this.defVal) {
							val.removeAttr(this.name);
						} else {
							v = (v < val.val().length) ? val.val().length : v;
							val.attr(this.name, v); 
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
						T.temp.lov = {
							//--> value
							value : T.params.value || []
						};
						(function(res) { 
							var
								val = $.ZAP.toString(res.val),
								dsc = $.ZAP.toString(res.dsc);			
							$.extend(T.temp, {
								lov : {								
									//--> val
									val : $('<input>', {
										type : 'text',
										val : val,
										title : val,
										'class' : "ui-widget-content zui-text-content zui-text-sizing ui-corner-all"
									}).appendTo(T.o),
									//--> dsc
									dsc : $('<input>', {
										type : 'text',
										val : dsc,
										title : dsc,
										'class' : "ui-widget-content ui-state-default zui-text-content zui-text-sizing ui-corner-all",	
										readonly : 'readonly'
									}).appendTo(T.o),
									//--> btn
									btn : $('<button>', {
										tabindex : -1,
										'class' : 'zui-center-v'
									}).appendTo(T.o)
								}
							});
						})(T.valDsc());
						T.o.addClass("zui-lov");
					}					
				},

				ajaxCallSuccess : {
                    event : function(v) {
                        var T = this.__;
                        if ($.isFunction(v)) {
                            T.temp.ajaxCallSuccessMethod = v;
                        }
					}
				},
				
				render : {
					method : function() {
						var 
							T = this.__,
							ajaxCall = function() {
								// for handle focus item, use setTimeout function
								setTimeout(function() {
									if (T.temp.handler.ajax) {
										var 
											focused = $(':focus'),
											obj = T.temp.lov.val,
											val = obj.val();	
										if (obj.attr('ajaxval') != 'ajaxProcess' && val.trim() != '' && val != obj.attr('ajaxval')) {										
											T.temp.handler.ajax({
												beforeSend : function() {
													obj.attr('ajaxval', 'ajaxProcess');
												},
												fail : function() {
													obj.removeAttr('ajaxval');
												},
												achievement : function() {
													obj.attr('ajaxval', val);
													focused.focus();
												}
											});
										}
									}		
								});
							};
						//--> val
						T.temp.lov.val
							.bind('keypress.lov', function(e) {
								if (!(e.which >= 48 && e.which <= 57)) {
									if ($.inArray(e.keyCode, $.ZOF.functionalKeys) === -1) {
										e.preventDefault();
										return;
									}
								};								
								switch (e.keyCode) {
								//--> 13
								case $.ui.keyCode.ENTER:
								case $.ui.keyCode.TAB:
									e.stopPropagation();
									ajaxCall();
									break;
								//--> 46	
								case $.ui.keyCode.DELETE:
									e.stopPropagation();
									T.clear();									
									break;
								//--> f9
								case 120:
									e.stopPropagation();
									T.dialog();								
									break;	
								default:
									setTimeout(function() {
										var 
											obj = T.temp.lov.val,
											val = obj.val();	
										if (obj.attr('ajaxval') != 'ajaxProcess' && val.trim() != '' && val != obj.attr('ajaxval')) {	
											obj.removeAttr('ajaxval');
											T.temp.lov.dsc.val('');
										}
									});
									break;
								}
							})
							.blur(function() {
								ajaxCall();
							});
						//--> dsc
						T.temp.lov.dsc
							.bind('keypress.lov', function(e) {
								if (!(e.which >= 48 && e.which <= 57)) {
									if ($.inArray(e.keyCode, $.ZOF.functionalKeys) === -1) {
										e.preventDefault();
										return;
									}
								};								
								switch (e.keyCode) {
								//--> 46	
								case $.ui.keyCode.DELETE:
									e.stopPropagation();
									T.clear();									
									break;
								//--> f9
								case 120:
									e.stopPropagation();
									T.dialog();								
									break;									
								}
							});						
						//--> btn 
						T.temp.lov.btn 
								.button({
									icons: {
										primary: "ui-icon-circle-zoomin",
										text : undefined
									}
								})
								.click(function() {
									T.dialog();	
								})
								.find('.ui-button-text')
									.remove();
					}
				},					
								
				clean : { 			
					method : function() {
						this.__.value([]);
					}
				},		
				
				equals : {					
					method : function() {
						var 
							T = this.__,
							value = T.value();
						if ($.isArray(arguments[0])) {
							return String(value) === String(arguments);	
						} else {
							return String(arguments[0]) === String(value[arguments[1] || 0][arguments[2] || 0]);
						}						
					}
				},				
				
				/*
				 * new
				 */
				
				valDsc : {
					method : function() {
						var 
							T = this.__,
							value = T.temp.lov.value,							
							res = { 
								val : [], 
								dsc : [] 
							};						
						for (var i = 0; i < value.length; i++) {
							res.val.push((value[i]) ? ((value[i][0]) ? value[i][0] : '') : '');
							res.dsc.push((value[i]) ? ((value[i][1]) ? value[i][1] : '') : '');							
						}	
						return res;
					}
				}
				
			});	
			
		}
						
	});
	
})(jQuery, jQuery.ZOF);