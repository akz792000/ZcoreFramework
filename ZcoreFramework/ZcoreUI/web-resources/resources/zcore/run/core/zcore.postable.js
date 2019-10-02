/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'postable',
		
		inherit : 'component',
		
		body : function(o) {	
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 initialize
			 * ---------------------------------------------------------------------- 
			 */	
			
			this.validateClass = "ui-state-error zui-state-error";
		
			/*
			 * ----------------------------------------------------------------------
			 * 								 properties
			 * ---------------------------------------------------------------------- 
			 */			
			
			this.properties({
				
				/*
				 * new
				 */
				
				readonly : _.defaultProperty('boolean'),
				
				post : _.defaultProperty('boolean', true),
				
				sequence : _.defaultProperty('int', 0),
				
				validators : {
					refresh : null,
					type : 'array',
					get : function() {
						//--> NOP
					},
					set : function(v) {
						//--> NOP
					}				
				}				
				
			});
			
			/*
			 * ----------------------------------------------------------------------
			 * 								methods
			 * ---------------------------------------------------------------------- 
			 */	
			
			this.methods({
				
				/*
				 * new
				 */		
				
				clear : { 				
					method : function() {
						var T = this.__;
						T.invoke('method', 'validateClear', 'execute');
						T.invoke('method', 'clean', 'execute');
					}
				},
				
				clean : {
					method : function() {
						this.__.invoke('property', 'value', 'execute', ['']);
					}
				},
				
				validate : {
					method : function(errors, value) {
						var 
							T = this.__, 
							vlds = T.params["validators"];				
						for (var item = 0; item < vlds.length; item++) {
							var vld = vlds[item],								
								M = {
									name : vld.value,
									value : value != undefined ? value : T.value(),
									args : vld.args != undefined ? vld.args.split(",") : "",
									code : vld.code,	
									applyIf : vld.applyIf,
									scope : T.owner.components
								};
							if (!$.ZVD.invoke(M)) {
								errors[T.id()] = [M.name, M.args, M.code];
								return false;
							}
						}
						return true;
					}
				},		
				
				validateClear : {
					method : function() {
						var T = this.__;
						$.ZAP.hint.hide(T.validateObject().removeClass(T.validateClass));
					}
				},				
				
				validateObject : {
					method : function() {
						return this.__.focused;
					}
				},
				
				validateSet : {
					method : function(v) {
						var T = this.__,
							code = v[2] || v[0];
						$.ZAP.hint.show(T.validateObject().addClass(T.validateClass), $.ZAP.message.msg(code, v[1]), T.owner.container);
					}
				}
								
			});
			
		}
		
	});
	
})(jQuery, jQuery.ZOF);