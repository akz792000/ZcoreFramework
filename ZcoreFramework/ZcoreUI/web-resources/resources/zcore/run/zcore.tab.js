/**
 * 
 * @author Ali Karimizandi
 * @since 2012 
 *   
 */

(function($, _) {
	
	_.clazz({
		
		name : 'tab',
		
		inherit : 'component',
		
		body : function(o) {	
			
			/*
			 * ----------------------------------------------------------------------
			 * 								 initialize
			 * ---------------------------------------------------------------------- 
			 */
			
			this.fireable = true;			
						
			/*
			 * ----------------------------------------------------------------------
			 * 								 properties
			 * ---------------------------------------------------------------------- 
			 */
			
			this.properties({
				
				/*
				 * override
				 */
				
				__null__ : ['tabindex', 'value'],
										
				disabled : {
					refresh : 'finalize',
					after : function(v) {
						_.disabledChild(this.__, v);			
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
							header : $('<ul>').appendTo(T.o),
							width : 0
						});
					}		
				},	
				
				refresh : { 				
					method : function() {
						this.__.o.children('[type="tabPage"]').each(function() {
							$.ZDS.getElement($(this)).refresh();
						});
					}			
				},				
			
				render : {
					advice : 'before',
					method : function() {
						this.__.o.tabs();
					}			
				},
				
				embed : {
					method : function() {				
						return this.__.o;
					}
				},	
				
				/*
				 * new
				 */

                collapsible : {
                    method : function(v) {
                        var T = this.__;
                        return v !== undefined ? T.o.tabs("option", 'collapsible', v) : T.o.tabs("option", 'collapsible');
                    }
                },
				
				active : {
					event : function(v) {
						var T = this.__;
						if ($.isFunction(v)) {	
							T.o.bind('tabsactivate', function(e) {
								if (T.fireable) {
									v.apply(T, arguments);
								}
							});					
						}
					},						
					method : function(v) {
						var T = this.__;
						return v !== undefined ? T.o.tabs("option", 'active', v) : T.o.tabs("option", 'active');
					}
				},
				
				addPage : {
					method : function(v) {
						//--> create
						var	T = this.__;
						try {
							$.ZAP.loading.show();
							T.fireable = false;
							for (var i in v) {
								//--> create component 
								var c = _.createComponent(
										{
											attributes : {
												type : 'tabPage',
												id : v[i].id || 'tabPage_' + (new Date().getMilliseconds()),
												value : v[i].value,
												disabled : v[i].disabled
											}
										},
										T,
										T.owner
								);
								//--> initialize component
								_.initializeComponent(c);
								//--> render component
								_.renderComponent(c);	
							}								
						} finally {
							T.fireable = true;
							$.ZAP.loading.hide();
						}						
					}
				},
				
				removePage : {
					method : function(v) {
						var T = this.__;
						try {
							$.ZAP.loading.show();
							for (var i in T.elements) {
								if (v(T.elements[i])) {
									T.elements[i].remove();
								}
							}								
						} finally {
							$.ZAP.loading.hide();
						}	
					}
				}
			
			});
			
		}
		
	});
	
})(jQuery, jQuery.ZOF);