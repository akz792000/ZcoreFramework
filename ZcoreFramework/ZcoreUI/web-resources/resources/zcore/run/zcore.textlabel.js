/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
			
	_.clazz({
		
		name : 'textlabel',
		
		inherit : 'text',
		
		body : function(o) {		
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 initialize
			 * ---------------------------------------------------------------------- 
			 */
			
			this.tag = '<div>',				
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 properties
			 * ---------------------------------------------------------------------- 
			 */		
			
			this.properties({
					
				/*
				 * override
				 */									
				
				disabled : {
					after : function(v) {
						var temp = this.__.temp;
						$.each(['val', 'lbl'], function(i, j) {
							if (v) {
								temp[j].attr('disabled', 'disabled').addClass('ui-state-disabled');
							} else {
								temp[j].removeAttr('disabled').removeClass('ui-state-disabled');	
							}
						});				
					}
				},			
				
				
				/*
				 * new
				 */
				
				label : {
					get : function() {
						return this.__.temp.lbl.html();
					},
					set : function(v) {
						this.__.temp.lbl.html(v);
					}					
				}				
				
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
				
				initialize : { 
					advice : 'after',
					method : function() {
						var T = this.__;
						$.extend(T.temp, {
							//--> val
							val : $('<input>').appendTo(T.o),
							//--> lbl
							lbl : $('<label>', { 'class' : 'zui-center-v' }).appendTo(T.o)							
						});
						//--> set mask
						T.temp.mask.o = T.temp.val;
						//--> set focused
						T.focused = T.temp.val;
					}					
				}				
				
			});	
			
		}
						
	});
	
})(jQuery, jQuery.ZOF);