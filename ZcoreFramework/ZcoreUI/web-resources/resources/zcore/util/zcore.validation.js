/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($) {
	
	function ZcoreValidationPlugin() {
		this.handlers = {};
		this.validators = [];
	}
	
	//--> register as global
	$.ZVD = new ZcoreValidationPlugin();
	
	(function(_) {
		
		/*
		 * ----------------------------------------------------------------------
		 * 								 methods
		 * ---------------------------------------------------------------------- 
		 */		
		
		$.extend(ZcoreValidationPlugin.prototype, {
			
			_init : function() {
				for (var name in _.handlers) {
					_.validators.push(name);
				}
			},				
			
			_isNumber : function(v) {
			  return !isNaN(parseFloat(v)) && isFinite(v);
			},
			
			_value : function(type, v) {
				switch (type) {
				case 'string' : return v;
				case 'number' : return Number(v);
				case 'array' : return v == undefined ? [] : v;				
				case 'object' : return v == undefined ? [] : v;
				default: return v;
				}
			},			
			
			_args : function(type, a) {
				var res = [];
				for (var i = 0; i < a.length; i++) {
					res.push(_._value(type, a[i]));
				}
				return res;
			},
			
			_call : function(name, v, a) {
				return _.handlers[name].method(v, a);	
			},
			
			invoke : function(params) {
				var 
					handler = _.handlers[params.name],
					value = _._value(handler.value, params.value),
					args = _._args(handler.args, params.args),
					result = false;
				params.code = params.code || handler.code;
				result = params.applyIf === undefined ? true : _.handlers["expression"].method(value, params.applyIf, params.scope);
				if (result) {
					return handler.method(value, args, params.scope);	
				}
				return true;
			}
			
		});
		
		/*
		 * ----------------------------------------------------------------------
		 * 								 handlers
		 * ---------------------------------------------------------------------- 
		 */
		
		$.extend(_.handlers, {
			
			/*
			 * ----------------------------------------------------------------------
			 *                       order by alphabetical
			 * ---------------------------------------------------------------------- 
			 */			
	
			"expression" : {
				value : 'string',
				args : 'string',
				method : function(v, a, scope) {					
					return new Function("return " + a.toString()
							.replace(/ and /g, ' && ')
							.replace(/ or /g, ' || ')
							.replace(/ eq /g, ' == ')
							.replace(/ ne /g, ' != ')
							.replace(/ gt /g, ' > ')
							.replace(/ ge /g, ' >= ')
							.replace(/ lt /g, ' < ')
							.replace(/ le /g, ' <= ')							
						).apply(scope);
				}
			},
			
			"email" : {
				value : 'string',
				args : 'string',
				method : function(v, a) {
					return _._call("regexp", v, "^(([A-Za-z0-9]+_+)|([A-Za-z0-9]+\\-+)|([A-Za-z0-9]+\\.+)|([A-Za-z0-9]+\\++))*[A-Za-z0-9]+@((\\w+\\-+)|(\\w+\\.))*\\w{1,63}\\.[a-zA-Z]{2,6}$");
				}
			},			
			
			"length" : {
				value : 'string',
				args : 'number',
				method : function(v, a) {
					return _._call("min.length", v, a[0]) && _._call("max.length", v, a[1]);
				}
			},
			
			"max" : {
				value : 'number',
				args : 'number',
				method : function(v, a) {
					return v <= a;
				}
			},
			
			"max.length" : {
				value : 'string',
				args : 'number',
				method : function(v, a) {
					return v.length <= a;
				}
			},
			
			"max.size" : {
				value : 'array',
				args : 'number',
				method : function(v, a) {
					return v.length <= a;
				}
			},
			
			"min" : {
				value : 'number',
				args : 'number',
				method : function(v, a) {
					a = Number(a);
					return _._isNumber(v) && v >= a;
				}
			},
			
			"min.length" : {
				value : 'string',
				args : 'number',
				method : function(v, a) {
					return v.length >= a;
				}
			},
			
			"min.size" : {
				value : 'array',
				args : 'number',
				method : function(v, a) {					
					return v.length >= a; 
				}
			},
			
			"not.blank" : {
				value : 'string',
				args : 'string',
				method : function(v, a) {
					return v != '';
				}
			},
			
			"not.empty" : {
				value : 'array',
				args : 'number',				
				method : function(v, a) {
					return v.length != 0;
				}
			},
			
			"not.null" : {
				value : 'object',
				args : 'object',
				method : function(v, a) {
					return v != null;
				}
			},
			
			"range" : {
				value : 'number',
				args : 'number',
				method : function(v, a) {
					a = a.split(",");
					return _.handlers["min"](v, a[0]) && _.handlers["max"](v, a[1]);
				}
			},
			
			"regexp" : {
				value : 'string',
				args : 'string',
				method : function(v, a) {
					return v.match(a);
				}
			},
			
			"size" : {
				value : 'array',
				args : 'number',
				method : function(v, a) {
					return _.handlers["min.size"](v, a[0]) && _.handlers["max.size"](v, a[1]);
				}
			},
			
			"url" : {
				value : 'string',
				args : 'string',
				method : function(v, a) {
					return _._call("regexp", v, "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)");
				}
			},
			
			"date" : {
				value : 'string',
				args : 'string',
				code : "date.fault",
				method : function(v, a) {
					if (a[0] == "GREGORIAN") {
						// not implemented
					} else {
						try {
							var 
								calendar = $.calendars.instance('persian', 'fa'),
								change = calendar.parseDate('yyyy/mm/dd', v),
								main = change.year() * 10000 + change.month() * 100 + change.day();
							return (main >= 12000101) && (main < 15000101);
						} catch (e) {
							return false;
						}
					}					
				}
			}
   
		});
		
		_._init();
		
	})($.ZVD);

})(jQuery);