/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'ajax',
		
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
						
				value : {
					refresh : 'finalize',
					get : function() {
						//--> must be implemented; 
					},
					set : function() {
						//--> must be implemented;
					}
				},			
							
				/*
				 * new
				 */
				
				data : {
					refresh : null,
					type : 'array',
					get : function() {
						//--> must be implemented;
					},
					set : function(v) {
						//--> must be implemented;
					}				
				},
				
				service : $.extend(_.defaultProperty(), { refresh : 'initiate' }),
				
				async : $.extend(_.defaultProperty('boolean', true), { refresh : 'initiate' }),
				
				methodType : _.defaultProperty('string', 'GET')				
					
			});	
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 methods
			 * ---------------------------------------------------------------------- 
			 */	
			
			this.methods({		
				
				/*
				 * new
				 */
				
				clientModel : {
					method : function() {
						//--> must be implemented by sub classes
					}
				},
				
				serviceParam : {
					event : function(v) {
						//--> add new method when it call from grid's finalize event
						this.__.methods({
							prepare : {
								method : function(options) {
									var serviceParam = v(), send = false;
									for (var k in serviceParam) {
										if ((typeof serviceParam[k] === 'array') && (serviceParam[k].length)) {
											send = true;
											break;
										} else if ((typeof serviceParam[k] === 'object') && (!$.isEmptyObject(serviceParam[k]))) {
											send = true;
											break;
										} else if (typeof serviceParam[k] === 'string' || $.ZAP.isNumber(serviceParam[k])) {
											send = true;
											break;
										}
									}
									if (send) {
										//--> delete key that doesn't have value
										for (var key in serviceParam) {
											var val = serviceParam[key];
											if (val == undefined || val.length == 0) {
												delete serviceParam[key];
											}
										}
										//--> ser source data
										return { serviceParam : JSON.stringify(serviceParam) };
									} else {
										return undefined;
									}
								}
							}
						});
					}					
				}				
				
			});
		
		}
						
	});

})(jQuery, jQuery.ZOF);