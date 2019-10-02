/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {

	_.clazz({
		
		name : 'multiselect',
		
		inherit : 'postable',
		
		friend : 'ajax',
		
		body : function(o) {
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 resources
			 * ---------------------------------------------------------------------- 
			 */			
			
			this.resource = {	
					/*
					 * copy jquery.multiselect.filter.js at the end of jquery.multiselect.js
					 */					
					root : '/zcore/util/multiselect/',
					files : ['multiselect.css', 'jquery.multiselect.js']
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
						this.__.temp.select.multiselect(v ? 'disable' : 'enable');
					}
				},	
								
				value : {		
					refresh : null,
					type : 'array',
					get : function() {
						var res = [];
						this.__.temp.select.multiselect('getChecked').each(function() {
							res.push($(this).val());
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
							res.push([$(this).val(), $(this).html()]);
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
				
				header : _.defaultProperty('string', 'false'),
				
				multiple : _.defaultProperty('boolean'),
				
				listHeight : _.defaultProperty('int', 100),
				
				selectedList : _.defaultProperty('int', 1),
				
				containerWidth : _.defaultProperty('int', 0),
				
				checkAllShow : _.defaultProperty('boolean', true),
				
				uncheckAllShow : _.defaultProperty('boolean', true),
					
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
						this.__.temp.select.multiselect('refresh');
					}
				},
				
				update : {
					method : function(v, refresh) {						
						var T = this.__;
						//--> data
						if (v.data !== undefined) {
							var data = v.data; 
							T.temp.select.html('');
							for (var i in data) {
								T.temp.select.append(
									"<option value='" + data[i] + "'>" +
									$.ZAP.message.translate('${' + i + '}') +
									"</option>"
								);
							}
						}
						//--> value
						if (v.value !== undefined) {
							T.temp.select.val(v.value);	
						}
						//--> refresh
						if (refresh === undefined || refresh) {
							T.refresh();
						}
					}
				},				
				
				render : {
					method : function() {
						var 
							T = this.__,
							P = T.params;
						//--> set select
						T.temp.select = $('<select>', {
								id : P.id + "Select",
								multiple : P.multiple ? "multiple" : undefined
							}).appendTo(T.o);
						//--> update without refresh
						T.update(P, false);
						//--> draw multiselect
						T.temp.select.multiselect({
									direction : T.orientation()[0],
									multiple : P.multiple,
									header : $.inArray(P.header, ['true', 'false']) !== -1 ? (P.header === 'true') : P.header,
									title : P.title,
									containerWidth : P.containerWidth,
									minWidth : P.width,
									height : P.listHeight,					
									selectedList : P.selectedList,
									checkAllText : $.ZAP.message.msg('checkAll'),
									uncheckAllText : $.ZAP.message.msg('uncheckAll'),
									noneSelectedText : $.ZAP.message.msg('ChooseItem'),
									selectedText : $.ZAP.message.msg('selectedItem'),
									container : T.owner.container,
									checkAllShow : P.checkAllShow,
									uncheckAllShow : P.uncheckAllShow
								});
						//--> refresh
						T.refresh();
						//--> set focused
						T.focused = T.o.children('button');
					}
				},
				
				finalize : {
					method : function() {
						/*
						 * we put multiselectfilter here because
						 * it renders rapidly than put it in render 
						 */
						var 
							T = this.__,
							P = T.params;
						if (P.header.toString() !== 'false') {
							T.temp.select.multiselectfilter({
								label : '',
								placeholder : $.ZAP.message.msg('search'),
							});
						}
					}
				},
				
				clean : {
					method : function() {
						this.__.value([]);
					}
				},
						
				/*
				 * new
				 */
				
				change : {
					event : function(v) {
						if ($.isFunction(v)) 	
							this.__.o.bind('multiselectchange', function(e, ui) { 
								/*
								 * ui = { value, text, checked }
								 */
								v(ui);
							});											
					}
				},
				
				click : {
					event : function(v) {
						if ($.isFunction(v)) 	
							this.__.o.bind('multiselectclick', function(e, ui) {
								/*
								 * ui = { value, text, checked }
								 */
								v(ui);
							});											
					}
				},
				
				close : {
					event : function(v) {
						if ($.isFunction(v)) 	
							this.__.o.bind('multiselectclose', function(e) { 
								v();
							});											
					},
					method : function() {
						this.__.temp.select.multiselect('close');
					}
				},
				
				open : {
					event : function(v) {
						if ($.isFunction(v)) 	
							this.__.o.bind('multiselectopen', function(e) { 
								v();
							});											
					},
					method : function() {
						this.__.temp.select.multiselect('open');
					}
				},
				
				checkAll : {
					event : function(v) {
						if ($.isFunction(v)) 	
							this.__.o.bind('multiselectcheckall', function(e) { 
								v();
							});											
					},
					method : function() {
						this.__.temp.select.multiselect('checkAll');
					}				
				},			
				
				uncheckAll : {
					event : function(v) {
						if ($.isFunction(v)) 	
							this.__.o.bind('multiselectuncheckall', function(e) { 
								v();
							});											
					},
					method : function() {
						this.__.temp.select.multiselect('uncheckAll');
					}	
				}			
			
			});	
			
		}
						
	});
	
})(jQuery, jQuery.ZOF);