/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
			
	_.clazz({
		
		name : 'menu',
		
		inherit : 'component',
		
		friend : 'ajax',
		
		body : function(o) {	
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 resources
			 * ---------------------------------------------------------------------- 
			 */			
			
			this.resource = {					
					root : '/zcore/util/menu/',
					files : ['zcore.menu.css', 'zcore.menu.js']
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
				
				__null__ : ['value', 'readonly', 'post', 'sequence', 'validators'],
					
				/*
				 * new
				 */		
				
				inside : _.defaultProperty('string', 'body'),
				
				menuWidth : _.defaultProperty('int', 250),
				
				menuHeight : _.defaultProperty('int', 24),	
				
				showSpeed : _.defaultProperty('int', 200)		
				
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
				
				__null__ : ['clear', 'clean', 'validate', 'validateClear', 'validateObject', 'validateSet'],
				
				finalize : {
					method : function() {
						var 
							T = this.__, 
							P = T.params,
							items = P.data,
							orientation = T.orientation(),
							distance = 0;
						for (var item in items) {	
							var opts = {};
							opts.name = P.id + '_' + item;
							opts.content = T.renderHTML(items[item].l);
							opts.inside = $(P.inside); 
							opts.showSpeed = P.showSpeed; 
							opts.direction = orientation[0];
							opts.width = P.menuWidth;
							opts.click = $.proxy(function() { this.signal('click', arguments); }, T);						
							distance += (T.o.children('div:last').outerWidth() || 0); 
							$( 
								 "<div id='" + P.id + item + 
								 "' style='position: relative;float:" + orientation[1] + ";height:" + P.menuHeight + "px;'" +
								 " class='fg-button ui-state-default ui-widget ui-widget-content ui-corner-all'>" + 
								 "<span class='ui-icon ui-icon-plus'></span>" +
								 "<label>" + 
								 $.ZAP.message.translate(items[item].c) +
								 "</label>" +
								 '</span>'
							)
								.appendTo(T.o)
								.hover(
						    		function(){ $(this).removeClass('ui-state-default').addClass('ui-state-focus'); },
						    		function(){ $(this).removeClass('ui-state-focus').addClass('ui-state-default'); }
						    	)					
								.addClass('fg-button-icon-' + orientation[1])
								.ZcoreMenu('create', opts);
						}
					}
				},
				
				/*
				 * new
				 */
				
				click : {},
								
				getItem : {
					method : function(v, type) {
						var T = this.__;						
						return T.trace(T.params.data, {
							value : v,
							type : type || 'title'
						});
					}
				},
				
				trace : {
					method : function(v, obj, path) {
						for (var i in v) {	
							var 
								item = v[i],
								comparable = item.t;
							if (obj.type == 'caption') {
								comparable = $.ZAP.message.translate(item.c);
							}
							if (comparable == obj.value) {
								return {
									caption : $.ZAP.message.translate(item.c),
									command : item.o,
									title : item.t,
									path : (path === undefined ? $.ZAP.message.translate(item.c) : path + ' -> ' + $.ZAP.message.translate(item.c))
								};
							}
							if (item.l !== undefined) { 
								var res = this.__.trace(item.l, obj, (path === undefined ? $.ZAP.message.translate(item.c) : path + ' -> ' + $.ZAP.message.translate(item.c)));
								if (res != null) {
									return res;
								}
							}
						}
						return null;
					}
				},
				
				getWitting : {
					method : function(v) {						
						var 
							T = this.__,
							data = v || T.params.data,
							result = [];
						for (var i in data) {	
							var item = data[i];							
							if (item.l !== undefined) { 
								result = result.concat(T.getWitting(item.l));
							} else {
								result.push($.ZAP.message.translate(item.c));
							}
						}
						return result;
					}
				},				
				
				renderHTML : {
					method : function(v) {
						var 
							queryStr = '<ul>',
							tempStr = '';
						for (var item in v) {
							if ((v[item].o) && (v[item].o.trim != '')) {
								tempStr = ' title="' + v[item].t + '" command="' + v[item].o + '"';
							} else {
								tempStr = '';
							}							
							queryStr += "<li>" 
								+ '<a ' + tempStr + ">" 
								+ (v[item].t != undefined ? '<div class="ui-state-default ui-corner-all fg-menu-title">' + v[item].t + '</div>' : '')								
								+ '<label>' + $.ZAP.message.translate(v[item].c) + '</label>'
								+ "</a>";
							if (v[item].l) {
								queryStr += this.__.renderHTML(v[item].l);
							}
							queryStr += "</li>";								
						}
						queryStr += '</ul>';
						return queryStr;					
					}
				}			
			
			});
		
		}
						
	});	
	
})(jQuery, jQuery.ZOF);