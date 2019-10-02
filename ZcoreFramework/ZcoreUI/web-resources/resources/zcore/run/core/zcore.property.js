/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'property',
		
		inherit : 'action',
				
		body : function(parent, name, params) {			
		
			/*
			 * ----------------------------------------------------------------------
			 * 								extends parameters
			 * ---------------------------------------------------------------------- 
			 */	
					
			$.extend(this, $.extend({
				
				temp : undefined,
				
				/*
				 * I. initiate
				 * II. render
				 * III. finalize
				 */			
				refresh : 'render',
				
				type : 'string',
				
				values : undefined,
				
				value : undefined,
				
				defVal : $.proxy(function() {
					switch (this.type) {
					case 'string': return '';
					case 'int': return 0;
					case 'float': return 0;
					case 'boolean' : return false;
					case 'enum' : return this.values[0];
					case 'array' : return [];
					case 'map' : return {};
					case 'object' : '';
					default : return '';
					}
				}, params)(),
							
				get : undefined,
							
				before : undefined,
				
				set : undefined,
				
				after : undefined,
				
				extend : function(params) {
					$.extend(this, params);								
				},			
				
				typeCast : function(v) {
					switch (this.type) {
					case 'string': return v;
					case 'int': return parseInt(v, 10);
					case 'float': return parseFloat(v);
					case 'boolean' : return v != undefined && v.toString() === 'true' ? true : false;
					case 'enum' : return v;
					case 'array' : return $.isArray(v) ? v : (v == undefined ? [] : v.toString().split(','));
					case 'map' : return $.isPlainObject(v) ? v : $.parseJSON(v);
					case 'object' : return v;
					default: return v;
					}
				},	
				
				abstractMethod : function(args) {
					args = args || [];
					/*
					 * eventBase for set active and for get 
					 * inactive by default for properties					  
					 */
					this.eventBase = args.length;
					if (this.eventBase) {
						args[0] = this.typeCast(args[0]);
						if ($.isFunction(this.before)) {
							this.before.apply(this, args);
						}
						this.set.apply(this, args);;
						if ($.isFunction(this.after)) {
							this.after.apply(this, args);;
						}
					} else {
						return this.typeCast(this.get());
					}
				}
				
			}, params));
		
		}
	
	});	
	
})(jQuery, jQuery.ZOF);