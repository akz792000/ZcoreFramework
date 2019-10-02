/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
		
	_.clazz({
		
		name : 'calendar',
		
		inherit : 'postable',
		
		friend : 'ajax,date',
		
		body : function(o) {
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 methods
			 * ---------------------------------------------------------------------- 
			 */	
			
			this.methods({				
				 
				render : { 		
					method : function() {
						var 
							T = this.__,
							P = T.params,
							defaults = $.ZAP.locale.get() === 'en' ? {} : $.calendars.picker.regional[$.ZAP.locale.get()],
							format = P.data[0] || P.format;
						$.extend(defaults, {	
							isRTL : T.orientation()[0] === 'rtl',
							dateFormat : format,
							calendar : $.calendars.instance(
								P.data[1] || P.dateType, //--> for calendar type 
								$.ZAP.locale.get() //--> for show date in input (when use MM)
							),
							showOnFocus : false,
							showAnim : 'slideDown',
							showTrigger : '<div id="' + P.id + '_Img" class="trigger zui-center-v ui-state-default ui-corner-all"><span class="ui-icon ui-icon-calendar"></span></div>',
							onSelect : $.proxy(function() { this.signal('selectClick', arguments); }, T),
							container : T.owner.container
						});	
						T.temp.val
							.val(P.data[2])
							.calendarsPicker(defaults)
							.bind('keypress.calendar', function(e) {
								switch (e.keyCode) {
								//--> f9
								case 120:
									e.stopPropagation();
									T.temp.img.trigger('click');							
									break;									
								}
							});
						T.temp.img = T.o.find('img').button();	
					}
				},
				
				abstractProc : {
					method : function(type, v) { 
						switch (type) {
						case 'disabled':
							this.__.temp.img.button("option", "disabled", v);
							return;
						}											
					}
				},
				
				/*
				 * new
				 */
				
				setDefaultDate : {
					method : function() {						
						this.__.temp.val.calendarsPicker('setDate', 'none');
					}
				},
				
				selectClick : {				
					//--> event
				}
							
			});
			
		}
		
	});
	
})(jQuery, jQuery.ZOF);