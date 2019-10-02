/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'tabPage',
		
		inherit : 'component',
		
		friend : 'table',
		
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
				
				__null__ : ['tabindex'],
				
				value : {
					get : function() {
						return this.__.temp.header.find('a').text();
					},
					set : function(v) {
						this.__.temp.header.find('a').text(v);
					}	
				},	
				
				visible : {
					after : function(v) {
						var 
							T = this.__,
							number = T.container.tabs("option", 'active');
						if (v) {
							T.temp.header.show();
							//--> after visible set active
							T.active();	
							T.container.tabs("option", 'active', number);
						} else {
							T.temp.header.hide();
						}							
					}
				},
				
				disabled : {
					refresh : 'finalize',
					after : function(v) {
						var 
							T = this.__,
							items = T.parent.o.tabs("option", "disabled"),
							makeArray = function(cnt) {
								var res = [];
								for (var i = 0; i < cnt; i++)
									res.push(i);
								return res;	
							};	
						//--> disabled component
						_.disabledChild(T, v);
						//--> disabled tab menu	
						if (typeof items === 'boolean')
							items = items ? makeArray(T.parent.temp.header.find('li').length) : [];
						var
							index = T.temp.header.index(),
							pos = $.inArray(index, items);
						if (v) {
							if (pos === -1)
								items.push(index);
						} else {	
							if (pos !== -1)
								items.splice(pos, 1);
						}
						T.parent.o.tabs("option", 'disabled', items);
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
						T.temp.header = $('<li>', { 
							html : '<a href="#:id" tabindex="-1">:id</a>'.replace(/:id/g, T.id()),
							number : T.container.find('ul > li').length,
							tabindex : "-1"
						});
						//--> add tab menu
						T.parent.temp.header.append(T.temp.header);
					}		
				},					
							
				render : {
					advice : 'before',
					method : function() {
						var T = this.__;						
						T.parent.o.tabs("refresh");
						T.parent.o.tabs("option", "active", T.temp.header.index());
					}			
				},					
				
				finalize : {
					method : function() {
						var T = this.__,
						 	outerWidth = T.o.outerWidth();
						if (T.parent.temp.width < outerWidth) {
							T.parent.temp.width = outerWidth;
						}					
					}
				},
				
				remove : {
					advice : 'before',
					method : function() {
						var T = this.__;
						if (T.mode != 'create') {
							T.temp.header.remove();
							//--> release it
							T.temp.header = undefined;
						}
					}
				},
				
				/*
				 * new
				 */
				
				active : {				
					method : function() {
						var T = this.__;
						T.container.tabs("option", 'active', parseInt(T.temp.header.attr('number')));
					}
				}
			
			});
			
		}
		
	});
	
})(jQuery, jQuery.ZOF);