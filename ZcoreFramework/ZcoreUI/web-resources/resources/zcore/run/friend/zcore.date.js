/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
			
	_.clazz({
		
		name : 'date',
		
		body : function(o) {
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 resources
			 * ---------------------------------------------------------------------- 
			 */			
			
			this.resource = {		
					/*
					 * copy jquery.calendars.picker-fa.js at the end of jquery.calendars.picker.js
					 */
					root : '/zcore/util/calendar/',
					files : ['jquery.calendars.picker.css', 'jquery.calendars.picker.js']
			};				
			
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
						var 
							T = this.__,
							val = T.temp.val;
						if (v) {
							val.attr('disabled', 'disabled').addClass('ui-state-disabled');
						} else {
							val.removeAttr('disabled').removeClass('ui-state-disabled');
						}		
						T.abstractProc('disabled', v);
					}
				},				
				
				tabindex : {
					get : function() {
						return this.__.temp.val.attr(this.name);
					},
					set : function(v) {
						this.__.temp.val.attr(this.name, v);
					}
				},
				
				readonly : {
					before : function(v) {
						var val = this.__.temp.val;
						v 
							? val.attr(this.name, this.name) 
							: val.removeAttr(this.name);					
					}
				},
				
				value : {
					get : function() {
						var res = this.__.temp.val.val();
						return res.indexOf('_') === -1 ? res : '';
					},
					set : function(v) {
						this.__.temp.val.val(v); 
					}			
				},
				
				/*
				 * new
				 */
				
				format : _.defaultProperty('string', 'yyyy/mm/dd'),
				
				dateType : $.extend(_.defaultProperty(), {
					type : 'enum',
					values : ['GREGORIAN', 'PERSIAN'],
					defVal : 'PERSIAN'
				})
				
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
						var
							T = this.__,
							P = T.params,
							format = P.data[0] || P.format;	
						$.extend(T.temp, {											
							val : $('<input>', {
								type : "text",
								'class' : "ui-widget-content zui-text-content zui-text-sizing ui-corner-all",
								tabindex : T.params.tabindex	
							}).appendTo(T.o)				
						});
						//--> fresh
						T.abstractProc('initialize');
						//--> set focused
						T.focused = T.temp.val;	
						//--> set format
						T.temp.val.mask(format.toLowerCase().replace(/[A-Za-z]/g, "9"));
					}			
				},				
		
				validateObject : {
					method : function() {
						return this.__.temp.val;                  	
					}
				},
				
				abstractProc : {
					method : function() {
						//--> this method override by sub classes
					}
				}
			
			});	
			
		}
						
	});
	
})(jQuery, jQuery.ZOF);