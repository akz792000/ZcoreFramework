/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'lovtree',
		
		inherit : 'tree',
		
		friend : 'lov,modal',
		
		body : function(o) {						
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 methods
			 * ---------------------------------------------------------------------- 
			 */	
			
			this.methods({
				
				/*
				 * override
				 */			
								
				render : {
					advice : 'after',
					method : function() {
						var T = this.__;		
						T.temp.handler = {
							widget : undefined,	
							ajax : function(params) {
								$.ZAP.ajax({
						    		type: "GET",
						  		  	url: T.service(),
						  		  	data: {
						  		  		operation : 'SELF',
						  		  		id : T.temp.lov.val.val(),
						  		  	},
						  		  	dataType: "json",
						  		  	beforeSend : function() {
						  		  		if (params.beforeSend) {
						  		  			params.beforeSend();
						  		  		}
						  		  	},
						  		  	success: function(dataCallback) {		
							  	  		if (dataCallback == undefined) {
								  			if (params.fail) {
							  		  			params.fail();
							  		  		}
							  	  			T.dialog();
							  	  		} else {
								  			if (params.achievement) {
							  		  			params.achievement();
							  		  		}
											var res = [];										
											for (var i = 0; i < dataCallback.length; i++) {
												res.push([dataCallback[0].attr.id, dataCallback[0].data]);
											}	
											T.value(res);
							  	  		}
						  		  	}
						      	});								
							},							
							render : function() {
								this.widget
									.height(T.lovHeight())
									.width(T.lovWidth())
									.ZcoreTreeInvoker('show', T.params.category, T)
									.ZcoreTreeInvoker('option', 'set', T);
							},
							value : function(v) {
								return this.widget.ZcoreTreeInvoker('option', 'get', T);
							}								
						};
					}
				}	
			
			});
			
		}
						
	});
	
})(jQuery, jQuery.ZOF);