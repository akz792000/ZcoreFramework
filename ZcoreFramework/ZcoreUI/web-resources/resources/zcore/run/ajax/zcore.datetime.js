/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
			
	_.clazz({
		
		name : 'datetime',
		
		inherit : 'postable',
		
		friend : 'ajax,date,modal',
		
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
				
				format : 'yyyy/mm/dd HH:mm',				
				
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
				
				render : {
					method : function() {
						var 
							T = this.__,
							P = T.params,				
							format = P.data[0] || P.format;		
						//--> set handler
						T.temp.handler = {
								dialogClass : 'zui-datetime-dialog',
								widget : undefined,	
								ajax : undefined,
								render : function() {
									var 
										defaults = $.ZAP.locale.get() === 'en' ? {} : $.calendars.picker.regional[$.ZAP.locale.get()],
										val = T.value().split(' '),
										calendar = $.calendars.instance(
											P.data[1] || P.dateType, 
											$.ZAP.locale.get() 
										),
										date = val[0] || calendar.today().formatDate(format.split(' ')[0]),
										time = val[1] || (new Date().getHours() + ":" + new Date().getMinutes());
									$.extend(defaults, {
										container : this.widget,
										isRTL : T.orientation()[0] === 'rtl',
										dateFormat : format.split(' ')[0],										
										calendar : calendar
									});									
									this._temp = {};
									//--> calendar
									this._temp.calendar = $('<div>').appendTo(defaults.container).calendarsPicker(defaults);
									this._temp.calendar.calendarsPicker('setDate', date);
									//--> time
									this._temp.time = $('<table>', {
										'class' : 'ui-state-default ui-corner-br ui-corner-bl',
										'style' : 'width:100%'
									}).appendTo(defaults.container)
									this._temp.time.append($.ZAP.message.translate('<tr><td>${minute}</td><td>${hour}</td></tr><tr><td><input id="minute"/></td><td><input id="hour"/></td></tr>'));
									//--> set style
									this._temp.time.find('td').attr('align', 'center');
									this._temp.time.find('input')									
										.each(function() {
											var opt = { 
													min : 0,
													max : 59
											};
											if ($(this).attr('id') == 'hour') {
												opt.max = 23;
												$(this).val(time.split(':')[0]);
											} else {
												$(this).val(time.split(':')[1]);
											}
											$(this)
												.spinner(opt)
												.css('width', 24)
												.attr('maxlength', 2)	
												.bind('keypress.mask', (function(event) {		
													if (!(event.which >= 48 && event.which <= 57)) {
														if ($.inArray(event.keyCode, $.ZOF.functionalKeys) !== -1) {
															return;
														}
														event.preventDefault();
														return;
													};
												}))
										})
								}, 
								value : function(v) {
									var 										
										date = this._temp.calendar.calendarsPicker('getDate')[0].formatDate(format.split(' ')[0]),
										time = this._temp.time.find('#hour').val().toString().lpad('0', 2) + ":" + this._temp.time.find('#minute').val().toString().lpad('0', 2);
									return date + " " + time;
								}
							};						
						T.temp.btn 
								.button({
									icons: {
										primary: "ui-icon-clock",
										text : undefined
									}
								})
								.click(function() {
									T.dialog();	
								})
								.find('.ui-button-text')
									.remove();							
					}
				},
				
				abstractProc : {
					method : function(type, v) {
						var T = this.__;
						switch (type) {
						case 'disabled':
							var btn = T.temp.btn;
							if (v) {
								btn.attr('disabled', 'disabled').addClass('ui-state-disabled');
							} else {
								btn.removeAttr('disabled').removeClass('ui-state-disabled');
							}
							btn.button("option", "disabled", v);							
							return;
						case 'initialize':
							T.temp.btn = $('<button>', { 
								tabindex : -1,
								'class' : 'zui-center-v'
							}).appendTo(T.o);
							return;
						}						
					}
				}				
			
			});	
			
		}
						
	});
	
})(jQuery, jQuery.ZOF);