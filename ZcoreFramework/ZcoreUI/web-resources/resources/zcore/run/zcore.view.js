/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'view',
		
		inherit : 'object',
		
		body : function(o) {	
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 initialize
			 * ---------------------------------------------------------------------- 
			 */
			
			$.extend(this, {
			
				style : undefined,
				
				components : {},
			
				template : undefined,
			
				parent : undefined,
			
				childs : {},
				
				generation : undefined,
				
				/*
				 * I. default
				 * II. dialog
				 */
				display : "default"
			
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
				
				embed : {
					method : function() {
						return this.__.container;
					}
				},
				
				/*
				 * new
				 */
				
				clear : {
					method : function(v) {
						var components = v || this.__.components;
						for (var id in components) {
							if ($.isFunction(components[id].clear)) {
								components[id].clear();
							}
						}
					}
				},	
				
				clean : {
					method : function(v) {
						var components = v || this.__.components;
						for (var id in components) {
							if ($.isFunction(components[id].clean)) {
								components[id].clean();
							}
						}
					}
				},	
				
				centeralize : {
					method : function() {
						var T = this.__;
						while (T.parent != undefined && T.parent.display == "default") {
							T = T.parent;
						}
						//--> centeralize if dialog
						if (T.display == 'dialog') {
							T.container.dialog('option', {
								position: { my: "center", at: "center", of: window }
							});
						}
					}					
				},
				
				featurize : {
					method : function(v) {
						var T = this.__;
						for (var name in v) {
							for (var property in v[name]) {
								if ($.isFunction(T.components[name][property])) {
									T.components[name][property](v[name][property]);
								}
							}
						}
					}
				},					
				
				validate : {
					method : function(errors, components, values) {						
						var res = true;		
						//--> invoke each component's validate method	
						for (var id in components) {
							if ($.isFunction(components[id].validate)) {
								res = components[id].validate(errors, (values || {})[id]) && res;
							}
						}
						return res;						
					}
				},	
				
				validateClear : {
					method : function() {
						var T = this.__;
						for (var id in T.components) {
							if ($.isFunction(T.components[id].validateClear)) {
								T.components[id].validateClear();
							}
						}
					}
				},				
								
				validateSet : { 
					method : function(v) {
						for (var id in v) {
							this.__.components[id].validateSet(v[id]);
						}
					}
				},
				
				validation : {
					method : function(components, values) {
						var 
							T = this.__,
							result = true;
						//--> validate clear
						T.validateClear();
						//--> validate before send
						var errors = {};
						components = components || T.components;
						if (!T.validate(errors, components, values)) {
							T.validateSet(errors);
							result = false;
						}
						return result;
					}
				},
				
				validity : {
					method : function(v) {
						var 
							T = this.__,
							toPlain = function(arr) {
								var res = {};
								for (var i in arr) {
									res[arr[i].id()] = arr[i];
								}
								return res;
							};						
						return T.validation(
								//--> validation components
								$.isArray(v.validation) 
									? toPlain(v.validation) 
									: (v.validation === undefined || v.validation 
											? T.components 
											: {}
										),
								//--> validation values
								v.values //--> prepare in predispose
						); 
					}
				},
				
				initialize : {
					method : function() {
						//--> call when view render
					}
				},
														
				finalize : {
					method : function() {
						//--> call when view render
					}
				},		
				
				call : {
					method : function(v) {
						var T = this.__;
						//--> call
						$.ZAP.call(v, {
							sequential : T.sequential(), 
							validity : T.validity(v),
							events : {
								validate : function(data, textStatus, jqXHR) {
									T.validateSet(data);
									if (v.validate) {
										v.validate(data, textStatus, jqXHR);
									}
								}
							}
						})
					}
				},
				
				sequential : {
					method : function() {
						var 
							T = this.__,							
							c = [];
						if (T.components !== undefined) {	
							$.each(T.components, function(key, val) {
								if ($.isFunction(val.post) && val.post()) {					
									var v = val.value();
									if (v != undefined && v.length !== 0) {
										//--> push
										c.push({
											type : val.type(), 
											key : key,
											val : v,
											seq : val.sequence()
										});
									}
								}
							});
							//--> sort
							c.sort(function(a, b) {
								return a.seq - b.seq;
							});
						}
						return c;
					}
				},
				
				remove : {
					method : function() {
						var T = this.__;
						//--> remove view
						if (T.style) {
							T.style.remove();
						}
						//--> remove elements
						for (var id in T.elements) {
							if ($.isFunction(T.elements[id].remove)) {
								T.elements[id].remove();
							}
						}		
						//--> close if dialog
						if (T.display == 'dialog') {
							T.container.dialog('close');
						}	
						//--> fire close event
						T.signal('close', arguments);
						//--> delete from parent's childs
						if (T.parent instanceof $.ZOF.view) {
							delete T.parent.childs[T.name];
						}
						//--> delete from container
						delete $.ZAP.views[T.name];
					}
				},
				
				close : {
					method : function() {
						this.__.remove();						
					}					
				},				
				
				clientModel : {
					method : function() {
						var 
							T = this.__,
							res = {};
						for (var id in T.components) {
							if ($.isFunction(T.components[id].clientModel)) {
								var v = T.components[id].clientModel();
								if (v !== undefined) {
									res[id] = v;
								}
							}
						}
						return $.isEmptyObject(res) ? undefined : res;
					}
				},
				
				loadView : {
					method : function(v) {
						//--> load view
						var T = this.__;
						return $.ZAP.loadView($.extend({}, v, {
							parent : this.__,
							validate : function(data, textStatus, jqXHR) {
								T.validateSet(data);
								if (v.validate) {
									v.validate(data, textStatus, jqXHR);
								}
							}
						}));
					}
				},
				
				childByIndex : {
					method : function(v) {
						var T = this.__;
						return T.childs[Object.keys(T.childs)[v]]; 
					}
				},
				
				keypress : {
					event : function(v) {
						var
							T = this.__;
						if ($.isFunction(v)) {	
							T.container.bind('keypress.zcore', function(e) {
								v(e);
							});					
						}
					}
				},	
								
			});
			
		}
												
	});
	
})(jQuery, jQuery.ZOF);