/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {	
	
	_.clazz({
		
		name : 'object',
		
		body : function() {	
				
			$.extend(this, {
				
				name : undefined,
				
				elements : {},
				
				temp : {},
				
				local : {},
				
				container : undefined,
												
				_prefixClass : function(type) {
					switch (type) {
					case 'property': return '_p_';
					case 'method': return '_m_';
					default: return undefined;
					}					
				},

				_getMethod : function(type, name) { 
					return this._prefixClass(type) + name;
				},

				_getEvent : function(name) {
					return 'on' + name.substr(0, 1).toUpperCase() + name.substr(1);
				},
				
				_createObject : function(type, p) {
					var that = this;
					$.each(p, function(k, v) {
						if (k === '__null__') {
							for (var i = 0; i < v.length; i++) {
								delete that[v[i]];
							    delete that[that._getMethod(type, v[i])];
							    delete that[that._getEvent(v[i])];								
							}
						} else {
							var 
								method = that._getMethod(type, k),
								event = that._getEvent(k); 
							//--> remove
							if (v == undefined) { 
								delete that[k];
							    delete that[method];
							    delete that[event];
							} else {
								//--> new
								if (that[method] === undefined) {
									that[method] = new _[type](that, k, v);
									that[k] = function() {
										return this[method].execute(arguments);
									};
									if (that[method].eventBase) {
										that[event] = function() {
											this[method].event.apply(this[method], arguments);
											return this;
										};						
									}
								//--> override	
								} else {
									 (typeof v === 'object')
										? that[method].extend(v)
									 	//--> means it's default value of property
									 	: that[method].defVal = v;							
								}
							}
						}
					}); 																
				},
				
				properties : function(p) {
					this._createObject('property', p);								
				},	
				
				methods : function(p) {
					this._createObject('method', p); 												
				},
				
				invoke : function(type, k, m, v) {
					var key = this._prefixClass(type) + k;
					if (this[key] !== undefined) {
						if (typeof this[key] === 'object') {
							return $.isFunction(this[key][m]) ? this[key][m](v)	: this[key][m];		
						}
					}				
				},			
				
				signal : function(e, p) {
					this.invoke('method', e, 'signal', p);
					return this;
				}						
				
			});		
			
			/*
			 * ----------------------------------------------------------------------
			 * 								methods
			 * ---------------------------------------------------------------------- 
			 */	
			
			this.methods({
				
				/*
				 *  calls in createComponent in object factory if it does exist
				 *  if component is childable this attribute must have method
				 */
				embed : null,
				
				equals : {
					method : function() {
						return null;
					}
				}
				
			});
			
		}
										
	});
		
})(jQuery, jQuery.ZOF);