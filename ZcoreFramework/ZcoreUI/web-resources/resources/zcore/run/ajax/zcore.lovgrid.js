/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'lovgrid',
		
		inherit : 'grid',
		
		friend : 'lov,modal',
		
		body : function(o) {		
		
			/*
			 * ----------------------------------------------------------------------
			 * 								 methods
			 * ---------------------------------------------------------------------- 
			 */	
			
			this.methods({
				
				render : {
					advice : 'after',
					method : function() {
						var T = this.__;
						T.temp.handler = {
							widget : undefined,
							onSuccess : undefined,
							ajax : function(params) {
								$.ZAP.ajax({
						    		type : "GET",
						  		  	url : T.service(),
						  		  	data : $.extend({'filterColumnsValue' : T.temp.lov.val.val()}, T.invoke('method', 'prepare', 'execute', [])),
						  		  	dataType : "json",	
						  		  	beforeSend : function() {
						  		  		if (params.beforeSend) {
						  		  			params.beforeSend();
						  		  		}
						  		  	},
						  		  	success : function(dataCallback) {
						    			switch (dataCallback.type) {
								  	  	case 'grid':			
									  		if (!T.multiSelected() && dataCallback.data.length > 1) {
									  			if (params.fail) {
								  		  			params.fail();
								  		  		}
									  			T.dialog();
									  		} else {
									  			if (params.achievement) {
								  		  			params.achievement();
								  		  		}
												T.value(T.temp.handler.value(dataCallback.data));
									  		}
								  	  		break;
						        		}
						        		// call on success
                                        if ($.isFunction(T.temp.ajaxCallSuccessMethod)) {
                                            T.temp.ajaxCallSuccessMethod(dataCallback);
										}
						        	} 
						      	});								
							},
							render : function() {
								//--> clear to force grid to get data again
								T.params.data[2] = undefined;
								T.params.refreshOnRender = true;
								//--> render grid
								this.widget.ZcoreGridInvoker('lovgrid', T);									
							}, 
							value : function(v) {
								return v === undefined ? this.widget.ZcoreGrid('getSelectedRows') : v;
							}
						};
					}
				}
			
			});
			
		}
						
	});
	
})(jQuery, jQuery.ZOF);