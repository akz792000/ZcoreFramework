/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {

	_.clazz({
		
		name : 'chosen',
		
		inherit : 'postable',
		
		friend : 'ajax',
		
		body : function(o) {
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 resources
			 * ---------------------------------------------------------------------- 
			 */			
			
			this.resource = {				
					root : '/zcore/util/chosen/',
					files : ['chosen.css', 'chosen.jquery.js']
			};				
				
			/*
			 * ----------------------------------------------------------------------
			 * 								 properties
			 * ---------------------------------------------------------------------- 
			 */		
			
			this.properties({
				
				/*
				 * override
				 */
				
				__null__ : ['readonly'],
				
				disabled : {
					after : function(v) {
						var	T = this.__;
						for (var i in T.temp) {
							T.temp[i]							
								.prop("disabled", v)
								.toggleClass('ui-state-disabled', v);
							if (v) {
								T.temp[i].attr(this.name, this.name);
							} else {
								T.temp[i].removeAttr(this.name);
							}
						}
						//--> for preventing focused while it's disabled
						if (v) {							
							T.focused.attr('tabindex-tmp', T.focused.attr('tabindex')).removeAttr('tabindex');
						} else {
							var tmp = T.focused.attr('tabindex-tmp'); 
							if (tmp != '') {
								T.focused.attr('tabindex', tmp).removeAttr('tabindex-tmp');	
							}
						}
						//--> update
						T.update({});
					}
				},	
				
				tabindex : {
					refresh : 'finalize',
					get : function() {
						var T = this.__;
						return T.valWidth() ? T.temp.input.attr(this.name) : T.temp.chosen.attr(this.name);
					},
					set : function(v) {
						var	T = this.__;
						if (T.valWidth()) {
							T.temp.input.attr(this.name, v);
							T.temp.dropdown.attr(this.name, "-1");
						} else {
							T.temp.input.attr(this.name, "-1");
							T.temp.dropdown.attr(this.name, v);				
						}
					}
				},					
								
				value : {			
					refresh : null,
					type : 'array',
					get : function() {
						var 
							T = this.__,
							res = [];
						T.temp.select.find('option:selected').each(function() {
							var val = $(this).val();
							//--> for allow_single_deselect
							if (val != '') {  
								res.push(val);
							}
						});
						return res;
					},
					set : function(v) {
						this.__.update({ value : v });
					}
				},			
				
				data : {
					type : 'object',
					get : function() {
						var res = [];
						this.__.temp.select.find("option").each(function() {
							var val = $(this).val();
							//--> for allow_single_deselect
							if (val != '') { 
								res.push([val, $(this).html()]);
							}
						});
						return res;
					},
					set : function(v) {
						this.__.update({ data : v });
					}				
				},
				
				/*
				 * new
				 */
				
				valWidth : {
					type : 'string',
					get : function() {
						var T = this.__;
						return T.temp.input.css('display') === 'none' ? 0 : T.temp.input.css('width');
					},
					set : function(v) {		
						var T = this.__;
						if (v.length) {
							T.temp.input.show().css('width', v);
							T.temp.chosen.css('width', 'calc(100% - ' + v + ')');
							//--> set focused
							T.focused = T.temp.input;
						} else {
							T.temp.input.hide();
							T.temp.chosen.css('width', '100%');	
							//--> set focused
							T.focused = T.temp.dropdown;
						}
					}
				},
				
				concatenate : _.defaultProperty('boolean', false),
				
				singleDeselect : _.defaultProperty('boolean', true)
					
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

				refresh : {
					method : function() {
						this.__.temp.select.trigger('chosen:updated');
					}
				},
				
				update : {
					method : function(v, refresh) {						
						var T = this.__;
						//--> data
						if (v.data !== undefined) {
							var 
								/*
								 * the empty option should set as first item
								 * the numeric number set before empty item, so the component 
								 * doesn't work properly because of 
								 * $.extend({'' : ''}, v.data); is not merge respectively
								 */
								data = v.data; //--> active allow_single_deselect 
							T.temp.select.html('');
							T.temp.select.append("<option value=''></option>");
							for (var i in data) {
								var msg = $.ZAP.message.translate('${' + i + '}');
								T.temp.select.append(
									"<option value='" + data[i] + "'>" +
									((T.params.concatenate && data[i] != '') ? data[i].toString().concat(' - ' + msg) : msg) +
									"</option>"
								);
							}
						}
						//--> value
						if (v.value !== undefined) {
							T.temp.select.val(v.value);
							T.temp.input.val(v.value).attr('title', v.value);
							if (T.temp.dropdown !== undefined) {
								T.temp.dropdown.attr('title', v.value);
							}
							//--> evt trigger and set value
							if (refresh === undefined || refresh) {
								var tempValue = T.temp.evt.attr('temp-value') || '';
								if (tempValue != v.value) {
									T.temp.evt.trigger('change');
								}
							}
							T.temp.evt.attr('temp-value', v.value);
						}
						//--> refresh
						if (refresh === undefined || refresh) {
							T.refresh();
						}						
					}
				},
				
				initialize : {
					advice : 'after',
					method : function() {
						var 
							T = this.__,
							P = T.params;
						//--> temp	
						T.temp = {
							//--> component for event handling
							evt : $('<div>').appendTo(T.o),	
							//--> value
							input : $('<input>', {
								type : 'text',
								'class' : "ui-widget-content zui-text-content zui-text-sizing ui-corner-all"
							}).appendTo(T.o),
							//--> select
							select : $('<select>', {
								id : P.id + "Select",
								'class' : 'chosen-' + T.o.css('direction')
							}).appendTo(T.o),
							//--> chosen
							chosen : undefined, //--> set after	
							dropdown : undefined //--> set after
						};
					}
				},
				
				render : {
					method : function() {
						var 
							T = this.__,
							P = T.params,
							setVal = function() {
								var val = T.temp.input.val();
								if (T.value() != val) {
									T.value(val);
									if (T.value() != val) {
										T.clear();
									}
								}
							};
						//--> update without refresh
						T.update(P, false);
						//--> chosen
						T.temp.select
							.chosen({
								allow_single_deselect : P.singleDeselect, //--> This will only work if the first option has blank text.
								placeholder_text_single : $.ZAP.message.msg('ChooseItem'),
								no_results_text : $.ZAP.message.msg('noResult'),
								width : "100%",
								search_contains : true,
								owner_container : T.owner.container,
								focusedItem : function() {
									setTimeout(function() {
											T.focused.focus();
									});
								}
							})
							.bind('change', function(e) {
								T.value($(this).val())
								setTimeout(function() {									
									if (T.valWidth() !== 0) {
										T.temp.input.focus();
									}
								});
							});
						T.temp.chosen = T.temp.select.next().addClass('zui-chosen-div');
						//--> drop down
						T.temp.dropdown = T.temp.chosen.children('a');
						//--> val
						T.temp.input
							.bind('keypress', function(e) {
								if (!(e.which >= 48 && e.which <= 57)) {
									if ($.inArray(e.keyCode, $.ZOF.functionalKeys) === -1) {
										e.preventDefault();
										return;
									}
								};								
								switch (e.keyCode) {
								//--> 13
								case $.ui.keyCode.ENTER:
									e.stopPropagation();
									setVal();									
									break;
								//--> 46	
								case $.ui.keyCode.DELETE:
									e.stopPropagation();
									if (T.singleDeselect()) {
										T.clear();
									}
									break;
								//--> f9
								case 120:
									e.stopPropagation();
									T.open();								
									break;									
								}
							})
							.bind('blur', function() {
								setVal();
							});
					}
				},
				
				enabled : {
					method : function() {
						var 
							T = this.__,
							qualified = T.temp.select;
						return !(qualified.is(':disabled') || qualified.prop('disabled') || qualified.attr('disabled') === 'disabled');
					}
				},			
				
				clean : {
					method : function() {
						this.__.value([]);
					}
				},
				
				remove : {
					advice : 'before',
					method : function() {
						var T = this.__;
						if (T.mode != 'create') {
							T.temp.select.chosen('destroy');
						}
					}
				},
				
				/*
				 * new
				 */
				
				valence : {
					method : function() {
						var 
							T = this.__,
							res = [];
						T.temp.select.find('option:selected').each(function() {
							var val = $(this).val();
							//--> for allow_single_deselect
							if (val != '') {  
								res.push({
									label : $(this).html(),
									value : val
								});
							}
						});
						return res;
					}
				},
				
				change : {
					event : function(v) {
						if ($.isFunction(v)) {	
							this.__.temp.evt.bind('change.chosen', function(e) { 
								v();
							});					
						}
					}
				},		
				
				close : {
					event : function(v) {
						if ($.isFunction(v)) 	
							this.__.o.bind('chosen:hiding_dropdown', function(e) { 
								v();
							});											
					},
					method : function() {
						this.__.temp.select.trigger("chosen:close"); 
					}
				},
				
				open : {
					event : function(v) {
						if ($.isFunction(v)) 	
							this.__.o.bind('chosen:showing_dropdown', function(e) { 
								v();
							});											
					},
					method : function() {
						this.__.temp.dropdown.trigger("mousedown");
					}
				}
				
			});	
			
		}
						
	});
	
})(jQuery, jQuery.ZOF);