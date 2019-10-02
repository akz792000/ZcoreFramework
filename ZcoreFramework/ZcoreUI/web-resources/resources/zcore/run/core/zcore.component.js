/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {	
	
	_.clazz({
		
		name : 'component',
		
		inherit : 'object',
		
		body : function(o) {	
				
			$.extend(this, {
				
				o : o,
				
				focused : undefined,
				
				parent : undefined,
				
				owner : undefined,
				
				append : true,
				
				/*
				 * I. create
				 * II. initialize
				 * III. render
				 * IV. finalize
				 */
				mode : 'create',
				
				tag : '<div>',			
				
				components : function() {
					var res = {};
					for (var name in this.elements) {
						res[name] = this.elements[name];
						if (this.elements[name].elements !== undefined) {
							$.extend(res, this.elements[name].components());
						}
					}
					return $.isEmptyObject(res) ? undefined : res;
				},
				
				resources : undefined,
				
				params : {},
				
				attributes : {},
				
				refreshProperty : function(mode, v) {
					var params = v || this.params;
					for (var p in params) {
						var property = this[this._prefixClass('property') + p];
						if (property !== undefined && property.refresh !== null && property.refresh === mode) {
							this.invoke('property', p, 'execute', [params[p]]);
						}
					}
					return this;
				},
						
				initializing : function() {					
					//--> load resources
					$.ZAP.loadResource(this.resource);
					//--> initialization
					this.invoke('method', 'initialize', 'execute');
					//--> set mode
					this.mode = 'initialize';
					//--> return
					return this;
				},
				
				canister : o,
				
				rendering : function() {							
					//--> render
					this.append ? this.o.appendTo(this.container) :	this.o.prependTo(this.container);
					//--> refresh property of component that must be set after initialize
					this.refreshProperty('initiate');					
					//--> renderer
					this.invoke('method', 'render', 'execute');
					//--> refresh property of component that must be set before it's child
					this.refreshProperty('render');		
					//--> set mode
					this.mode = 'render';
					//--> return
					return this;
				},					
				
				finalizing : function() {
					//--> refresh property of component that must be set after it's child
					this.refreshProperty('finalize');
					//--> initialization
					this.invoke('method', 'finalize', 'execute');
					//--> set mode
					this.mode = 'finalize';
					//--> return
					return this;
				}	
				
			});		
			
			/*
			 * ----------------------------------------------------------------------
			 * 								properties
			 * ---------------------------------------------------------------------- 
			 */				
							
			this.properties({												
								
				type : {
					refresh : null,
					get : function() {	
						return this.__.o.attr(this.name); 
					},
					set : function() {
						throw "can not set type";
					}
				},
				
				id : {
					refresh : null,
					get : function() {			
						return this.__.o.attr(this.name); 
					},
					set : function() {
						throw "can not set id";
					}				
				},
				
				value : {
					get : function() {
						return this.__.o.text(); 
					},
					set : function(v) {
						this.__.o.text(v);
					}					
				},
				
				height : {
					type : 'int',
					get : function() {
						return this.__.o.height();
					},
					set : function(v) {
						if (v) {
							var T = this.__;
							v = (v < parseInt(T.o.css('min-height'))) ? parseInt(T.o.css('min-height')) : v;
							v = (v > parseInt(T.o.css('max-height'))) ? parseInt(T.o.css('max-height')) : v;
							T.o.height(v);
						}
					}					
				},
				
				width : {
					type: 'string',
					get : function() {					
						return this.__.o.css('width');
					},
					set : function(v) {
						if (v) {
							this.__.o.css('width', v);
						}
					}					
				},
				
				direction : {
					temp : 'default',
					type : 'enum',
					values : ['inherit', 'ltr', 'rtl'],
					get : function() {
						/*
						 * if we use this.__.o.css(this.name) for return value
						 * when the property is inherit, the return value doesn't "inherit" 
						 * it does the value of parent's direction
						 */
						return this.__.o.get(0).style.direction; 
					},				
					set : function(v) {
						var 
							T = this.__,
							pattern = "zui-:t-".replace(':t', this.temp === 'default' ? T.type() : this.temp);
						T.o
							.css(this.name, v)
							.removeClass(function(index, css) {
								return (css.match(new RegExp('\\b' + pattern + '\\S+', 'g')) || []).join('');
							})
							.addClass(pattern + T.o.css('direction'));
					}									
				},					
				
				disabled : {
					type : 'boolean',
					values : [false, true],
					get : function() {	
						return this.__.o.hasClass('zui-state-' + this.name);  
					},
					before : function(v) {
						var T = this.__;
						v 
							? T.o.attr(this.name, this.name).addClass('ui-state-' + this.name)
							: T.o.removeAttr(this.name).removeClass('ui-state-' + this.name);					
					},					
					set : function(v) {	
						var T = this.__;
						if (v) { 
							//--> add class to main and focused object
							T.o.addClass('zui-state-' + this.name);
							T.focused.addClass('zui-state-focused-' + this.name);
						} else {
							//--> remove class from main and focused object							
							T.o.removeClass('zui-state-' + this.name);
							T.focused.removeClass('zui-state-focused-' + this.name);
						}
					}
				},		
				
				visible : {		
					type : 'boolean',
					values : [false, true],
					defVal : true,
					get : function() {	
						return !this.__.o.hasClass('zui-state-hidden');  
					},
					before : function(v, effect, options, speed, func) {
						var T = this.__;
						!v 							
							? T.o.hide(effect, options, speed, func)
							: T.o.show(effect, options, speed, func);
					},					
					set : function(v) {	
						var T = this.__;
						!v 
							? T.o.addClass('zui-state-hidden')
							: T.o.removeClass('zui-state-hidden');
					}					
				},
				
				tabindex : {
					type : 'int',
					defVal : 0,
					get : function() {	
						return this.__.focused.attr(this.name);
					},
					set : function(v) {	
						this.__.focused.attr(this.name, v); 
					}
				},
				
				cssClass : $.extend(_.defaultProperty(), {
					before : function(v) {
						this.__.o.addClass(v);
					}				
				}),
				
				cssStyle : $.extend(_.defaultProperty(), {
					before : function(v) {
						if (v) {
							var css = v.split(';');
							for (var i in css) {
								if (css[i]) {
									var cssItem = css[i].split(':');
									var key = cssItem[0].trim(),
										value = cssItem[1].trim();
									if (value.indexOf("!important") === -1) {
										this.__.o.css(key, value);	
									} else {
										this.__.o.get(0).style.setProperty(key, value.replace(/!important/g, ""), "important");
									}
								}
							}
						}
					}				
				}),				
				
				title : _.defaultProperty(),
				
				placeholder : _.defaultProperty()
								
			});
			
			/*
			 * ----------------------------------------------------------------------
			 * 								methods
			 * ---------------------------------------------------------------------- 
			 */	
			
			this.methods({
				
				//--> calls in initializing
				initialize : {
					eventBase : false, 
					method : function() {
						var T = this.__;
						T.focused = T.canister = T.o = $(T.tag, {
							type : T.params.type,
							id : T.params.id,
							name : T.params.id,
							'class' : 'zui-global zui-component zui-' + T.params.type
						});
					}					
				},
				
				//--> calls in rendering
				render : { 		
					method : function() {
						//--> not null because we need its event
					}
				},
				
				remove : {
					method : function() {
						var T = this.__;
						//--> remove elements
						for (var id in T.elements) {
							if ($.isFunction(T.elements[id].remove)) {
								T.elements[id].remove();
							}
						}
						//--> remove element
						if (T.mode != 'create') {
							T.o.remove();
						}
						//--> delete from parent's elements
						delete T.parent.elements[T.name];
						//--> delete from view's components
						delete T.owner.components[T.name];
						//--> release it
						T.o = undefined;
					}
				},			
				
				enabled : {
					method : function() {
						var 
							T = this.__,
							qualified = T.focused;
						return !(
							qualified.is(':disabled') ||
							qualified.prop('disabled') ||
							//--> for grid (title bar's collapse icon)
							qualified.attr('disabled') === 'disabled'							
						);
					}
				},
				
				//--> calls in finalizing
				finalize : {
					method : function() {
						var 
							T = this.__;
						T.focused
							.bind('focus', function() {
								if (T.enabled()) {
									$(this).addClass('zui-state-hover');
								}								
							})
							.bind('blur', function() {
								$(this).removeClass('zui-state-hover');
							});	
					}
				},		
				
				equals : {
					method : function(v) {
						return String(this.__.value()) === String(v);
					}
				},
				
				blur : {
					event : function(v) {
						var 
							T = this.__;
						if ($.isFunction(v)) 	
							T.focused.bind('blur.zcore', function(e) {
								if (T.enabled()) {
									v(e);
								}
							});											
					},
					method : function(v) {
						this.__.focused.trigger('blur.zcore');
					}
				},			
						
				focus : {
					event : function(v) {
						var
							T = this.__;
						if ($.isFunction(v)) 	
							T.focused.bind('focus.zcore', function(e) {
								if (T.enabled()) {
									v(e);
								}
							});											
					},
					method : function(v) {						
						this.__.focused.trigger('focus.zcore');
					}
				},
				
				keypress : {
					event : function(v) {
						var
							T = this.__;
						if ($.isFunction(v)) {	
							T.focused.bind('keypress.zcore', function(e) {
								if (T.enabled()) {
									v(e);
								}
							});					
						}
					}
				},	
				
				paste : {
					event : function(v) {
						var
							T = this.__;
						if ($.isFunction(v)) {	
							T.focused.bind('paste.zcore', function(e) {
								if (T.enabled()) {
									v(e);
								}
							});					
						}
					}
				},				
				
				change : {
					event : function(v) {
						var
							T = this.__;
						if ($.isFunction(v)) {	
							T.focused.bind('change.zcore', function(e) {
								if (T.enabled()) {
									v(e);
								}
							});					
						}
					},
					method : function() {
						this.__.focused.trigger('change.zcore');
					}
				},
				
				orientation : {
					method : function() {
						/*
						 * when you call this method the answer always 
						 * be rtl or ltr not inherit, doesn't need to go 
						 * to parnet and get it's direction
						 */ 				
						return $.ZAP.orientation(this.__.o.css('direction'));
					}
				},
				
				events : {
					event : function(v, func) {
						var es = v.split(' ');
						for (var item in es) {
							var e = es[item];
							this.__['on' + e.substring(0, 1).toUpperCase() + e.substring(1)](func)
						}
					}
				}				
					
			});		
			
		}
										
	});
		
})(jQuery, jQuery.ZOF);