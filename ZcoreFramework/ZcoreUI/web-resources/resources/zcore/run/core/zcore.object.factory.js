/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($) {	
	
	function ZcoreObjectFactoryPlugin() {}
	
	$.extend(ZcoreObjectFactoryPlugin.prototype, {
							
		getInstance : function(o) {
			if (o === undefined) {
				return new this.component();
			} else if (typeof o === "string") { 
				return new this[o]();
			} else {	
				return new this[o.attr('type')](o);
			}
		},	
		
		clazz : function(params) {
			if (params.inherit) {
				var applies = [params.inherit];
				if (params.friend !== undefined) {
					applies = applies.concat(params.friend.split(','));
				}
				$.ZOF.constructor.prototype[params.name] = function() {
					//--> set applies
					for (var i = 0; i < applies.length; i++) { 				
						$.ZOF[applies[i]].apply(this, arguments);
					}
					params.body.apply(this, arguments);
				};
				$.ZOF[params.name].prototype = Object.create($.ZOF[params.inherit].prototype);
			} else {
				$.ZOF.constructor.prototype[params.name] = params.body;
			}
		},		

		createComponent : function(model, parent, owner, guid) {
			var 
				clazz = model.attributes.type,
				c = this.getInstance(clazz);
			//--> set attributes
			c.attributes = model.attributes;
			//--> base attributes
			c.clazz = clazz;
			c.parent = parent;
			c.owner = owner;			
			//--> tabindex attribute
			if ($.isFunction(c.tabindex)) {
				if (c.attributes.tabindex !== undefined) {
					c.attributes.tabindex = parseInt(c.attributes.tabindex) + guid.tabindex++;
				} else {
					c.attributes.tabindex = guid.tabindex++;	
				}				
			}
			//--> set id if it does not set
			if (c.attributes.id === undefined) {	
				c.attributes.id = c.clazz + '_' + guid.id++;
			}			
			//--> set default value
			for (var p in c) {
				if (c[p] instanceof this.property) { 
					var v = c.attributes[c[p].name];
					c.params[c[p].name] = (v === undefined) ? c[p].defVal : c[p].typeCast(c.attributes[c[p].name]);
				}
			}
			//--> set name
			c.name = c.params.id;
			//--> create sub components by hierarchy
			for (var j in model.components) {
				this.createComponent(model.components[j], c, owner, guid);
			}
			//--> register in parent's elements
			c.parent.elements[c.name] = c;
			//--> register in owner's components
			owner.components[c.name] = c;			
			return c;
		},
				
		initializeComponent : function(c, data) {
			//--> extera data that comes from another request			
			var ext = data !== undefined ? data[c.params.id] : undefined;
			if (ext !== undefined) {
				for (var p in ext) {
					c.params[p] = ext[p];
				}				
			}			
			//--> set container
			c.container = c.parent.invoke('method', 'embed', 'execute', [c.attributes]);			
			//--> initializing
			c.initializing(); 
			//--> initialize component by hierarchy
			for (var name in c.elements) {
				this.initializeComponent(c.elements[name], data);
			}
			return c;
		},	
		
		renderComponent : function(c) {
			//var start = new Date().getTime();
			//--> rendering
			c.rendering();			
			//--> render component by hierarchy
			for (var name in c.elements) {
				this.renderComponent(c.elements[name]);
			}
			//--> finalizing
			c.finalizing();	
			//console.log(c.name + ' : ' + ((new Date().getTime()) - start));
			return c;
		},
			
		defaultProperty : function(type, defVal, values) {
			var
				res = { 
					type : type,
					defVal : defVal,
					values : values,
					get : function() {	
						return this.__.o.attr(this.name);
					},
					set : function(v) {	
						this.__.o.attr(this.name, v); 
					}
				};
			switch (type) {
			case 'boolean':				 
				$.extend(res, { 
					values : [false, true],
					get : function() {	
						return this.__.o.attr(this.name) === this.name;  
					},
					set : function(v) {
						v
							? this.__.o.attr(this.name, this.name)
							: this.__.o.removeAttr(this.name);					
					}
				});
				break;
			case 'array':
				$.extend(res, {					
					defVal : values[defVal], 
				});		
				break;
			case 'enum':
				$.extend(res, {
					defVal : values[defVal], 
				});		
				break;
			}
			return res;
		},
		
		disabledChild : function(c, v) {
			if (c.elements !== undefined) {
				$.each(c.elements, function(n, e) {
					if ($.isFunction(e.disabled) && !e.disabled()) {
						var methods = ['before', 'after'];
						for (var i = 0; i < methods.length; i++) { 										
							e.invoke('property', 'disabled', methods[i], v);
						}
					}
				});	
			}
		},
		
		functionalKeys : [46, 8, 9, 27, 13, 33, 34, 35, 36, 37, 38, 39, 40, 110, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 190],
		
		signatureKeys : function(ew) {
			return (33 <= ew && ew <= 47) || (58 <= ew && ew <= 64) || (91 <= ew && ew <= 96) || (123 <= ew && ew <= 126); 
		},
		
		extend : function(obj, params) {
			obj = obj || {};
			for (var param in params) {
				obj[param] = $.isPlainObject(params[param]) ? this.extend(obj[param], params[param]) : params[param];
			}
			return obj;
		}
				
	});	
	
	//--> register as global
	$.ZOF = new ZcoreObjectFactoryPlugin();

})(jQuery);